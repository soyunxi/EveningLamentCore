package org.yunxi.EveningLament.common.Events;

import com.cpearl.gamephase.GamePhaseHelper;
import com.cpearl.gamephase.events.GamePhaseUpdateEvent;
import com.sammy.malum.registry.common.block.BlockRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.yunxi.EveningLament.Eveninglament;

import java.util.Collection;
import java.util.List;

@Mod.EventBusSubscriber(modid = Eveninglament.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GamePhase {

    public static final String NETHER = "nether";
    public static final String BLOCK_BREAK = "message.eveninglament.gamephase.break";

    @SubscribeEvent
    // 玩家破坏方块事件
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
                            // 不允许玩家破坏方块
                            event.setCanceled(true);
                            event.setExpToDrop(0);
                            player.displayClientMessage(Component.translatable(BLOCK_BREAK, NETHER), true);

                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void on(EntityTravelToDimensionEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof ServerPlayer serverPlayer) {

        }
    }

    @SubscribeEvent
    public static void  onGamePhaseUpdate(GamePhaseUpdateEvent event) {
        Collection<String> phasesNew = event.getPhasesNew();
        Collection<String> phasesOld = event.getPhasesOld();
        Player player = event.getEntity();
        //玩家添加了游戏阶段
        if (phasesNew.size() > phasesOld.size()) {
            List<String> list = phasesNew.stream().filter(phase -> !phasesOld.contains(phase)).toList();
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new ClientboundSetTitleTextPacket(Component.nullToEmpty(" ")));
                serverPlayer.connection.send(new ClientboundSetSubtitleTextPacket(Component.translatable("message.eveninglament.gamephase.add", list.get(0)).setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_RED))));
                serverPlayer.connection.send(new ClientboundSetTitlesAnimationPacket(20, 60, 20));
            }
        }

    }
}
