package org.yunxi.EveningLament.common.items;


import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import org.yunxi.EveningLament.Eveninglament;
import org.yunxi.EveningLament.api.Engraving.Engraving;
import org.yunxi.EveningLament.common.Engraving.EngravingRegister;
import org.yunxi.EveningLament.util.EngravingHelper;

import java.util.ArrayList;
import java.util.List;


public class EngravingItem extends Item {
    public EngravingItem() {
        super(new Properties().fireResistant().stacksTo(1).rarity(Rarity.create("engraving", style -> style.withColor(0xFF55DD))));
    }

    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return false;
    }

    public static List<ItemStack> getEngravings() {
        List<ItemStack> engravings = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            for (RegistryObject<Engraving> entry : EngravingRegister.ENGRAVINGS.getEntries()) {
                if (entry.get().getGrade().getGradeLevel() == i) {
                    for (int j = 0; j < entry.get().getMaxLevel(); j++){
                        ItemStack itemStack = new ItemStack(ItemRegister.FLOURISHING_BLOSSOM_ENGRAVING.get());
                        EngravingHelper.addEngraving(itemStack, entry.get(), j + 1);
                        engravings.add(itemStack);
                    }
                }
            }
        }
        return engravings;
    }

}
