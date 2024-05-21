package com.example.block;

import com.example.block.custom.EnhancementTableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import static com.example.Customized_enhancement.MOD_ID;

public class EnhancementBlocks {

    public static final Block MAGIC_CRYSTAL_BLOCK = registerBlock("magic_crystal_block",
            new Block(Block.Settings.create().mapColor(MapColor.BLUE).strength(50.0f, 1200.0f).sounds(BlockSoundGroup.AMETHYST_BLOCK)));
 
    public static final Block MAGIC_CRYSTAL_ORE = registerBlock("magic_crystal_ore",
            new Block(Block.Settings.create().mapColor(MapColor.BLUE).strength(50.0f, 1200.0f).sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block CRYSTAL_CRAFTING_TABLE = registerBlock("crystal_crafting_table",
            new Block(Block.Settings.create().mapColor(MapColor.BLUE).strength(3.0f, 45.0f).sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block ENHANCEMENT_TABLE_BLOCK = registerBlock("enhancement_table_block",
            new EnhancementTableBlock(Block.Settings.create().mapColor(MapColor.BLUE).strength(3.0f, 45.0f).sounds(BlockSoundGroup.AMETHYST_BLOCK)));



    private static Block registerBlock(String name,Block block){
        registerBlockItemWithFireproof(name,block);
        return Registry.register(Registries.BLOCK, new Identifier(MOD_ID, name), block);
    }
    private static Item registerBlockItemWithFireproof(String name, Block block){
        return Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, name),
                new BlockItem(block, new Item.Settings().fireproof()));
    }
    public static void registerModBlocks(){

    }
}
