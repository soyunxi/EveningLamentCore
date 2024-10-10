package org.yunxi.EveningLament.api.Imprint;

import net.minecraft.network.chat.Component;

public class SoulImprint {
    private final IImprint oneImprint;

    private final IImprint twoImprint;

    private final IImprint threeImprint;

    private final Component oneEffect;

    private final Component twoEffect;

    private final Component threeEffect;

    public final int maxEffect;

    public SoulImprint(IImprint oneImprint, IImprint twoImprint, IImprint threeImprint, Component oneEffect, Component twoEffect, Component threeEffect, int maxEffect) {
        this.oneImprint = oneImprint;
        this.twoImprint = twoImprint;
        this.threeImprint = threeImprint;
        this.oneEffect = oneEffect;
        this.twoEffect = twoEffect;
        this.threeEffect = threeEffect;
        this.maxEffect = maxEffect;
    }

    public IImprint getOneImprint() {
        return oneImprint;
    }

    public IImprint getTwoImprint() {
        return twoImprint;
    }

    public IImprint getThreeImprint() {
        return threeImprint;
    }

    public Component getTwoEffect() {
        return twoEffect;
    }

    public Component getOneEffect() {
        return oneEffect;
    }

    public Component getThreeEffect() {
        return threeEffect;
    }
}
