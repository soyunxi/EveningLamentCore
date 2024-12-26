package org.yunxi.EveningLament.api.Item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

public class ItemTooltipComponent implements TooltipComponent {
    private final ResourceLocation texture;

    public ItemTooltipComponent(ResourceLocation texture) {
        this.texture = texture;
    }

    public ResourceLocation getTexture() {
        return texture;
    }
}
