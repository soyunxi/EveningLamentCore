package org.yunxi.EveningLament.common.DamageSource;

import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class DamageSources {
    public static final DamageSource VOID_DAMAGE = new DamageSource(Holder.direct(new DamageType("void", DamageScaling.NEVER, 1.0F))) {
        @Override
        public boolean is(TagKey<DamageType> p_270890_) {
            return false;
        }
    };
}
