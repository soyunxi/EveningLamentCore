package org.yunxi.EveningLament.common.Engraving;

import dev.shadowsoffire.apotheosis.ench.asm.EnchHooks;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.yunxi.EveningLament.api.Engraving.Engraving;
import org.yunxi.EveningLament.api.Engraving.EngravingCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldLibraryEngraving extends Engraving {
    public WorldLibraryEngraving() {
        super(new EngravingCategory[]{EngravingCategory.FISHING_ROD}, Engraving.Grade.FifthGrade, "world_library");
    }

    public static ItemStack getWorldLibraryOutPut(Player player) {
        ItemStack itemStack = new ItemStack(Items.ENCHANTED_BOOK);
        float luck = player.getLuck();
        List<Enchantment> enchantments = new ArrayList<>();
        Map<Enchantment, Integer> out = new HashMap<>();
        for (Enchantment enchantment : ForgeRegistries.ENCHANTMENTS) {
            if (enchantment.isAllowedOnBooks() && enchantment.isDiscoverable()){
                if (enchantment.isTreasureOnly() && luck > 5){
                    enchantments.add(enchantment);
                } else if (!enchantment.isTreasureOnly()) {
                    enchantments.add(enchantment);
                }
            }
        }

        RandomSource random = player.getRandom();
        int temp = Math.max(((int) (luck / 5)), 1);
        int max = Math.min(random.nextIntBetweenInclusive(1, temp), 5);
        for (int i = 0; i < max; i++) {
            Enchantment enchantment = enchantments.get(random.nextInt(enchantments.size()));
            int level = random.nextIntBetweenInclusive(1, Math.min(EnchHooks.getMaxLevel(enchantment), Math.max(1, (int) luck)));
            out.put(enchantment, level);
        }
        EnchantmentHelper.setEnchantments(out, itemStack);
        return itemStack;
    }
}
