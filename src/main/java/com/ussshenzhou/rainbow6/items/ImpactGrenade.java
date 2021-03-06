package com.ussshenzhou.rainbow6.items;

import com.ussshenzhou.rainbow6.entities.ImpactGrenadeEntity;
import com.ussshenzhou.rainbow6.entities.ModEntityTypes;
import com.ussshenzhou.rainbow6.util.ModItemGroups;
import com.ussshenzhou.rainbow6.util.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
/**
 * @author USS_Shenzhou
 */
public class ImpactGrenade extends Item {

    public ImpactGrenade() {
        super(new Properties().group(ModItemGroups.Group1));
        this.setRegistryName("impactgrenade");
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (!playerIn.abilities.isCreativeMode) {
            itemstack.shrink(1);
        }
        worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), ModSounds.IMPACT_GRENADE_THROW, SoundCategory.PLAYERS, 1.0f, 1.0f);
        if (!worldIn.isRemote) {
            ImpactGrenadeEntity impactgrenadeentity = new ImpactGrenadeEntity(ModEntityTypes.impactGrenadeEntityType,playerIn,worldIn);
            impactgrenadeentity.setItem(itemstack);
            impactgrenadeentity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 1.0F, 0.1F);
            worldIn.addEntity(impactgrenadeentity);
        }

        playerIn.addStat(Stats.ITEM_USED.get(this));
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }
}
