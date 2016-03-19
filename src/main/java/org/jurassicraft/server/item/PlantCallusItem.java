package org.jurassicraft.server.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jurassicraft.server.lang.AdvLang;
import org.jurassicraft.server.plant.JCPlantRegistry;
import org.jurassicraft.server.plant.Plant;

public class PlantCallusItem extends Item
{
    public String getItemStackDisplayName(ItemStack stack)
    {
        return new AdvLang("item.plant_callus.name").withProperty("plant", "plants." + JCPlantRegistry.getPlantById(stack.getItemDamage()).getName().toLowerCase().replaceAll(" ", "_") + ".name").build();
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (side == EnumFacing.UP && player.canPlayerEdit(pos.offset(side), side, stack))
        {
            if (world.isAirBlock(pos.offset(side)) && world.getBlockState(pos).getBlock() == Blocks.farmland)
            {
                Plant plant = JCPlantRegistry.getPlantById(stack.getItemDamage());

                if (plant != null)
                {
                    world.setBlockState(pos.up(), plant.getBlock().getDefaultState());
                    world.setBlockState(pos, Blocks.dirt.getDefaultState());
                    --stack.stackSize;
                    return EnumActionResult.SUCCESS;
                }
            }
        }

        return EnumActionResult.PASS;
    }
}
