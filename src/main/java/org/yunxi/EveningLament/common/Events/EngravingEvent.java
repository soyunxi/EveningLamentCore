package org.yunxi.EveningLament.common.Events;


import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.commands.DamageCommand;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.minecraftforge.client.event.RecipesUpdatedEvent;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;
import org.yunxi.EveningLament.Eveninglament;
import org.yunxi.EveningLament.api.Engraving.Engraving;
import org.yunxi.EveningLament.api.Register;
import org.yunxi.EveningLament.common.DamageSource.DamageSources;
import org.yunxi.EveningLament.common.DamageSource.DamageTypeRegister;
import org.yunxi.EveningLament.common.Engraving.BloodMaryEngraving;
import org.yunxi.EveningLament.common.Engraving.EngravingRegister;
import org.yunxi.EveningLament.common.items.ItemRegister;
import org.yunxi.EveningLament.util.EngravingHelper;

import java.util.Map;

@Mod.EventBusSubscriber(modid = Eveninglament.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EngravingEvent {
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
                    entity.hurt(entity.level().damageSources().outOfBorder(), voidAmount);
                    event.setAmount(amount * (1.0f - 0.05f * level));
                }
            }

        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onCriticalHit(CriticalHitEvent event) {

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
                if (!EngravingHelper.hasEngraving(left, engraving)){
                    if (engraving.canEnchant(output)) {
                        EngravingHelper.addEngraving(output, engraving, engravings.get(engraving));
                        cost += engraving.getGrade().getGradeLevel() * 2 * engravings.get(engraving);
                    }
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
