package org.yunxi.EveningLament.common.Events;


import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.yunxi.EveningLament.Eveninglament;
import org.yunxi.EveningLament.common.Engraving.BloodMaryEngraving;
import org.yunxi.EveningLament.common.Engraving.EngravingRegister;
import org.yunxi.EveningLament.util.EngravingHelper;

@Mod.EventBusSubscriber(modid = Eveninglament.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EngravingEvent {
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        Entity directEntity = event.getSource().getDirectEntity();
        LivingEntity entityLiving = event.getEntity();
        float amount = event.getAmount();
        if (directEntity instanceof Player player) {
            ItemStack mainHandItem = player.getMainHandItem();
            player.displayClientMessage(Component.nullToEmpty(Boolean.toString(EngravingHelper.hasEngraving(mainHandItem, EngravingRegister.BLOOD_MARY.get()))), false);
            player.displayClientMessage(Component.nullToEmpty(Integer.toString(EngravingHelper.getEngravingLevel(mainHandItem, EngravingRegister.BLOOD_MARY.get()))), false);

            EngravingHelper.addEngraving(mainHandItem, EngravingRegister.BLOOD_MARY.get(), 2);
        }

    }
}
