package com.example.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

import static com.example.item.EnhancementItems.*;

public class lootTableModifiers {
    public static ArrayList<Identifier> boss = new ArrayList<>();
    public static ArrayList<Identifier> overworld_mobs = new ArrayList<>();

    // boss
    private static final Identifier WITHER_ID = registerLootClass(boss,
            new Identifier("minecraft","entities/wither"));
    private static final Identifier ENDER_DRAGON_ID = registerLootClass(boss,
            new Identifier("minecraft","entities/ender_dragon"));
    private static final Identifier WARDEN_ID = registerLootClass(boss,
            new Identifier("minecraft","entities/warden"));
    // ordinary mobs
    private static final Identifier ZOMBIE_ID = registerLootClass(overworld_mobs,
            new Identifier("minecraft","entities/zombie"));
    private static final Identifier WITCH_ID = registerLootClass(overworld_mobs,
            new Identifier("minecraft","entities/witch"));
    private static final Identifier SPIDER_ID = registerLootClass(overworld_mobs,
            new Identifier("minecraft","entities/spider"));
    private static final Identifier SKELETON_ID = registerLootClass(overworld_mobs,
            new Identifier("minecraft","entities/skeleton"));
    private static final Identifier CREEPER_ID = registerLootClass(overworld_mobs,
            new Identifier("minecraft","entities/creeper"));

    private static Identifier registerLootClass(ArrayList<Identifier> l, Identifier i){
        l.add(i);
        return i;
    }
    private static boolean lootTableBelongsTo(RegistryKey<LootTable> lootTable, ArrayList<Identifier> l){
        for(Identifier i : l){
            if(i.equals(lootTable.getValue())) return true;
        }
        return false;
    }
    public static void modifyLootTables(){
        LootTableEvents.MODIFY.register((lootTable, tableBuilder, source)->{
            if (source.isBuiltin() && lootTableBelongsTo(lootTable,boss)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1f))
                        .with(ItemEntry.builder(LUCKY_CRYSTAL_CHEST))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build());
                tableBuilder.pool(poolBuilder);
            }
        });
        LootTableEvents.MODIFY.register((lootTable, tableBuilder, source)->{
            if (source.isBuiltin() && lootTableBelongsTo(lootTable,boss)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1f))
                        .with(ItemEntry.builder(RARE_CLOVER_CHEST))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build());
                tableBuilder.pool(poolBuilder);
            }
        });




        LootTableEvents.MODIFY.register((lootTable, tableBuilder, source)->{
            if (source.isBuiltin() && lootTableBelongsTo(lootTable,overworld_mobs)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.025f))
                        .with(ItemEntry.builder(LUCKY_CLOVER_CHEST))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build());
                tableBuilder.pool(poolBuilder);
            }
        });
        LootTableEvents.MODIFY.register((lootTable, tableBuilder, source)->{
            if (source.isBuiltin() && lootTableBelongsTo(lootTable,overworld_mobs)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.001f))
                        .with(ItemEntry.builder(LUCKY_CRYSTAL_CHEST))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build());
                tableBuilder.pool(poolBuilder);
            }
        });


    }
}
