package droid.mobile.games.common.lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import droid.mobile.games.common.parameter.Parameter;

public interface Lifecycle {
    enum State {
        START,
        SUCC,
        FAIL
    }

    void onInit(State state, Activity activity, Parameter request, Parameter response);

    void onLogin(State state, Activity activity, Parameter request,Parameter response);

    void onLogout(State state, Activity activity, Parameter request,Parameter response);

    void onRole(State state, Activity activity, Parameter request, Parameter response);

    void onPay(State state, Activity activity, Parameter request,Parameter response);

    void onExit(State state, Activity activity, Parameter request,Parameter response);

    void onExec(State state, Activity activity, Parameter request,Parameter response);

    void onCreate(Activity activity);

    void onResume(Activity activity);

    void onRestart(Activity activity);

    void onStart(Activity activity);

    void onPause(Activity activity);

    void onStop(Activity activity);

    void onDestroy(Activity activity);

    void onNewIntent(Activity activity, Intent intent);

    void onConfigurationChanged(Activity activity, Configuration newConfig);

    void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data);

    void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults);
}
