package org.jurassicraft.server.item.itemblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.server.block.tree.JCDoubleSlabBlock;
import org.jurassicraft.server.block.tree.JCSlabBlock;
import org.jurassicraft.server.block.tree.JCSlabHalfBlock;

public class JCSlabItemBlock extends ItemBlock
{
    private final BlockSlab singleSlab;
    private final BlockSlab doubleSlab;

    public JCSlabItemBlock(Block block, JCSlabHalfBlock singleSlab, JCDoubleSlabBlock doubleSlab)
    {
        super(block);
        this.singleSlab = singleSlab;
        this.doubleSlab = doubleSlab;
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return this.singleSlab.getUnlocalizedName(stack.getMetadata());
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (stack.stackSize != 0 && playerIn.canPlayerEdit(pos.offset(facing), facing, stack))
        {
            Comparable<?> comparable = this.singleSlab.getTypeForItem(stack);
            IBlockState state = worldIn.getBlockState(pos);

            if (state.getBlock() == this.singleSlab)
            {
                IProperty<?> iproperty = this.singleSlab.getVariantProperty();
                Comparable<?> comparable1 = state.getValue(iproperty);
                JCSlabBlock.EnumBlockHalf half = state.getValue(BlockSlab.HALF);

                if ((facing == EnumFacing.UP && half == BlockSlab.EnumBlockHalf.BOTTOM || facing == EnumFacing.DOWN && half == BlockSlab.EnumBlockHalf.TOP) && comparable1 == comparable)
                {
                    IBlockState iblockstate1 = this.getState(iproperty, comparable1);
                    AxisAlignedBB axisalignedbb = iblockstate1.getSelectedBoundingBox(worldIn, pos);

                    if (axisalignedbb != Block.NULL_AABB && worldIn.checkNoEntityCollision(axisalignedbb.offset(pos)) && worldIn.setBlockState(pos, iblockstate1, 11))
                    {
                        SoundType soundtype = this.doubleSlab.getStepSound();
                        worldIn.playSound(playerIn, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                        --stack.stackSize;
                    }

                    return EnumActionResult.SUCCESS;
                }
            }

            return this.tryPlace(playerIn, stack, worldIn, pos.offset(facing), comparable) ? EnumActionResult.SUCCESS : super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack)
    {
        BlockPos blockpos = pos;
        IProperty<?> iproperty = this.singleSlab.getVariantProperty();
        Comparable<?> comparable = this.singleSlab.getTypeForItem(stack);
        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (iblockstate.getBlock() == this.singleSlab)
        {
            boolean flag = iblockstate.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.TOP;

            if ((side == EnumFacing.UP && !flag || side == EnumFacing.DOWN && flag) && comparable == iblockstate.getValue(iproperty))
            {
                return true;
            }
        }

        pos = pos.offset(side);
        IBlockState iblockstate1 = worldIn.getBlockState(pos);
        return iblockstate1.getBlock() == this.singleSlab && comparable == iblockstate1.getValue(iproperty) || super.canPlaceBlockOnSide(worldIn, blockpos, side, player, stack);
    }

    private boolean tryPlace(EntityPlayer player, ItemStack stack, World worldIn, BlockPos pos, Object itemSlabType)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (iblockstate.getBlock() == this.singleSlab)
        {
            Comparable<?> comparable = iblockstate.getValue(this.singleSlab.getVariantProperty());

            if (comparable == itemSlabType)
            {
                IBlockState iblockstate1 = this.getState(this.singleSlab.getVariantProperty(), comparable);
                AxisAlignedBB axisalignedbb = iblockstate1.getSelectedBoundingBox(worldIn, pos);

                if (axisalignedbb != Block.NULL_AABB && worldIn.checkNoEntityCollision(axisalignedbb.offset(pos)) && worldIn.setBlockState(pos, iblockstate1, 11))
                {
                    SoundType soundtype = this.doubleSlab.getStepSound();
                    worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                    --stack.stackSize;
                }

                return true;
            }
        }

        return false;
    }

    protected <T extends Comparable<T>> IBlockState getState(IProperty<T> property, Comparable<?> comparable)
    {
        return this.doubleSlab.getDefaultState().withProperty(property, (T)comparable);
    }
}
