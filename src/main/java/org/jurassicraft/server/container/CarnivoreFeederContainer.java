package org.jurassicraft.server.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class CarnivoreFeederContainer extends Container
{

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return false;
    }
}
