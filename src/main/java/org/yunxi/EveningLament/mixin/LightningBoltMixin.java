package org.yunxi.EveningLament.mixin;


import net.minecraft.world.entity.LightningBolt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningBolt.class)
public abstract class LightningBoltMixin{
    @Shadow protected abstract void spawnFire(int p_20871_);

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LightningBolt;spawnFire(I)V", ordinal = 0))
    private void spawnFire(LightningBolt lightningBolt, int i) {
        this.spawnFire(0);
    }

    @Inject(method = "spawnFire", at = @At("HEAD"), cancellable = true)
    private void spawnFire(int i, CallbackInfo ci) {
        ci.cancel();
    }
}
