package org.jurassicraft.server.entity.ai.animations;

import net.ilexiconn.llibrary.common.animation.Animation;
import net.ilexiconn.llibrary.common.animation.IAnimated;
import org.jurassicraft.client.animation.Animations;
import org.jurassicraft.server.animation.AIAnimation;
import org.jurassicraft.server.entity.base.DinosaurEntity;

/**
 * Created by jnad325 on 7/23/15.
 */
public class JCNonAutoAnimBase extends AIAnimation
{
    protected DinosaurEntity animatingEntity;
    protected int duration;
    protected Animation animation;
    protected int chance;

    public JCNonAutoAnimBase(IAnimated entity, int duration, Animation animation, int chance)
    {
        super(entity);
        this.duration = duration;
        animatingEntity = (DinosaurEntity) entity;
        this.animation = animation;
        this.chance = chance;
    }

    @Override
    public Animation getAnimation()
    {
        return animation;
    }

    @Override
    public boolean isAutomatic()
    {
        return false;
    }

    @Override
    public int getDuration()
    {
        return duration;
    }

    @Override
    public boolean shouldExecute()
    {
        return animatingEntity.getAnimation() == Animations.IDLE.get() && animatingEntity.getRNG().nextInt(chance) == 0;
    }

    @Override
    public void startExecuting()
    {
        super.startExecuting();
        animatingEntity.currentAnim = this;
    }

    @Override
    public void resetTask()
    {
        super.resetTask();
        animatingEntity.currentAnim = null;
    }
}
