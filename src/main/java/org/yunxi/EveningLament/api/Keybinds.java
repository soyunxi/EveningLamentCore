package org.yunxi.EveningLament.api;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;


public class Keybinds {
    private static final String CATEGORY = "key.eveninglament.category";

    private static final String SOUL_IMPRINT_KEY_NAME = "key.eveninglament.soul_imprint";


    public static KeyMapping SoulImprintKey = new KeyMapping(SOUL_IMPRINT_KEY_NAME, KeyConflictContext.IN_GAME, KeyModifier.NONE, InputConstants.Type.KEYSYM, InputConstants.KEY_H, CATEGORY);

}
