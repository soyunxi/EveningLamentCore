package org.yunxi.EveningLament.common.items.Food;

import com.aizistral.enigmaticlegacy.handlers.SuperpositionHandler;
import com.aizistral.enigmaticlegacy.items.generic.ItemBaseFood;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.yunxi.EveningLament.common.Events.Register;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;

import java.util.UUID;

public class BloodOrange extends ItemBaseFood {
    public BloodOrange() {
        super(new Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), new FoodProperties.Builder()
                .nutrition(10)
                .saturationMod(0)
                .alwaysEat()
                .build());
    }

    @Override
    public void onConsumed(Level worldIn, Player player, ItemStack food) {
        UUID id = UUID.fromString("780537fd-23e3-43af-9b14-de2b40cd89b3");
        if (player instanceof ServerPlayer serverPlayer) {
            ICuriosHelper curiosHelper = CuriosApi.getCuriosHelper();
            curiosHelper.getCuriosHandler(serverPlayer).ifPresent(handler -> {
                handler.getStacksHandler("imprint").ifPresent(imprint -> {
                    if (!imprint.getModifiers().containsKey(id)) {
                        imprint.addPermanentModifier(new AttributeModifier(id, "BloodOrange", 1.0, AttributeModifier.Operation.ADDITION));
                    }
                });

            });
            if (SuperpositionHandler.getPersistentBoolean(serverPlayer, "bloodOrange", false)) {
                serverPlayer.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 3600, 2, false, false));
                serverPlayer.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 3600, 2, false, false));
            } else {
                serverPlayer.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 12000, 1, false, false));
                serverPlayer.addEffect(new MobEffectInstance(MobEffects.POISON, 12000, 0, false, true));
            }
            SuperpositionHandler.setPersistentBoolean(serverPlayer, "bloodOrange", true);


        }
    }

    @Override
    public CreativeModeTab getCreativeTab() {
        return Register.ENGRAVING_MAIN_TAB;
    }
}
