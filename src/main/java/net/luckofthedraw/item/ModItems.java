package net.luckofthedraw.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.luckofthedraw.LuckOfTheDraw;
import net.luckofthedraw.item.custom.majorArcana.tier1.*;
import net.luckofthedraw.item.custom.majorArcana.tier2.*;
//import net.luckofthedraw.item.custom.majorArcana.tier3.*;
import net.luckofthedraw.item.custom.majorArcana.tier4.*;
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

        // The Hierophant allows the user to store up to 50 levels of experience into the card
        public static final Item THE_HIEROPHANT_TAROT_CARD = registerItem("the_hierophant_tarot_card",
            new TheHierophantItem(new FabricItemSettings()));

        // The Lovers is WIP currently
        public static final Item THE_LOVERS_TAROT_CARD = registerItem("the_lovers_tarot_card",
            new TheLoversItem(new FabricItemSettings()));

        // The Chariot grants a temporary speed boost to the user
        public static final Item THE_CHARIOT_TAROT_CARD = registerItem("the_chariot_tarot_card",
            new TheChariotItem(new FabricItemSettings()));

        // Strength grants a temporary resistance boost for a limited time
        public static final Item STRENGTH_TAROT_CARD = registerItem("strength_tarot_card",
            new StrengthItem(new FabricItemSettings()));

        // The Hermit increases the user's proficiency in a specific task, but they become worse at others
        public static final Item THE_HERMIT_TAROT_CARD = registerItem("the_hermit_tarot_card",
            new TheHermitItem(new FabricItemSettings()));

        // Wheel of Fortune grants a stacking luck effect to the user, ranging from +2 to -2
        public static final Item WHEEL_OF_FORTUNE_TAROT_CARD = registerItem("wheel_of_fortune_tarot_card",
            new WheelOfFortuneItem(new FabricItemSettings()));

        // Justice resets the aggression entities and discounts/increases of all nearby villagers
        public static final Item JUSTICE_TAROT_CARD = registerItem("justice_tarot_card",
            new JusticeItem(new FabricItemSettings()));

        // The Hanged man reveals all nearby entities to the user
        public static final Item THE_HANGED_MAN_TAROT_CARD = registerItem("the_hanged_man_tarot_card",
            new TheHangedManItem(new FabricItemSettings()));

        // Death transports the user to their spawn point and fakes a death message
        public static final Item DEATH_TAROT_CARD = registerItem("death_tarot_card",
            new DeathItem(new FabricItemSettings()));

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
        entries.add(THE_HIEROPHANT_TAROT_CARD);
        entries.add(THE_LOVERS_TAROT_CARD);
        entries.add(THE_CHARIOT_TAROT_CARD);
        entries.add(STRENGTH_TAROT_CARD);
        entries.add(THE_HERMIT_TAROT_CARD);
        entries.add(WHEEL_OF_FORTUNE_TAROT_CARD);
        entries.add(JUSTICE_TAROT_CARD);
        entries.add(THE_HANGED_MAN_TAROT_CARD);
        entries.add(DEATH_TAROT_CARD);

        entries.add(THE_WORLD_TAROT_CARD);
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
