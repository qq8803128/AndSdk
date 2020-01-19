package droid.mobile.games.common.manager;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import droid.mobile.games.common.compat.ThreadCompat;
import droid.mobile.games.common.lifecycle.Lifecycle;
import droid.mobile.games.common.log.LogRecord;
import droid.mobile.games.common.parameter.Parameter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LifecycleManager implements Lifecycle{
    public static LifecycleManager manager(){
        return Holder.holder;
    }

    private List<Lifecycle> mLifecycler;
    private Lifecycle mLifecycle;
    LifecycleManager() {
        super();
        mLifecycler = Collections.synchronizedList(new ArrayList<Lifecycle>());
        mLifecycle = (Lifecycle) Proxy.newProxyInstance(LifecycleManager.class.getClassLoader(),new Class[]{Lifecycle.class},mInvocationHandler);
    }

    public void add(Lifecycle lifecycle){
        if (lifecycle != null && !mLifecycler.contains(lifecycle)){
            mLifecycler.add(lifecycle);
        }
    }

    @Override
    public void onInit(State state, Activity activity, Parameter request, Parameter response) {
        mLifecycle.onInit(state,activity,request,response);
    }

    @Override
    public void onLogin(State state, Activity activity, Parameter request,Parameter response) {
        mLifecycle.onLogin(state,activity,request,response);
    }

    @Override
    public void onLogout(State state, Activity activity, Parameter request,Parameter response) {
        mLifecycle.onLogout(state,activity,request,response);
    }

    @Override
    public void onRole(State state, Activity activity, Parameter request,Parameter response) {
        mLifecycle.onRole(state,activity,request,response);
    }

    @Override
    public void onPay(State state, Activity activity, Parameter request,Parameter response) {
        mLifecycle.onPay(state,activity,request,response);
    }

    @Override
    public void onExit(State state, Activity activity, Parameter request,Parameter response) {
        mLifecycle.onExit(state,activity,request,response);
    }

    @Override
    public void onExec(State state, Activity activity, Parameter request, Parameter response) {
        mLifecycle.onExec(state,activity,request,response);
    }

    @Override
    public void onCreate(Activity activity) {
        mLifecycle.onCreate(activity);
    }

    @Override
    public void onResume(Activity activity) {
        mLifecycle.onResume(activity);
    }

    @Override
    public void onRestart(Activity activity) {
        mLifecycle.onRestart(activity);
    }

    @Override
    public void onStart(Activity activity) {
        mLifecycle.onStart(activity);
    }

    @Override
    public void onPause(Activity activity) {
        mLifecycle.onPause(activity);
    }

    @Override
    public void onStop(Activity activity) {
        mLifecycle.onStop(activity);
    }

    @Override
    public void onDestroy(Activity activity) {
        mLifecycle.onDestroy(activity);
    }

    @Override
    public void onNewIntent(Activity activity, Intent intent) {
        mLifecycle.onNewIntent(activity,intent);
    }

    @Override
    public void onConfigurationChanged(Activity activity, Configuration newConfig) {
        mLifecycle.onConfigurationChanged(activity,newConfig);
    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        mLifecycle.onActivityResult(activity,requestCode,resultCode,data);
    }

    @Override
    public void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults) {
        mLifecycle.onRequestPermissionsResult(activity,requestCode,permissions,grantResults);
    }

    private InvocationHandler mInvocationHandler = new InvocationHandler() {
        @Override
        public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
            ThreadCompat.runNow(new Runnable() {
                @Override
                public void run() {
                    try{
                        for (Lifecycle lifecycle : mLifecycler) {
                            try {
                                method.invoke(lifecycle,args);
                            } catch (Throwable e) {
                                LogRecord.w(e);
                            }
                        }
                    }catch (Throwable e){
                        LogRecord.w(e);
                    }
                }
            });
            return null;
        }
    };

    private static class Holder{
        private static final LifecycleManager holder = new LifecycleManager();
    }
}