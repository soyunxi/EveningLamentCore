package org.yunxi.EveningLament.common.Engraving;

import org.yunxi.EveningLament.api.Engraving.Engraving;
import org.yunxi.EveningLament.api.Engraving.EngravingCategory;

public class BloodMaryEngraving extends Engraving {
    public BloodMaryEngraving() {
        super(EngravingCategory.WEAPON, Engraving.Grade.FourthGrade, "blood_mary");
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
