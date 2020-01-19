package droid.mobile.games.common.alert;


import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import droid.mobile.games.common.bus.BusProvider;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class Alert extends Dialog {
    private Context mContext;
    protected boolean mBackPressedOnTouchOutsize = true;
    protected boolean mBackPressable = true;
    protected int mFeatureId = Window.FEATURE_NO_TITLE;
    protected int mFlags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
    protected int mMask = WindowManager.LayoutParams.FLAG_FULLSCREEN;
    protected Drawable mBackgroundDrawable = new ColorDrawable(Color.TRANSPARENT);
    protected float mAmount = 0.3f;
    protected int mWidth = WRAP_CONTENT;
    protected int mHeight = WRAP_CONTENT;
    protected View mContentView;
    protected int mGravity = Gravity.CENTER;

    protected IViewCreator mViewCreator = new IViewCreator(){
        @Override
        public View createView(Alert alert) {
            return new FrameLayout(alert.mContext);
        }
    };

    public Alert(Context context) {
        this(context, android.R.style.Theme_Dialog);
    }

    public Alert(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    public Alert setBackPressedOnTouchOutside(boolean cancel){
        mBackPressedOnTouchOutsize = cancel;
        setCanceledOnTouchOutside(cancel);
        return this;
    }

    public Alert setBackPressable(boolean flag){
        mBackPressable = flag;
        setCancelable(flag);
        return this;
    }

    public Alert setWindowFeature(int featureId){
        mFeatureId = featureId;
        return this;
    }

    public Alert setFlags(int flags,int mask){
        mFlags = flags;
        mMask = mask;
        return this;
    }

    public Alert setBackgroundDrawable(Drawable drawable){
        mBackgroundDrawable = drawable;
        return this;
    }

    public Alert setGravity(int gravity){
        mGravity = gravity;
        return this;
    }

    public Alert setLayout(int width,int height){
        mWidth = width;
        mHeight = height;
        return this;
    }

    public Alert setDimAmount(float amount){
        mAmount = amount;
        return this;
    }

    public Alert setViewCreator(IViewCreator viewCreator){
        mViewCreator = viewCreator;
        return this;
    }

    public Context context(){
        return mContext;
    }

    public View contentView(){
        return mContentView;
    }

    public Alert showAlert(){
        try{
            show();
        }catch (Throwable e){
        }
        return this;
    }

    public Alert addOnCancelListener(OnCancelListener listener) {
        super.setOnCancelListener(listener);
        return this;
    }

    public Alert addOnDismissListener(OnDismissListener listener) {
        super.setOnDismissListener(listener);
        return this;
    }

    public Alert dismissAlert(){
        try{
            dismiss();
        }catch (Throwable e){
        }
        return this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            if (getWindow() != null){
                getWindow().setDimAmount(mAmount);
                getWindow().setBackgroundDrawable(mBackgroundDrawable);
                getWindow().setLayout(mWidth,mHeight);
                getWindow().setGravity(mGravity);
            }
        }catch (Throwable e){
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            requestWindowFeature(mFeatureId);
            getWindow().setFlags(mFlags,mMask);
        }catch (Throwable e){
        }

        try{
            View view = mViewCreator.createView(this);
            if (view != null){
                mContentView = view;
                setContentView(view);
            }else{
                mContentView = new FrameLayout(mContext);
                setContentView(mContentView);
            }
        }catch (Throwable e){
        }
    }

    @Override
    public void show() {
        BusProvider.getInstance().register(this);
        super.show();
    }

    @Override
    public void dismiss() {
        BusProvider.getInstance().unregister(this);
        super.dismiss();
    }

    public interface IViewCreator{
        View createView(Alert alert);
    }

    protected static int dp(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, Resources.getSystem().getDisplayMetrics());
    }
}
