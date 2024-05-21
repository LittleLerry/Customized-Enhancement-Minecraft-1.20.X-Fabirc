package com.example.datagen;

import com.example.block.EnhancementBlocks;
import com.example.block.custom.EnhancementTableBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(EnhancementBlocks.MAGIC_CRYSTAL_BLOCK)
                .add(EnhancementBlocks.MAGIC_CRYSTAL_ORE)
                .add(EnhancementBlocks.CRYSTAL_CRAFTING_TABLE)
                .add(EnhancementBlocks.ENHANCEMENT_TABLE_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(EnhancementBlocks.MAGIC_CRYSTAL_BLOCK)
                .add(EnhancementBlocks.MAGIC_CRYSTAL_ORE)
                .add(EnhancementBlocks.CRYSTAL_CRAFTING_TABLE)
                .add(EnhancementBlocks.ENHANCEMENT_TABLE_BLOCK);

    }
}
