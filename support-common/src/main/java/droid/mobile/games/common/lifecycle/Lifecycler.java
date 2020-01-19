package droid.mobile.games.common.lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import droid.mobile.games.common.parameter.Parameter;

public class Lifecycler implements Lifecycle{
    @Override
    public void onInit(State state, Activity activity, Parameter request, Parameter response) {
        switch (state){
            case START:
                onInitStart(activity,request);
                break;
            case SUCC:
                onInitSucc(activity,request,response);
                break;
            case FAIL:
                onInitFail(activity,request,response);
                break;
        }
    }

    @Override
    public void onLogin(State state, Activity activity, Parameter request, Parameter response) {
        switch (state){
            case START:
                onLoginStart(activity,request);
                break;
            case SUCC:
                onLoginSucc(activity,request,response);
                break;
            case FAIL:
                onLoginFail(activity,request,response);
                break;
        }
    }

    @Override
    public void onLogout(State state, Activity activity, Parameter request, Parameter response) {
        switch (state){
            case START:
                onLogoutStart(activity,request);
                break;
            case SUCC:
                onLogoutSucc(activity,request,response);
                break;
            case FAIL:
                onLogoutFail(activity,request,response);
                break;
        }
    }

    @Override
    public void onRole(State state, Activity activity, Parameter request, Parameter response) {
        switch (state){
            case START:
                onRoleStart(activity,request);
                break;
            case SUCC:
                onRoleSucc(activity,request,response);
                break;
            case FAIL:
                onRoleFail(activity,request,response);
                break;
        }
    }

    @Override
    public void onPay(State state, Activity activity, Parameter request, Parameter response) {
        switch (state){
            case START:
                onPayStart(activity,request);
                break;
            case SUCC:
                onPaySucc(activity,request,response);
                break;
            case FAIL:
                onPayFail(activity,request,response);
                break;
        }
    }

    @Override
    public void onExit(State state, Activity activity, Parameter request, Parameter response) {
        switch (state){
            case START:
                onExitStart(activity,request);
                break;
            case SUCC:
                onExitSucc(activity,request,response);
                break;
            case FAIL:
                onExitFail(activity,request,response);
                break;
        }
    }


    @Override
    public void onExec(State state, Activity activity, Parameter request, Parameter response) {
        switch (state){
            case START:
                onExecStart(activity,request);
                break;
            case SUCC:
                onExecSucc(activity,request,response);
                break;
            case FAIL:
                onExecFail(activity,request,response);
                break;
        }
    }

    public void onInitStart(Activity activity, Parameter request){

    }

    public void onInitSucc(Activity activity,Parameter request,Parameter response){

    }

    public void onInitFail(Activity activity,Parameter request,Parameter response){

    }

    public void onLoginStart(Activity activity,Parameter request){

    }

    public void onLoginSucc(Activity activity,Parameter request,Parameter response){

    }

    public void onLoginFail(Activity activity,Parameter request,Parameter response){

    }

    public void onLogoutStart(Activity activity,Parameter request){

    }

    public void onLogoutSucc(Activity activity,Parameter request,Parameter response){

    }

    public void onLogoutFail(Activity activity,Parameter request,Parameter response){

    }

    public void onRoleStart(Activity activity,Parameter request){

    }

    public void onRoleSucc(Activity activity,Parameter request,Parameter response){

    }

    public void onRoleFail(Activity activity,Parameter request,Parameter response){

    }

    public void onPayStart(Activity activity,Parameter request){

    }

    public void onPaySucc(Activity activity,Parameter request,Parameter response){

    }

    public void onPayFail(Activity activity,Parameter request,Parameter response){

    }

    public void onExitStart(Activity activity,Parameter request){

    }

    public void onExitSucc(Activity activity,Parameter request,Parameter response){

    }

    public void onExitFail(Activity activity,Parameter request,Parameter response){

    }

    public void onExecStart(Activity activity,Parameter request){

    }

    public void onExecSucc(Activity activity,Parameter request,Parameter response){

    }

    public void onExecFail(Activity activity,Parameter request,Parameter response){

    }

    @Override
    public void onCreate(Activity activity) {

    }

    @Override
    public void onResume(Activity activity) {

    }

    @Override
    public void onRestart(Activity activity) {

    }

    @Override
    public void onStart(Activity activity) {

    }

    @Override
    public void onPause(Activity activity) {

    }

    @Override
    public void onStop(Activity activity) {

    }

    @Override
    public void onDestroy(Activity activity) {

    }

    @Override
    public void onNewIntent(Activity activity, Intent intent) {

    }

    @Override
    public void onConfigurationChanged(Activity activity, Configuration newConfig) {

    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults) {

    }
}