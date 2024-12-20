package org.yunxi.EveningLament.mixin.Engraving;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.spongepowered.asm.mixin.Mixin;
import org.yunxi.EveningLament.common.Engraving.EngravingRegister;
import org.yunxi.EveningLament.util.EngravingHelper;

@Mixin(PickaxeItem.class)
public class PickaxeItemMixin extends DiggerItem {
    public PickaxeItemMixin(float p_204108_, float p_204109_, Tier p_204110_, Properties p_204112_) {
        super(p_204108_, p_204109_, p_204110_, BlockTags.MINEABLE_WITH_PICKAXE, p_204112_);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        if (EngravingHelper.hasEngraving(stack, EngravingRegister.SPOON.get())) {
            return toolAction == ToolActions.AXE_DIG || toolAction == ToolActions.PICKAXE_DIG || toolAction == ToolActions.SHOVEL_DIG || toolAction == ToolActions.HOE_DIG || toolAction == ToolActions.SWORD_DIG;
        }
        return super.canPerformAction(stack, toolAction);
    }
}
