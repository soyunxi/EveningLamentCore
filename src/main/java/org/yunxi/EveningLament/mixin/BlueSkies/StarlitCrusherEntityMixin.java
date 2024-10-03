package org.yunxi.EveningLament.mixin.BlueSkies;

import com.legacy.blue_skies.entities.hostile.boss.StarlitCrusherEntity;
import com.legacy.blue_skies.entities.util.SkiesDungeonType;
import com.legacy.blue_skies.entities.util.StarlitCrusherSpinSound;
import com.legacy.blue_skies.entities.util.StunnableLookController;
import com.legacy.blue_skies.entities.util.base.SkiesBossEntity;
import com.legacy.blue_skies.items.LootBagItem;
import com.legacy.blue_skies.registries.SkiesItems;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(value = StarlitCrusherEntity.class, remap = false)
public class StarlitCrusherEntityMixin {

}
