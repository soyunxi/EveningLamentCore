package org.yunxi.EveningLament.common.Engraving;

import org.yunxi.EveningLament.api.Engraving.Engraving;
import org.yunxi.EveningLament.api.Engraving.EngravingCategory;

public class WorldFPSEngraving extends Engraving {
    public WorldFPSEngraving() {
        super(new EngravingCategory[]{EngravingCategory.WEAPON, EngravingCategory.ARMOR}, Grade.ThirdGrade, "world_fps");
    }
}
