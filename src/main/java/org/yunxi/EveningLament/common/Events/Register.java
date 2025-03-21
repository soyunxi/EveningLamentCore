package org.yunxi.EveningLament.common.Events;


import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;
import org.checkerframework.checker.units.qual.C;
import org.yunxi.EveningLament.Eveninglament;
import org.yunxi.EveningLament.common.items.EngravingItem;
import org.yunxi.EveningLament.common.items.ItemRegister;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Eveninglament.MODID)
public class Register {
    public static final CreativeModeTab ENGRAVING_MAIN_TAB = CreativeModeTab.builder().title(Component.translatable("itemGroup.eveninglament.main_tab")).icon(() -> new ItemStack(ItemRegister.BLOOD_ORANGE.get())).build();
    @SubscribeEvent
    public static void onRegisterEvent(RegisterEvent event) {
        event.register(Registries.CREATIVE_MODE_TAB, new ResourceLocation(Eveninglament.MODID, "engraving_tab"), () -> CreativeModeTab.builder()
                .displayItems((parameters, output) -> {
                    EngravingItem.getEngravings().forEach(output::accept);
                })
                .icon(() -> new ItemStack(ItemRegister.FLOURISHING_BLOSSOM_ENGRAVING.get()))
                .title(Component.translatable("itemGroup.eveninglament.engraving_tab"))
                .build());

        event.register(Registries.CREATIVE_MODE_TAB, new ResourceLocation(Eveninglament.MODID, "main_tab"), () -> ENGRAVING_MAIN_TAB);

    }
}
