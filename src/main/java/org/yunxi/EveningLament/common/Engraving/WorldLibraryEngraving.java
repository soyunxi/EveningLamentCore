package org.yunxi.EveningLament.common.Engraving;

import org.yunxi.EveningLament.api.Engraving.Engraving;
import org.yunxi.EveningLament.api.Engraving.EngravingCategory;

public class WorldLibraryEngraving extends Engraving {
    public WorldLibraryEngraving() {
        super(new EngravingCategory[]{EngravingCategory.FISHING_ROD}, Engraving.Grade.FifthGrade, "world_library");
    }
}
