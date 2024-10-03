package org.yunxi.EveningLament.mixin.BlueSkies;


import com.legacy.blue_skies.asm_hooks.LivingEntityHooks;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = LivingEntityHooks.class, remap = false)
public class LivingEntityHooksMixin {
    @Inject(at = @At("HEAD"), method = "getArmorValue", cancellable = true)
    private static void getArmorValue(int original, LivingEntity entity, CallbackInfoReturnable<Integer> cir) {
        if (entity instanceof Player player) {
            cir.setReturnValue(original);
        }
    }
}
