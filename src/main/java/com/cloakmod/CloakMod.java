package com.cloakmod;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@Mod(CloakMod.MOD_ID)
public class CloakMod {
    public static final String MOD_ID = "cloakmod";

    public CloakMod(IEventBus modBus) {
        ModItems.ITEMS.register(modBus);
        ModItems.TABS.register(modBus);
        NeoForge.EVENT_BUS.addListener(CloakEvents::onPlayerTick);
        NeoForge.EVENT_BUS.addListener(CloakEvents::onIncomingDamage);
    }
}
