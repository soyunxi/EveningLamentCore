package org.yunxi.EveningLament.mixin.Engraving;

import com.teammetallurgy.aquaculture.entity.AquaFishingBobberEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.yunxi.EveningLament.common.Engraving.EngravingRegister;
import org.yunxi.EveningLament.util.EngravingHelper;

import java.util.*;

@Mixin(value = AquaFishingBobberEntity.class, remap = false)
public class AquaFishingBobberEntityMixin {
    @Shadow @Final private ItemStack fishingRod;

    @Inject(method = "retrieve", at = @At(value = "INVOKE", target = "Lcom/teammetallurgy/aquaculture/entity/AquaFishingBobberEntity;getLoot(Lnet/minecraft/world/level/storage/loot/LootParams;Lnet/minecraft/server/level/ServerLevel;)Ljava/util/List;"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void retrieve(ItemStack stack, CallbackInfoReturnable<Integer> cir, boolean isAdminRod, Player angler, Level level, int rodDamage, ItemFishedEvent event, ServerLevel serverLevel, LootParams lootParams) {
        ItemStack fishingRodItem = this.fishingRod;

        if (EngravingHelper.hasEngraving(fishingRodItem, EngravingRegister.WORLD_LIBRARY.get())) {
            List<Enchantment> enchantments = new ArrayList<>();
            for (Enchantment enchantment : ForgeRegistries.ENCHANTMENTS) {
                if (enchantment.isAllowedOnBooks() && enchantment.isDiscoverable()) enchantments.add(enchantment);
            }
            Random r = new Random();
            Map<Enchantment, Integer> enchantmentMap = new HashMap<>();
            Enchantment enchantment = enchantments.get(r.nextInt(enchantments.size()));
            enchantmentMap.put(enchantment, 1);
            ItemStack out = new ItemStack(Items.ENCHANTED_BOOK);
            EnchantmentHelper.setEnchantments(enchantmentMap, out);

        }
    }
}
