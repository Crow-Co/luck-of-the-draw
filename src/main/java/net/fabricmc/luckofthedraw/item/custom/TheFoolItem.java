package net.fabricmc.luckofthedraw.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;



public class TheFoolItem extends MajorArcanaItem {
    public TheFoolItem(Settings majorArcanaItem) {
        super(majorArcanaItem,120f);
    }
    // Item Settings


    // The Tooltip
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.luck_of_the_draw.the_fool_tarot_card.tooltip_type"));
        tooltip.add(Text.translatable("item.luck_of_the_draw.the_fool_tarot_card.tooltip_description"));
    }
}