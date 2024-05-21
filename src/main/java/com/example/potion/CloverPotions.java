package com.example.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import static com.example.Customized_enhancement.MOD_ID;

public class CloverPotions {
    /*
    public static final Potion LV4_5_CLOVER_POTION =
            Registry.register(Registries.POTION, new Identifier(MOD_ID, "lv4_5_clover_potion"),
                    new Potion(new StatusEffectInstance(StatusEffects.LUCK, 3000, 3)));
    public static final Potion LV6_S_CLOVER_POTION =
            Registry.register(Registries.POTION, new Identifier(MOD_ID, "lv6_s_clover_potion"),
                    new Potion(new StatusEffectInstance(StatusEffects.LUCK, 6000, 7)));
    public static final Potion LVSS_SSS_CLOVER_POTION =
            Registry.register(Registries.POTION, new Identifier(MOD_ID, "lvss_sss_clover_potion"),
                    new Potion(new StatusEffectInstance(StatusEffects.LUCK, 9000, 15)));
    public static final Potion SSR_CLOVER_POTION =
            Registry.register(Registries.POTION, new Identifier(MOD_ID, "ssr_clover_potion"),
                    new Potion(new StatusEffectInstance(StatusEffects.LUCK, 12000, 31)));
    */
    public static final RegistryEntry<Potion> LV4_5_CLOVER_POTION = register("lv4_5_clover_potion", new Potion(
            new StatusEffectInstance(StatusEffects.HASTE, 3000, 1),
            new StatusEffectInstance(StatusEffects.SPEED, 3000, 1),
            new StatusEffectInstance(StatusEffects.LUCK, 3000, 0)));
    public static final RegistryEntry<Potion> LV6_S_CLOVER_POTION = register("lv6_s_clover_potion", new Potion(
            new StatusEffectInstance(StatusEffects.HASTE, 6000, 1),
            new StatusEffectInstance(StatusEffects.SPEED, 6000, 1),
            new StatusEffectInstance(StatusEffects.LUCK, 6000, 1)));
    public static final RegistryEntry<Potion> LVSS_SSS_CLOVER_POTION = register("lvss_sss_clover_potion", new Potion(
            new StatusEffectInstance(StatusEffects.HASTE, 18000, 1),
            new StatusEffectInstance(StatusEffects.SPEED, 18000, 1),
            new StatusEffectInstance(StatusEffects.LUCK, 18000, 3)));
    public static final RegistryEntry<Potion> SSR_CLOVER_POTION = register("ssr_clover_potion", new Potion(
            new StatusEffectInstance(StatusEffects.HASTE, 288000, 1),
            new StatusEffectInstance(StatusEffects.SPEED, 288000, 1),
            new StatusEffectInstance(StatusEffects.LUCK, 288000, 7)));
    public static final RegistryEntry<Potion> MAGIC_CRYSTAL_POTION = register("magic_crystal_potion", new Potion(
            new StatusEffectInstance(StatusEffects.RESISTANCE, 72000, 4),
            new StatusEffectInstance(StatusEffects.LUCK, 72000, 7)));


    private static RegistryEntry<Potion> register(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, new Identifier(MOD_ID, name), potion);
    }
    public static void registerCloverPotions(){
    }

}
