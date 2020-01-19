package droid.mobile.games.common.manager;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import droid.mobile.games.common.log.LogRecord;

import java.io.File;
import java.util.List;

public class PluginManager {

    public static final PluginManager manager(){
        return Holder.holder;
    }

    private IPluginLoader mLoader;

    private PluginManager() {
        super();
    }

    public final void initialize(IPluginLoader loader){
        mLoader = loader;
    }

    public final IPluginLoader loader(){
        return mLoader;
    }

    private static class Holder{
        private static final PluginManager holder = new PluginManager();
    }

    public interface ILoadedApk{
        String getLocation();
        String getPackageName();
        AssetManager getAssets();
        Resources getResources();
        ClassLoader getClassLoader();
        Context getHostContext();
        Context getPluginContext();
        Application getApplication();
        ApplicationInfo getApplicationInfo();
        PackageInfo getPackageInfo();
        IPluginLoader getPluginLoader();
    }

    public interface IPluginLoader{
        void doInitialzie(Context context,Config config);
        Config getConfig();
        void loadPlugin(File apk);
        ILoadedApk getLoadedApk(String packageName);
        List<ILoadedApk> getAllLoadedApk();
    }

    public static class Config{
        private String jniOutputDir = "jni";
        private String dexOutputDir = "dex";
        private String pluginPackageName = "droid.mobile.games.app";
        private String sharedPreferences = "droid_mobile_settings";
        private int maxSingleTaskStubActivityCount = 0;
        private int maxSingleTopStubActivityCount = 2;
        private int maxSingleInstanceStubActivityCount =  0;

        private boolean debug = true;
        private boolean hook = false;
        private boolean combineClassLoader = true;
        private boolean combineResources = false;

        public String getJniOutputDir() {
            return jniOutputDir;
        }

        public Config setJniOutputDir(String jniOutputDir) {
            this.jniOutputDir = jniOutputDir;
            LogRecord.d("so库加载地址:" + jniOutputDir);
            return this;
        }

        public String getDexOutputDir() {
            return dexOutputDir;
        }

        public Config setDexOutputDir(String dexOutputDir) {
            this.dexOutputDir = dexOutputDir;
            LogRecord.d("dex加载地址:" + dexOutputDir);
            return this;
        }

        public String getPluginPackageName() {
            return pluginPackageName;
        }

        public Config setPluginPackageName(String pluginPackageName) {
            this.pluginPackageName = pluginPackageName;
            LogRecord.d("插件包路径:" + pluginPackageName);
            return this;
        }

        public String getSharedPreferences() {
            return sharedPreferences;
        }

        public Config setSharedPreferences(String sharedPreferences) {
            this.sharedPreferences = sharedPreferences;
            LogRecord.d("SP文件名:" + sharedPreferences);
            return this;
        }

        public int getMaxSingleTaskStubActivityCount() {
            return maxSingleTaskStubActivityCount;
        }

        public Config setMaxSingleTaskStubActivityCount(int maxSingleTaskStubActivityCount) {
            this.maxSingleTaskStubActivityCount = maxSingleTaskStubActivityCount;
            LogRecord.d("singleTask Activity数量:" + maxSingleTaskStubActivityCount);
            return this;
        }

        public int getMaxSingleTopStubActivityCount() {
            return maxSingleTopStubActivityCount;
        }

        public Config setMaxSingleTopStubActivityCount(int maxSingleTopStubActivityCount) {
            this.maxSingleTopStubActivityCount = maxSingleTopStubActivityCount;
            LogRecord.d("singleTop Activity数量:" + maxSingleTopStubActivityCount);
            return this;
        }

        public int getMaxSingleInstanceStubActivityCount() {
            return maxSingleInstanceStubActivityCount;
        }

        public Config setMaxSingleInstanceStubActivityCount(int maxSingleInstanceStubActivityCount) {
            this.maxSingleInstanceStubActivityCount = maxSingleInstanceStubActivityCount;
            LogRecord.d("singleInstance Activity数量:" + maxSingleInstanceStubActivityCount);
            return this;
        }

        public boolean isDebug() {
            return debug;
        }

        public Config setDebug(boolean debug) {
            this.debug = debug;
            LogRecord.d("Debug模式:" + debug);
            return this;
        }

        public boolean isHook() {
            return hook;
        }

        public Config setHook(boolean hook) {
            this.hook = hook;
            LogRecord.d("Hook模式:" + hook);
            return this;
        }

        public boolean isCombineClassLoader() {
            return combineClassLoader;
        }

        public Config setCombineClassLoader(boolean combineClassLoader) {
            this.combineClassLoader = combineClassLoader;
            LogRecord.d("合并classLoader:" + combineClassLoader);
            return this;
        }

        public boolean isCombineResources() {
            return combineResources;
        }

        public Config setCombineResources(boolean combineResources) {
            this.combineResources = combineResources;
            LogRecord.d("合并Resources:" + combineResources);
            return this;
        }
    }
}
