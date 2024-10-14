package org.yunxi.EveningLament.common.items.Imprints.Paranoia;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import org.yunxi.EveningLament.api.Imprint.IImprint;
import org.yunxi.EveningLament.api.Imprint.ImprintItem;
import org.yunxi.EveningLament.api.Item.EveningLamentRarity;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class RiseItem extends ImprintItem {
    public RiseItem() {
        super(new Properties().fireResistant().rarity(EveningLamentRarity.PARANOIA).stacksTo(1));
    }


}
