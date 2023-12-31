package net.fabricmc.luckofthedraw.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class TheMagicianItem extends MajorArcanaItem {
    // Item Settings
    public TheMagicianItem(Settings MajorArcanaItem) {
        super(MajorArcanaItem);
    }

    // The Tooltip
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.luck_of_the_draw.the_magician_tarot_card.tooltip_line1"));
        tooltip.add(Text.translatable("item.luck_of_the_draw.the_magician_tarot_card.tooltip_line2"));
    }
}