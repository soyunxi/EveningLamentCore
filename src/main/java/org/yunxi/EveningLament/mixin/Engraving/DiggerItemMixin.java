package org.yunxi.EveningLament.mixin.Engraving;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.TierSortingRegistry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.yunxi.EveningLament.common.Engraving.EngravingRegister;
import org.yunxi.EveningLament.util.EngravingHelper;

@Mixin(DiggerItem.class)
public class DiggerItemMixin {
    @Shadow @Final protected float speed;

    @Inject(method = "getDestroySpeed", at = @At("HEAD"), cancellable = true)
    private void getDestroySpeed(ItemStack p_41004_, BlockState p_41005_, CallbackInfoReturnable<Float> cir){
        if (p_41004_.getItem() instanceof PickaxeItem pickaxeItem &&EngravingHelper.hasEngraving(p_41004_, EngravingRegister.SPOON.get())) {
            if (p_41005_.is(BlockTags.MINEABLE_WITH_SHOVEL) || p_41005_.is(BlockTags.MINEABLE_WITH_HOE) || p_41005_.is(BlockTags.MINEABLE_WITH_AXE)) cir.setReturnValue(this.speed);
        }
    }

    @Inject(method = "isCorrectToolForDrops(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/block/state/BlockState;)Z", remap = false, at = @At("HEAD"), cancellable = true)
    private void isCorrectToolForDrops(ItemStack stack, BlockState p_41007_, CallbackInfoReturnable<Boolean> cir){
        if (stack.getItem() instanceof PickaxeItem pickaxeItem &&EngravingHelper.hasEngraving(stack, EngravingRegister.SPOON.get())) {
            if (p_41007_.is(BlockTags.MINEABLE_WITH_SHOVEL) || p_41007_.is(BlockTags.MINEABLE_WITH_HOE) || p_41007_.is(BlockTags.MINEABLE_WITH_AXE) || p_41007_.is(BlockTags.MINEABLE_WITH_PICKAXE)) cir.setReturnValue(TierSortingRegistry.isCorrectTierForDrops(pickaxeItem.getTier(), p_41007_));
        }
    }
}
