package droid.mobile.games.common.floater;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.PixelFormat;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.WindowManager;
import android.widget.LinearLayout;

import static droid.mobile.games.common.alert.AlertLayout.*;

public class FloaterToast extends LinearLayout {
    public static FloaterToast create(Activity activity) {
        return new FloaterToast(activity);
    }

    private WindowManager wm;
    private WindowManager.LayoutParams lp;
    private IViewCreator mViewCreator;

    FloaterToast(Activity activity) {
        this(activity,null);
    }

    FloaterToast(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    FloaterToast(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        lp = new WindowManager.LayoutParams();
        setFormat(PixelFormat.RGBA_8888);
        setPosition(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        setType(WindowManager.LayoutParams.TYPE_APPLICATION);
        setFlag(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        int w = (int) (windowWidth());
        if (!portrait()){
            w = (int)(windowHeight());
        }
        w  -= dp(32);

        int h = dp(76);
        setLayout(w,h);
        setPoint(0,dp(16));
    }

    public final View createDefaultView(Context ctx){
        int w = (int) (windowWidth());
        if (!portrait()){
            w = (int)(windowHeight());
        }
        w  -= dp(32);
        int h = dp(76);

        setGravity(Gravity.CENTER);
        LinearLayout linearLayout = new LinearLayout(ctx);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        linearLayout.setPadding(dp(12),dp(12),dp(12),dp(12));
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(w-dp(4),h - dp(4)));

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setCornerRadius(dp(8));
        linearLayout.setBackgroundDrawable(drawable);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setClickable(false);
                hide();
            }
        });

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                linearLayout.setElevation(dp(1.5f));
                linearLayout.setOutlineProvider(new ViewOutlineProvider() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void getOutline(View view, Outline outline) {
                        try {
                            outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(),dp(7.5f));
                        }catch (Throwable e){
                            e.printStackTrace();
                        }
                    }
                });
            }else{
                drawable.setStroke(1,0x95333333);
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
        return linearLayout;
    }

    public FloaterToast setFormat(int format){
        lp.format = format;
        return this;
    }

    public FloaterToast setPosition(int gravity){
        lp.gravity = gravity;
        return this;
    }

    public FloaterToast setType(int type){
        lp.type = type;
        return this;
    }

    public FloaterToast setFlag(int flag){
        lp.flags = flag;
        return this;
    }

    public FloaterToast setLayout(int w,int h){
        lp.width = w;
        lp.height = h;
        return this;
    }

    public FloaterToast setPoint(int x,int y){
        lp.x = x;
        lp.y = y;
        return this;
    }

    public FloaterToast setView(IViewCreator viewCreator){
        mViewCreator = viewCreator;
        return this;
    }

    public WindowManager.LayoutParams getWindowLayoutParam(){
        return lp;
    }

    public FloaterToast show(){
        try {
            if (mViewCreator == null){
                mViewCreator = new IViewCreator() {
                    @Override
                    public View createView(FloaterToast toast) {
                        return createDefaultView(toast.getContext());
                    }
                };
            }
            if (getChildCount() == 0) {
                addView(mViewCreator.createView(this));
            }
            wm.addView(this, lp);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return this;
    }

    public FloaterToast hide(){
        try {
            wm.removeViewImmediate(this);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return this;
    }

    public interface IViewCreator{
        View createView(FloaterToast toast);
    }
}
