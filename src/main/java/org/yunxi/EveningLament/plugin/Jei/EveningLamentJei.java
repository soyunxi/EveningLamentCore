package org.yunxi.EveningLament.plugin.Jei;


import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.RegistryObject;
import org.yunxi.EveningLament.Eveninglament;
import org.yunxi.EveningLament.api.Engraving.Engraving;
import org.yunxi.EveningLament.common.Engraving.EngravingRegister;
import org.yunxi.EveningLament.common.items.EngravingItem;
import org.yunxi.EveningLament.common.items.ItemRegister;
import org.yunxi.EveningLament.util.EngravingHelper;

import java.util.List;
import java.util.Map;

@JeiPlugin
public class EveningLamentJei implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Eveninglament.MODID + "jei");
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.useNbtForSubtypes(ItemRegister.FLOURISHING_BLOSSOM_ENGRAVING.get());
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<ItemStack> engravings = EngravingItem.getEngravings();
        for (ItemStack engravingItem : engravings){
            Map<Engraving, Integer> map = EngravingHelper.getEngravings(engravingItem);
            for(Engraving engraving: map.keySet()) {
                for (RegistryObject<Engraving> entry : EngravingRegister.ENGRAVINGS.getEntries()) {
                    if (engraving.equals(entry.get())) {
                        MutableComponent Grade = Component.translatable("name.engraving.grade." + engraving.getGrade().getGradeName()).append(" ").append("(").append(Integer.toString(engraving.getGrade().getGradeLevel())).append(")");
                        HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_ITEM,
                                new HoverEvent.ItemStackInfo(new ItemStack(Items.DIAMOND_AXE)));
                        MutableComponent mutableComponent = Component.translatable("jei.info." + Eveninglament.MODID + ".engraving.desc").withStyle(style -> style.withHoverEvent(hoverEvent));
                        registration.addIngredientInfo(engravingItem, VanillaTypes.ITEM_STACK,
                                Component.translatable("jei.info." + Eveninglament.MODID + ".engraving"),
                                Component.translatable("engraving." + (entry.getId().getNamespace() + "." + entry.getId().getPath())).withStyle(ChatFormatting.BLACK),
                                Component.translatable("jei.info." + Eveninglament.MODID + ".engraving.level", map.get(engraving)),
                                Grade,
                                Component.translatable("engraving." + (entry.getId().getNamespace() + "." + entry.getId().getPath() + ".desc")),
                                Component.translatable("jei.info." + Eveninglament.MODID + ".engraving.deplete", map.get(engraving) * 2 * engraving.getGrade().getGradeLevel()));


                    }
                }
            }
        }
    }
}
