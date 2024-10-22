package org.yunxi.EveningLament.common.Events;


import com.teammetallurgy.aquaculture.entity.AquaFishingBobberEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.yunxi.EveningLament.Eveninglament;
import org.yunxi.EveningLament.api.Engraving.Engraving;
import org.yunxi.EveningLament.common.Engraving.EngravingRegister;
import org.yunxi.EveningLament.common.items.ItemRegister;
import org.yunxi.EveningLament.util.EngravingHelper;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Eveninglament.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvent {
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        Entity directEntity = event.getSource().getDirectEntity();
        LivingEntity entityLiving = event.getEntity();
        float amount = event.getAmount();
        if (directEntity instanceof Player player) {
            ItemStack mainHandItem = player.getMainHandItem();
            if (!mainHandItem.getItem().equals(ItemRegister.FLOURISHING_BLOSSOM_ENGRAVING.get())) {
                if (EngravingHelper.hasEngraving(mainHandItem, EngravingRegister.BLOOD_MARY.get())){
                    event.setAmount(amount + (entityLiving.getMaxHealth() - entityLiving.getHealth()) * 0.025f);
                }
            }

            player.displayClientMessage(Component.nullToEmpty(Boolean.toString(EngravingHelper.canEngraving(mainHandItem, EngravingRegister.VOID_GIFT.get()))), false);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLivingHurt(LivingHurtEvent event) {
        float amount = event.getAmount();
        Entity directEntity = event.getSource().getDirectEntity();
        LivingEntity entity = event.getEntity();
        if (directEntity instanceof Player player) {
            ItemStack mainHandItem = player.getMainHandItem();
            if (!mainHandItem.getItem().equals(ItemRegister.FLOURISHING_BLOSSOM_ENGRAVING.get())) {
                if (EngravingHelper.hasEngraving(mainHandItem, EngravingRegister.VOID_GIFT.get())) {
//                    entity.hurt(new DamageSource(DamageSources.VOID_DAMAGE), 200);
                    int level = EngravingHelper.getEngravingLevel(mainHandItem, EngravingRegister.VOID_GIFT.get());
                    float voidAmount = amount * 0.05f * level >= 10 ? amount * 0.05f * level : 10 + level;
                    entity.hurt(player.level().damageSources().outOfBorder(), voidAmount);
                    event.setAmount(amount * (1.0f - 0.05f * level));
                }
            }

        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onCriticalHit(CriticalHitEvent event) {

    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        Entity entity = event.getSource().getEntity();
        LivingEntity livingEntity = event.getEntity();
        if (entity instanceof Player player) {
            ItemStack mainHandItem = player.getMainHandItem();
            if (EngravingHelper.hasEngraving(mainHandItem, EngravingRegister.SOUL_EATER.get())) {
                Random random = new Random();
                if (random.nextFloat(100) < EngravingHelper.getEngravingLevel(mainHandItem, EngravingRegister.SOUL_EATER.get()) * 2.5f){
                    CompoundTag orCreateTagElement = mainHandItem.getOrCreateTagElement(Eveninglament.MODID);
                    orCreateTagElement.putInt("soul_eater_kill_count", orCreateTagElement.getInt("soul_eater_kill_count") + 1);
                    player.displayClientMessage(Component.translatable("message.eveninglament.soul_eater_kill_count", orCreateTagElement.getInt("soul_eater_kill_count")), true);
                }
            }

        }
    }

    @SubscribeEvent
    public static void onItemAttributeModifier(ItemAttributeModifierEvent event) {
        ItemStack itemStack = event.getItemStack();
        if (!itemStack.getItem().equals(ItemRegister.FLOURISHING_BLOSSOM_ENGRAVING.get())){
            if (event.getSlotType().equals(EquipmentSlot.MAINHAND) && EngravingHelper.hasEngraving(itemStack, EngravingRegister.SOUL_EATER.get())) {
                CompoundTag orCreateTagElement = itemStack.getOrCreateTagElement(Eveninglament.MODID);
                event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("f8b8f8b8-f8b8-fdb7-f8b8-f8b8f8b8f8b8"), "generic.attack_damage", orCreateTagElement.getInt("soul_eater_kill_count"), AttributeModifier.Operation.ADDITION));
            }
        }
    }


    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack output = left.copy();
        ItemStack right = event.getRight();
        int cost = 0;
        if (right.getItem() == ItemRegister.FLOURISHING_BLOSSOM_ENGRAVING.get()) {
            Map<Engraving, Integer> engravings = EngravingHelper.getEngravings(right);
            for (Engraving engraving : engravings.keySet()) {
                if (EngravingHelper.canEngraving(output, engraving)){
                    EngravingHelper.addEngraving(output, engraving, engravings.get(engraving));
                    cost += engraving.getGrade().getGradeLevel() * 2 * engravings.get(engraving);

                }
            }
            if (cost > 0){
                event.setOutput(output);
                event.setCost(cost);
                event.setMaterialCost(1);
            }
        }
    }
}
