package net.luckofthedraw.item.custom.base;

import com.sun.tools.jconsole.JConsolePlugin;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Objects;

public abstract class MajorArcanaItem extends Item {
    // * Helper methods
    private float getCooldown(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        return (nbt != null && nbt.contains("cooldown")) ? nbt.getFloat("cooldown") : 0.0F;
    }

    private void setCooldown(ItemStack stack, float cooldown) {
        NbtCompound nbt = stack.getNbt();
        if (nbt == null) {
            nbt = new NbtCompound();
            stack.setNbt(nbt);
        }
        nbt.putFloat("cooldown", cooldown);
    }

    // * Custom attributes
    private float MaxCooldown;

    public void setMaxCooldown(float maxCooldown) {
        this.MaxCooldown = maxCooldown;
    }

    public float getMaxCooldown() {
        return MaxCooldown;
    }

    // * Base settings IF NO cooldown is specified
    public MajorArcanaItem(Settings settings) {
        super(settings.maxCount(1));
    }

    // * Base settings IF A cooldown is specified
    public MajorArcanaItem(Settings settings, float cooldown) {
        super(settings.maxCount(1));
        setMaxCooldown(cooldown);
    }

    // * Cooldown bar color
    @Override
    public int getItemBarColor(ItemStack stack) {
        return MathHelper.hsvToRgb(0.5f, 1.0F, 1.0F);
    }

    // * Cooldown bar visible
    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return getCooldown(stack) > 0.0F;
    }

    // * Cooldown bar step
    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0F - Objects.requireNonNull(stack.getNbt()).getFloat("cooldown") * 13.0F / MaxCooldown);
    }

    // * Cooldown
    // ! NOTE: 1 tick = 1/20th of a second, this running at 20 times per second
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        float currentCooldown = getCooldown(stack);
        if (currentCooldown > 0.0F) {
            setCooldown(stack, currentCooldown - 1.0F);
        }
    }
}
