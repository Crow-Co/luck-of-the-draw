package net.luckofthedraw.item.custom.base;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class MajorArcanaItem extends Item {
    // TODO: Rewrite this entire class to use an item tag instead of durability.
    // TODO: Figure out how to make a custom bar in the GUI, use isItemBarVisible and getItemBarStep?

    // ? Base settings IF NO cooldown is specified
    public MajorArcanaItem(Settings settings) {
        super(
                settings.maxCount(1)
        );
    }

    // ? Base settings IF A cooldown is specified
    public MajorArcanaItem(Settings settings, int cooldown) {
        super(
            settings.maxDamage(cooldown)
        );
    }

    // * Cooldown bar color
    @Override
    public int getItemBarColor(ItemStack stack) {
        return MathHelper.hsvToRgb(0.5f, 1.0F, 1.0F);
    }

    // * Cooldown
    // ! NOTE: 1 tick = 1/20th of a second, this running at 20 times per second
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (stack.getMaxDamage()==0) {
            return;
        }

        if (stack.getDamage() > 0) {
            stack.setDamage(stack.getDamage() - 1);
        }
    }
}
