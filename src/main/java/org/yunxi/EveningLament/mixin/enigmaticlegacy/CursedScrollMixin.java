package org.yunxi.EveningLament.mixin.enigmaticlegacy;


import com.aizistral.enigmaticlegacy.handlers.SuperpositionHandler;
import com.aizistral.enigmaticlegacy.items.CursedScroll;
import com.aizistral.enigmaticlegacy.items.generic.ItemBaseCurio;
import com.aizistral.omniconfig.wrappers.Omniconfig;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

@Mixin(value = CursedScroll.class, remap = false)
public class CursedScrollMixin extends ItemBaseCurio {

    @Shadow public static Omniconfig.PerhapsParameter damageBoost;

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        Multimap<Attribute, AttributeModifier> slot = LinkedHashMultimap.create();
        if (entity instanceof Player player) {
            slot.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("b3b82db0-172e-4c76-975a-5d225bc67090"), "attack_damage", damageBoost.getValue().asModifier() * SuperpositionHandler.getCurseAmount(player), AttributeModifier.Operation.MULTIPLY_TOTAL));

        }
        return super.getAttributeModifiers(slotContext, uuid, stack);
    }
}
