package org.yunxi.EveningLament.common.Engraving;

import org.yunxi.EveningLament.api.Engraving.Engraving;
import org.yunxi.EveningLament.api.Engraving.EngravingCategory;

public class WorldLibrary extends Engraving {
    public WorldLibrary() {
        super(new EngravingCategory[]{EngravingCategory.FISHING_ROD}, Engraving.Grade.FifthGrade, "world_library");
    }
}
