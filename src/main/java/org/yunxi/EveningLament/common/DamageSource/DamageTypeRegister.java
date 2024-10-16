package org.yunxi.EveningLament.common.DamageSource;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;
import org.yunxi.EveningLament.Eveninglament;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Eveninglament.MODID)
public class DamageTypeRegister {

    public static final DamageType void_damage_type = new DamageType("void_damage", DamageScaling.NEVER, 1.0F);
    @SubscribeEvent
    public static void onRegisterEvent(RegisterEvent event) {
        event.register(Registries.DAMAGE_TYPE, helper -> {
            helper.register(new ResourceLocation(Eveninglament.MODID, "void_damage"), void_damage_type);
        });
    }

}
