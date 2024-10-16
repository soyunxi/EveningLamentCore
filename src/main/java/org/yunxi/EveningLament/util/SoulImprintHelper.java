package org.yunxi.EveningLament.util;

import com.aizistral.enigmaticlegacy.handlers.SuperpositionHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.yunxi.EveningLament.api.Imprint.ImprintItem;
import org.yunxi.EveningLament.api.Imprint.SoulImprint;

import java.util.List;

public class SoulImprintHelper {
    private SoulImprintHelper() {}

    public static int getTakeEffect(SoulImprint soulImprint) {
        LocalPlayer player = Minecraft.getInstance().player;
        int takeEffect = 0;
        if (player != null){
            List<ImprintItem> imprintList = soulImprint.getImprintList();
            for (ImprintItem imprint : imprintList) {
                if (SuperpositionHandler.hasCurio(player, imprint)) takeEffect++;
            }
        }
        return takeEffect;
    }
}
