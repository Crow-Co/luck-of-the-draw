package net.luckofthedraw.textEffects;

import snownee.textanimator.effect.Effect;
import snownee.textanimator.effect.EffectSettings;
import snownee.textanimator.effect.params.Params;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;

public class SmallWaveEffect implements Effect {
    public SmallWaveEffect(Params params) {
    }

    @Override
    public void apply(EffectSettings settings) {
        settings.y += MathHelper.sin(Util.getMeasuringTimeMs() * 0.01F + settings.index);
    }

    @Override
    public String getName() {
        return "small-wave";
    }
}
