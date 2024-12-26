package org.yunxi.EveningLament.api.Imprint;

import com.aizistral.enigmaticlegacy.handlers.SuperpositionHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.yunxi.EveningLament.util.SoulImprintHelper;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.Map;

public abstract class ImprintItem extends Item implements IImprint {
    public ImprintItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        SoulImprint soulImprint = SoulImprintHelper.getSoulImprint(this);
        if (soulImprint != null) {
            int takeEffect = SoulImprintHelper.getTakeEffectLevel(soulImprint);
            MutableComponent tipMutableComponent = soulImprint.getTipMutableComponent();
            p_41423_.add(tipMutableComponent);
            MutableComponent action = Component.nullToEmpty("[").copy()
                    .append("imprint.actions.name")
                    .append("]")
                    .append(" ");
            Map<MutableComponent, Boolean> effects = soulImprint.getEffects();
            ChatFormatting[] activatedColors = {ChatFormatting.GREEN, ChatFormatting.DARK_BLUE, ChatFormatting.RED};
            ChatFormatting notActivated = ChatFormatting.DARK_GRAY;

            int temp = 0;
            for (Map.Entry<MutableComponent, Boolean> entry : effects.entrySet()) {
                if (temp < takeEffect){
                    p_41423_.add(Component.nullToEmpty(temp + "," + takeEffect));
                    if (entry.getValue()) {
                        p_41423_.add(action.append(entry.getKey()).withStyle(activatedColors[temp]));
                    } else p_41423_.add(entry.getKey().withStyle(activatedColors[temp]));
                } else {
                    MutableComponent mutableComponent = entry.getKey().withStyle(notActivated);
                    if (entry.getValue()) {
                        p_41423_.add(action.append(mutableComponent));
                    } else {
                        p_41423_.add(mutableComponent);
                    }
                }
                temp++;
            }
        }
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            return !SuperpositionHandler.hasCurio(player, this);
        }
       return true;
    }
}
