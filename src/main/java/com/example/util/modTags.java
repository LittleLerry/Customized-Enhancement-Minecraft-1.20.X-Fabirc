package com.example.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import static com.example.Customized_enhancement.MOD_ID;

public class modTags {

    public static class Blocks{
        private static TagKey<Block> registerTag(String name){
            return TagKey.of(RegistryKeys.BLOCK,new Identifier(MOD_ID,name));
        }
        // use following:
        // public static final TagKey<Block> CRYSTAL_BLOCKS = registerTag("crystal_blocks");
    }

    public static class Items{
        private static TagKey<Item> registerTag(String name){
            return TagKey.of(RegistryKeys.ITEM,new Identifier(MOD_ID,name));
        }
    }
}
