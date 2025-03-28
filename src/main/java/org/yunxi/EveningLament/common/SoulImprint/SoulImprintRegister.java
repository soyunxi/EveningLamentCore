package org.yunxi.EveningLament.common.SoulImprint;


import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
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
import org.yunxi.EveningLament.api.Imprint.ImprintItem;
import org.yunxi.EveningLament.api.Imprint.SoulImprint;
import org.yunxi.EveningLament.common.items.ItemRegister;

import java.util.LinkedHashMap;

@Mod.EventBusSubscriber(modid = Eveninglament.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SoulImprintRegister {
    public static final ResourceKey<Registry<SoulImprint>> SOUL_IMPRINT_REGISTRY_KEY =
            ResourceKey.createRegistryKey(new ResourceLocation(Eveninglament.MODID, "soul_imprint"));

    public static final DeferredRegister<SoulImprint> SOUL_IMPRINTS =
            DeferredRegister.create(SOUL_IMPRINT_REGISTRY_KEY, Eveninglament.MODID);


    public static final RegistryObject<SoulImprint> PARANOIA = SOUL_IMPRINTS.register("paranoia", () ->
            new SoulImprint(new ImprintItem[] {
                    (ImprintItem) ItemRegister.DROP.get(),
                    (ImprintItem) ItemRegister.REVIVIFICATION.get(),
                    (ImprintItem) ItemRegister.RISE.get()},
                    new LinkedHashMap<>() {{
                        put(Component.translatable("tooltip.eveninglament.paranoia1"), false);
                        put(Component.translatable("tooltip.eveninglament.paranoia2"), false);
                        put(Component.translatable("tooltip.eveninglament.paranoia3"), false);
                    }},
                    Component.translatable("soulimprint.eveninglament.paranoia")));

    public static final RegistryObject<SoulImprint> LIFE = SOUL_IMPRINTS.register("life", () ->
            new SoulImprint(new ImprintItem[] {
                    (ImprintItem) ItemRegister.MORNING_DEW.get(),
                    (ImprintItem) ItemRegister.SPLENDID.get()},
                    new LinkedHashMap<>() {{
                        put(Component.translatable("tooltip.eveninglament.life2"), false);
                        put(Component.translatable("tooltip.eveninglament.life3"), false);
                    }},
                    Component.translatable("soulimprint.eveninglament.life"))
    );

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
