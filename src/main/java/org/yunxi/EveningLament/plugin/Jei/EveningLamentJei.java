package org.yunxi.EveningLament.plugin.Jei;


import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.yunxi.EveningLament.Eveninglament;
import org.yunxi.EveningLament.common.items.EngravingItem;
import org.yunxi.EveningLament.common.items.ItemRegister;

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
        ItemStack ENGRAVING = new ItemStack(ItemRegister.FLOURISHING_BLOSSOM_ENGRAVING.get());
        registration.addIngredientInfo(ENGRAVING, VanillaTypes.ITEM_STACK,
                Component.translatable("jei.info." + Eveninglament.MODID + ".engraving"));
    }
}
