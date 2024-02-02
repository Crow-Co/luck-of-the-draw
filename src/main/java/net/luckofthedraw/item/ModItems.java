package net.luckofthedraw.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.luckofthedraw.LuckOfTheDraw;
import net.luckofthedraw.item.custom.majorArcana.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    // Tier 1 Major Arcana
        // The fool is the first obtainable card, it is the starting line that allows players to start progressing
        public static final Item THE_FOOL_TAROT_CARD = registerItem("the_fool_tarot_card",
            new TheFoolItem(new FabricItemSettings()));

        // The Magician is WIP currently
        public static final Item THE_MAGICIAN_TAROT_CARD = registerItem("the_magician_tarot_card",
            new TheMagicianItem(new FabricItemSettings()));

        // The High Priestess allows the player to craft and obtain tier 2 Major Arcana
        public static final Item THE_HIGH_PRIESTESS_TAROT_CARD = registerItem("the_high_priestess_tarot_card",
            new TheHighPriestessItem(new FabricItemSettings()));

    // Tier 2 Major Arcana
        // The Empress breeds nearby passive creatures, pairs well with The Emperor
        public static final Item THE_EMPRESS_TAROT_CARD = registerItem("the_empress_tarot_card",
            new TheEmpressItem(new FabricItemSettings()));

        // The Emperor commands nearby passive creatures to follow you for a limited time, pairs well with the Empress
        public static final Item THE_EMPEROR_TAROT_CARD = registerItem("the_emperor_tarot_card",
            new TheEmperorItem(new FabricItemSettings()));

        // Temperance allows the player to craft and obtain tier 3 Major Arcana
        public static final Item TEMPERANCE_TAROT_CARD = registerItem("temperance_tarot_card",
            new TemperanceItem(new FabricItemSettings()));

    // Tier 3 Major Arcana
        //Judgement allows the player to craft The World and also provides a DOT effect in a radius to hostiles
        public static final Item JUDGEMENT_TAROT_CARD = registerItem("judgement_tarot_card",
            new JudgementItem(new FabricItemSettings()));
    // Tier 4 Major Arcana
        /* The World, the end of the player's journey and the most busted card lmao. Allows flight on a toggle that drains a bar,
           grants cooldown reduction to all card in the inventory, and combines all toggle cards into a singular one. */
        public static final Item THE_WORLD_TAROT_CARD = registerItem("the_world_tarot_card",
            new TheWorldItem(new FabricItemSettings()));


    // Methods to add items to item groups
    private static void addItemsToSearchTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(THE_FOOL_TAROT_CARD);
        entries.add(THE_MAGICIAN_TAROT_CARD);
        entries.add(THE_HIGH_PRIESTESS_TAROT_CARD);
        entries.add(THE_EMPRESS_TAROT_CARD);
        entries.add(THE_EMPEROR_TAROT_CARD);
    }

    // Helper method for registering items
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(LuckOfTheDraw.MOD_ID, name), item);
    }

    // Method to print a log in the console and register the items
    public static void registerModItems() {
        LuckOfTheDraw.LOGGER.info("Registering Mod Items for " + LuckOfTheDraw.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SEARCH).register(ModItems::addItemsToSearchTabItemGroup);
    }
}
