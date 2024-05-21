package com.example.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;

import java.util.List;

import static com.example.item.EnhancementItems.*;
import static com.example.util.enchantmentHelper.*;
import static com.example.villager.ModVillagers.CRYSTAL_MASTER;

public class modCustomTrades {


    public static void registerCustomTrades() {
        TradeOfferHelper.registerVillagerOffers(CRYSTAL_MASTER, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 32),
                            new ItemStack(LV4_CLOVER, 1),
                            4, 2, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 48),
                            getRandomMaxEnchantmentBookItemstack(random),
                            4, 8, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.DIAMOND, 8),
                            new ItemStack(MAGIC_CRYSTAL, 1),
                            4, 8, 0.05f));

                });

        TradeOfferHelper.registerVillagerOffers(CRYSTAL_MASTER, 2,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(MAGIC_CRYSTAL, 4),
                            new ItemStack(S_CLOVER, 1),
                            16, 32, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.NETHER_STAR, 4),
                            new ItemStack(MAGIC_CRYSTAL, 1),
                            16, 16, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.WITHER_SKELETON_SKULL, 2),
                            new ItemStack(RARE_CLOVER_CHEST, 1),
                            16, 16, 0.05f));

                });

        TradeOfferHelper.registerVillagerOffers(CRYSTAL_MASTER, 3,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(MAGIC_CRYSTAL, 32),
                            new ItemStack(SS_CLOVER, 1),
                            16, 32, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(MAGIC_CRYSTAL, 4),
                            random.nextBoolean()
                                    ? getMaxEnchantmentItemstack(new ItemStack(Items.NETHERITE_HOE, 1), List.of(Enchantments.EFFICIENCY,Enchantments.UNBREAKING,Enchantments.MENDING))
                                    : getMaxEnchantmentItemstack(new ItemStack(Items.NETHERITE_SHOVEL, 1), List.of(Enchantments.EFFICIENCY,Enchantments.UNBREAKING,Enchantments.MENDING)),
                            4, 32, 0.05f));
                });

        TradeOfferHelper.registerVillagerOffers(CRYSTAL_MASTER, 4,
                factories -> {
                    factories.add((entity, random) -> random.nextBoolean()
                            ? (new TradeOffer(new TradedItem(MAGIC_CRYSTAL, 5), getMaxEnchantmentItemstack(new ItemStack(Items.DIAMOND_PICKAXE, 1), List.of(Enchantments.EFFICIENCY,Enchantments.UNBREAKING,Enchantments.SILK_TOUCH,Enchantments.MENDING)), 16, 32, 0.05f))
                            : (new TradeOffer(new TradedItem(MAGIC_CRYSTAL, 6), getMaxEnchantmentItemstack(new ItemStack(Items.NETHERITE_PICKAXE, 1), List.of(Enchantments.EFFICIENCY,Enchantments.UNBREAKING,Enchantments.SILK_TOUCH,Enchantments.MENDING)), 16, 32, 0.05f))
                    );

                    factories.add((entity, random) -> random.nextBoolean()
                            ? (new TradeOffer(new TradedItem(MAGIC_CRYSTAL, 3), getMaxEnchantmentBookItemstack(List.of(Enchantments.PROTECTION,Enchantments.FEATHER_FALLING,Enchantments.UNBREAKING,Enchantments.MENDING)), 16, 32, 0.05f))
                            : (new TradeOffer(new TradedItem(MAGIC_CRYSTAL, 1), new ItemStack(Blocks.BUDDING_AMETHYST), 16, 32, 0.05f))
                    );
                });

        TradeOfferHelper.registerVillagerOffers(CRYSTAL_MASTER, 5,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(S_CLOVER, 12),
                            new ItemStack(SS_CLOVER,1),
                            16, 32, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(SS_CLOVER, 32),
                            new ItemStack(SSS_CLOVER,1),
                            16, 32, 0.05f));
                });

    }

}
