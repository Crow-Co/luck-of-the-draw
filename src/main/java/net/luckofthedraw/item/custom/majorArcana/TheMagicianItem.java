package net.luckofthedraw.item.custom.majorArcana;

import net.luckofthedraw.item.custom.base.MajorArcanaItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class TheMagicianItem extends MajorArcanaItem {
    // Item Settings
    public TheMagicianItem(Settings MajorArcanaItem) {
        super(MajorArcanaItem, 0);
    }

    // The Tooltip
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.luck_of_the_draw.the_magician_tarot_card.tooltip_line1"));
        tooltip.add(Text.translatable("item.luck_of_the_draw.the_magician_tarot_card.tooltip_line2"));
    }
}