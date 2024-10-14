package org.yunxi.EveningLament.util;

import com.aizistral.enigmaticlegacy.handlers.SuperpositionHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.entity.vehicle.MinecartFurnace;
import net.minecraft.world.item.Item;
import org.yunxi.EveningLament.api.Imprint.IImprint;
import org.yunxi.EveningLament.api.Imprint.ImprintItem;
import org.yunxi.EveningLament.api.Imprint.SoulImprint;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

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
