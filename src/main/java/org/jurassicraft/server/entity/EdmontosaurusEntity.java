package org.jurassicraft.server.entity;

import net.minecraft.world.World;
import org.jurassicraft.server.entity.base.DefensiveDinosaurEntity;

public class EdmontosaurusEntity extends DefensiveDinosaurEntity
{
    public EdmontosaurusEntity(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 6;
    }
}
