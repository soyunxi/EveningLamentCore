package org.yunxi.EveningLament.api.Imprint;

import net.minecraft.network.chat.MutableComponent;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Objects;

public class SoulImprint {
    private final ImprintItem[] ImprintItems; //灵魂刻印物品

    private final LinkedHashMap<MutableComponent, Boolean> effects; //效果

    private final MutableComponent tipMutableComponent; //灵魂共鸣的名字

    public SoulImprint(ImprintItem[] ImprintItems, LinkedHashMap<MutableComponent, Boolean> effects, MutableComponent tipMutableComponent) {
        this.ImprintItems = ImprintItems;
        if (effects.keySet().size() > ImprintItems.length) {
            this.effects = new LinkedHashMap<>();
            int count = 0;
            for (MutableComponent key : effects.keySet()) {
                if (count++ >= ImprintItems.length) break;
                this.effects.put(key, effects.get(key));
                count++;
            }
        } else this.effects = effects;
        this.tipMutableComponent = tipMutableComponent;
    }

    public boolean hasActionSkills() {
        for (MutableComponent mutableComponent : effects.keySet()) {
            if (effects.get(mutableComponent)) return true;
        }
        return false;
    }

    public int getEffectSize() {
        return this.effects.size();
    }

    public ImprintItem[] getImprintItems() {
        return ImprintItems;
    }

    public LinkedHashMap<MutableComponent, Boolean> getEffects() {
        return effects;
    }

    public MutableComponent getTipMutableComponent() {
        return tipMutableComponent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoulImprint that = (SoulImprint) o;
        return Objects.deepEquals(ImprintItems, that.ImprintItems) && Objects.equals(effects, that.effects) && Objects.equals(tipMutableComponent, that.tipMutableComponent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(ImprintItems), effects, tipMutableComponent);
    }
}
