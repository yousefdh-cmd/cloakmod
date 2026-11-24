package com.cloakmod;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

/**
 * A cloak worn in the Curios "back" slot (the slot comes from the curios:back item tag).
 * The effects are applied in {@link CloakEvents}.
 */
public class CloakItem extends Item {
    private final int level;
    private final double bonus;
    private final int color;

    /**
     * @param bonus bonus as a fraction (0.05 = +5% damage dealt and +5% max health)
     * @param color RGB color of the item name
     */
    public CloakItem(Properties properties, int level, double bonus, int color) {
        super(properties);
        this.level = level;
        this.bonus = bonus;
        this.color = color;
    }

    public int getLevel() {
        return level;
    }

    public double getBonus() {
        return bonus;
    }

    @Override
    public Component getName(ItemStack stack) {
        return super.getName(stack).copy().withStyle(style -> style.withColor(color));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        int percent = (int) Math.round(bonus * 100.0D);
        tooltip.add(Component.translatable("tooltip.cloakmod.level", level).withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("tooltip.cloakmod.damage", percent).withStyle(ChatFormatting.GREEN));
        tooltip.add(Component.translatable("tooltip.cloakmod.health", percent).withStyle(ChatFormatting.GREEN));
        tooltip.add(Component.translatable("tooltip.cloakmod.slot").withStyle(ChatFormatting.DARK_GRAY));
    }
}
