package org.yunxi.EveningLament.mixin.Engraving;

import com.teammetallurgy.aquaculture.entity.AquaFishingBobberEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.yunxi.EveningLament.common.Engraving.EngravingRegister;
import org.yunxi.EveningLament.common.Engraving.WorldLibraryEngraving;
import org.yunxi.EveningLament.util.EngravingHelper;

import java.util.List;

@Mixin(value = AquaFishingBobberEntity.class, remap = false)
public class AquaFishingBobberEntityMixin {
    @Shadow @Final private ItemStack fishingRod;

    @Inject(method = "spawnLoot", at = @At(value = "HEAD"))
    private void onspawnLoot(Player angler, List<ItemStack> lootEntries, CallbackInfo ci) {
        ItemStack fishingRodItem = this.fishingRod;

        if (EngravingHelper.hasEngraving(fishingRodItem, EngravingRegister.WORLD_LIBRARY.get())) {
            /*List<Enchantment> enchantments = new ArrayList<>();
            for (Enchantment enchantment : ForgeRegistries.ENCHANTMENTS) {
                if (enchantment.isAllowedOnBooks() && enchantment.isDiscoverable()) enchantments.add(enchantment);
            }
            Random r = new Random();
            Map<Enchantment, Integer> enchantmentMap = new HashMap<>();
            Enchantment enchantment = enchantments.get(r.nextInt(enchantments.size()));
            enchantmentMap.put(enchantment, 1);
            ItemStack out = new ItemStack(Items.ENCHANTED_BOOK);
            EnchantmentHelper.setEnchantments(enchantmentMap, out);*/
            ItemStack out = WorldLibraryEngraving.getWorldLibraryOutPut(angler);
            lootEntries.clear();
            lootEntries.add(out);
            fishingRodItem.hurtAndBreak(5, angler, player -> player.broadcastBreakEvent(player.getUsedItemHand()));
        }
    }
}
