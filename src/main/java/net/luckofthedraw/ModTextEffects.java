package net.luckofthedraw;

import net.luckofthedraw.textEffects.SmallWaveEffect;
import snownee.textanimator.effect.EffectFactory;

public class ModTextEffects {
    public static void registerModTextEffects() {
        EffectFactory.register("small-wave", SmallWaveEffect::new);
    }
}
