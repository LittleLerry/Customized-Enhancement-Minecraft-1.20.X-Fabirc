package com.example.item.custom;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import static com.example.item.EnhancementItems.MAGIC_CRYSTAL;

public class LuckyCrystalChestItem extends Item {
    public LuckyCrystalChestItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            ItemStack lootStack = new ItemStack(MAGIC_CRYSTAL,Random.create(System.currentTimeMillis()).nextInt(2)+1);
            ItemEntity itemEntity = new ItemEntity(world, user.getX(), user.getY(), user.getZ(), lootStack);
            world.spawnEntity(itemEntity);
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, user);
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
