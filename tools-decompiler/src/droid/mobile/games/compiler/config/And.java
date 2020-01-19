package droid.mobile.games.compiler.config;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import static droid.mobile.games.compiler.util.FileUtils.getConfigDir;
import static droid.mobile.games.compiler.util.FileUtils.json;

public class And {
    public static List<Item> getItems(){
        return json(getConfigDir(),"android.json", new TypeToken<List<Item>>(){}.getType());
    }
    public static class Item{
        public String name;
        public String description;
        public String workspace;
    }
}
