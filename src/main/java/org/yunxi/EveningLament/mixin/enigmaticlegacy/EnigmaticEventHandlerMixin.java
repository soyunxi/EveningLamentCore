package org.yunxi.EveningLament.mixin.enigmaticlegacy;


import com.aizistral.enigmaticlegacy.handlers.EnigmaticEventHandler;
import com.aizistral.enigmaticlegacy.handlers.SuperpositionHandler;
import com.aizistral.enigmaticlegacy.items.CursedScroll;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = EnigmaticEventHandler.class, remap = false)
public class EnigmaticEventHandlerMixin {
    @Inject(method = "onEntityHurt", at = @At(value = "INVOKE", target = "Lcom/aizistral/enigmaticlegacy/handlers/SuperpositionHandler;getCurseAmount(Lnet/minecraft/world/entity/player/Player;)I", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void onEntityHurt(LivingHurtEvent event, CallbackInfo ci, Player player, Entity immediateSource, float damageBoost) {
        damageBoost -= event.getAmount() * CursedScroll.damageBoost.getValue().asModifier() * (float) SuperpositionHandler.getCurseAmount(player);
    }

    @Redirect(method = "onConfirmedDeath", at = @At(value = "INVOKE", target = "Lcom/aizistral/enigmaticlegacy/handlers/SuperpositionHandler;hasItem(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/Item;)Z", ordinal = 2))
    private boolean onConfirmedDeath(Player itemstack, Item list){
        return false;
    }
}
