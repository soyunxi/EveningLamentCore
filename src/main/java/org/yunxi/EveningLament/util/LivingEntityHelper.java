package org.yunxi.EveningLament.util;

import com.github.alexthe666.iceandfire.event.ServerEvents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class LivingEntityHelper {
    private LivingEntityHelper() {}

    private static Set<LivingEntity> attackedEntities = new HashSet<>();

    public static void chainLightningWithParticles(Level level, LivingEntity source, LivingEntity target, int maxJumps, double range, float damage, float attenuation, boolean canLightning) {
        if (maxJumps <= 0) {
            attackedEntities.clear();
            return;
        } // 达到最大跳跃次数，结束递归

        // 搜索范围内最近的目标
        LivingEntity nearestEntity = findNearestEntityInRange(target, source, range);
        if (nearestEntity == null) {
            attackedEntities.clear();
            chainLightningWithParticles(level, source, target, maxJumps - 1, range, damage, attenuation, canLightning);
        }

        // 对目标施加伤害
        nearestEntity.hurt(target.level().damageSources().magic(), damage * (1 - (attenuation / 100)));

        // 生成粒子效果模拟闪电
        spawnLightningParticles(level, target.position(), nearestEntity.position());

        chainLightningWithParticles(level, source, nearestEntity, maxJumps - 1, range, damage * (1 - attenuation / 100), attenuation, canLightning); // 递归处理下一个目标
        if (canLightning){
            LightningBolt lightningboltentity = EntityType.LIGHTNING_BOLT.create(nearestEntity.level());
            lightningboltentity.getTags().add(ServerEvents.BOLT_DONT_DESTROY_LOOT);
            lightningboltentity.getTags().add(source.getStringUUID());
            lightningboltentity.moveTo(nearestEntity.position());
            if (!nearestEntity.level().isClientSide) {
                nearestEntity.level().addFreshEntity(lightningboltentity);
            }
        }
        attackedEntities.add(nearestEntity);
    }


    //检索最近的生物
    public static LivingEntity findNearestEntityInRange(LivingEntity source, LivingEntity player, double range) {
        List<LivingEntity> entities = source.level().getEntitiesOfClass(LivingEntity.class, source.getBoundingBox().inflate(range));
        entities.removeIf(entity -> entity instanceof Player || entity == source || !(entity instanceof Enemy) || entity == player || attackedEntities.contains(entity));
        LivingEntity nearestEntity = null;
        double min = Double.MAX_VALUE;
        for (LivingEntity entity : entities) {
            double v = source.distanceToSqr(entity);
            if (v < min) {
                min = v;
                nearestEntity = entity;
            }
        }
        return nearestEntity;
    }


    private static void spawnLightningParticles(Level level, Vec3 start, Vec3 end) {
        int particleCount = 50; // 粒子数量
        for (int i = 0; i <= particleCount; i++) {
            double t = i / (double) particleCount; // 插值比例
            double x = Mth.lerp(t, start.x, end.x);
            double y = Mth.lerp(t, start.y, end.y);
            double z = Mth.lerp(t, start.z, end.z);

            Vec3 velocity = calculateVelocity(start, end, 1);

            // 自定义粒子类型
            level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x, y, z, 0, 0, 0); // 示例粒子
        }
    }

    public static Vec3 calculateVelocity(Vec3 start, Vec3 end, double timeInSeconds) {
        double velocityX = (end.x - start.x) / timeInSeconds;
        double velocityY = (end.y - start.y) / timeInSeconds;
        double velocityZ = (end.z - start.z) / timeInSeconds;
        return new Vec3(velocityX, velocityY, velocityZ);
    }
}
