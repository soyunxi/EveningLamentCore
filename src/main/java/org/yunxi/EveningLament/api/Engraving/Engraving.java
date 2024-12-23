package org.yunxi.EveningLament.api.Engraving;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public abstract class Engraving {

    private final EngravingCategory[] EngravingCategory;

    private final Grade Grade;

    private final String Name;

    public Engraving(EngravingCategory[] engravingCategory, Engraving.Grade grade, String name) {
        this.EngravingCategory = engravingCategory;
        this.Grade = grade;
        this.Name = name;
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

    public Enchantment[] conflictEnchantmentList() {
       return new Enchantment[0];
    }

    public Engraving[] conflictEngravingList() {
        return new Engraving[0];
    }

    public boolean canEnchant(ItemStack itemStack) {
        for (EngravingCategory engravingCategory : getEngravingCategory()) {
            if (engravingCategory.canEnchant(itemStack)) return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Engraving && ((Engraving) obj).getName().equals(this.getName()) && obj.getClass() == this.getClass();
    }

    @Override
    public String toString() {
        return getName();
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
