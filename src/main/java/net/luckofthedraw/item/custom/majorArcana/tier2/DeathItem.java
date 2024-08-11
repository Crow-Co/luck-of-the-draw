package net.luckofthedraw.item.custom.majorArcana.tier2;

import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import net.luckofthedraw.LuckOfTheDraw;
import net.luckofthedraw.item.custom.base.MajorArcanaItem;
import net.luckofthedraw.util.TeleportUtilities;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.damage.DamageSource;
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
import java.util.Random;

public class DeathItem extends MajorArcanaItem {
    // * Helper methods
    private static final Random RANDOM = new Random();

    private float getCooldown(ItemStack stack) {
        return stack.getOrCreateNbt().getFloat("cooldown");
    }

    private void setCooldown(ItemStack stack, float cooldown) {
        stack.getOrCreateNbt().putFloat("cooldown", cooldown);
    }

    // * Get random death message
    private static final List<String> DEATH_MESSAGES = List.of(
        "%s experienced existential dread",
        "%s shuffled off this mortal coil",
        "%s's fortune ran out",
        "%s faced their final destiny",
        "%s's life was a game of chance",
        "%s was doomed to die",
        "%s died a horrible death",
        "%s took a one-way trip to the afterlife",
        "%s's soul was collected",
        "%s met their untimely demise",
        "%s's fate was sealed by the Death card",
        "%s's life story reached its final chapter",
        "%s answered Death's call",
        "%s's time in this realm came to an end",
        "%s embraced their mortality",
        "%s's life flashed before their eyes"
    );

    private Text getRandomDeathMessage(String playerName) {
        String randomMessage = DEATH_MESSAGES.get(RANDOM.nextInt(DEATH_MESSAGES.size()));
        return Text.of(String.format(randomMessage, playerName));
    }


    // * Item Settings
    public DeathItem(Settings MajorArcanaItem) {
        super(MajorArcanaItem,2400);
    }
    private static final ParticleEmitterInfo TELEPORT = new ParticleEmitterInfo(new Identifier(LuckOfTheDraw.MOD_ID, "teleport"));

    // * Item Interaction
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack stack = playerEntity.getStackInHand(hand);

        // ! Stops the interaction on the client
        if (world.isClient) {
            return TypedActionResult.pass(stack);
        }

        // * Main interaction
        if (getCooldown(stack) == 0.0F) {
            setCooldown(stack, getMaxCooldown());

            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) playerEntity;
            ServerWorld targetWorld = serverPlayerEntity.server.getWorld(serverPlayerEntity.getSpawnPointDimension());

            Text deathMessage = getRandomDeathMessage(serverPlayerEntity.getName().getString());
            serverPlayerEntity.server.getPlayerManager().broadcast(deathMessage, false);

            TeleportUtilities.teleportToSpawnpoint(serverPlayerEntity);
            AAALevel.addParticle(targetWorld, false, TELEPORT.clone().position(TeleportUtilities.getPlayerSpawn(serverPlayerEntity).get().getX(), TeleportUtilities.getPlayerSpawn(serverPlayerEntity).get().getY() + 0.01, TeleportUtilities.getPlayerSpawn(serverPlayerEntity).get().getZ()).scale(0.25f));
        // * Cooldown handler
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