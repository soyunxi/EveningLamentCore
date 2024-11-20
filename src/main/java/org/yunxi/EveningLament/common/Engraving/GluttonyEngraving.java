package org.yunxi.EveningLament.common.Engraving;

import org.yunxi.EveningLament.api.Engraving.Engraving;
import org.yunxi.EveningLament.api.Engraving.EngravingCategory;

public class GluttonyEngraving extends Engraving {
    public GluttonyEngraving() {
        super(new EngravingCategory[]{EngravingCategory.ARMOR_HEAD}, Engraving.Grade.ThirdGrade, "gluttony");
    }
}
