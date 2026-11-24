package com.cloakmod;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CloakMod.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CloakMod.MOD_ID);

    // level, bonus (fraction), name color
    public static final DeferredItem<CloakItem> WANDERER_CLOAK = cloak("wanderer_cloak", 1, 0.05, 0xFFFFFF);    // white
    public static final DeferredItem<CloakItem> ADVENTURER_CLOAK = cloak("adventurer_cloak", 2, 0.10, 0x55FF55); // green
    public static final DeferredItem<CloakItem> WARRIOR_CLOAK = cloak("warrior_cloak", 3, 0.25, 0x5555FF);     // blue
    public static final DeferredItem<CloakItem> CHAMPION_CLOAK = cloak("champion_cloak", 4, 0.30, 0xAA00AA);   // purple
    public static final DeferredItem<CloakItem> CONQUEROR_CLOAK = cloak("conqueror_cloak", 5, 0.40, 0xFFAA00); // gold
    public static final DeferredItem<CloakItem> SOVEREIGN_CLOAK = cloak("sovereign_cloak", 6, 0.60, 0xFF55FF); // pink
    public static final DeferredItem<CloakItem> MYTHIC_CLOAK = cloak("mythic_cloak", 7, 0.75, 0x55FFFF);       // sky blue

    private static DeferredItem<CloakItem> cloak(String name, int level, double bonus, int color) {
        return ITEMS.registerItem(name, p -> new CloakItem(p.stacksTo(1), level, bonus, color));
    }

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CLOAK_TAB = TABS.register("cloaks",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.cloakmod"))
                    .icon(() -> new ItemStack(MYTHIC_CLOAK.get()))
                    .displayItems((params, output) -> {
                        output.accept(WANDERER_CLOAK.get());
                        output.accept(ADVENTURER_CLOAK.get());
                        output.accept(WARRIOR_CLOAK.get());
                        output.accept(CHAMPION_CLOAK.get());
                        output.accept(CONQUEROR_CLOAK.get());
                        output.accept(SOVEREIGN_CLOAK.get());
                        output.accept(MYTHIC_CLOAK.get());
                    })
                    .build());
}
