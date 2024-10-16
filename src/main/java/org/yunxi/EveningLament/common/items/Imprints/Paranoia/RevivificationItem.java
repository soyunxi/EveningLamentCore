package org.yunxi.EveningLament.common.items.Imprints.Paranoia;

import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.yunxi.EveningLament.api.Imprint.ImprintItem;
import org.yunxi.EveningLament.api.Item.EveningLamentRarity;
import top.theillusivec4.curios.api.SlotContext;

public class RevivificationItem extends ImprintItem {
    public RevivificationItem() {
        super(new Properties().fireResistant().rarity(EveningLamentRarity.PARANOIA).stacksTo(1));
    }

    @Override
    public boolean makesPiglinsNeutral(SlotContext slotContext, ItemStack stack) {
        return slotContext.entity() instanceof Player;
    }

    @Override
    public boolean canWalkOnPowderedSnow(SlotContext slotContext, ItemStack stack) {
        return slotContext.entity() instanceof Player;
    }

    @Override
    public boolean isEnderMask(SlotContext slotContext, EnderMan enderMan, ItemStack stack) {
        return slotContext.entity() instanceof Player;
    }
}
