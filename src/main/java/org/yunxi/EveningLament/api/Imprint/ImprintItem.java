package org.yunxi.EveningLament.api.Imprint;

import com.aizistral.enigmaticlegacy.handlers.SuperpositionHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.yunxi.EveningLament.util.SoulImprintHelper;
import top.theillusivec4.curios.api.SlotContext;

import java.util.*;

public abstract class ImprintItem extends Item implements IImprint {
    public ImprintItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        SoulImprint soulImprint = SoulImprintHelper.getSoulImprint(this);
        LocalPlayer player = Minecraft.getInstance().player;
        if (soulImprint != null && player != null) {
            int takeEffect = SoulImprintHelper.getTakeEffectLevel(soulImprint);
            MutableComponent tipMutableComponent = soulImprint.getTipMutableComponent();
            components.add(tipMutableComponent);
            LinkedHashMap<MutableComponent, Boolean> effects = soulImprint.getEffects();
            ChatFormatting[] activatedColors = {ChatFormatting.GREEN, ChatFormatting.AQUA, ChatFormatting.LIGHT_PURPLE};
            ChatFormatting not = ChatFormatting.DARK_GRAY;
            ChatFormatting has = ChatFormatting.GREEN;

            int temp = 0;
            for (Map.Entry<MutableComponent, Boolean> entry : effects.entrySet()) {
                ChatFormatting activatedColor = activatedColors[temp + 3 - soulImprint.getEffectSize()];
                MutableComponent append = Component.nullToEmpty("[").copy()
                        .append(Component.translatable("imprint.actions.name"))
                        .append("]")
                        .append(" ")
                        .append(entry.getKey());
                if (temp < takeEffect) {
                    if (entry.getValue()) {
                        components.add(append.setStyle(Style.EMPTY.withColor(activatedColor)));
                    } else
                        components.add(entry.getKey().setStyle(Style.EMPTY.withColor(activatedColor)));
                } else {
                    if (entry.getValue()) {
                        components.add(append.setStyle(Style.EMPTY.withColor(not)));
                    } else {
                        components.add(entry.getKey().setStyle(Style.EMPTY.withColor(not)));
                    }
                }
                temp++;
            }

            MutableComponent translatable = Component.translatable("imprint.contain.all").append(":");
            List<ImprintItem> imprintItems = new ArrayList<>(List.of(soulImprint.getImprintItems()));
            imprintItems.remove(this);
            if (SuperpositionHandler.hasCurio(player, this))
            translatable.append(Component.nullToEmpty("[").copy().append(this.getDescription()).append("]").setStyle(Style.EMPTY.withColor(has)));
            else if (SoulImprintHelper.getPLayerHasTakeEffect(soulImprint) < Math.min(soulImprint.getImprintItems().length, 3)){
                translatable.append(Component.nullToEmpty("[").copy().append(this.getDescription()).append("]").setStyle(Style.EMPTY.withColor(not)));
            }

            List<ImprintItem> copy = new ArrayList<>(imprintItems);
            imprintItems.removeIf(imprintItem -> !SuperpositionHandler.hasCurio(player, imprintItem));
            copy.removeIf(imprintItem -> SuperpositionHandler.hasCurio(player, imprintItem));
            imprintItems.addAll(copy);

            int k = Math.min(soulImprint.getImprintItems().length, 3) <= SoulImprintHelper.getPLayerHasTakeEffect(soulImprint) ? Math.min(imprintItems.size(), 2) : imprintItems.size();
            for (int i = 0; i < k; i++) {
                if (i == 0) translatable.append(",");
                if (SuperpositionHandler.hasCurio(player, imprintItems.get(i))) {
                    translatable.append(Component.nullToEmpty("[").copy().append(imprintItems.get(i).getDescription()).append("]").setStyle(Style.EMPTY.withColor(has)));
                } else {
                    translatable.append(Component.nullToEmpty("[").copy().append(imprintItems.get(i).getDescription()).append("]").setStyle(Style.EMPTY.withColor(not)));
                }
                if (i != imprintItems.size() - 1) {
                    translatable.append(",");
                }
            }


            components.add(translatable);

        }
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            return !SuperpositionHandler.hasCurio(player, this);
        }
       return true;
    }
}
