package droid.mobile.games.common.manager;

import android.app.Activity;
import droid.mobile.games.common.keep.Action;
import droid.mobile.games.common.keep.Consumer;


public class SplashManager {
    public static final SplashManager manager() {
        return Holder.holder;
    }

    private ISplashLoader mLoader;

    private SplashManager() {
        super();
    }

    public final void initialize(ISplashLoader loader){
        mLoader = loader;
    }

    public final ISplashLoader loader(){
        return mLoader;
    }

    public interface ISplashLoader{
        void show(Activity activity, Consumer<Void> onSuccess,Consumer<String> onError);
    }

    private static class Holder{
        private static final SplashManager holder = new SplashManager();
    }
}
