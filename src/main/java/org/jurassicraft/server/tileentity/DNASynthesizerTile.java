package org.jurassicraft.server.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.server.container.DNASynthesizerContainer;
import org.jurassicraft.server.item.JCItemRegistry;

public class DNASynthesizerTile extends MachineBaseTile
{
    private int[] inputs = new int[] { 0, 1, 2 };
    private int[] outputs = new int[] { 3, 4, 5, 6 };

    private ItemStack[] slots = new ItemStack[7];

    @Override
    protected int getProcess(int slot)
    {
        return 0;
    }

    @Override
    protected boolean canProcess(int process)
    {
        ItemStack storage = slots[0];
        ItemStack testTube = slots[1];
        ItemStack baseMaterial = slots[2];

        if (storage != null && storage.getItem() == JCItemRegistry.storage_disc && testTube != null && testTube.getItem() == JCItemRegistry.empty_test_tube && baseMaterial != null && baseMaterial.getItem() == JCItemRegistry.dna_base && (storage.getTagCompound() != null && storage.getTagCompound().hasKey("DNAQuality")))
        {
            ItemStack output = null;

            if (storage.getTagCompound().getInteger("DNAQuality") == 100)
            {
                if (storage.getTagCompound().getString("StorageId").equalsIgnoreCase("DinoDNA"))
                {
                    output = new ItemStack(JCItemRegistry.dna, 1, storage.getItemDamage());
                    output.setTagCompound(storage.getTagCompound());
                }
                else
                {
                    output = new ItemStack(JCItemRegistry.plant_dna, 1, storage.getItemDamage());
                    output.setTagCompound(storage.getTagCompound());
                }
            }

            return output != null && hasOutputSlot(output);
        }

        return false;
    }

    @Override
    protected void processItem(int process)
    {
        if (this.canProcess(process))
        {
            ItemStack storageDisc = slots[0];

            ItemStack output = new ItemStack(storageDisc.getTagCompound().getString("StorageId").equalsIgnoreCase("DinoDNA") ? JCItemRegistry.dna : JCItemRegistry.plant_dna, 1, storageDisc.getItemDamage());
            output.setTagCompound(storageDisc.getTagCompound());

            int emptySlot = getOutputSlot(output);

            if (emptySlot != -1)
            {
                mergeStack(emptySlot, output);

                decreaseStackSize(1);
                decreaseStackSize(2);
            }
        }
    }

    @Override
    protected int getMainInput(int process)
    {
        return 0;
    }

    @Override
    protected int getMainOutput(int process)
    {
        return 1;
    }

    @Override
    protected int getStackProcessTime(ItemStack stack)
    {
        return 1000;
    }

    @Override
    protected int getProcessCount()
    {
        return 1;
    }

    @Override
    protected int[] getInputs()
    {
        return inputs;
    }

    @Override
    protected int[] getOutputs()
    {
        return outputs;
    }

    @Override
    protected ItemStack[] getSlots()
    {
        return slots;
    }

    @Override
    protected void setSlots(ItemStack[] slots)
    {
        this.slots = slots;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new DNASynthesizerContainer(playerInventory, this);
    }

    @Override
    public String getGuiID()
    {
        return JurassiCraft.MODID + ":dna_synthesizer";
    }

    public String getName()
    {
        return hasCustomName() ? customName : "container.dna_synthesizer";
    }
}
