package org.yunxi.EveningLament.common.Events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.yunxi.EveningLament.Eveninglament;
import org.yunxi.EveningLament.api.Keybinds;

public class ClientModEvents {
    @Mod.EventBusSubscriber(modid = Eveninglament.MODID, value = Dist.CLIENT)
    public static class ClientEvents {

    }

    @Mod.EventBusSubscriber(modid = Eveninglament.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class KeyRegisterEvents {
        @SubscribeEvent
        public static void register(RegisterKeyMappingsEvent event) {
            event.register(Keybinds.SoulImprintKey);
        }
    }
}
