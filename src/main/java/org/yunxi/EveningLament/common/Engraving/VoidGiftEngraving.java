package org.yunxi.EveningLament.common.Engraving;

import org.yunxi.EveningLament.api.Engraving.Engraving;
import org.yunxi.EveningLament.api.Engraving.EngravingCategory;

public class VoidGiftEngraving extends Engraving {
    public VoidGiftEngraving() {
        super(EngravingCategory.WEARABLE, Grade.FifthGrade, "void_gift");
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
