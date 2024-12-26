package org.yunxi.EveningLament.common.Engraving;

import org.yunxi.EveningLament.api.Engraving.Engraving;
import org.yunxi.EveningLament.api.Engraving.EngravingCategory;

public class SpoonEngraving extends Engraving {
    public SpoonEngraving() {
        super(new EngravingCategory[]{EngravingCategory.PICKAXE}, Engraving.Grade.SecondGrade, "spoon");
    }
}
