package org.jurassicraft.server.block.tree;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class JCDoubleSlabBlock extends JCSlabBlock
{
    private Block singleSlab;

    public JCDoubleSlabBlock(TreeType type, Block slab, IBlockState state)
    {
        super(state);
        this.setUnlocalizedName(type.name().toLowerCase() + "_double_slab");
        this.singleSlab = slab;
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(singleSlab);
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Item.getItemFromBlock(singleSlab);
    }

    public boolean isDouble()
    {
        return true;
    }
}
