package droid.mobile.games.compiler.config;

import static droid.mobile.games.compiler.util.FileUtils.*;

public class Bin {
    private static String apktool;
    private static String dex2jar;

    static {
        Data data = json(getConfigDir(),"bin.json",Data.class);
        apktool = getDir("tools",data.apktool.dir) + data.apktool.name;
        dex2jar = getDir("tools",data.dex2jar.dir) + data.dex2jar.name;
    }

    public static String getApktool(){
        return apktool;
    }

    public static String getDex2jar(){
        return dex2jar;
    }

    public static class Data{
        private Item apktool;
        private Item dex2jar;
    }

    public static class Item{
        private String dir;
        private String name;
    }

}
