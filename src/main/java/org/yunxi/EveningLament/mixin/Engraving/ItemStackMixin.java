package org.yunxi.EveningLament.mixin.Engraving;


import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.KeybindContents;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.yunxi.EveningLament.Eveninglament;
import org.yunxi.EveningLament.api.Engraving.Engraving;
import org.yunxi.EveningLament.common.Engraving.EngravingRegister;
import org.yunxi.EveningLament.common.items.ItemRegister;
import org.yunxi.EveningLament.util.EngravingHelper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow public abstract ItemStack copy();

    @Inject(method = "getTooltipLines", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;appendEnchantmentNames(Ljava/util/List;Lnet/minecraft/nbt/ListTag;)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void EngravingTooltipLines(Player p_41652_, TooltipFlag p_41653_, CallbackInfoReturnable<List<Component>> cir, List list, MutableComponent mutablecomponent, int j) {
        ItemStack copy = this.copy();
        Map<Engraving, Integer> engravings = EngravingHelper.getEngravings(copy);
        if (!engravings.isEmpty()) {
            list.add(Component.translatable("engraving.tooltip"));
            if (!copy.getItem().equals(ItemRegister.FLOURISHING_BLOSSOM_ENGRAVING.get())){
                list.add(Component.translatable("eveninglament.tooltip.engraving.grade_level", EngravingHelper.getGradeLevel(copy)));
            }
            for (Engraving engraving : engravings.keySet()) {

                MutableComponent translatable = Component.translatable("name.engraving.grade." + engraving.getGrade().getGradeName());
                for (RegistryObject<Engraving> entry : EngravingRegister.ENGRAVINGS.getEntries()) {
                    if (entry.get().equals(engraving)) {
                        int level = engravings.get(engraving);
                        MutableComponent engravingName = Component.translatable("engraving." + entry.getId().getNamespace() + "." + entry.getId().getPath());
                        for (int i = 0; i < level - 1; i++){
                            engravingName.append(Component.translatable("eveninglament.tooltip.engraving.level"));
                        }
                        engravingName.append(Component.translatable("eveninglament.tooltip.space"))
                                .append("(").append(translatable).append(")");
                        list.add(engravingName);
                        if (copy.getItem().equals(ItemRegister.FLOURISHING_BLOSSOM_ENGRAVING.get()) && engravings.size() == 1) {
                            if (!Screen.hasShiftDown()) {
                                list.add(Component.translatable("tooltip." + Eveninglament.MODID + ".engraving.holdShift"));
                            }
                        }
                        if (Screen.hasShiftDown()) {
                            list.add(Component.translatable("engraving." + (entry.getId().getNamespace() + "." + entry.getId().getPath() + ".desc")).withStyle(ChatFormatting.DARK_GRAY));
                        }
                    }
                }
            }
        }
    }
}
