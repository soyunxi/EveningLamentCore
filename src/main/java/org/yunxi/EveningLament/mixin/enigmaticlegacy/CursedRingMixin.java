package org.yunxi.EveningLament.mixin.enigmaticlegacy;


import com.aizistral.enigmaticlegacy.items.CursedRing;
import com.aizistral.enigmaticlegacy.items.generic.ItemBaseCurio;
import com.google.common.collect.Multimap;
import dev.xkmc.curseofpandora.init.registrate.CoPAttrs;
import dev.xkmc.l2screentracker.screen.source.PlayerSlot;
import dev.xkmc.pandora.content.base.IPandoraHolder;
import dev.xkmc.pandora.content.menu.edit.PandoraEditPvd;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

@Mixin(value = CursedRing.class, remap = false)
public class CursedRingMixin extends ItemBaseCurio/* implements IPandoraHolder*/ {
    @Inject(method = "getAttributeModifiers", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Multimap;put(Ljava/lang/Object;Ljava/lang/Object;)Z"), locals = LocalCapture.CAPTURE_FAILSOFT)
    public void getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack, CallbackInfoReturnable<Multimap<Attribute, AttributeModifier>> cir, Multimap atrributeMap) {
        atrributeMap.put(CoPAttrs.REALITY.get(), new AttributeModifier(UUID.fromString("5d1ff8cd-4936-41e0-b321-a3c940f9cfc9"), "cursed_reality", 7, AttributeModifier.Operation.ADDITION));
    }

    /*@Override
    public int getSlots(ItemStack itemStack) {
        return 18;
    }

    @Override
    public void open(ServerPlayer serverPlayer, PlayerSlot<?> playerSlot, ItemStack itemStack) {
        (new PandoraEditPvd(serverPlayer, playerSlot, itemStack)).open();
    }*/


}
