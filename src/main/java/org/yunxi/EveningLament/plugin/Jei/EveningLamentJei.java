package org.yunxi.EveningLament.plugin.Jei;


import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.ResourceLocation;
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
}
