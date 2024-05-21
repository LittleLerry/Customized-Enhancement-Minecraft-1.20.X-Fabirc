package com.example.util;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.random.Random;

import java.util.HashMap;
import java.util.Map;

import static com.example.item.EnhancementItems.*;

public class CloverEnhancementHelper {
    public static final Map<Item, Double> factor = Map.ofEntries(
            Map.entry(LV4_CLOVER,2.0),
            Map.entry(LV5_CLOVER,2.5),
            Map.entry(LV6_CLOVER,3.0),
            Map.entry(S_CLOVER,4.0),
            Map.entry(SS_CLOVER,6.0),
            Map.entry(SSS_CLOVER,7.5),
            Map.entry(SSR_CLOVER,10.0));

    public static boolean isClover(ItemStack itemStack){
        Item item = itemStack.getItem();
        return item.equals(LV4_CLOVER) ||
                item.equals(LV5_CLOVER) ||
                item.equals(LV6_CLOVER) ||
                item.equals(S_CLOVER) ||
                item.equals(SS_CLOVER) ||
                item.equals(SSS_CLOVER) ||
                item.equals(SSR_CLOVER);
    }
    public static double getProbability(ItemStack fst, ItemStack snd, Enchantment target){
        ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(EnchantmentHelper.getEnchantments(fst));
        int cur_level = builder.getLevel(target);
        int max_level = target.getMaxLevel();
        double p = 0f;

        if(cur_level < max_level){
            p = 0.1f + (max_level-cur_level)*0.1;
        }else{
            int i =cur_level - max_level;
            switch (i) {
                case 0:
                    p = 0.14;
                    break;
                case 1:
                    p = 0.11;
                    break;
                case 2:
                    p = 0.08;
                    break;
                case 3:
                    p = 0.07;
                    break;
                case 4:
                    p = 0.06;
                    break;
                default:
                    p = 0.05;
                    break;
            }
        }
        return getProbabilityWithClover(p,snd);
    }
    public static double getProbabilityWithClover(double base_p,ItemStack stack){
        if(!isClover(stack))
            return base_p;
        double p = base_p*factor.get(stack.getItem());
        if(p >= 0.499 && (stack.getItem().equals(SSS_CLOVER) || stack.getItem().equals(SSR_CLOVER)))
            return 1.0;
        return Math.min(p, 1.0);
    }
}
