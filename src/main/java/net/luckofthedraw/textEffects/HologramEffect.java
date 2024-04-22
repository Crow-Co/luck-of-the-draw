package net.luckofthedraw.textEffects;

import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import snownee.textanimator.effect.Effect;
import snownee.textanimator.effect.EffectSettings;
import snownee.textanimator.effect.params.Params;

public class HologramEffect implements Effect {
    public HologramEffect(Params params) {
    }

    @Override
    public void apply(EffectSettings settings) {
        if (settings.isShadow) {
            int color = MathHelper.hsvToRgb(((float)Util.getMeasuringTimeMs() * 0.03f + (float)settings.index) % 30.0f / 30.0f, 0.8f, 0.5f);
            //settings.r = (float)(color >> 16 & 255) / 255.0F;
            settings.g = (float)(color >> 8 & 255) / 255.0F;
            settings.b = (float)(color & 255) / 255.0F;
        } else {
            int color = MathHelper.hsvToRgb(((float)Util.getMeasuringTimeMs() * 0.02f + (float)settings.index) % 30.0f / 30.0f, 0.8f, 0.8f);
            settings.r = (float)(color >> 16 & 255) / 255.0F;
            //settings.g = (float)(color >> 8 & 255) / 255.0F;
            //settings.b = (float)(color & 255) / 255.0F;
        }
    }

    @Override
    public String getName() {
        return "hologram";
    }
}
