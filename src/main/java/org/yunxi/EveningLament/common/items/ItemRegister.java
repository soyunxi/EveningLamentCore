package org.yunxi.EveningLament.common.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import org.yunxi.EveningLament.Eveninglament;

public class ItemRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Eveninglament.MODID);

    public static final RegistryObject<Item> FLOURISHING_BLOSSOM_ENGRAVING = ITEMS.register("flourishing_blossom_engraving",
            EngravingItem::new);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
