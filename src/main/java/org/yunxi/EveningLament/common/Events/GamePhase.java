package org.yunxi.EveningLament.common.Events;

import com.cpearl.gamephase.GamePhaseHelper;
import com.sammy.malum.registry.common.block.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.yunxi.EveningLament.Eveninglament;

@Mod.EventBusSubscriber(modid = Eveninglament.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GamePhase {

    public static final String NETHER = "nether";
    public static final String BLOCK_BREAK = "message.eveninglament.gamephase.break";

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockPos pos = event.getPos();
        Player player = event.getPlayer();
        BlockState blockState = player.level().getBlockState(pos);
        if (player instanceof ServerPlayer serverPlayer) {
            if (!player.isCreative()){
                if (!GamePhaseHelper.hasPhase(serverPlayer, NETHER)) {
                    if (blockState.getBlock().equals(BlockRegistry.SOULSTONE_ORE.get()) || blockState.getBlock().equals(BlockRegistry.DEEPSLATE_SOULSTONE_ORE.get())) {
                        if (player.isShiftKeyDown()) {
                            event.setCanceled(true);
                            event.setExpToDrop(0);
                            player.level().destroyBlock(pos, false);
                        } else {
                            event.setCanceled(true);
                            event.setExpToDrop(0);
                            player.displayClientMessage(Component.translatable(BLOCK_BREAK, NETHER), true);

                        }
                    }
                }
            }
        }
    }
}
