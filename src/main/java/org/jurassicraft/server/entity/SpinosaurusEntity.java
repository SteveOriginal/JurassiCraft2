package org.jurassicraft.server.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.jurassicraft.client.animation.Animations;
import org.jurassicraft.server.entity.ai.animations.JCNonAutoAnimSoundBase;
import org.jurassicraft.server.entity.base.AggressiveDinosaurEntity;

import java.util.Random;

public class SpinosaurusEntity extends AggressiveDinosaurEntity // implements IEntityAICreature, IOmnivore
{
    private static final String[] hurtSounds = new String[] { "spinosaurus_hurt_1" };
    private static final String[] livingSounds = new String[] { "spinosaurus_living_1", "spinosaurus_living_2", "spinosaurus_living_3", "spinosaurus_living_4" };
    private static final String[] deathSounds = new String[] { "spinosaurus_death_1", "spinosaurus_death_2" };

    private static final Class[] targets = { TyrannosaurusEntity.class, CompsognathusEntity.class, AnkylosaurusEntity.class, EntityPlayer.class, DilophosaurusEntity.class, DimorphodonEntity.class, DodoEntity.class, LeaellynasauraEntity.class, LudodactylusEntity.class, HypsilophodonEntity.class, GallimimusEntity.class, SegisaurusEntity.class, ProtoceratopsEntity.class, ParasaurolophusEntity.class, OthnieliaEntity.class, MicroceratusEntity.class, TriceratopsEntity.class, StegosaurusEntity.class, BrachiosaurusEntity.class, ApatosaurusEntity.class, RugopsEntity.class, HerrerasaurusEntity.class, VelociraptorEntity.class, AchillobatorEntity.class, CarnotaurusEntity.class };

    private int stepCount = 0;

    public SpinosaurusEntity(World world)
    {
        super(world);

        for (int i = 0; i < targets.length; i++)
        {
            this.addAIForAttackTargets(targets[i], new Random().nextInt(3) + 1);
        }

        tasks.addTask(2, new JCNonAutoAnimSoundBase(this, 75, Animations.INJURED.get(), 750, "jurassicraft:spinosaurus_hurt_1", 1.5F));
    }

    @Override
    public int getTailBoxCount()
    {
        return 6;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        /** Step Sound */
        if (this.moveForward > 0 && this.stepCount <= 0)
        {
            this.playSound("jurassicraft:stomp", (float) transitionFromAge(0.1F, 1.0F), this.getSoundPitch());
            stepCount = 65;
        }

        this.stepCount -= this.moveForward * 9.5;
    }

    @Override
    public String getLivingSound()
    {
        return randomSound(livingSounds);
    }

    @Override
    public String getHurtSound()
    {
        return randomSound(hurtSounds);
    }

    @Override
    public String getDeathSound()
    {
        return randomSound(deathSounds);
    }
}
