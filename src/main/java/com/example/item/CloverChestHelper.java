package com.example.item;

import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.item.EnhancementItems.*;

public class CloverChestHelper {
    ArrayList<Item> lootTable;
    Random selector;
    public CloverChestHelper() {
        lootTable = new ArrayList<>();
        lootTable.add(LV4_CLOVER);
        lootTable.add(LV5_CLOVER);
        lootTable.add(LV6_CLOVER);
        lootTable.add(S_CLOVER);
        // may add more!
        selector = new Random(System.currentTimeMillis());
    }
    public CloverChestHelper(List<Item> l) {
        lootTable = new ArrayList<>();
        lootTable.addAll(l);
        selector = new Random(System.currentTimeMillis());
    }
    // shit code, rebuild later:
    public Item getLootItem(){
        int crystal_probability_distribution = 99;
        int power = selector.nextInt(crystal_probability_distribution + 1);
        if(power == crystal_probability_distribution) {
            return MAGIC_CRYSTAL;
        }else {
            return getClover();
        }
    }

    // shit code, rebuild later:
    public Item getClover(){
        int len = lootTable.size();
        int power = selector.nextInt((len * (len + 1) * (2 * len + 1)) / 6)+1;
        for(int i=0;i<len;i++){
            power -= (len-i)*(len-i);
            if(power <= 0){
                return lootTable.get(i);
            }
        }
        return lootTable.get(0);
    }
    // shit code, rebuild later:
    public Item getRareClover(){
        // 0.1% SSR, 0.2% SSS, 5% SS 40% S
        int power = selector.nextInt(1000);
        if(power == 0){
            return SSR_CLOVER;
        }else if(power <= 2){
            return SSS_CLOVER;
        }else if(power <= 52)
            return SS_CLOVER;
        else if(power <= 452)
            return S_CLOVER;
        else return LV6_CLOVER;
    }
}
