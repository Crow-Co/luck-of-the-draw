package net.luckofthedraw.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class MajorArcanaItem extends Item {
    // Base settings
    public MajorArcanaItem(Settings settings, int cooldownTime) {
        super(settings
           .maxCount(1));

           // Sets the durability to the cooldown time
           if (cooldownTime > 0) {
               settings.maxDamage(cooldownTime);
           }
    }

    // Cooldown bar color
    @Override
    public int getItemBarColor(ItemStack stack) {
        return MathHelper.hsvToRgb(0.5f, 1.0F, 1.0F);
    }

    // The code for the Cooldown. NOTE: 1 tick = 1/20th of a second, this is running at 20 ticks per second
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (stack.getItem() instanceof MajorArcanaItem) {
            if (stack.getDamage() < stack.getMaxDamage()) {
                stack.setDamage(stack.getDamage() - 1);
            }
        }
    }
}
