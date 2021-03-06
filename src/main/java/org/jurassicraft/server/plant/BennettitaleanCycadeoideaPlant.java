package org.jurassicraft.server.plant;

import net.minecraft.block.Block;
import org.jurassicraft.server.block.JCBlockRegistry;

public class BennettitaleanCycadeoideaPlant extends Plant
{
    @Override
    public EnumPlantType getPlantType()
    {
        return EnumPlantType.FERN;
    }

    @Override
    public String getName()
    {
        return "Bennettitalean Cycadeoidea";
    }

    @Override
    public Block getBlock()
    {
        return JCBlockRegistry.bennettitalean_cycadeoidea;
    }
}
