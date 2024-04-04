package net.luckofthedraw;

import net.luckofthedraw.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class LuckOfTheDrawDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(AdvancementsProvider::new);
    }

    static class AdvancementsProvider extends FabricAdvancementProvider {
        protected AdvancementsProvider(FabricDataOutput dataGenerator) {
            super(dataGenerator);
        }

        @Override
        public void generateAdvancement(Consumer<Advancement> consumer) {
            Advancement got_the_fool = Advancement.Builder.create()
                    .display(
                            ModItems.THE_FOOL_TAROT_CARD, // ? The display icon
                            Text.literal("The Fool"), // ? The title
                            Text.literal("A simple card with no perceivable function, and yet it feels more meaningful than the other junk you found."), // The description
                            new Identifier("textures/gui/advancements/backgrounds/adventure.png"), // ? Background image used
                            AdvancementFrame.TASK, // ? Options: TASK, CHALLENGE, GOAL
                            true, // ? Show toast top right
                            true, // ? Announce to chat
                            false // ? Hidden in the advancement tab
                    )
                    // ? The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
                    .criterion("got_the_fool", InventoryChangedCriterion.Conditions.items(ModItems.THE_FOOL_TAROT_CARD))
                    .build(consumer, "luckofthedraw" + "/got_the_fool");

            Advancement got_the_high_priestess = Advancement.Builder.create().parent(got_the_fool)
                    .display(
                            ModItems.THE_HIGH_PRIESTESS_TAROT_CARD,
                            Text.literal("The High Priestess"),
                            Text.literal("In the pursuit of bettering yourself you feel your knowledge growing."),
                            new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                            AdvancementFrame.TASK,
                            true,
                            true,
                            false
                    )
                    .criterion("got_the_high_priestess", InventoryChangedCriterion.Conditions.items(ModItems.THE_EMPRESS_TAROT_CARD))
                    .build(consumer, "luckofthedraw" + "/got_the_high_priestess");

            Advancement got_temperance = Advancement.Builder.create().parent(got_the_high_priestess)
                    .display(
                            ModItems.TEMPERANCE_TAROT_CARD,
                            Text.literal("Temperance"),
                            Text.literal("By facing many challenges, you feel more free than ever before, overcoming anything that stands in your way."), // The description
                            new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                            AdvancementFrame.TASK,
                            true,
                            true,
                            false
                    )
                    .criterion("got_temperance", InventoryChangedCriterion.Conditions.items(ModItems.TEMPERANCE_TAROT_CARD))
                    .build(consumer, "luckofthedraw" + "/got_temperance");

            Advancement got_judgement = Advancement.Builder.create().parent(got_temperance)
                    .display(
                            ModItems.JUDGEMENT_TAROT_CARD,
                            Text.literal("Judgement"),
                            Text.literal("Through this journey you have seen many sights, and faced many hardships, but will it all be worth it in the end?"), // The description
                            new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                            AdvancementFrame.TASK,
                            true,
                            true,
                            false
                    )
                    .criterion("got_temperance", InventoryChangedCriterion.Conditions.items(ModItems.JUDGEMENT_TAROT_CARD))
                    .build(consumer, "luckofthedraw" + "/got_judgement");

            Advancement got_the_world = Advancement.Builder.create().parent(got_judgement)
                    .display(
                            ModItems.THE_WORLD_TAROT_CARD,
                            Text.literal("The World"),
                            Text.literal("After all you've been through, you've finally reached your journey's end. It was all worth it."),
                            new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                            AdvancementFrame.TASK,
                            true,
                            true,
                            false
                    )
                    .criterion("got_temperance", InventoryChangedCriterion.Conditions.items(ModItems.THE_WORLD_TAROT_CARD))
                    .build(consumer, "luckofthedraw" + "/got_the_world");
        }
    }
}