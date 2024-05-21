package com.example;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.math.random.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.example.block.EnhancementBlocks.registerModBlocks;
import static com.example.item.EnhancementItemGroups.registerModItemGroups;
import static com.example.item.EnhancementItems.registerModItems;
import static com.example.potion.CloverPotions.registerCloverPotions;
import static com.example.util.lootTableModifiers.modifyLootTables;
import static com.example.util.modCustomTrades.registerCustomTrades;
import static com.example.villager.ModVillagers.registerVillagers;

public class Customized_enhancement implements ModInitializer {
	public static final String MOD_ID = "customized_enhancement";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Random enhancementRandom = Random.create(System.currentTimeMillis());

	@Override
	public void onInitialize() {
		registerModItemGroups();
		registerModItems();
		registerModBlocks();
		registerCloverPotions();

		registerVillagers();
		registerCustomTrades();

		modifyLootTables();
	}
}