package net.luckofthedraw.item.custom.majorArcana.tier2;

import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import net.luckofthedraw.LuckOfTheDraw;
import net.luckofthedraw.item.custom.base.MajorArcanaItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class StrengthItem extends MajorArcanaItem {
    // * Item Settings
    public StrengthItem(Settings majorArcanaItem) {
        super(majorArcanaItem,800);
    }

    private static final ParticleEmitterInfo RESISTANCE = new ParticleEmitterInfo(new Identifier(LuckOfTheDraw.MOD_ID, "resistance"));

    // * Item Interaction
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack stack = playerEntity.getStackInHand(hand);

        // ? Stops the interaction on the client
        if (world.isClient) {
            return TypedActionResult.pass(stack);
        }

        // ? Grants the player Speed I for 100 ticks (5 secs)
        if (stack.getDamage() == 0) {
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 0));
            AAALevel.addParticle(world, false, RESISTANCE.clone().position(playerEntity.getX(), playerEntity.getY(), playerEntity.getZ()).scale(0.25f));

            stack.setDamage(stack.getMaxDamage());
        // ! If the current durability is smaller than the max, send a cooldown message and stop usage for 5 ticks
        } else {
            playerEntity.getItemCooldownManager().set(this, 5);
            playerEntity.sendMessage(Text.translatable("item.luck_of_the_draw.tarot_card.interact_fail"), false);
        }

        // ? Validates the interaction
        return TypedActionResult.success(stack, true);
    }

    // * Item Tooltip
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.luck_of_the_draw.strength_tarot_card.tooltip_line1"));
        tooltip.add(Text.translatable("item.luck_of_the_draw.strength_tarot_card.tooltip_line2"));
    }
}