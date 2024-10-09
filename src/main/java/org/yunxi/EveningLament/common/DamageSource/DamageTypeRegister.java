package org.yunxi.EveningLament.common.DamageSource;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.yunxi.EveningLament.Eveninglament;

public class DamageTypeRegister {
    public static final DeferredRegister<DamageType> DAMAGETYPES = DeferredRegister.create(Registries.DAMAGE_TYPE, Eveninglament.MODID);

    public static final RegistryObject<DamageType> VOID_DAMAGE_TYPE =
            DAMAGETYPES.register("void", () -> new DamageType("void", DamageScaling.NEVER, 1.0F));

    public static void register(IEventBus eventBus) {
        DAMAGETYPES.register(eventBus);
    }
}
