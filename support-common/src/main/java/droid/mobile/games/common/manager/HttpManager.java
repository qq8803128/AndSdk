package droid.mobile.games.common.manager;

public class HttpManager {
    public static final HttpManager manager() {
        return Holder.holder;
    }

    private IHttpLoader mLoader;
    private HttpManager() {
        super();
    }

    public final void initialize(IHttpLoader loader){
        mLoader = loader;
    }

    public final IHttpLoader loader(){
        return mLoader;
    }

    private static class Holder{
        private static final HttpManager holder = new HttpManager();
    }

    public interface IHttpLoader{

    }
}
