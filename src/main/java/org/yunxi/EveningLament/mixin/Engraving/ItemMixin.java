package org.yunxi.EveningLament.mixin.Engraving;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.yunxi.EveningLament.util.EngravingHelper;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(at = @At("HEAD"), method = "isFoil", cancellable = true)
    private void isFoil(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (!EngravingHelper.getEngravings(stack).isEmpty()) {
            cir.setReturnValue(true);
        }
    }
}
