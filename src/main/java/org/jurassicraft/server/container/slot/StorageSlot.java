package org.jurassicraft.server.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jurassicraft.server.item.JCItemRegistry;

public class StorageSlot extends Slot
{
    private boolean stored;

    public StorageSlot(IInventory inventory, int slotIndex, int xPosition, int yPosition, boolean stored)
    {
        super(inventory, slotIndex, xPosition, yPosition);

        this.stored = stored;
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        if (stored)
        {
            return stack.getItem() == JCItemRegistry.storage_disc && (stack.getTagCompound() != null && stack.getTagCompound().hasKey("DNAQuality"));
        }
        else
        {
            return stack.getItem() == JCItemRegistry.storage_disc && (stack.getTagCompound() == null || !stack.getTagCompound().hasKey("DNAQuality"));
        }
    }
}
