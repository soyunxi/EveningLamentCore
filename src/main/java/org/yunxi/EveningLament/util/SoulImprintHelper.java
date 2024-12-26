package org.yunxi.EveningLament.util;

import com.aizistral.enigmaticlegacy.handlers.SuperpositionHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.registries.RegistryObject;
import org.yunxi.EveningLament.api.Imprint.ImprintItem;
import org.yunxi.EveningLament.api.Imprint.SoulImprint;
import org.yunxi.EveningLament.common.SoulImprint.SoulImprintRegister;

import java.util.Arrays;

public final class SoulImprintHelper {
    private SoulImprintHelper() {}

    public static int getPLayerHasTakeEffect(SoulImprint soulImprint) {
        LocalPlayer player = Minecraft.getInstance().player;
        int takeEffect = 0;
        if (player != null){
            ImprintItem[] imprintList = soulImprint.getImprintItems();
            for (ImprintItem imprint : imprintList) {
                if (SuperpositionHandler.hasCurio(player, imprint)) takeEffect++;
            }
        }
        return Math.min(takeEffect, soulImprint.getEffectSize());
    }

    public static int getTakeEffectLevel(SoulImprint soulImprint) {
        int pLayerHasTakeEffect = getPLayerHasTakeEffect(soulImprint);
        int effectSize = soulImprint.getEffectSize();
        return switch (effectSize) {
            case 3 -> pLayerHasTakeEffect;
            case 2 -> Math.max(0, pLayerHasTakeEffect - 1);
            case 1 -> Math.max(0, pLayerHasTakeEffect - 2);
            default -> 0;
        };
    }

    public static SoulImprint getSoulImprint(ImprintItem imprintItem) {
        for (RegistryObject<SoulImprint> entry : SoulImprintRegister.SOUL_IMPRINTS.getEntries()) {
            if (Arrays.asList(entry.get().getImprintItems()).contains(imprintItem)) return entry.get();
        }
        return null;
    }
}
