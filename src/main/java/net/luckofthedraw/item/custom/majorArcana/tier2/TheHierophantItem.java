package net.luckofthedraw.item.custom.majorArcana.tier2;

import net.luckofthedraw.item.custom.base.MajorArcanaItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class TheHierophantItem extends MajorArcanaItem {
    // * Helper methods
    private float getCooldown(ItemStack stack) {
        return stack.getOrCreateNbt().getFloat("cooldown");
    }

    private void setCooldown(ItemStack stack, float cooldown) {
        stack.getOrCreateNbt().putFloat("cooldown", cooldown);
    }

    // * Item Settings
    public TheHierophantItem(Settings MajorArcanaItem) {
        super(MajorArcanaItem, 40);
    }

    // * Item Interaction
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack stack = playerEntity.getStackInHand(hand);

        // ? Stops the interaction on the client
        if (world.isClient) {
            return TypedActionResult.pass(stack);
        }

        // ? Grants the player EXP
        if (getCooldown(stack) == 0.0F) {
            setCooldown(stack, getMaxCooldown());

            playerEntity.addExperience(50);

        // ? If the current cooldown isn't over, send a cooldown message and stop usage for 5 ticks
        } else {
            playerEntity.getItemCooldownManager().set(this, 5);
            playerEntity.sendMessage(Text.translatable("item.luck_of_the_draw.tarot_card.interact_fail"), false);
        }

        // ? Validates the interaction
        return TypedActionResult.success(stack, true);
    }

    // * Item Tooltip
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.luck_of_the_draw.the_hierophant_tarot_card.tooltip_line1"));
        tooltip.add(Text.translatable("item.luck_of_the_draw.the_hierophant_tarot_card.tooltip_line2"));
    }
}