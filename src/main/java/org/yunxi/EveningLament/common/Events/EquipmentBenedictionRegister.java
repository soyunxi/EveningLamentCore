package org.yunxi.EveningLament.common.Events;


import com.xiaohunao.equipment_benediction.common.event.BenedictionRegisterEvent;
import com.xiaohunao.equipment_benediction.common.modifier.ModifierHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.yunxi.EveningLament.Eveninglament;

public class EquipmentBenedictionRegister {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerBenediction(BenedictionRegisterEvent event) {
        System.out.println("registerBenediction");
        Eveninglament.LOGGER.info("registerBenediction");
        ResourceLocation eveninglamentModifier = new ResourceLocation(Eveninglament.MODID, "eveninglament_modifier");
        event.registerModifier(new ResourceLocation[]{eveninglamentModifier},modifier -> {
            modifier.setViable(s -> true);
            modifier.bonus.addAttributeModifier(Attributes.ATTACK_SPEED, "4f0f0f0f-0f0f-4f0f-0f0f-0f0f0f0f0f0f", 0.5, AttributeModifier.Operation.ADDITION);
        });

        event.registerQuality(new ResourceLocation[]{new ResourceLocation(Eveninglament.MODID, "eveninglament_quality")}, quality -> {
            quality.setAutoAdd(true);
            quality.setViable(s -> true);
            quality.properties(Ingredient.of(Items.GOLD_INGOT), 1, 1, 1);

            quality.addRandomModifier(ModifierHelper.getModifier(eveninglamentModifier));
        });
    }
}
