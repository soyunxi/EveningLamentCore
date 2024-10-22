package org.yunxi.EveningLament.common.Engraving;

import org.yunxi.EveningLament.api.Engraving.Engraving;
import org.yunxi.EveningLament.api.Engraving.EngravingCategory;

import java.util.ArrayList;
import java.util.List;

public class SoulEaterEngraving extends Engraving {
    public SoulEaterEngraving() {
        super(new EngravingCategory[]{EngravingCategory.WEAPON}, Engraving.Grade.ThirdGrade, "soul_eater");
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public Engraving[] conflictEngravingList() {

        return new Engraving[]{EngravingRegister.VOID_GIFT.get()};
    }
}
