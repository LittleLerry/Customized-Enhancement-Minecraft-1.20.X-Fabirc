package com.example.item;
import com.example.item.custom.LuckyCloverChestItem;
import com.example.item.custom.LuckyCrystalChestItem;
import com.example.item.custom.RareCloverChestItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.example.Customized_enhancement.MOD_ID;


public class EnhancementItems {
    public static final Item MAGIC_CRYSTAL = new Item(new Item.Settings());
    public static final Item LV4_CLOVER = new Item(new Item.Settings());
    public static final Item LV5_CLOVER = new Item(new Item.Settings());
    public static final Item LV6_CLOVER = new Item(new Item.Settings());
    public static final Item S_CLOVER = new Item(new Item.Settings());
    public static final Item SS_CLOVER = new Item(new Item.Settings());
    public static final Item SSS_CLOVER = new Item(new Item.Settings());
    public static final Item SSR_CLOVER = new Item(new Item.Settings());
    public static final LuckyCloverChestItem LUCKY_CLOVER_CHEST = new LuckyCloverChestItem(new Item.Settings());
    public static final LuckyCrystalChestItem LUCKY_CRYSTAL_CHEST = new LuckyCrystalChestItem(new Item.Settings());

    public static final RareCloverChestItem RARE_CLOVER_CHEST = new RareCloverChestItem(new Item.Settings());


    // block item
    public static void registerItem(String name,Item item){
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, name), item);
    }
    public static void registerModItems(){
        registerItem("magic_crystal",MAGIC_CRYSTAL);
        registerItem("lv4_clover",LV4_CLOVER);
        registerItem("lv5_clover",LV5_CLOVER);
        registerItem("lv6_clover",LV6_CLOVER);
        registerItem("s_clover",S_CLOVER);
        registerItem("ss_clover",SS_CLOVER);
        registerItem("sss_clover",SSS_CLOVER);
        registerItem("ssr_clover",SSR_CLOVER);
        registerItem("lucky_clover_chest",LUCKY_CLOVER_CHEST);
        registerItem("lucky_crystal_chest",LUCKY_CRYSTAL_CHEST);
        registerItem("rare_clover_chest",RARE_CLOVER_CHEST);
    }
}
