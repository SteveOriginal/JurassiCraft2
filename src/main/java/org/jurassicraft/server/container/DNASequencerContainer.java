package org.jurassicraft.server.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.jurassicraft.server.container.slot.DNAHolderSlot;
import org.jurassicraft.server.container.slot.StorageSlot;
import org.jurassicraft.server.tileentity.DNASequencerTile;

public class DNASequencerContainer extends Container
{
    private DNASequencerTile dnaSequencer;

    public DNASequencerContainer(InventoryPlayer playerInventory, TileEntity tileEntity)
    {
        this.dnaSequencer = (DNASequencerTile) tileEntity;

        this.addSlotToContainer(new DNAHolderSlot(dnaSequencer, 0, 44, 16));
        this.addSlotToContainer(new StorageSlot(dnaSequencer, 1, 66, 16, false));
        this.addSlotToContainer(new DNAHolderSlot(dnaSequencer, 2, 44, 36));
        this.addSlotToContainer(new StorageSlot(dnaSequencer, 3, 66, 36, false));
        this.addSlotToContainer(new DNAHolderSlot(dnaSequencer, 4, 44, 56));
        this.addSlotToContainer(new StorageSlot(dnaSequencer, 5, 66, 56, false));

        this.addSlotToContainer(new StorageSlot(dnaSequencer, 6, 113, 16, true));
        this.addSlotToContainer(new StorageSlot(dnaSequencer, 7, 113, 36, true));
        this.addSlotToContainer(new StorageSlot(dnaSequencer, 8, 113, 56, true));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);

        if (!player.worldObj.isRemote)
        {
            dnaSequencer.closeInventory(player);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return dnaSequencer.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int i)
    {
        return null;
    }
}
