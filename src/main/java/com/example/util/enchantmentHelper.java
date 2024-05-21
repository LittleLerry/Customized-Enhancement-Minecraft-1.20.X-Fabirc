package com.example.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;

import java.util.ArrayList;
import java.util.List;

public class enchantmentHelper {
    public static boolean _DEBUG_enchantmentHelper_ = false;
    private static final List<Enchantment> allEnchantments = List.of(
            Enchantments.PROTECTION,
            Enchantments.FIRE_PROTECTION,
            Enchantments.FEATHER_FALLING,
            Enchantments.BLAST_PROTECTION,
            Enchantments.PROJECTILE_PROTECTION,
            Enchantments.RESPIRATION,
            Enchantments.AQUA_AFFINITY,
            Enchantments.THORNS,
            Enchantments.DEPTH_STRIDER,
            Enchantments.FROST_WALKER,
            Enchantments.BINDING_CURSE,
            Enchantments.SOUL_SPEED,
            Enchantments.SWIFT_SNEAK,
            Enchantments.SHARPNESS,
            Enchantments.SMITE,
            Enchantments.BANE_OF_ARTHROPODS,
            Enchantments.KNOCKBACK,
            Enchantments.FIRE_ASPECT,
            Enchantments.LOOTING,
            Enchantments.SWEEPING_EDGE,
            Enchantments.EFFICIENCY,
            Enchantments.SILK_TOUCH,
            Enchantments.UNBREAKING,
            Enchantments.FORTUNE,
            Enchantments.POWER,
            Enchantments.PUNCH,
            Enchantments.FLAME,
            Enchantments.INFINITY,
            Enchantments.LUCK_OF_THE_SEA,
            Enchantments.LURE,
            Enchantments.LOYALTY,
            Enchantments.IMPALING,
            Enchantments.RIPTIDE,
            Enchantments.CHANNELING,
            Enchantments.MULTISHOT,
            Enchantments.QUICK_CHARGE,
            Enchantments.PIERCING,
            Enchantments.DENSITY,
            Enchantments.BREACH,
            Enchantments.WIND_BURST,
            Enchantments.MENDING,
            Enchantments.VANISHING_CURSE
    );
    public static ItemStack getMaxEnchantmentItemstack(ItemStack stack, List<Enchantment> enchantments){
        for (Enchantment e : enchantments){
            stack.addEnchantment(e,e.getMaxLevel());
        }
        return stack;
    }

    public static ItemStack getMaxEnchantmentBookItemstack(List<Enchantment> enchantments){
        ItemStack itemStack = new ItemStack(Items.ENCHANTED_BOOK);
        for (Enchantment e : enchantments){
            itemStack.addEnchantment(e,e.getMaxLevel());
        }
        return itemStack;
    }

    public static ItemStack getRandomMaxEnchantmentBookItemstack(Random random){
        ItemStack itemStack = new ItemStack(Items.ENCHANTED_BOOK);
        if(_DEBUG_enchantmentHelper_){
            itemStack.addEnchantment(Enchantments.EFFICIENCY,6);
        }else{
            Enchantment e = getRandomEnchantment(random);
            itemStack.addEnchantment(e,e.getMaxLevel());
        }
        return itemStack;
    }
    public static Enchantment getRandomEnchantment(Random random){
        return allEnchantments.get(random.nextInt(allEnchantments.size()));
    }
}
