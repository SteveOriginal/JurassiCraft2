package org.jurassicraft.server.plant;

import net.minecraft.block.Block;
import org.jurassicraft.server.block.JCBlockRegistry;

public class CryPansyPlant extends Plant
{
    @Override
    public EnumPlantType getPlantType()
    {
        return EnumPlantType.FLOWER;
    }

    @Override
    public String getName()
    {
        return "Cry Pansy";
    }

    @Override
    public Block getBlock()
    {
        return JCBlockRegistry.cry_pansy;
    }
}
