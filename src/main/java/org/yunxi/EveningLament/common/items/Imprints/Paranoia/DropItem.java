package org.yunxi.EveningLament.common.items.Imprints.Paranoia;

import org.yunxi.EveningLament.api.Imprint.ImprintItem;
import org.yunxi.EveningLament.api.Item.EveningLamentRarity;

public class DropItem extends ImprintItem {
    public DropItem() {
        super(new Properties().fireResistant().rarity(EveningLamentRarity.PARANOIA).stacksTo(1));
    }
}