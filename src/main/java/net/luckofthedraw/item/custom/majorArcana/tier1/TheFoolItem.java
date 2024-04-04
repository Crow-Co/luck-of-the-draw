package net.luckofthedraw.item.custom.majorArcana.tier1;

import net.luckofthedraw.item.custom.base.MajorArcanaItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class TheFoolItem extends MajorArcanaItem {
    // * Item Settings
    public TheFoolItem(Settings majorArcanaItem) {
        super(majorArcanaItem);
    }

    // * Item Tooltip
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.luck_of_the_draw.the_fool_tarot_card.tooltip_line1"));
        tooltip.add(Text.translatable("item.luck_of_the_draw.the_fool_tarot_card.tooltip_line2"));
    }
}