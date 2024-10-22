package org.yunxi.EveningLament.common.Engraving;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;
import org.yunxi.EveningLament.Eveninglament;
import org.yunxi.EveningLament.api.Engraving.Engraving;

@Mod.EventBusSubscriber(modid = Eveninglament.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EngravingRegister {
    public static final ResourceKey<Registry<Engraving>> ENGRAVING_REGISTRY_KEY =
            ResourceKey.createRegistryKey(new ResourceLocation(Eveninglament.MODID, "engraving"));

    public static final DeferredRegister<Engraving> ENGRAVINGS =
            DeferredRegister.create(ENGRAVING_REGISTRY_KEY, Eveninglament.MODID);

    public static final RegistryObject<Engraving> BLOOD_MARY =
            ENGRAVINGS.register("blood_mary", BloodMaryEngraving::new);

    public static final RegistryObject<Engraving> VOID_GIFT =
            ENGRAVINGS.register("void_gift", VoidGiftEngraving::new);

    public static final RegistryObject<Engraving> WORLD_LIBRARY =
            ENGRAVINGS.register("world_library", WorldLibrary::new);

    public static final RegistryObject<Engraving> SOUL_EATER =
            ENGRAVINGS.register("soul_eater", SoulEaterEngraving::new);

    public static void register(IEventBus eventBus) {
        ENGRAVINGS.register(eventBus);
    }

    @SubscribeEvent
    public static void onNewRegistry(NewRegistryEvent event) {
        event.create(new RegistryBuilder<Engraving>()
                .setName(ENGRAVING_REGISTRY_KEY.location())
                .disableSaving()
                .disableSync());
    }

}
