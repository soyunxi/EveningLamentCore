package org.yunxi.EveningLament.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.registries.RegistryObject;
import org.yunxi.EveningLament.Eveninglament;
import org.yunxi.EveningLament.api.Engraving.Engraving;
import org.yunxi.EveningLament.common.Engraving.EngravingRegister;
import org.yunxi.EveningLament.common.items.ItemRegister;

import java.util.*;

public final class EngravingHelper {
    private EngravingHelper() {}

    public static final String LIST_TAG_KEY = "Engravings";
    public static final String LEVEL_KYE = "lvl";
    public static final String ENGRAVING_KYE = "id";


    public static int getEngravingLevel(CompoundTag compoundTag) {
        return Mth.clamp(compoundTag.getInt(LEVEL_KYE), 1, 3);
    }

    public static int getEngravingLevel(ItemStack itemStack, Engraving engraving) {
        if (!itemStack.isEmpty() && hasEngraving(itemStack, engraving)) {
            return Mth.clamp(getEngravings(itemStack).get(engraving), 1, Math.min(engraving.getMaxLevel(), 3));
        }
        return 0;
    }

    public static String getEngravingId(Engraving engraving) {
        for (RegistryObject<Engraving> entry : EngravingRegister.ENGRAVINGS.getEntries()) {
            if (engraving.equals(entry.get())) {
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

        if (itemStack.hasTag()){
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
        }

        return engravingIntegerHashMap;
    }

    public static Tag getEngravingTag(Engraving engraving, int level) {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putString(ENGRAVING_KYE, Objects.requireNonNull(getEngravingId(engraving)));
        compoundTag.putInt(LEVEL_KYE, Mth.clamp(level, 1, Math.min(engraving.getMaxLevel(), 3)));
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

    public static boolean canEngraving(ItemStack itemStack, Engraving engraving) {
        if (!hasEngraving(itemStack, engraving) && !itemStack.getItem().equals(ItemRegister.FLOURISHING_BLOSSOM_ENGRAVING.get())) {
            Set<Engraving> engravings = engraving.conflictEngravingList().length > 0 ? new HashSet<>(List.of(engraving.conflictEngravingList())) : null;
            Set<Enchantment> enchantments = engraving.conflictEnchantmentList().length > 0 ? new HashSet<>(List.of(engraving.conflictEnchantmentList())) : null;
            Iterator<Engraving> engravingIterator = getEngravings(itemStack).keySet().iterator();
            Iterator<Enchantment> enchantmentIterator = EnchantmentHelper.getEnchantments(itemStack).keySet().iterator();

            if (engravings != null){
                while (engravingIterator.hasNext()) {
                    Engraving next = engravingIterator.next();
                    if (engravings.contains(next)) return false;
                }
            }

            if (enchantments != null){
                while (enchantmentIterator.hasNext()) {
                    Enchantment next = enchantmentIterator.next();
                    if (enchantments.contains(next)) return false;
                }
            }

        }
        return engraving.canEnchant(itemStack);
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

    public static int getMaxGradeLevel(ItemStack itemStack) {
        int gradeLevel = 5;

        //附魔总等级加成
        int sumEnchantment = 0;
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(itemStack);
        for (Integer value : enchantments.values()) {
            sumEnchantment += value;
        }
        int MAX_SUM = 20;   // 硬性最大值
        int LINEAR_LIMIT = 8;   // 线性区间阈值（<=此值时应用线性规则）
        double LINEAR_FACTOR = 0.65;     // 线性阶段增长系数（1.0为原值，2.0为双倍）
        double K = 0.25;    // 衰减系数（控制曲线陡峭度）
        if (sumEnchantment <= LINEAR_LIMIT) {
            // 线性增长阶段：原始值 * 系数，但不超过MAX_SUM
            int linearValue = (int) Math.round(sumEnchantment * LINEAR_FACTOR);
            gradeLevel += Math.min(linearValue, MAX_SUM);
        } else {
            // 衰减阶段：非线性增长公式
            double adjusted = MAX_SUM * (1 - Math.exp(-K * (sumEnchantment - LINEAR_LIMIT) / MAX_SUM));
            gradeLevel +=  Math.min((int) Math.round(adjusted + (LINEAR_LIMIT * LINEAR_FACTOR)), MAX_SUM);
        }

        //最大耐久加成
        int maxDamage = itemStack.getMaxDamage();
        int MIN_OUTPUT = 3;   // 输出最小值
        int MAX_OUTPUT = 50;   // 输出最大值
        double CURVE_FACTOR = 0.0001; // 曲线陡峭度（越小低值增长越慢）
        if (maxDamage <= 0) gradeLevel += MIN_OUTPUT;
        // 使用指数函数抑制低值增长
        double ratio = 1 - Math.exp(-CURVE_FACTOR * maxDamage);
        int result = (int) Math.round(MIN_OUTPUT + (MAX_OUTPUT - MIN_OUTPUT) * ratio);
        gradeLevel += Math.min(result, MAX_OUTPUT);

        return gradeLevel;
    }
}
