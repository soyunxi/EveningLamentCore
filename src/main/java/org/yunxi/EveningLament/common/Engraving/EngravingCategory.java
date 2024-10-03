package org.yunxi.EveningLament.common.Engraving;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.yunxi.EveningLament.Eveninglament;
import org.yunxi.EveningLament.api.Engraving.Engraving;

public class EngravingCategory implements IRecipeCategory<Engraving> {
    @Override
    public @NotNull RecipeType<Engraving> getRecipeType() {
        return new RecipeType<>(new ResourceLocation(Eveninglament.MODID, "engraving"), Engraving.class);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei." + Eveninglament.MODID + ".engraving");
    }

    @Override
    public IDrawable getBackground() {
        return null;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return null;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder iRecipeLayoutBuilder, Engraving engraving, IFocusGroup iFocusGroup) {

    }
}
