package org.yunxi.EveningLament.common.SoulImprint;


import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.yunxi.EveningLament.Eveninglament;
import org.yunxi.EveningLament.api.Imprint.SoulImprint;

@Mod.EventBusSubscriber(modid = Eveninglament.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SoulImprintRegister {
    public static final ResourceKey<Registry<SoulImprint>> SOUL_IMPRINT_REGISTRY_KEY =
            ResourceKey.createRegistryKey(new ResourceLocation(Eveninglament.MODID, "soul_imprint"));

    public static final DeferredRegister<SoulImprint> SOUL_IMPRINTS =
            DeferredRegister.create(SOUL_IMPRINT_REGISTRY_KEY, Eveninglament.MODID);



    public static void register(IEventBus eventBus) {
        SOUL_IMPRINTS.register(eventBus);
    }

    @SubscribeEvent
    public static void onNewRegistry(NewRegistryEvent event) {
        event.create(new RegistryBuilder<SoulImprint>()
                .setName(SOUL_IMPRINT_REGISTRY_KEY.location())
                .disableSaving()
                .disableSync());
    }
}
