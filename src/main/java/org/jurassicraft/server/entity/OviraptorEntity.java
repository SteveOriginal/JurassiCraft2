package org.jurassicraft.server.entity;

import net.minecraft.world.World;
import org.jurassicraft.server.entity.base.DefensiveDinosaurEntity;

public class OviraptorEntity extends DefensiveDinosaurEntity // implements IEntityAICreature, IHerbivore
{
    public OviraptorEntity(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 0;
    }
}
