package com.example.mixin;

import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static com.example.item.EnhancementItems.*;
import static com.example.potion.CloverPotions.*;

@Mixin(BrewingRecipeRegistry.class)
public class registerDefaultsMixin {
    @Inject(method = "registerDefaults(Lnet/minecraft/recipe/BrewingRecipeRegistry$Builder;)V", at = @At("TAIL"),locals = LocalCapture.CAPTURE_FAILHARD)
    private static void injectedRegisterDefaults(BrewingRecipeRegistry.Builder builder,CallbackInfo ci) {
        builder.registerPotionRecipe(Potions.WATER, LV4_CLOVER, LV4_5_CLOVER_POTION);
        builder.registerPotionRecipe(Potions.WATER, LV5_CLOVER, LV4_5_CLOVER_POTION);
        builder.registerPotionRecipe(Potions.WATER, LV6_CLOVER, LV6_S_CLOVER_POTION);
        builder.registerPotionRecipe(Potions.WATER, S_CLOVER, LV6_S_CLOVER_POTION);
        builder.registerPotionRecipe(Potions.WATER, SS_CLOVER, LVSS_SSS_CLOVER_POTION);
        builder.registerPotionRecipe(Potions.WATER, SSS_CLOVER, LVSS_SSS_CLOVER_POTION);
        builder.registerPotionRecipe(Potions.WATER, SSR_CLOVER, SSR_CLOVER_POTION);
        builder.registerPotionRecipe(Potions.WATER, MAGIC_CRYSTAL, MAGIC_CRYSTAL_POTION);
    }
}
