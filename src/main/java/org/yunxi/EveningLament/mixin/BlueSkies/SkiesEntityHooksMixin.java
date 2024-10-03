package org.yunxi.EveningLament.mixin.BlueSkies;


import com.legacy.blue_skies.entities.util.SkiesEntityHooks;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = SkiesEntityHooks.class, remap = false)
public class SkiesEntityHooksMixin {
    @Inject(at = @At("HEAD"), method = "nerfDamage", cancellable = true)
    private static void destroyBlock(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(amount);
    }
}
