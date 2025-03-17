package org.yunxi.EveningLament.common.Blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.yunxi.EveningLament.Eveninglament;
import org.yunxi.EveningLament.common.items.ItemRegister;

import java.util.List;

public class BlockRegister {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Eveninglament.MODID);

    public static final RegistryObject<Block> RED_OBSIDIAN =
            BLOCKS.register("red_obsidian", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
                    .mapColor(MapColor.COLOR_RED)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(50f, 1200f)
                    .lightLevel(state -> 10)
                    .pushReaction(PushReaction.BLOCK)
                    ){
                @Override
                public List<ItemStack> getDrops(BlockState p_287732_, LootParams.Builder p_287596_) {
                    return List.of(new ItemStack(this));
                }});
    public static final RegistryObject<Item> RED_OBSIDIAN_ITEM =
            ItemRegister.ITEMS.register("red_obsidian", () -> new BlockItem(RED_OBSIDIAN.get(), new Item.Properties()));


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
