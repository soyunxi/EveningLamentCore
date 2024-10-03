package org.yunxi.EveningLament.mixin.BlueSkies;


import com.legacy.blue_skies.asm_hooks.PlayerHooks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PlayerHooks.class, remap = false)
public class PlayerHooksMixin {
    @Inject(at = @At("HEAD"), method = "modifyBreakSpeed", cancellable = true)
    private static void modifyBreakSpeed(float speed, BlockState state, BlockPos pos, Player player, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(speed);
    }

    @Inject(at = @At("HEAD"), method = "isNerfableTool", cancellable = true)
    private static synchronized void isNerfableTool(ItemStack stack, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
