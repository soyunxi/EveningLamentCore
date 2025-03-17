package org.yunxi.EveningLament.common.items.Imprints.Life;

import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.yunxi.EveningLament.api.Imprint.ImprintItem;
import org.yunxi.EveningLament.util.ColorHelper;

import java.awt.*;
import java.util.List;

public class MorningDew extends ImprintItem {
    public MorningDew() {
        super(new Properties().stacksTo(1).fireResistant());
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {

        components.add(ColorHelper.applyWhiteToGold(Component.translatable("tooltip.imprint.life.doce1"), 0, 0));

        components.add(Component.translatable("tooltip.imprint.morning_dew.doce1"));
        components.add(Component.translatable("tooltip.imprint.morning_dew.doce2"));
        components.add(Component.translatable("tooltip.imprint.morning_dew.doce3"));
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }

    @Override
    public boolean hasCurioCapability(ItemStack stack) {
        return super.hasCurioCapability(stack);
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
