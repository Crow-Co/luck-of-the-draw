package net.luckofthedraw.util;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

import java.util.Optional;

// * ALL the following code was used from Viniciuslth, see https://github.com/ViniciusIth/gohome-mod
public class TeleportUtilities {
    public static boolean isValidSpawnBlock(ServerPlayerEntity serverPlayerEntity) {
        ServerWorld targetWorld = serverPlayerEntity.server.getWorld(serverPlayerEntity.getSpawnPointDimension());
        BlockPos spawnpoint = serverPlayerEntity.getSpawnPointPosition();

        if (spawnpoint == null) {
            return false; // ! No spawn point set, consider it invalid
        }

        BlockState respawnBlockState = targetWorld.getBlockState(spawnpoint);
        Block respawnBlock = respawnBlockState.getBlock();

        if (respawnBlock instanceof RespawnAnchorBlock || respawnBlock instanceof BedBlock) {
            return true; // ? Valid spawn blocks
        } else if (serverPlayerEntity.isSpawnForced()) {
            boolean footBlockClear = respawnBlock.canMobSpawnInside(respawnBlockState);
            boolean headBlockClear = targetWorld.getBlockState(spawnpoint.up()).getBlock().canMobSpawnInside(respawnBlockState);

            return footBlockClear && headBlockClear;
        }

        return false; // ! Not a valid spawn block
    }

    public static Optional<Vec3d> getPlayerSpawn(ServerPlayerEntity serverPlayerEntity) {
        ServerWorld targetWorld = serverPlayerEntity.server.getWorld(serverPlayerEntity.getSpawnPointDimension());
        BlockPos spawnpoint = serverPlayerEntity.getSpawnPointPosition();

        if (spawnpoint == null) {
            return Optional.empty();
        }

        BlockState respawnBlockState = targetWorld.getBlockState(spawnpoint);
        Block respawnBlock = respawnBlockState.getBlock();

        if (respawnBlock instanceof RespawnAnchorBlock) {
            return RespawnAnchorBlock.findRespawnPosition(EntityType.PLAYER, targetWorld, spawnpoint);
        } else if (respawnBlock instanceof BedBlock) {
            return BedBlock.findWakeUpPosition(
                    EntityType.PLAYER,
                    targetWorld,
                    spawnpoint,
                    respawnBlockState.get(BedBlock.FACING),
                    serverPlayerEntity.getSpawnAngle()
            );
        } else if (serverPlayerEntity.isSpawnForced()) {
            boolean footBlockClear = respawnBlock.canMobSpawnInside(respawnBlockState);
            boolean headBlockClear = targetWorld.getBlockState(spawnpoint.up()).getBlock().canMobSpawnInside(respawnBlockState);

            if (footBlockClear && headBlockClear) {
                return Optional.of(new Vec3d((double) spawnpoint.getX() + 0.5D, (double) spawnpoint.getY() + 0.1D, (double) spawnpoint.getZ() + 0.5D));
            }
        }

        return Optional.empty();
    }

    public static boolean teleportPlayerTo(ServerPlayerEntity playerEntity, Vec3d targetPos, RegistryKey<World> destination) {
        ServerWorld destinationDim = playerEntity.getServer().getWorld(destination);

        if (!destination.equals(playerEntity.getServerWorld().getRegistryKey())) {
            FabricDimensions.teleport(playerEntity, destinationDim, new TeleportTarget(targetPos, Vec3d.ZERO, 0, 0));
        }

        playerEntity.teleport(targetPos.getX(), targetPos.getY(), targetPos.getZ());
        return true;
    }

    public static Vec3d getWorldSpawnPos(ServerPlayerEntity playerEntity) {
        ServerWorld overworld = playerEntity.getServer().getWorld(ServerWorld.OVERWORLD);
        BlockPos worldSpawn = overworld.getSpawnPos();
        return new Vec3d(worldSpawn.getX()+0.5, worldSpawn.getY(), worldSpawn.getZ()+0.5);
    }

    public static void teleportToSpawnpoint(ServerPlayerEntity playerEntity) {
        Optional<Vec3d> spawn = TeleportUtilities.getPlayerSpawn(playerEntity);
        RegistryKey<World> spawnDimension = playerEntity.getSpawnPointDimension();

        playerEntity.stopRiding();
        playerEntity.fallDistance = 0;

        if (spawn.isEmpty()) {
            Vec3d worldSpawn = TeleportUtilities.getWorldSpawnPos(playerEntity);
            boolean teleportResult = TeleportUtilities.teleportPlayerTo(playerEntity, worldSpawn, ServerWorld.OVERWORLD);

            if (!teleportResult) {
                playerEntity.networkHandler.sendPacket(new OverlayMessageS2CPacket(Text.translatable(
                        "luck_of_the_draw.util.teleport_fail")));
                return;
            }

            playerEntity.getWorld().playSound(
                    null,
                    worldSpawn.getX(),
                    worldSpawn.getY(),
                    worldSpawn.getZ(),
                    SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT,
                    SoundCategory.PLAYERS,
                    1f,
                    1f
            );

            playerEntity.networkHandler.sendPacket(new OverlayMessageS2CPacket(Text.translatable(
                    "block.minecraft.spawn.not_valid")));
        }


        TeleportUtilities.teleportPlayerTo(playerEntity, spawn.get(), spawnDimension);
        playerEntity.getWorld().playSound(
                null,
                spawn.get().getX(),
                spawn.get().getY(),
                spawn.get().getZ(),
                SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT,
                SoundCategory.PLAYERS,
                1f,
                1f
        );
    }
}
