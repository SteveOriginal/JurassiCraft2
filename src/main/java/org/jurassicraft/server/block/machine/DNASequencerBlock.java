package org.jurassicraft.server.block.machine;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.server.block.JCBlockRegistry;
import org.jurassicraft.server.block.OrientedBlock;
import org.jurassicraft.server.creativetab.JCCreativeTabs;
import org.jurassicraft.server.tileentity.DNASequencerTile;

import java.util.Random;

public class DNASequencerBlock extends OrientedBlock
{
    public DNASequencerBlock()
    {
        super(Material.iron);
        this.setUnlocalizedName("dna_sequencer");
        this.setHardness(2.0F);
        this.setStepSound(Block.soundTypeMetal);
        this.setCreativeTab(JCCreativeTabs.blocks);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof DNASequencerTile)
            {
                ((DNASequencerTile) tileentity).setCustomInventoryName(stack.getDisplayName());
            }
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof DNASequencerTile)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, (DNASequencerTile) tileentity);
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(JCBlockRegistry.dna_sequencer);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Item.getItemFromBlock(JCBlockRegistry.dna_sequencer);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else if (!player.isSneaking())
        {
            TileEntity tileEntity = world.getTileEntity(pos);

            if (tileEntity instanceof DNASequencerTile)
            {
                DNASequencerTile dnaSequencer = (DNASequencerTile) tileEntity;

                if (dnaSequencer.isUseableByPlayer(player))
                {
                    player.openGui(JurassiCraft.instance, 2, world, pos.getX(), pos.getY(), pos.getZ());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new DNASequencerTile();
    }
}
