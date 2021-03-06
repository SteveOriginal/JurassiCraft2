package org.jurassicraft.server.entity.ai.flyer;

import net.minecraft.entity.ai.EntityAIBase;
import org.jurassicraft.server.entity.base.AggressiveFlyingDinosaurEntity;

public class TakeOffEntityAI extends EntityAIBase
{
    protected AggressiveFlyingDinosaurEntity flyer;

    public TakeOffEntityAI(AggressiveFlyingDinosaurEntity dinosaur)
    {
        this.flyer = dinosaur;
    }

    @Override
    public boolean shouldExecute()
    {
        return !flyer.isFlying() && flyer.onGround && flyer.getRNG().nextFloat() < 0.01F;
    }

    @Override
    public void updateTask()
    {
        flyer.rotationPitch = 80;
        flyer.setFlying(true);

        if (!flyer.onGround && flyer.posY - flyer.worldObj.getHorizon() > 10)
        {
            flyer.rotationPitch = 0;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
        return flyer != null && !this.flyer.getNavigator().noPath();
    }
}
