package org.jurassicraft.server.block.tree;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.server.creativetab.JCCreativeTabs;
import org.jurassicraft.server.world.jurdstrees.trees.WorldGenCalamites;
import org.jurassicraft.server.world.jurdstrees.trees.WorldGenGinkgo;

import java.util.List;
import java.util.Random;

public class JCSaplingBlock extends BlockBush implements IGrowable
{
    private WoodType treeType;

    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

    public JCSaplingBlock(WoodType type, String name)
    {
        super();
        treeType = type;
        setUnlocalizedName(name + "_sapling");
        this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, Integer.valueOf(0)));
        this.setStepSound(Block.soundTypeGrass);

        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);

        this.setCreativeTab(JCCreativeTabs.plants);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            super.updateTick(worldIn, pos, state, rand);

            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
            {
                this.grow(worldIn, pos, state, rand);
            }
        }
    }

    public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (((Integer) state.getValue(STAGE)).intValue() == 0)
        {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
        }
        else
        {

            switch (treeType)
            {

                case GINKGO:
                    WorldGenGinkgo ginkgogen = new WorldGenGinkgo(0);
                    ginkgogen.generate(worldIn, rand, pos);
                    break;
                case CALAMITES:
                    WorldGenCalamites calamitesgen = new WorldGenCalamites(1);
                    calamitesgen.generate(worldIn, rand, pos);
                    break;
            }

            // this.generateTree(worldIn, pos, state, rand);
        }
    }

    /*
     * public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) { WorldGenAbstractTree gen; switch (treeType) { case ASPEN: gen = HighlandsGenerators.aspenSapling; break; case POPLAR: gen = HighlandsGenerators.poplarSapling; break; case EUCA: gen = HighlandsGenerators.eucalyptusSapling; break; case PALM: gen = HighlandsGenerators.palmSapling; break; case FIR: gen = HighlandsGenerators.firSapling; break; case REDWOOD: gen = HighlandsGenerators.redwoodSapling; break; case BAMBOO: gen = HighlandsGenerators.bambooSapling; break; default: return; } boolean flag = gen.generate(worldIn, rand, pos); // if tree is not in legal position, reset sapling. if (!flag) worldIn.setBlockState(pos, state); }
     */

    public int damageDropped(IBlockState state)
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
    {
        list.add(new ItemStack(itemIn, 1, 0));
    }

    /**
     * Whether this IGrowable can grow
     */
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return (double) worldIn.rand.nextFloat() < 0.45D;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        this.grow(worldIn, pos, state, rand);
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i |= ((Integer) state.getValue(STAGE)).intValue() << 3;
        return i;
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { STAGE });
    }
}
