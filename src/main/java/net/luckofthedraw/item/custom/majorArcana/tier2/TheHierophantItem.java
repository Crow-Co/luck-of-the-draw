package net.luckofthedraw.item.custom.majorArcana.tier2;

import net.luckofthedraw.item.custom.base.MajorArcanaItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class TheHierophantItem extends MajorArcanaItem {
    // Item Settings
    public TheHierophantItem(Settings MajorArcanaItem) {
        super(MajorArcanaItem, 120);
    }

    //This detects a player's input and starts the cooldown, also includes anti-spam to avoid spamming the client chat

    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        ItemStack stack = player.getStackInHand(hand);

        // Stops the interaction if it is on the client
        if (world.isClient) {
            return TypedActionResult.pass(stack);
        }

        // Teleports the player on the serverside, sends a message, and sets durability to 0
        if (player instanceof ServerPlayerEntity serverPlayer && stack.getDamage() == 0) {

            // Sets the durability to 0 and prints the durability before and after the process in game chat
            player.sendMessage(Text.literal(String.valueOf(stack.getDamage())), false);
            stack.setDamage(stack.getMaxDamage());
            player.sendMessage(Text.literal(String.valueOf(stack.getDamage())), false);

            // Teleports to world spawn for testing
            BlockPos serverSpawnPos = serverPlayer.getServerWorld().getSpawnPos();
            serverPlayer.teleport(serverSpawnPos.getX() + 0.5, serverSpawnPos.getY(), serverSpawnPos.getZ() + 0.5);

            // If the current durability is smaller than the max, send a cooldown message and stop usage for 1/2th of a sec
        } else if (stack.getDamage() != 0) {
            player.getItemCooldownManager().set(this, 10);
            player.sendMessage(Text.translatable("item.luck_of_the_draw.tarot_card.interact_fail"), false);
        }

        // Validates the interaction
        return TypedActionResult.success(player.getStackInHand(hand), true);
    }

    // The Tooltip
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.luck_of_the_draw.the_hierophant_tarot_card.tooltip_line1"));
        tooltip.add(Text.translatable("item.luck_of_the_draw.the_hierophant_tarot_card.tooltip_line2"));
    }
}