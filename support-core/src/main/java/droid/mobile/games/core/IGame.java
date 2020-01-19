package droid.mobile.games.core;

import android.app.Activity;
import droid.mobile.games.common.keep.Receiver;
import droid.mobile.games.common.parameter.Parameter;

public interface IGame {
    void registerReceiver(Receiver receiver);

    void unregisterReceiver(Receiver receiver);

    void init(Activity activity, Parameter parameter);

    void login(Activity activity, Parameter parameter);

    void logout(Activity activity, Parameter parameter);

    void role(Activity activity, Parameter parameter);

    void pay(Activity activity, Parameter parameter);

    void exit(Activity activity, Parameter parameter);

    void exec(Activity activity, Parameter parameter);
}
