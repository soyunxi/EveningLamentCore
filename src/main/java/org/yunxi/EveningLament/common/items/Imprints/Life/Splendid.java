package org.yunxi.EveningLament.common.items.Imprints.Life;

import me.shedaniel.rei.impl.client.gui.text.TextTransformations;
import net.minecraft.Util;
import net.minecraft.network.chat.*;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.yunxi.EveningLament.api.Imprint.ImprintItem;
import org.yunxi.EveningLament.util.ColorHelper;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public class Splendid extends ImprintItem {
    public Splendid() {
        super(new Properties().stacksTo(1).fireResistant());
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        int rgb = Color.HSBtoRGB((float)(Util.getMillis() % 2000L) / 2000.0F, 0.8F, 0.95F);
        components.add(ColorHelper.applyRainbow(Component.translatable("tooltip.imprint.life.doce1"), 0, 0));

        components.add(Component.translatable("tooltip.imprint.splendid.doce1"));
        components.add(Component.translatable("tooltip.imprint.splendid.doce2"));
        components.add(Component.translatable("tooltip.imprint.splendid.doce3"));
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }

    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return false;
    }

    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return true;
    }


}
