package org.jurassicraft.server.entity.ai.animations;

import net.ilexiconn.llibrary.common.animation.Animation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import org.jurassicraft.client.animation.Animations;
import org.jurassicraft.server.animation.AIAnimation;
import org.jurassicraft.server.entity.GallimimusEntity;
import org.jurassicraft.server.entity.TyrannosaurusEntity;
import org.jurassicraft.server.entity.base.DinosaurEntity;

public class BiteAnimationAI extends AIAnimation
{
    private final DinosaurEntity entityBiting;
    private EntityLivingBase entityTarget;
    private final int duration;
    private boolean eat;
    protected Animation animation;

    public BiteAnimationAI(DinosaurEntity dino, int duration)
    {
        super(dino);
        this.entityBiting = dino;
        this.entityTarget = null;
        this.duration = duration;
        eat = false;
        this.animation = Animations.ATTACKING.get();
    }

    @Override
    public Animation getAnimation()
    {
        return animation;
    }

    @Override
    public boolean isAutomatic()
    {
        return true;
    }

    @Override
    public int getDuration()
    {
        return this.duration;
    }

    @Override
    public void startExecuting()
    {
        super.startExecuting();
        this.entityTarget = this.entityBiting.getAttackTarget();
    }

    @Override
    public void updateTask()
    {
        if (this.entityTarget != null)
        {
            if (this.entityBiting.getAnimationTick() < ((this.duration / 2) - 2))
            {
                this.entityBiting.getLookHelper().setLookPositionWithEntity(this.entityTarget, 30F, 30F);
            }

            if (this.entityBiting.getAnimationTick() == ((this.duration / 2) - 2))
            {
                float damage = (float) getCreatureSpeed();

                if ((this.entityTarget.getHealth() - damage <= 0.0F) && this.entityBiting instanceof TyrannosaurusEntity && this.entityTarget instanceof GallimimusEntity)
                {
                    eat = true;
                }
                else
                {
                    eat = false;
                    this.entityTarget.attackEntityFrom(DamageSource.causeMobDamage(this.entityBiting), damage);
                }
            }
        }
    }

    public double getCreatureSpeed()
    {
        return (double) ((int) (100 * this.entityBiting.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue())) / 100;
    }

    @Override
    public void resetTask()
    {
        /**
         * Eating animations, should not use super.resetTask, or the eating animation ID will be replaced
         */
        // if (eat && this.entityTarget instanceof GallimimusEntity &&
        // entityTarget.ridingEntity == null)
        // {
        // super.resetTask();
        // this.entityTarget.mountEntity(this.entityBiting);
        // this.entityBiting.setAttackTarget(null);
        // this.entityBiting.getNavigator().clearPathEntity();
        // entityBiting.setAnimationTick(0);
        // AnimationHandler.sendAnimationPacket(this.entityBiting,
        // JurassiCraftAnimationIDs.EATING.animID());
        // GallimimusEntity gallimimus = (GallimimusEntity) this.entityTarget;
        // gallimimus.setAttackTarget(null);
        // gallimimus.getNavigator().clearPathEntity();
        // AnimationHandler.sendAnimationPacket(gallimimus,
        // JurassiCraftAnimationIDs.BEING_EATEN.animID());
        // this.entityTarget = null;
        // return;
        // }

        this.entityTarget = null;
        super.resetTask();
    }
}
