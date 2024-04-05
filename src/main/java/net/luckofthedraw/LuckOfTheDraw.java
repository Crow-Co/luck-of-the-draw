package net.luckofthedraw;

import net.fabricmc.api.ModInitializer;
import net.luckofthedraw.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LuckOfTheDraw implements ModInitializer {
    // * Mod ID and Logger
    public static final String MOD_ID = "luck_of_the_draw";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModTextEffects.registerModTextEffects();
        ModItems.registerModItems();
    }
}