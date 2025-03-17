package org.yunxi.EveningLament.common.items;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.RegistryObject;
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
        List<ItemStack> engravingItems = new ArrayList<>();

        int maxGrade = 5;
        int minGrade = 0;

        for (RegistryObject<Engraving> entry : EngravingRegister.ENGRAVINGS.getEntries()) {
            maxGrade = Math.max(entry.get().getMaxLevel(), maxGrade);
            minGrade = Math.min(entry.get().getMinLevel(), minGrade);
        }


        for (RegistryObject<Engraving> entry : EngravingRegister.ENGRAVINGS.getEntries()) {
            Engraving engraving = entry.get();
            for (int i = 0; i < maxGrade + 1; i++) {
                if (engraving.getGrade().getGradeLevel() == i){
                    for (int j = 0; j < engraving.getMaxLevel(); j++) {
                        ItemStack itemStack = new ItemStack(ItemRegister.FLOURISHING_BLOSSOM_ENGRAVING.get());
                        EngravingHelper.addEngraving(itemStack, engraving, j + 1);
                        engravingItems.add(itemStack);
                    }
                }
            }
        }

        return engravingItems;
    }

}
