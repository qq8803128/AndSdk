package droid.mobile.games.common.alert;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.LinearLayout;

public class AlertLayout extends LinearLayout {
    public static AlertLayout create(Context ctx){
        AlertLayout alertLayout = new AlertLayout(ctx);
        alertLayout.setOrientation(LinearLayout.VERTICAL);
        alertLayout.setGravity(Gravity.CENTER);
        alertLayout.setPadding(0,dp(28),0,dp(28));

        int w = (int) (windowWidth() * 0.72f);
        if (!portrait()){
            w = (int)(windowHeight() * 0.78f);
        }
        LinearLayout linearLayout = new LinearLayout(ctx);
        LinearLayout.LayoutParams lp = new LayoutParams(w,LayoutParams.WRAP_CONTENT);
        lp.leftMargin = dp(1);
        lp.rightMargin = dp(1);
        lp.topMargin = dp(1);
        lp.bottomMargin = dp(3);
        alertLayout.addView(linearLayout,lp);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(dp(12));
        drawable.setColor(Color.parseColor("#f5ffffff"));
        //drawable.setStroke(dp(1),Color.parseColor("#00000000"));
        linearLayout.setBackgroundDrawable(drawable);
        linearLayout.setPadding(0, dp(18), 0, 0);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                linearLayout.setElevation(dp(1.5f));
                linearLayout.setOutlineProvider(new ViewOutlineProvider() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void getOutline(View view, Outline outline) {
                        try {
                            outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(),dp(11));
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
        return alertLayout;
    }

    public static int dp(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, Resources.getSystem().getDisplayMetrics());
    }

    public static boolean portrait() {
        return windowWidth() < windowHeight();
    }

    public static int windowWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int windowHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    private AlertLayout(Context context) {
        this(context,null);
    }

    private AlertLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    private AlertLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
