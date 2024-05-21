package com.example.villager;

import com.example.block.EnhancementBlocks;
import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterest;
import net.minecraft.world.poi.PointOfInterestType;

import static com.example.Customized_enhancement.MOD_ID;

public class ModVillagers {

    public static final RegistryKey<PointOfInterestType> CRYSTAL_POI_KEY = poiKey("crystal_poi");
    public static final PointOfInterestType CRYSTAL_POI = registerPOI("crystal_poi", EnhancementBlocks.CRYSTAL_CRAFTING_TABLE);
    public static final VillagerProfession CRYSTAL_MASTER = registerProfession("crystal_master",CRYSTAL_POI_KEY);

    private static VillagerProfession registerProfession(String name,RegistryKey<PointOfInterestType> type){
        return Registry.register(Registries.VILLAGER_PROFESSION,new Identifier(MOD_ID,name),
                new VillagerProfession(name, entry-> entry.matchesKey(type),
                        entry-> entry.matchesKey(type),
                        ImmutableSet.of(),
                        ImmutableSet.of(),
                        SoundEvents.ENTITY_VILLAGER_WORK_TOOLSMITH));
    }

    private static PointOfInterestType registerPOI(String name, Block block){
        return PointOfInterestHelper.register(new Identifier(MOD_ID,name),1,1,block);
    }

    private static RegistryKey<PointOfInterestType> poiKey(String name){
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE,new Identifier(MOD_ID,name));
    }
    public static void registerVillagers(){

    }
}
