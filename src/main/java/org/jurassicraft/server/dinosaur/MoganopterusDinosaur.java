package org.jurassicraft.server.dinosaur;

import org.jurassicraft.server.entity.MoganopterusEntity;
import org.jurassicraft.server.entity.base.EnumDiet;
import org.jurassicraft.server.period.EnumTimePeriod;

public class MoganopterusDinosaur extends Dinosaur
{
    public MoganopterusDinosaur()
    {
        super();

        this.setName("Moganopterus");
        this.setDinosaurClass(MoganopterusEntity.class);
        this.setTimePeriod(EnumTimePeriod.CRETACEOUS);
        this.setEggColorMale(0xE6E2D8, 0xD67F5C);
        this.setEggColorFemale(0xE0DED3, 0xD37B58);
        this.setHealth(1, 10);
        this.setSpeed(0.46, 0.40);
        this.setStrength(1, 5);
        this.setMaximumAge(fromDays(40));
        this.setEyeHeight(0.225F, 1.3F);
        this.setSizeX(0.3F, 1.0F);
        this.setSizeY(0.3F, 1.3F);
        this.setStorage(27);
        this.setDiet(EnumDiet.CARNIVORE);
        this.setBones("leg_bones", "pelvis", "skull", "tail_vertebrae", "teeth", "wing_bones");
        this.setHeadCubeName("Head");
        this.setScale(0.725F, 0.2F);
    }
}
