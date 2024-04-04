package net.luckofthedraw.item.custom.majorArcana.tier2;

import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import net.luckofthedraw.LuckOfTheDraw;
import net.luckofthedraw.item.custom.base.MajorArcanaItem;
import net.luckofthedraw.util.TeleportUtilities;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class DeathItem extends MajorArcanaItem {
    // * Item Settings
    public DeathItem(Settings majorArcanaItem) {
        super(majorArcanaItem,2400);
    }
    private static final ParticleEmitterInfo TELEPORT = new ParticleEmitterInfo(new Identifier(LuckOfTheDraw.MOD_ID, "teleport"));

    // * Item Interaction
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack stack = playerEntity.getStackInHand(hand);

        // ? Stops the interaction on the client
        if (world.isClient) {
            return TypedActionResult.pass(stack);
        }

        // ? Teleports the player on the serverside, sends a message, and sets durability to 0
        if (stack.getDamage() == 0) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) playerEntity;
            ServerWorld targetWorld = serverPlayerEntity.server.getWorld(serverPlayerEntity.getSpawnPointDimension());

            TeleportUtilities.teleportToSpawnpoint(serverPlayerEntity);
            AAALevel.addParticle(targetWorld, false, TELEPORT.clone().position(TeleportUtilities.getPlayerSpawn(serverPlayerEntity).get().getX(), TeleportUtilities.getPlayerSpawn(serverPlayerEntity).get().getY() + 0.01, TeleportUtilities.getPlayerSpawn(serverPlayerEntity).get().getZ()).scale(0.25f));

            stack.setDamage(stack.getMaxDamage());

        // ? If the current durability is smaller than the max, send a cooldown message and stop usage for 5 ticks
        } else {
            playerEntity.getItemCooldownManager().set(this, 5);
            playerEntity.sendMessage(Text.translatable("item.luck_of_the_draw.tarot_card.interact_fail"), false);
        }

        // ? Validates the interaction
        return TypedActionResult.success(stack, true);
    }

    // * The Tooltip
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.luck_of_the_draw.death_tarot_card.tooltip_line1"));
        tooltip.add(Text.translatable("item.luck_of_the_draw.death_tarot_card.tooltip_line2"));
    }
}