package net.fabricmc.luckofthedraw.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public abstract class MajorArcanaItem extends Item {
    // Base settings
    public MajorArcanaItem(Settings settings, int cooldownTime) {
        super(settings
           .maxCount(1));

           // Sets the durability to the cooldown time
           if (cooldownTime > 0) {
               settings.maxDamage(cooldownTime);
           }
    };

    // Cooldown bar color
    @Override
    public int getItemBarColor(ItemStack stack) {
        return MathHelper.hsvToRgb(0.5f, 1.0F, 1.0F);
    }

}
