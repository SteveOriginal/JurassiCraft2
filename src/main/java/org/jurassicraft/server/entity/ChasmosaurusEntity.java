package org.jurassicraft.server.entity;

import net.minecraft.world.World;
import org.jurassicraft.server.entity.base.DefensiveDinosaurEntity;

public class ChasmosaurusEntity extends DefensiveDinosaurEntity
{
    public ChasmosaurusEntity(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 5;
    }
}
