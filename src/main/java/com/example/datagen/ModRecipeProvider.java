package com.example.datagen;

import com.example.block.EnhancementBlocks;
import com.example.item.EnhancementItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.item.Items.STONE;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> MAGIC_CRYSTAL_TABLES = List.of(EnhancementBlocks.MAGIC_CRYSTAL_ORE);
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        // burning recipe
        offerSmelting(exporter, MAGIC_CRYSTAL_TABLES, RecipeCategory.MISC,
                EnhancementItems.MAGIC_CRYSTAL,10.0f,200,"magic_crystal");
        offerBlasting(exporter, MAGIC_CRYSTAL_TABLES, RecipeCategory.MISC,
                EnhancementItems.MAGIC_CRYSTAL,10.0f,100,"magic_crystal");

        // block recipe
        offerReversibleCompactingRecipes(exporter,RecipeCategory.BUILDING_BLOCKS,EnhancementItems.MAGIC_CRYSTAL,
                RecipeCategory.DECORATIONS,EnhancementBlocks.MAGIC_CRYSTAL_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,EnhancementBlocks.CRYSTAL_CRAFTING_TABLE,1)
                .pattern("OOO")
                .pattern("OMO")
                .pattern("OOO")
                .input('M',EnhancementItems.MAGIC_CRYSTAL)
                .input('O', Items.OBSIDIAN)
                .criterion(hasItem(EnhancementItems.MAGIC_CRYSTAL),conditionsFromItem(EnhancementItems.MAGIC_CRYSTAL))
                .criterion(hasItem(Items.OBSIDIAN),conditionsFromItem(Items.OBSIDIAN))
                .offerTo(exporter,new Identifier(getRecipeName(EnhancementBlocks.CRYSTAL_CRAFTING_TABLE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,EnhancementBlocks.ENHANCEMENT_TABLE_BLOCK,1)
                .pattern(" B ")
                .pattern("MOM")
                .pattern("OOO")
                .input('B', Items.BOOK)
                .input('M',EnhancementItems.MAGIC_CRYSTAL)
                .input('O', Items.OBSIDIAN)
                .criterion(hasItem(Items.BOOK),conditionsFromItem(Items.BOOK))
                .criterion(hasItem(EnhancementItems.MAGIC_CRYSTAL),conditionsFromItem(EnhancementItems.MAGIC_CRYSTAL))
                .criterion(hasItem(Items.OBSIDIAN),conditionsFromItem(Items.OBSIDIAN))
                .offerTo(exporter,new Identifier(getRecipeName(EnhancementBlocks.ENHANCEMENT_TABLE_BLOCK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,Items.DIAMOND,2)
                .pattern("M")
                .input('M',EnhancementItems.MAGIC_CRYSTAL)
                .criterion(hasItem(EnhancementItems.MAGIC_CRYSTAL),conditionsFromItem(EnhancementItems.MAGIC_CRYSTAL))
                .offerTo(exporter,new Identifier(getRecipeName(EnhancementItems.MAGIC_CRYSTAL)+"_to_diamonds"));

        // clover recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,EnhancementItems.LV5_CLOVER,1)
                .pattern("XX")
                .pattern("XX")
                .input('X',EnhancementItems.LV4_CLOVER)
                .criterion(hasItem(EnhancementItems.LV4_CLOVER),conditionsFromItem(EnhancementItems.LV4_CLOVER))
                .offerTo(exporter,new Identifier(getRecipeName(EnhancementItems.LV5_CLOVER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,EnhancementItems.LV6_CLOVER,1)
                .pattern("XX")
                .pattern("XX")
                .input('X',EnhancementItems.LV5_CLOVER)
                .criterion(hasItem(EnhancementItems.LV5_CLOVER),conditionsFromItem(EnhancementItems.LV5_CLOVER))
                .offerTo(exporter,new Identifier(getRecipeName(EnhancementItems.LV6_CLOVER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,EnhancementItems.S_CLOVER,1)
                .pattern("XX")
                .pattern("XX")
                .input('X',EnhancementItems.LV6_CLOVER)
                .criterion(hasItem(EnhancementItems.LV6_CLOVER),conditionsFromItem(EnhancementItems.LV6_CLOVER))
                .offerTo(exporter,new Identifier(getRecipeName(EnhancementItems.S_CLOVER)));

        /*
        I decide to remove the following recipes after the kindly consideration.

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,EnhancementItems.SS_CLOVER,1)
                .pattern("XX")
                .pattern("XX")
                .input('X',EnhancementItems.S_CLOVER)
                .criterion(hasItem(EnhancementItems.S_CLOVER),conditionsFromItem(EnhancementItems.S_CLOVER))
                .offerTo(exporter,new Identifier(getRecipeName(EnhancementItems.SS_CLOVER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,EnhancementItems.SSS_CLOVER,1)
                .pattern("XX")
                .pattern("XX")
                .input('X',EnhancementItems.SS_CLOVER)
                .criterion(hasItem(EnhancementItems.SS_CLOVER),conditionsFromItem(EnhancementItems.SS_CLOVER))
                .offerTo(exporter,new Identifier(getRecipeName(EnhancementItems.SSS_CLOVER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,EnhancementItems.SSR_CLOVER,1)
                .pattern("XX")
                .pattern("XX")
                .input('X',EnhancementItems.SSS_CLOVER)
                .criterion(hasItem(EnhancementItems.SSS_CLOVER),conditionsFromItem(EnhancementItems.SSS_CLOVER))
                .offerTo(exporter,new Identifier(getRecipeName(EnhancementItems.SSR_CLOVER)));
        */




    }
}
