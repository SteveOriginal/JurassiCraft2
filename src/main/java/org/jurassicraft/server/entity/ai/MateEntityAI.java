package org.jurassicraft.server.entity.ai;

import net.ilexiconn.llibrary.common.animation.Animation;
import net.minecraft.entity.ai.EntityAIBase;
import org.jurassicraft.client.animation.Animations;
import org.jurassicraft.server.entity.base.DinosaurEntity;

public class MateEntityAI extends EntityAIBase
{
    protected DinosaurEntity dinosaur;
    protected DinosaurEntity mate;

    public MateEntityAI(DinosaurEntity dinosaur)
    {
        this.dinosaur = dinosaur;
    }

    @Override
    public boolean shouldExecute()
    {
        // int minEnergy = 12000;
        //
        // if (dinosaur.getFood() > minEnergy)
        // {
        // World world = dinosaur.worldObj;
        //
        // double posX = dinosaur.posX;
        // double posY = dinosaur.posY;
        // double posZ = dinosaur.posZ;
        //
        // List<DinosaurEntity> entities = world.getEntitiesWithinAABB(dinosaur.getClass(), AxisAlignedBB.fromBounds(posX - 16, posY - 16, posZ - 16, posX + 16, posY + 16, posZ + 16));
        //
        // for (DinosaurEntity entity : entities)
        // {
        // if (entity.isMale() != dinosaur.isMale() && entity.getFood() > minEnergy)
        // {
        // dinosaur.getNavigator().tryMoveToEntityLiving(entity, 1.0D);
        // mate = entity;
        //
        // return true;
        // }
        // }
        // }

        return false;
    }

    @Override
    public void updateTask()
    {
        if (dinosaur.getEntityBoundingBox().intersectsWith(mate.getEntityBoundingBox().expand(0.5D, 0.5D, 0.5D)))
        {
            Animation.sendAnimationPacket(dinosaur, Animations.MATING.get());

            dinosaur.getMetabolism().decreaseFood(1000);
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
        return dinosaur != null && !this.dinosaur.getNavigator().noPath() && mate != null && !mate.isDead;
    }
}
