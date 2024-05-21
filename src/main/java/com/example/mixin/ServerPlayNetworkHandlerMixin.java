package com.example.mixin;


import com.example.screen.EnhancementTableScreenHandler;
import net.minecraft.network.packet.c2s.play.RenameItemC2SPacket;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static com.example.item.EnhancementItems.*;
import static com.example.item.EnhancementItems.MAGIC_CRYSTAL;
import static com.example.potion.CloverPotions.*;
import static com.example.potion.CloverPotions.MAGIC_CRYSTAL_POTION;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Inject(method = "onRenameItem(Lnet/minecraft/network/packet/c2s/play/RenameItemC2SPacket;)V", at = @At("TAIL"),locals = LocalCapture.CAPTURE_FAILHARD)
    private void injectedRegisterDefaults(RenameItemC2SPacket packet, CallbackInfo ci) {
        ServerPlayerEntity player1 = ((ServerPlayNetworkHandler)((Object)this)).player;
        ScreenHandler screenHandler1 = player1.currentScreenHandler;

        if (screenHandler1 instanceof EnhancementTableScreenHandler) {
            EnhancementTableScreenHandler enhancementTableScreenHandler = (EnhancementTableScreenHandler)screenHandler1;
            if (!enhancementTableScreenHandler.canUse(player1)) {
                return;
            }
            // not set name but set option
            enhancementTableScreenHandler.setNewItemName(packet.getName());
            enhancementTableScreenHandler.setChoosenEnchantmanetIndex(packet.getName());
        }
    }
}
