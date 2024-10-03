package org.yunxi.EveningLament.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.registries.RegistryObject;
import org.yunxi.EveningLament.Eveninglament;
import org.yunxi.EveningLament.api.Engraving.Engraving;
import org.yunxi.EveningLament.common.Engraving.EngravingRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class EngravingHelper {
    private EngravingHelper() {}

    public static final String LIST_TAG_KEY = "Engravings";
    public static final String LEVEL_KYE = "lvl";
    public static final String ENGRAVING_KYE = "id";


    public static int getEngravingLevel(CompoundTag compoundTag) {
        return Mth.clamp(compoundTag.getInt(LEVEL_KYE), 1, 3);
    }

    public static int getEngravingLevel(ItemStack itemStack, Engraving engraving) {
        if (!itemStack.isEmpty() && hasEngraving(itemStack, engraving)) {
            return Mth.clamp(getEngravings(itemStack).get(engraving), 1, 3);
        }
        return 0;
    }

    public static String getEngravingId(Engraving engraving) {
        for (RegistryObject<Engraving> entry : EngravingRegister.ENGRAVINGS.getEntries()) {
            if (Objects.equals(entry.get(), engraving)) {
                ResourceLocation id = entry.getId();
                return id.getNamespace() + ":" + id.getPath();
            }
        }
        return null;
    }

    public static Engraving getEngraving(String EngravingId) {
        for (RegistryObject<Engraving> entry : EngravingRegister.ENGRAVINGS.getEntries()) {
            if (Objects.equals(getEngravingId(entry.get()), EngravingId)) return entry.get();
        }

        return null;
    }

    public static Map<Engraving, Integer> getEngravings(ItemStack itemStack) {
        HashMap<Engraving, Integer> engravingIntegerHashMap = new HashMap<>();

        CompoundTag orCreateTagElement = itemStack.getOrCreateTagElement(Eveninglament.MODID);
        if (orCreateTagElement.contains(LIST_TAG_KEY, 9)) {
            ListTag list = orCreateTagElement.getList(LIST_TAG_KEY, 10);
            for (int i = 0; i < list.size(); i++) {
                CompoundTag compound = list.getCompound(i);
                Engraving engraving = getEngraving(compound.getString(ENGRAVING_KYE));
                int anInt = getEngravingLevel(compound);
                engravingIntegerHashMap.put(engraving, anInt);

            }
        }
        return engravingIntegerHashMap;
    }

    public static Tag getEngravingTag(Engraving engraving, int level) {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putString(ENGRAVING_KYE, Objects.requireNonNull(getEngravingId(engraving)));
        compoundTag.putInt(LEVEL_KYE, Mth.clamp(level, 1, 3));
        return compoundTag;
    }

    public static boolean hasEngraving(ItemStack itemStack, Engraving engraving) {
        return itemStack.hasTag() && getEngravings(itemStack).containsKey(engraving);
    }

    public static void addEngraving(ItemStack itemStack, Engraving engraving, int level) {
        if (!hasEngraving(itemStack, engraving)) {
            CompoundTag orCreateTagElement = itemStack.getOrCreateTagElement(Eveninglament.MODID);
            if (!orCreateTagElement.contains(LIST_TAG_KEY, 9)) {
                orCreateTagElement.put(LIST_TAG_KEY,new ListTag());
            }
            ListTag list = orCreateTagElement.getList(LIST_TAG_KEY, 10);
            list.add(getEngravingTag(engraving, level));
        }
    }

    public static void setEngravingLevel(ItemStack itemStack, Engraving engraving, int level) {
        if (hasEngraving(itemStack, engraving)) {
            CompoundTag orCreateTagElement = itemStack.getOrCreateTagElement(Eveninglament.MODID);
            ListTag list = orCreateTagElement.getList(LIST_TAG_KEY, 10);
            list.remove(getEngravingTag(engraving, getEngravingLevel(itemStack, engraving)));
            list.add(getEngravingTag(engraving, level));
        }
    }

    public static void removeEngraving(ItemStack itemStack, Engraving engraving) {
        if (hasEngraving(itemStack, engraving)) {
            CompoundTag orCreateTagElement = itemStack.getOrCreateTagElement(Eveninglament.MODID);
            ListTag list = orCreateTagElement.getList(LIST_TAG_KEY, 10);
            list.remove(getEngravingTag(engraving, getEngravingLevel(itemStack, engraving)));
        }
    }
    public static int getGradeLevel(ItemStack itemStack) {
        Map<Engraving, Integer> engravings = getEngravings(itemStack);
        int gradeLevel = 0;
        if (!engravings.isEmpty()) {
            for (Engraving engraving : engravings.keySet()) {
                gradeLevel += engraving.getGrade().getGradeLevel();
            }
        }
        return gradeLevel;
    }
}
