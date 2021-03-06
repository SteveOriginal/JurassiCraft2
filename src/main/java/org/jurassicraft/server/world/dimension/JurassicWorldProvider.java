package org.jurassicraft.server.world.dimension;

import net.minecraft.world.WorldProvider;

public class JurassicWorldProvider extends WorldProvider
{
    @Override
    public String getDimensionName()
    {
        return "JurassiCraft";
    }

    @Override
    public String getInternalNameSuffix()
    {
        return "";
    }

    @Override
    public String getWelcomeMessage()
    {
        return "Welcome to JurassiCraft.";
    }

    @Override
    protected void registerWorldChunkManager()
    {
        this.worldChunkMgr = new JurassicWorldChunkManager(this.worldObj);
    }
    //
    // @Override
    // public IChunkProvider createChunkGenerator()
    // {
    // return new JurassicChunkGenerator(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), this.worldObj.getWorldInfo().getGeneratorOptions());
    // }

}
