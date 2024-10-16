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
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import org.yunxi.EveningLament.api.Keybinds;
import org.yunxi.EveningLament.common.SoulImprint.SoulImprintRegister;
import org.yunxi.EveningLament.util.SoulImprintHelper;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public abstract class     ImprintItem extends Item implements IImprint {
    public ImprintItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        for (RegistryObject<SoulImprint> entry : SoulImprintRegister.SOUL_IMPRINTS.getEntries()) {
            if (entry.get().getImprintList().contains(this)) {
                SoulImprint soulImprint = entry.get();
                List<ImprintItem> imprintList = soulImprint.getImprintList();
                List<Component> effectList = soulImprint.getEffectList();
                int takeEffect = SoulImprintHelper.getTakeEffect(soulImprint);
                LocalPlayer player = Minecraft.getInstance().player;
                if (player == null) {
                    return;
                }
                p_41423_.add(Component.translatable("soulimprint." + entry.getId().getNamespace() + "." + entry.getId().getPath()).append(":"));                 if (imprintList.size() == 3) {
                    if (effectList.size() == 3) {
                        for (int i = 0; i < takeEffect; i++) {
                            ChatFormatting color;
                            if (i == 0) color = ChatFormatting.GREEN;
                            else if (i == 1) color = ChatFormatting.DARK_PURPLE;
                            else color = ChatFormatting.RED;
                            p_41423_.add(effectList.get(i).copy().setStyle(Style.EMPTY.withColor(color)));
                        }
                        for (int i = takeEffect; i < 3; i++) {
                            p_41423_.add(effectList.get(i).copy().setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
                        }
                    } else if (effectList.size() == 2) {
                        if (takeEffect > 1) {
                            for (int i = 0; i < takeEffect - 1; i++) {
                                ChatFormatting color;
                                if (i == 0) color = ChatFormatting.DARK_PURPLE;
                                else color = ChatFormatting.RED;
                                p_41423_.add(effectList.get(i).copy().setStyle(Style.EMPTY.withColor(color)));
                            }
                            for (int i = takeEffect - 1; i < 2; i++) {
                                p_41423_.add(effectList.get(i).copy().setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
                            }
                        }
                    } else {
                        if (takeEffect > 2)
                            p_41423_.add(effectList.get(0).copy().setStyle(Style.EMPTY.withColor(ChatFormatting.RED)));
                        else
                            p_41423_.add(effectList.get(0).copy().setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
                    }
                } else if (imprintList.size() == 2) {
                    if (effectList.size() == 2) {
                        for (int i = 0; i < takeEffect; i++) {
                            ChatFormatting color;
                            if (i == 0) color = ChatFormatting.DARK_PURPLE;
                            else color = ChatFormatting.RED;
                            p_41423_.add(effectList.get(i).copy().setStyle(Style.EMPTY.withColor(color)));
                        }
                        for (int i = takeEffect; i < 2; i++) {
                            p_41423_.add(effectList.get(i).copy().setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
                        }
                    } else {
                        if (takeEffect > 1)
                            p_41423_.add(effectList.get(0).copy().setStyle(Style.EMPTY.withColor(ChatFormatting.RED)));
                        else
                            p_41423_.add(effectList.get(0).copy().setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
                    }
                } else {
                    if (takeEffect > 0)
                        p_41423_.add(effectList.get(0).copy().setStyle(Style.EMPTY.withColor(ChatFormatting.RED)));
                    else
                        p_41423_.add(effectList.get(0).copy().setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
                }

                if (entry.get().hasActionSkills()){
                    if (takeEffect >= soulImprint.getActivation()){
                        p_41423_.add(Component.translatable("imprint.take.hasactionskills", Keybinds.SoulImprintKey.getKey().getDisplayName()).setStyle(Style.EMPTY.withColor(ChatFormatting.GOLD)));
                    } else p_41423_.add(Component.translatable("imprint.take.noactionskills", soulImprint.getActivation()).setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
                }

                if (soulImprint.getTipComponent() != null) p_41423_.add(soulImprint.getTipComponent());

                MutableComponent translatable = Component.translatable("imprint.contain.all").append(":");
                for (int i = 0; i < imprintList.size(); i++) {
                    translatable.append("[");
                    ChatFormatting color;
                    if (SuperpositionHandler.hasCurio(player, imprintList.get(i))) color = ChatFormatting.GREEN;
                    else color = ChatFormatting.DARK_GRAY;
                    translatable.append(imprintList.get(i).getDescription().copy().setStyle(Style.EMPTY.withColor(color)));
                    translatable.append("]");
                    if (i != imprintList.size() - 1) {
                        translatable.append(",");
                    }
                }
                p_41423_.add(translatable);


            }
        }

        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (SuperpositionHandler.hasCurio(player, this)) return false;
        }
       return true;
    }
}
