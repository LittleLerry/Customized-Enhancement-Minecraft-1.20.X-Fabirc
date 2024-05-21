package com.example.item.custom;

import com.example.item.CloverChestHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class LuckyCloverChestItem extends Item {
    public LuckyCloverChestItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            int attempts = getAttempts();
            CloverChestHelper helper = new CloverChestHelper();
            // clover & magic crystal
            for(int i=0;i < attempts;i++) {
                ItemStack lootStack = new ItemStack(helper.getLootItem());
                ItemEntity itemEntity = new ItemEntity(world, user.getX(), user.getY(), user.getZ(), lootStack);
                world.spawnEntity(itemEntity);
            }
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, user);
        return TypedActionResult.success(itemStack, world.isClient());
    }

    private int getAttempts(){
        return 2;
    }
}
