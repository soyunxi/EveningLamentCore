package org.yunxi.EveningLament.common.Events;


import com.github.alexthe666.iceandfire.item.ItemModSword;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.yunxi.EveningLament.Eveninglament;
import org.yunxi.EveningLament.api.Engraving.Engraving;
import org.yunxi.EveningLament.common.Engraving.EngravingRegister;
import org.yunxi.EveningLament.common.SoulImprint.SoulImprintRegister;
import org.yunxi.EveningLament.common.items.ItemRegister;
import org.yunxi.EveningLament.util.EngravingHelper;
import org.yunxi.EveningLament.util.SoulImprintHelper;

import java.util.Map;
import java.util.Objects;
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
                    float addAmount = (entityLiving.getMaxHealth() - entityLiving.getHealth()) * 0.025f;
                    event.setAmount(amount + addAmount);}
            }

            if (EngravingHelper.hasEngraving(mainHandItem, EngravingRegister.WORLD_FPS.get())) {
                Minecraft instance = Minecraft.getInstance();
                int fps = instance.getFps();
                if (fps < 90){
                    event.setAmount(amount + ((90 - fps) * 0.01f * amount));
                } else if (fps > 90) {
                    event.setAmount(Math.max(0, amount - ((fps - 90) * 0.01f * amount)));
                }
            }


            if (SoulImprintHelper.getTakeEffectLevel(SoulImprintRegister.PARANOIA.get()) > 0) {
                player.heal(amount * 0.1f);
            }
            if (SoulImprintHelper.getTakeEffectLevel(SoulImprintRegister.PARANOIA.get()) > 1) {
                if (entityLiving.getHealth() / entityLiving.getMaxHealth() <= 0.4f) {
                    event.setAmount(amount + amount * 0.2f);
                }
            }
            if (SoulImprintHelper.getTakeEffectLevel(SoulImprintRegister.PARANOIA.get()) > 2) {
                if (entityLiving.getHealth() / entityLiving.getMaxHealth() <= 0.15f) {
                    entityLiving.hurt(player.level().damageSources().fellOutOfWorld(), entityLiving.getMaxHealth() * 1000);
                }
            }
            if (mainHandItem.getItem() instanceof ItemModSword itemModSword) {
                /*if (itemModSword.getTier() == DragonSteelTier.DRAGONSTEEL_TIER_LIGHTNING) {
                    LivingEntityHelper.chainLightningWithParticles(player.level(), player, entityLiving, 10, 5, amount, 5, true);
                }*/
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLowestLivingDamage(LivingDamageEvent event) {
        Entity directEntity = event.getSource().getDirectEntity();
        LivingEntity entityLiving = event.getEntity();
        float amount = event.getAmount();
        if (directEntity instanceof Player player) {
            ItemStack mainHandItem = player.getMainHandItem();
            NonNullList<ItemStack> armor = player.getInventory().armor;
            ItemStack head = armor.get(3);
            if (EngravingHelper.hasEngraving(head, EngravingRegister.GLUTTONY.get())) {
                CompoundTag orCreateTagElement = head.getOrCreateTagElement(Eveninglament.MODID);
                CompoundTag compoundTag = new CompoundTag();
                compoundTag.putFloat("amount", amount);
                compoundTag.putFloat("health", entityLiving.getHealth());
                orCreateTagElement.put("overflow", compoundTag);
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
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLowestLivingDeath(LivingDeathEvent event) {
        Entity entity = event.getSource().getEntity();
        LivingEntity livingEntity = event.getEntity();
        if (entity instanceof Player player) {
            NonNullList<ItemStack> armor = player.getInventory().armor;
            ItemStack head = armor.get(3);
            if (EngravingHelper.hasEngraving(head, EngravingRegister.GLUTTONY.get())) {
                CompoundTag orCreateTagElement = head.getOrCreateTagElement(Eveninglament.MODID);
                if (orCreateTagElement.contains("overflow")) {
                    CompoundTag compoundTag = orCreateTagElement.getCompound("overflow");
                    float health = compoundTag.getFloat("health");
                    float amount = compoundTag.getFloat("amount");
                    FoodData foodData = player.getFoodData();
                    int food = 20 - foodData.getFoodLevel();
                    float saturation = 20 - foodData.getSaturationLevel();
                    float overflow = (amount - health) / 5;
                    if (overflow <= food) {
                        foodData.eat((int) overflow, 0);
                    } else {
                        foodData.eat(Math.min(food, (int) overflow), Math.min(saturation, overflow - food));
                    }
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
    public static void onFinishLivingEntityUseItem(LivingEntityUseItemEvent.Finish event) {
        LivingEntity entity = event.getEntity();
        ItemStack item = event.getItem();
        if (entity instanceof Player player) {
            if (item.getItem().isEdible()) {
                if (EngravingHelper.hasEngraving(player.getInventory().armor.get(3), EngravingRegister.GLUTTONY.get())){
                    player.getFoodData().eat((int) (-Objects.requireNonNull(item.getFoodProperties(player)).getNutrition() * 0.5), -Objects.requireNonNull(item.getFoodProperties(player)).getSaturationModifier() * 0.5f);
                    //TODO 修bug
                    if ((20 - player.getFoodData().getFoodLevel()) <= (item.getFoodProperties(player).getNutrition() * 0.5f)){
                        player.getFoodData().eat((int) (Objects.requireNonNull(item.getFoodProperties(player)).getNutrition() * 0.5), Objects.requireNonNull(item.getFoodProperties(player)).getSaturationModifier() * 0.5f);

                    }
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1, false, true));
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 200, 1, false, true));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onStartLivingEntityUseItem(LivingEntityUseItemEvent.Start event) {
        LivingEntity entity = event.getEntity();
        ItemStack item = event.getItem();
        if (entity instanceof Player player) {
            if (item.getItem().isEdible()) {
                if (EngravingHelper.hasEngraving(player.getInventory().armor.get(3), EngravingRegister.GLUTTONY.get())){
                    event.setDuration((int) (event.getDuration() * 0.1f));
                }
            }
        }
    }

    /*@SubscribeEvent
    public static void onItemFished(ItemFishedEvent event) {
        Player entity = event.getEntity();
        Entity hookEntity = event.getHookEntity();
        if (entity != null) {
            ItemStack stack = entity.getMainHandItem().isEmpty() ? entity.getOffhandItem() : entity.getMainHandItem();
            if (stack.getItem() instanceof FishingRodItem) {
                if (EngravingHelper.hasEngraving(stack, EngravingRegister.WORLD_LIBRARY.get())) {
                    ItemStack worldLibraryOutPut = WorldLibraryEngraving.getWorldLibraryOutPut(entity);
                    entity.displayClientMessage(worldLibraryOutPut.getDisplayName(),false);
                    event.getDrops().clear();
                    ItemEntity itemEntity = new ItemEntity(entity.level(), hookEntity.getX(), hookEntity.getY(), hookEntity.getZ(), worldLibraryOutPut);
                    BlockPos pos = event.getHookEntity().getOnPos();
                    double d0 = entity.getX() - pos.getX();
                    double d1 = entity.getY() - pos.getY();
                    double d2 = entity.getZ() - pos.getZ();
                    itemEntity.setDeltaMovement(d0 * 0.1D, d1 * 0.1D + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08D, d2 * 0.1D);
                    entity.level().addFreshEntity(itemEntity);
                }
            }
        }
    }*/





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
