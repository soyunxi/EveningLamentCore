package org.yunxi.EveningLament.mixin.Engraving;


import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.yunxi.EveningLament.common.Engraving.EngravingRegister;
import org.yunxi.EveningLament.common.Engraving.WorldLibraryEngraving;
import org.yunxi.EveningLament.util.EngravingHelper;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(FishingHook.class)
public abstract class FishingHookMixin {
    @Shadow @Nullable public abstract Player getPlayerOwner();

    @Inject(method = "retrieve", at = @At(value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void onRetrieve(ItemStack p_37157_, CallbackInfoReturnable<Integer> cir, Player player, int i, ItemFishedEvent event, LootParams lootparams, LootTable loottable, List list) {
        Player entity = this.getPlayerOwner();
        ItemStack mainHandItem = null;
        if (entity != null) {
            mainHandItem = entity.getMainHandItem();
        }
        ItemStack offhandItem = null;
        if (entity != null) {
            offhandItem = entity.getOffhandItem();
        }
        ItemStack fishingRodItem = null;
        if (mainHandItem != null && mainHandItem.getItem() instanceof FishingRodItem && EngravingHelper.hasEngraving(mainHandItem, EngravingRegister.WORLD_LIBRARY.get())) {
            fishingRodItem = mainHandItem;
        }
        if (fishingRodItem != null && offhandItem.getItem() instanceof FishingRodItem && EngravingHelper.hasEngraving(offhandItem, EngravingRegister.WORLD_LIBRARY.get())) {
            fishingRodItem = offhandItem;
        }

        if (fishingRodItem == null) return;

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
            ItemStack out = WorldLibraryEngraving.getWorldLibraryOutPut(player);
            list.clear();
            list.add(out);
//            list.add(new ItemStack(Items.DIAMOND_AXE));
            fishingRodItem.hurtAndBreak(5, player, p -> p.broadcastBreakEvent(p.getUsedItemHand()));
        }
    }
}
