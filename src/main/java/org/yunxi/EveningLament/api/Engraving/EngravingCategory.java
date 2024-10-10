package org.yunxi.EveningLament.api.Engraving;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.IExtensibleEnum;

import java.util.function.Predicate;

public enum EngravingCategory implements IExtensibleEnum {
    ARMOR{
        @Override
        public boolean canEnchant(ItemStack itemStack) {
            return itemStack.getItem() instanceof ArmorItem;
        }
    },
    ARMOR_FEET{
        @Override
        public boolean canEnchant(ItemStack itemStack) {
            return itemStack.getItem() instanceof ArmorItem armorItem && armorItem.getEquipmentSlot() == EquipmentSlot.FEET;
        }
    },
    ARMOR_LEGS{
        @Override
        public boolean canEnchant(ItemStack itemStack) {
            return itemStack.getItem() instanceof ArmorItem armorItem && armorItem.getEquipmentSlot() == EquipmentSlot.LEGS;
        }
    },
    ARMOR_CHEST{
        @Override
        public boolean canEnchant(ItemStack itemStack) {
            return itemStack.getItem() instanceof ArmorItem armorItem && armorItem.getEquipmentSlot() == EquipmentSlot.CHEST;
        }
    },
    ARMOR_HEAD{
        @Override
        public boolean canEnchant(ItemStack itemStack) {
            return itemStack.getItem() instanceof ArmorItem armorItem && armorItem.getEquipmentSlot() == EquipmentSlot.HEAD;
        }
    },
    WEAPON {
        @Override
        public boolean canEnchant(ItemStack itemStack) {
            return itemStack.getItem() instanceof SwordItem;
        }
    },
    DIGGER{
        @Override
        public boolean canEnchant(ItemStack itemStack) {
            return itemStack.getItem() instanceof DiggerItem;
        }
    },
    FISHING_ROD{
        @Override
        public boolean canEnchant(ItemStack itemStack) {
            return itemStack.getItem() instanceof FishingRodItem;
        }
    },
    TRIDENT{
        @Override
        public boolean canEnchant(ItemStack itemStack) {
            return itemStack.getItem() instanceof TridentItem;
        }
    },
    DAMAGEABLE{
        @Override
        public boolean canEnchant(ItemStack itemStack) {
            return itemStack.isDamageableItem();
        }
    },
    BOW{
        @Override
        public boolean canEnchant(ItemStack itemStack) {
            return itemStack.getItem() instanceof BowItem;
        }
    },
    WEARABLE{
        @Override
        public boolean canEnchant(ItemStack itemStack) {
            return itemStack.getItem() instanceof Equipable || Block.byItem(itemStack.getItem()) instanceof Equipable;
        }
    },
    CROSSBOW{
        @Override
        public boolean canEnchant(ItemStack itemStack) {
            return itemStack.getItem() instanceof CompassItem;

        }
    };

    private Predicate<ItemStack> predicate;

    EngravingCategory() {
    }

    EngravingCategory(Predicate<ItemStack> predicate) {
        this.predicate = predicate;
    }

    public boolean canEnchant(ItemStack itemStack) {
        return this.predicate != null && this.predicate.test(itemStack);
    }

    public static EngravingCategory create(String name, Predicate<Item> delegate) {
        throw new IllegalStateException("Enum not extended");
    }
}
