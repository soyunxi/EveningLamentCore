package org.yunxi.EveningLament.common.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.yunxi.EveningLament.Eveninglament;
import org.yunxi.EveningLament.common.items.Food.BloodOrange;
import org.yunxi.EveningLament.common.items.Imprints.Life.MorningDew;
import org.yunxi.EveningLament.common.items.Imprints.Life.Splendid;
import org.yunxi.EveningLament.common.items.Imprints.Paranoia.DropItem;
import org.yunxi.EveningLament.common.items.Imprints.Paranoia.RevivificationItem;
import org.yunxi.EveningLament.common.items.Imprints.Paranoia.RiseItem;

public class ItemRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Eveninglament.MODID);

    public static final RegistryObject<Item> FLOURISHING_BLOSSOM_ENGRAVING = ITEMS.register("flourishing_blossom_engraving",
            EngravingItem::new);
    public static final RegistryObject<Item> RISE = ITEMS.register("rise",
            RiseItem::new);
    public static final RegistryObject<Item> REVIVIFICATION = ITEMS.register("revivification",
            RevivificationItem::new);
    public static final RegistryObject<Item> DROP = ITEMS.register("drop",
            DropItem::new);

    public static final RegistryObject<Item> MORNING_DEW = ITEMS.register("morning_dew",
            MorningDew::new);
    public static final RegistryObject<Item> SPLENDID = ITEMS.register("splendid",
            Splendid::new);

    public static final RegistryObject<Item> BLOOD_ORANGE = ITEMS.register("blood_orange",
            BloodOrange::new);


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
