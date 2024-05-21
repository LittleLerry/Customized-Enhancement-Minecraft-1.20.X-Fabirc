package com.example.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.example.Customized_enhancement.MOD_ID;
import static com.example.block.EnhancementBlocks.*;
import static com.example.item.EnhancementItems.*;

public class EnhancementItemGroups {
    private static final ItemGroup ENHANCEMENT_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(MAGIC_CRYSTAL))
            .displayName(Text.translatable("enhancement.itemgroup"))
            .entries((context, entries) -> {
                entries.add(MAGIC_CRYSTAL);

                entries.add(LV4_CLOVER);
                entries.add(LV5_CLOVER);
                entries.add(LV6_CLOVER);
                entries.add(S_CLOVER);
                entries.add(SS_CLOVER);
                entries.add(SSS_CLOVER);
                entries.add(SSR_CLOVER);

                entries.add(LUCKY_CLOVER_CHEST);
                entries.add(LUCKY_CRYSTAL_CHEST);
                entries.add(RARE_CLOVER_CHEST);

                entries.add(MAGIC_CRYSTAL_BLOCK);
                entries.add(MAGIC_CRYSTAL_ORE);
                entries.add(CRYSTAL_CRAFTING_TABLE);
                entries.add(ENHANCEMENT_TABLE_BLOCK);
                // potions
            })
            .build();
    public static void registerModItemGroups(){
        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "enhancement_group"), ENHANCEMENT_GROUP);
    }
}
