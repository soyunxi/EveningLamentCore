package org.yunxi.EveningLament.api.Imprint;

import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SoulImprint {
    private final ImprintItem oneImprint;

    private final ImprintItem twoImprint;

    private final ImprintItem threeImprint;

    private final Component oneEffect;

    private final Component twoEffect;

    private final Component threeEffect;

    private final Component tipComponent;

    private final int activation;

    private final boolean hasActionSkills;

    public SoulImprint(ImprintItem oneImprint, ImprintItem twoImprint, ImprintItem threeImprint, Component oneEffect, Component twoEffect, Component threeEffect, Component tipComponent, int activation, boolean hasActionSkills) {
        this.oneImprint = oneImprint;
        this.twoImprint = twoImprint;
        this.threeImprint = threeImprint;
        this.oneEffect = oneEffect;
        this.twoEffect = twoEffect;
        this.threeEffect = threeEffect;
        this.tipComponent = tipComponent;
        this.activation = activation;
        this.hasActionSkills = hasActionSkills;
    }

    public SoulImprint(ImprintItem oneImprint, ImprintItem twoImprint, Component oneEffect, Component twoEffect, Component tipComponent, int activation, boolean hasActionSkills) {
        this.oneImprint = oneImprint;
        this.twoImprint = twoImprint;
        this.threeImprint = null;
        this.oneEffect = oneEffect;
        this.twoEffect = twoEffect;
        this.threeEffect = null;
        this.tipComponent = tipComponent;
        this.activation = activation;
        this.hasActionSkills = hasActionSkills;
    }

    public SoulImprint(ImprintItem oneImprint, Component oneEffect, Component tipComponent, int activation, boolean hasActionSkills) {
        this.oneImprint = oneImprint;
        this.twoImprint = null;
        this.threeImprint = null;
        this.oneEffect = oneEffect;
        this.twoEffect = null;
        this.threeEffect = null;
        this.tipComponent = tipComponent;
        this.activation = activation;
        this.hasActionSkills = hasActionSkills;
    }

    public boolean hasActionSkills() {
        return hasActionSkills;
    }

    public List<ImprintItem> getImprintList() {
        List<ImprintItem> list = new ArrayList<>();
        if (this.oneImprint != null) list.add(this.oneImprint);
        if (this.twoImprint != null) list.add(this.twoImprint);
        if (this.threeImprint != null) list.add(this.threeImprint);
        return list;
    }

    public int getEffectSize() {
        return getEffectList().size();
    }

    public List<Component> getEffectList() {
        List<Component> list = new ArrayList<>();
        if (this.oneEffect != null) list.add(this.oneEffect);
        if (this.twoEffect != null) list.add(this.twoEffect);
        if (this.threeEffect != null) list.add(this.threeEffect);
        return list;
    }

    public Component getTipComponent() {
        return tipComponent;
    }

    public int getActivation() {
        return Math.min(getEffectList().size(), activation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoulImprint that = (SoulImprint) o;
        return hasActionSkills == that.hasActionSkills && Objects.equals(oneImprint, that.oneImprint) && Objects.equals(twoImprint, that.twoImprint) && Objects.equals(threeImprint, that.threeImprint) && Objects.equals(oneEffect, that.oneEffect) && Objects.equals(twoEffect, that.twoEffect) && Objects.equals(threeEffect, that.threeEffect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oneImprint, twoImprint, threeImprint, oneEffect, twoEffect, threeEffect, hasActionSkills);
    }
}
