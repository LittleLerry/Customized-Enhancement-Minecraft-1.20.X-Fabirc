package com.example.datagen;

import com.example.block.EnhancementBlocks;
import com.example.item.EnhancementItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        blockStateModelGenerator.registerSimpleCubeAll(EnhancementBlocks.MAGIC_CRYSTAL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(EnhancementBlocks.MAGIC_CRYSTAL_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(EnhancementBlocks.CRYSTAL_CRAFTING_TABLE);
        blockStateModelGenerator.registerSimpleCubeAll(EnhancementBlocks.ENHANCEMENT_TABLE_BLOCK);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(EnhancementItems.LV4_CLOVER, Models.GENERATED);
        itemModelGenerator.register(EnhancementItems.LV5_CLOVER, Models.GENERATED);
        itemModelGenerator.register(EnhancementItems.LV6_CLOVER, Models.GENERATED);
        itemModelGenerator.register(EnhancementItems.S_CLOVER, Models.GENERATED);
        itemModelGenerator.register(EnhancementItems.SS_CLOVER, Models.GENERATED);
        itemModelGenerator.register(EnhancementItems.SSS_CLOVER, Models.GENERATED);
        itemModelGenerator.register(EnhancementItems.SSR_CLOVER, Models.GENERATED);

        itemModelGenerator.register(EnhancementItems.LUCKY_CLOVER_CHEST, Models.GENERATED);
        itemModelGenerator.register(EnhancementItems.LUCKY_CRYSTAL_CHEST, Models.GENERATED);
        itemModelGenerator.register(EnhancementItems.RARE_CLOVER_CHEST, Models.GENERATED);
        itemModelGenerator.register(EnhancementItems.MAGIC_CRYSTAL, Models.GENERATED);

    }
}
