package org.jurassicraft.server.plant;

import net.minecraft.block.Block;
import org.jurassicraft.server.block.JCBlockRegistry;

public class ScalyTreeFernPlant extends Plant
{
    @Override
    public EnumPlantType getPlantType()
    {
        return EnumPlantType.FERN;
    }

    @Override
    public String getName()
    {
        return "Scaly Tree Fern";
    }

    @Override
    public Block getBlock()
    {
        return JCBlockRegistry.scaly_tree_fern;
    }
}
