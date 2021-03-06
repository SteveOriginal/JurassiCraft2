package org.jurassicraft.server.paleopad;

import net.ilexiconn.llibrary.common.content.IContentHandler;

import java.util.ArrayList;
import java.util.List;

public class AppRegistry implements IContentHandler
{
    private static List<App> registeredApps = new ArrayList<App>();
    public static App file_explorer;
    public static App flappy_dino;
    public static App minimap;
    public static App security;

    public void registerApp(App app)
    {
        registeredApps.add(app);
    }

    @Override
    public void init()
    {
        file_explorer = new FileExplorerApp();
        flappy_dino = new FlappyDinoApp();
        minimap = new MinimapApp();
        security = new SecurityApp();
    }

    @Override
    public void gameRegistry() throws Exception
    {
        registerApp(file_explorer);
        registerApp(flappy_dino);
        registerApp(minimap);
        registerApp(security);
    }

    public static List<App> getApps()
    {
        return registeredApps;
    }
}
