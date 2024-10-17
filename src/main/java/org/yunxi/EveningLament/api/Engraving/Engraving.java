package org.yunxi.EveningLament.api.Engraving;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.yunxi.EveningLament.common.items.ItemRegister;
import org.yunxi.EveningLament.util.EngravingHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class Engraving {

    private final EngravingCategory[] EngravingCategory;

    private final Grade Grade;

    private final String Name;

    public Engraving(EngravingCategory[] engravingCategory, Engraving.Grade grade, String name) {
        this.EngravingCategory = engravingCategory;
        this.Grade = grade;
        this.Name = name;
    }

    public boolean canEnchant(ItemStack itemStack) {
        if (itemStack.getItem() != ItemRegister.FLOURISHING_BLOSSOM_ENGRAVING.get() && EngravingHelper.hasEngraving(itemStack, this)){
            for (int i = 0; i < EngravingCategory.length; i++) {
                for (EngravingCategory e : EngravingCategory) {
                    if (!e.canEnchant(itemStack)) {
                        return false;
                    }
                }
                for (Enchantment enchantment : EnchantmentHelper.getEnchantments(itemStack).keySet()) {
                    if (conflictEnchantmentList() != null && conflictEnchantmentList().contains(enchantment)) return false;
                }
                for (Engraving engraving : EngravingHelper.getEngravings(itemStack).keySet()) {
                    if (conflictEngravingList() != null && conflictEngravingList().contains(engraving)) return false;
                }


            }
        }

        return true;
    }

    public EngravingCategory[] getEngravingCategory() {
        return EngravingCategory;
    }

    public Engraving.Grade getGrade() {
        return Grade;
    }

    public String getName() {
        return Name;
    }

    public int getMaxLevel() {
        return 1;
    }

    public int getMinLevel() {
        return 1;
    }

    public static List<Enchantment> conflictEnchantmentList() {
       return null;
    }

    public static List<Engraving> conflictEngravingList() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Engraving && ((Engraving) obj).getName().equals(this.getName()) && obj.getClass() == this.getClass();
    }

    public static class Grade {
        public static final Grade FirstGrade = new Grade("common", 1);
        public static final Grade SecondGrade = new Grade("uncommon", 2);
        public static final Grade ThirdGrade = new Grade("epic", 3);
        public static final Grade FourthGrade = new Grade("legend", 4);
        public static final Grade FifthGrade = new Grade("mythic", 5);

        private final int GradeLevel;
        private final String GradeName;

        public Grade(String GradeName, int gradeLevel) {
            this.GradeLevel = gradeLevel;
            this.GradeName = GradeName;
        }

        public int getGradeLevel() {
            return GradeLevel;
        }

        public String getGradeName() {
            return GradeName;
        }
    }
}
