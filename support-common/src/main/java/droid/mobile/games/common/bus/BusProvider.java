package droid.mobile.games.common.bus;

public final class BusProvider extends Bus{
    public static BusProvider getInstance(){
        return Holder.holder;
    }

    private static class Holder{
        private static final BusProvider holder = new BusProvider();
    }
}
