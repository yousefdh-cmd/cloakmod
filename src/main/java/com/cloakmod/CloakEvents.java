package com.cloakmod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import top.theillusivec4.curios.api.CuriosApi;

public final class CloakEvents {
    private static final ResourceLocation HEALTH_ID =
            ResourceLocation.fromNamespaceAndPath(CloakMod.MOD_ID, "cloak_health");

    private CloakEvents() {}

    /** Bonus of the cloak the entity is wearing (0 if none). Looks in all Curios slots. */
    public static double getBonus(LivingEntity entity) {
        return CuriosApi.getCuriosInventory(entity)
                .flatMap(handler -> handler.findFirstCurio(stack -> stack.getItem() instanceof CloakItem))
                .map(result -> ((CloakItem) result.stack().getItem()).getBonus())
                .orElse(0.0D);
    }

    /** Keeps the max health bonus in sync with the worn cloak (checked twice per second). */
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide() || player.tickCount % 10 != 0) {
            return;
        }
        AttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealth == null) {
            return;
        }
        double bonus = getBonus(player);
        AttributeModifier existing = maxHealth.getModifier(HEALTH_ID);
        if (bonus <= 0.0D) {
            if (existing != null) {
                maxHealth.removeModifier(HEALTH_ID);
            }
            return;
        }
        if (existing == null || existing.amount() != bonus) {
            maxHealth.removeModifier(HEALTH_ID);
            maxHealth.addTransientModifier(
                    new AttributeModifier(HEALTH_ID, bonus, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }
    }

    /** All damage dealt by a player wearing a cloak (melee, arrows, tridents...) is increased. */
    public static void onIncomingDamage(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player attacker && attacker != event.getEntity()) {
            double bonus = getBonus(attacker);
            if (bonus > 0.0D) {
                event.setAmount((float) (event.getAmount() * (1.0D + bonus)));
            }
        }
    }
}
