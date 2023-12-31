package net.fabricmc.luckofthedraw.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;



public class TheFoolItem extends Item {
    // Item Settings
    public TheFoolItem (Settings settings) {
        super(settings
            .maxCount(1)
        );
    }

    // The Tooltip
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.luck_of_the_draw.the_fool_tarot_card.tooltip_type"));
        tooltip.add(Text.translatable("item.luck_of_the_draw.the_fool_tarot_card.tooltip_description"));
    }
}