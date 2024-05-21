package com.example.screen;

/*
 * Decompiled with CFR 0.2.2 (FabricMC 7c48b8c4).
 */

import com.example.block.EnhancementBlocks;
import com.example.util.MyMutableText;
import com.mojang.logging.LogUtils;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.ForgingSlotsManager;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.StringHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.regex.Pattern;

import static com.example.Customized_enhancement.enhancementRandom;
import static com.example.util.CloverEnhancementHelper.getProbability;
import static com.example.util.CloverEnhancementHelper.isClover;

public class EnhancementTableScreenHandler
        extends ForgingScreenHandler {
    private static MyMutableText text_box;

    public static final int INPUT_1_ID = 0;
    public static final int INPUT_2_ID = 1;
    public static final int OUTPUT_ID = 2;
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final boolean field_30752 = false;
    public static final int MAX_NAME_LENGTH = 50;
    private int repairItemUsage;
    private double enhancementProbability = 0f;
    private String choosenEnchantmanetIndex = "1";
    private Enchantment choosenEnchantmanet = null;
    private double probability = 0f;

    @Nullable
    private String newItemName;
    private final Property levelCost = Property.create();
    private static final int field_30753 = 0;
    private static final int field_30754 = 1;
    private static final int field_30755 = 1;
    private static final int field_30747 = 1;
    private static final int field_30748 = 2;
    private static final int field_30749 = 1;
    private static final int field_30750 = 1;
    private static final int INPUT_1_X = 27;
    private static final int INPUT_2_X = 76;
    private static final int OUTPUT_X = 134;
    private static final int SLOT_Y = 47;

    public EnhancementTableScreenHandler(int syncId, PlayerInventory inventory) {
        this(syncId, inventory, ScreenHandlerContext.EMPTY);
    }
    public EnhancementTableScreenHandler(int syncId, PlayerInventory inventory, ScreenHandlerContext context) {
        super(ScreenHandlerType.ANVIL, syncId, inventory, context);
        this.addProperty(this.levelCost);
    }

    @Override
    protected ForgingSlotsManager getForgingSlotsManager() {
        return ForgingSlotsManager.create()
                .input(0, 27, 47, stack -> true)
                .input(1, 76, 47, stack -> true)
                .output(2, 134, 47)
                .build();
    }
    // return true
    @Override
    protected boolean canUse(BlockState state) {
        return state.isOf(EnhancementBlocks.ENHANCEMENT_TABLE_BLOCK);
    }

    @Override
    protected boolean canTakeOutput(PlayerEntity player, boolean present) {
        return (player.isInCreativeMode() || player.experienceLevel >= this.levelCost.get()) && this.levelCost.get() > 0;
    }

    // name has been changed
    @Override
    protected void onTakeOutput(PlayerEntity player, ItemStack stack) {
        if (!player.getAbilities().creativeMode) {
            player.addExperienceLevels(-this.levelCost.get());
        }

        this.input.setStack(0, ItemStack.EMPTY);
        if (this.repairItemUsage > 0) {
            ItemStack itemStack = this.input.getStack(1);
            if (!itemStack.isEmpty() && itemStack.getCount() > this.repairItemUsage) {
                itemStack.decrement(this.repairItemUsage);
                this.input.setStack(1, itemStack);
            } else {
                this.input.setStack(1, ItemStack.EMPTY);
            }
        } else {
            this.input.setStack(1, ItemStack.EMPTY);
        }
        this.levelCost.set(0);
        this.context.run((world, pos) -> {});
        if (enhancementRandom.nextDouble() <= this.probability){
            if(!player.getWorld().isClient){
                player.getWorld().playSound(null,player.getBlockPos(),SoundEvents.BLOCK_BEACON_POWER_SELECT,SoundCategory.BLOCKS,0.33f,1f);
                player.sendMessage(Text.literal("Succeeded"));
            }
        }else{
            stack.setCount(0);
            if(!player.getWorld().isClient){
                player.getWorld().playSound(null,player.getBlockPos(),SoundEvents.BLOCK_BEACON_DEACTIVATE,SoundCategory.BLOCKS,0.33f,1f);
                player.sendMessage(Text.literal("Failed"));
            }
        }
    }

    /*
    @Override
    public void updateResult() {
        // directly access options locally using this.newItemName!
        // this.player.sendMessage(Text.literal("updateResult:"+this.newItemName));
        int k;
        ItemStack first_ItemStack = this.input.getStack(0);
        this.levelCost.set(1);
        int intergerI = 0;
        long total_repair_cost = 0L;
        int j = 0;
        // If first slot is not enchantable, just return
        if (first_ItemStack.isEmpty() || !EnchantmentHelper.canHaveEnchantments(first_ItemStack)) {
            this.output.setStack(0, ItemStack.EMPTY);
            this.levelCost.set(0);
            return;
        }

        ItemStack copy_of_fst_ItemStack = first_ItemStack.copy();
        ItemStack second_ItemStack = this.input.getStack(1);
        ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(EnchantmentHelper.getEnchantments(copy_of_fst_ItemStack));
        // total repair cost = 1st cost  + 2nd cost
        total_repair_cost += (long)first_ItemStack.getOrDefault(DataComponentTypes.REPAIR_COST, 0).intValue() + (long)second_ItemStack.getOrDefault(DataComponentTypes.REPAIR_COST, 0).intValue();
        this.repairItemUsage = 0;

        // case1: update repairItemUsage.
        // case2:
        if (!second_ItemStack.isEmpty()) {
            boolean snd_item_contains_enchantments = second_ItemStack.contains(DataComponentTypes.STORED_ENCHANTMENTS);
            // update repairItemUsage. It records the state of crafting type: repair(repairItemUsage!=0) or enchant(repairItemUsage==0)
            // repair case
            if (copy_of_fst_ItemStack.isDamageable() && copy_of_fst_ItemStack.getItem().canRepair(first_ItemStack, second_ItemStack)) {
                int m;
                k = Math.min(copy_of_fst_ItemStack.getDamage(), copy_of_fst_ItemStack.getMaxDamage() / 4);
                if (k <= 0) {
                    this.output.setStack(0, ItemStack.EMPTY);
                    this.levelCost.set(0);
                    return;
                }
                for (m = 0; k > 0 && m < second_ItemStack.getCount(); ++m) {
                    int n = copy_of_fst_ItemStack.getDamage() - k;
                    copy_of_fst_ItemStack.setDamage(n);
                    ++intergerI;
                    k = Math.min(copy_of_fst_ItemStack.getDamage(), copy_of_fst_ItemStack.getMaxDamage() / 4);
                }
                this.repairItemUsage = m;
            }

            else {
                // we will go further only if snd_item_contains_enchantments OR (fst + snd items will be the same item and can repair)
                if (!(snd_item_contains_enchantments || copy_of_fst_ItemStack.isOf(second_ItemStack.getItem()) && copy_of_fst_ItemStack.isDamageable())) {
                    this.output.setStack(0, ItemStack.EMPTY);
                    this.levelCost.set(0);
                    return;
                }
                // reset the damage to copy_of_fst_ItemStack
                if (copy_of_fst_ItemStack.isDamageable() && !snd_item_contains_enchantments) {
                    int k2 = first_ItemStack.getMaxDamage() - first_ItemStack.getDamage();
                    int m = second_ItemStack.getMaxDamage() - second_ItemStack.getDamage();
                    int n = m + copy_of_fst_ItemStack.getMaxDamage() * 12 / 100;
                    int o = k2 + n;
                    int p = copy_of_fst_ItemStack.getMaxDamage() - o;
                    if (p < 0) {
                        p = 0;
                    }
                    if (p < copy_of_fst_ItemStack.getDamage()) {
                        copy_of_fst_ItemStack.setDamage(p);
                        intergerI += 2;
                    }
                }

                ItemEnchantmentsComponent snd_ItemStack_EnchantmentsComponent = EnchantmentHelper.getEnchantments(second_ItemStack);
                boolean bl2 = false;
                boolean bl3 = false;
                for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : snd_ItemStack_EnchantmentsComponent.getEnchantmentsMap()) {
                    int r;
                    RegistryEntry registryEntry = (RegistryEntry)entry.getKey();
                    Enchantment enchantment = (Enchantment)registryEntry.value();
                    int q = builder.getLevel(enchantment);
                    r = q == (r = entry.getIntValue()) ? r + 1 : Math.max(r, q);

                    boolean fst_ItemStack_is_acceptable = enchantment.isAcceptableItem(first_ItemStack);
                    if (this.player.getAbilities().creativeMode || first_ItemStack.isOf(Items.ENCHANTED_BOOK)) {
                        fst_ItemStack_is_acceptable = true;
                    }

                    for (RegistryEntry<Enchantment> registryEntry2 : builder.getEnchantments()) {
                        if (registryEntry2.equals(registryEntry) || enchantment.canCombine(registryEntry2.value())) continue;
                        fst_ItemStack_is_acceptable = false;
                        ++intergerI;
                    }
                    if (!fst_ItemStack_is_acceptable) {
                        bl3 = true;
                        continue;
                    }
                    bl2 = true;
                    if (r > enchantment.getMaxLevel()) {
                        r = enchantment.getMaxLevel();
                    }
                    builder.set(enchantment, r);
                    int s = enchantment.getAnvilCost();
                    if (snd_item_contains_enchantments) {
                        s = Math.max(1, s / 2);
                    }
                    intergerI += s * r;
                    if (first_ItemStack.getCount() <= 1) continue;
                    intergerI = 40;
                }
                if (bl3 && !bl2) {
                    this.output.setStack(0, ItemStack.EMPTY);
                    this.levelCost.set(0);
                    return;
                }
            }
        }
        // name settings
        if (this.newItemName == null || StringHelper.isBlank(this.newItemName)) {
            if (first_ItemStack.contains(DataComponentTypes.CUSTOM_NAME)) {
                j = 1;
                intergerI += j;
                copy_of_fst_ItemStack.remove(DataComponentTypes.CUSTOM_NAME);
            }
        } else if (!this.newItemName.equals(first_ItemStack.getName().getString())) {
            j = 1;
            intergerI += j;
            copy_of_fst_ItemStack.set(DataComponentTypes.CUSTOM_NAME, Text.literal(this.newItemName));
        }
        // set level cost, if too high them set EMPTY
        int t = (int)MathHelper.clamp(total_repair_cost + (long)intergerI, 0L, Integer.MAX_VALUE);
        this.levelCost.set(t);
        if (intergerI <= 0) {
            copy_of_fst_ItemStack = ItemStack.EMPTY;
        }
        if (j == intergerI && j > 0 && this.levelCost.get() >= 40) {
            this.levelCost.set(39);
        }
        if (this.levelCost.get() >= 40 && !this.player.getAbilities().creativeMode) {
            copy_of_fst_ItemStack = ItemStack.EMPTY;
        }
        // copy_of_fst_ItemStack will be the final result, update cost for it..
        if (!copy_of_fst_ItemStack.isEmpty()) {
            k = copy_of_fst_ItemStack.getOrDefault(DataComponentTypes.REPAIR_COST, 0);
            if (k < second_ItemStack.getOrDefault(DataComponentTypes.REPAIR_COST, 0)) {
                k = second_ItemStack.getOrDefault(DataComponentTypes.REPAIR_COST, 0);
            }
            if (j != intergerI || j == 0) {
                k = EnhancementTableScreenHandler.getNextCost(k,false);
            }
            copy_of_fst_ItemStack.set(DataComponentTypes.REPAIR_COST, k);
            EnchantmentHelper.set(copy_of_fst_ItemStack, builder.build());
        }

        this.output.setStack(0, copy_of_fst_ItemStack);

        // this.output.getStack(0).getItem()
        this.sendContentUpdates();
    }

     */
    @Override
    public void updateResult() {
        // directly access options locally using this.newItemName!
        // this.player.sendMessage(Text.literal("updateResult:"+this.newItemName));
        int k;
        ItemStack first_ItemStack = this.input.getStack(0);
        this.levelCost.set(1);
        long total_repair_cost = 0L;
        // If first slot is not enchantable, just return
        if (first_ItemStack.isEmpty() || !EnchantmentHelper.canHaveEnchantments(first_ItemStack)) {
            this.output.setStack(0, ItemStack.EMPTY);
            this.levelCost.set(0);
            return;
        }
        ItemStack copy_of_fst_ItemStack = first_ItemStack.copy();
        ItemStack second_ItemStack = this.input.getStack(1);
        // we only consider (snd is clover or empty) and fst contains enchantment
        if(!((second_ItemStack.isEmpty() || isClover(second_ItemStack)) && first_ItemStack.hasEnchantments())){
            this.output.setStack(0, ItemStack.EMPTY);
            this.levelCost.set(0);
            return;
        }
        ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(EnchantmentHelper.getEnchantments(copy_of_fst_ItemStack));
        total_repair_cost += (long) first_ItemStack.getOrDefault(DataComponentTypes.REPAIR_COST, 0);
        // update parameters
        if(second_ItemStack.isEmpty())
            this.repairItemUsage = 0;
        else
            this.repairItemUsage = 1;
        this.choosenEnchantmanet = getEnhancement(first_ItemStack,this.newItemName);// should not be null
        // update probability
        this.probability = getProbability(first_ItemStack,second_ItemStack,choosenEnchantmanet);
        player.sendMessage(Text.literal("Probability: "+this.probability));

        // update enchantment
        if(choosenEnchantmanet != null)
            builder.set(choosenEnchantmanet,builder.getLevel(choosenEnchantmanet)+1);
        // name settings
        if (this.newItemName == null || StringHelper.isBlank(this.newItemName)) {
            if (first_ItemStack.contains(DataComponentTypes.CUSTOM_NAME)) {
                copy_of_fst_ItemStack.remove(DataComponentTypes.CUSTOM_NAME);
            }
        } else if (!this.newItemName.equals(first_ItemStack.getName().getString())) {
            copy_of_fst_ItemStack.set(DataComponentTypes.CUSTOM_NAME, Text.literal(this.newItemName));
        }
        // set level cost, if too high them set EMPTY
        int t = (int)MathHelper.clamp(total_repair_cost, 0L, Integer.MAX_VALUE);
        this.levelCost.set(t);
        // copy_of_fst_ItemStack will be the final result, update cost for it..
        if (!copy_of_fst_ItemStack.isEmpty()) {
            k = copy_of_fst_ItemStack.getOrDefault(DataComponentTypes.REPAIR_COST, 0);
            copy_of_fst_ItemStack.set(DataComponentTypes.REPAIR_COST, k+1);
            EnchantmentHelper.set(copy_of_fst_ItemStack, builder.build());
        }
        this.output.setStack(0, copy_of_fst_ItemStack);
        this.sendContentUpdates();
    }


    public static int getNextCost(int cost,boolean penalty) {
        return penalty ? (int)Math.min((long)cost * 2L + 1L, Integer.MAX_VALUE) : cost;
    }

    public boolean setNewItemName(String newItemName) {
        String string = sanitize(newItemName);
        if (string == null || string.equals(this.newItemName)) {
            return false;
        }
        this.newItemName = string;
        if (this.getSlot(2).hasStack()) {
            ItemStack itemStack = this.getSlot(2).getStack();
            if (StringHelper.isBlank(string)) {
                itemStack.remove(DataComponentTypes.CUSTOM_NAME);
            } else {
                itemStack.set(DataComponentTypes.CUSTOM_NAME, Text.literal(string));
            }
        }
        this.updateResult();
        return true;
    }

    @Nullable
    private static String sanitize(String name) {
        String string = StringHelper.stripInvalidChars(name);
        if (string.length() <= 50) {
            return string;
        }
        return null;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        /*
        Player will bypass stack.setCount(0) via quick-move functionality. Ban it for output slot.
         */
        if(slot == OUTPUT_ID)
            return ItemStack.EMPTY;
        else
            return super.quickMove(player,slot);
    }
    public int getLevelCost() {
        return this.levelCost.get();
    }
    public void setChoosenEnchantmanetIndex(String s){
        choosenEnchantmanetIndex = s;
    }
    /*
    tool_name:id -> id
     */
    public Enchantment getEnhancement(ItemStack itemStack,String input){
        Enchantment defaultEnchantment = null;
        ItemEnchantmentsComponent itemStack_EnchantmentsComponent = EnchantmentHelper.getEnchantments(itemStack);
        // get default enchantment
        for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : itemStack_EnchantmentsComponent.getEnchantmentsMap()) {
            RegistryEntry registryEntry = (RegistryEntry) entry.getKey();
            Enchantment enchantment = (Enchantment) registryEntry.value();
            // get most powerful enchantment
            if(defaultEnchantment == null || (defaultEnchantment.getMaxLevel() < enchantment.getMaxLevel()))
                defaultEnchantment = enchantment;
        }
        // scan to get target enchantment
        for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : itemStack_EnchantmentsComponent.getEnchantmentsMap()) {
            RegistryEntry registryEntry = (RegistryEntry) entry.getKey();
            Enchantment enchantment = (Enchantment) registryEntry.value();

            String enchantment_reg_name = getLastPart(enchantment.getTranslationKey());
            String target_name = getLastPart(input);
            if(enchantment_reg_name.startsWith(target_name))
                return enchantment;
        }

        return defaultEnchantment;
    }
    public static String getLastPart(String input) {
        int lastDotIndex = input.lastIndexOf(".");
        if (lastDotIndex >= 0 && lastDotIndex < input.length() - 1) {
            return input.substring(lastDotIndex + 1).trim().toUpperCase();
        }
        return input.trim().toUpperCase();
    }
}


