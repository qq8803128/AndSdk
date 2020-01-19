package droid.mobile.games.common.alert;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import static android.text.TextUtils.isEmpty;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class AlertMsg extends Alert{
    private String mTitle;
    private String mContent;
    private String mPositive;
    private String mNegative;
    private OnClickListener mPositiveListener;
    private OnClickListener mNegativeListener;
    private long mDuration = 0;
    private View mTitleView,mContentView,mPositiveView,mNegativeView;

    public AlertMsg(Context context) {
        this(context,android.R.style.Theme_Dialog);
    }

    public AlertMsg(Context context, int themeResId) {
        super(context, themeResId);
        setViewCreator(null);
    }

    @Override
    public Alert setViewCreator(IViewCreator viewCreator) {
        return super.setViewCreator(new IViewCreator() {
            @Override
            public View createView(Alert alert) {
                return createContentView();
            }
        });
    }

    private final View createContentView(){
        Context ctx = context();
        AlertLayout alertLayout = AlertLayout.create(ctx);
        LinearLayout linearLayout = (LinearLayout) alertLayout.getChildAt(0);

        TextView textView2 = new TextView(ctx);
        LinearLayout.LayoutParams layoutParam2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView2.setTextColor(Color.parseColor("#000000"));
        textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        textView2.setTypeface(Typeface.DEFAULT_BOLD);
        textView2.setGravity(Gravity.CENTER);
        textView2.setText("更新提醒");
        textView2.setLayoutParams(layoutParam2);
        linearLayout.addView(textView2);
        textView2.setPadding(dp(16), dp(6), dp(16), 0);

        ScrollView scrollView3 = new ScrollView(ctx);
        scrollView3.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
        scrollView3.setVerticalScrollBarEnabled(false);
        LinearLayout.LayoutParams layoutParam3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        layoutParam3.weight = 1;
        scrollView3.setLayoutParams(layoutParam3);
        linearLayout.addView(scrollView3);

        LinearLayout linearLayout4 = new LinearLayout(ctx);
        ScrollView.LayoutParams layoutParam4 = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout4.setOrientation(LinearLayout.VERTICAL);
        linearLayout4.setLayoutParams(layoutParam4);
        scrollView3.addView(linearLayout4);

        TextView textView5 = new TextView(ctx);
        LinearLayout.LayoutParams layoutParam5 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView5.setGravity(Gravity.CENTER);
        textView5.setText("hello world");
        textView5.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView5.setTextColor(Color.parseColor("#333333"));
        textView5.setLayoutParams(layoutParam5);
        linearLayout4.addView(textView5);
        textView5.setPadding(dp(16), dp(6), dp(16), dp(24));

        View view6 = new View(ctx);
        LinearLayout.LayoutParams layoutParam6 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(1f));
        //layoutParam6.topMargin = dp(24);
        view6.setBackgroundColor(Color.parseColor("#dddddd"));
        view6.setLayoutParams(layoutParam6);
        linearLayout.addView(view6);

        LinearLayout linearLayout7 = new LinearLayout(ctx);
        LinearLayout.LayoutParams layoutParam7 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(40));
        linearLayout7.setLayoutParams(layoutParam7);
        linearLayout.addView(linearLayout7);

        TextView textView8 = new TextView(ctx);
        LinearLayout.LayoutParams layoutParam8 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        textView8.setTypeface(Typeface.DEFAULT_BOLD);
        textView8.setTextColor(Color.parseColor("#3478f6"));
        textView8.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        textView8.setText("立即升级");
        textView8.setGravity(Gravity.CENTER);
        layoutParam8.weight = 1;
        textView8.setLayoutParams(layoutParam8);
        linearLayout7.addView(textView8);

        View view9 = new View(ctx);
        LinearLayout.LayoutParams layoutParam9 = new LinearLayout.LayoutParams(dp(1f), ViewGroup.LayoutParams.MATCH_PARENT);
        view9.setBackgroundColor(Color.parseColor("#dddddd"));
        view9.setLayoutParams(layoutParam9);
        linearLayout7.addView(view9);

        TextView textView10 = new TextView(ctx);
        LinearLayout.LayoutParams layoutParam10 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        textView10.setTextColor(Color.parseColor("#3478f6"));
        textView10.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        textView10.setText("稍后提醒");
        textView10.setGravity(Gravity.CENTER);
        layoutParam10.weight = 1;
        textView10.setLayoutParams(layoutParam10);
        linearLayout7.addView(textView10);

        if (TextUtils.isEmpty(mTitle)){
            textView2.setVisibility(GONE);
        }else{
            textView2.setText(mTitle);
            textView2.setVisibility(VISIBLE);
        }

        if (TextUtils.isEmpty(mContent)){
            textView5.setVisibility(GONE);
        }else{
            textView5.setText(mContent);
            textView5.setVisibility(VISIBLE);
        }

        boolean positiveEmpty = isEmpty(mPositive);
        boolean negativeEmpty = isEmpty(mNegative);

        textView10.setClickable(true);
        textView8.setClickable(true);
        if (positiveEmpty){
            textView8.setVisibility(View.GONE);
        }else{
            textView8.setVisibility(View.VISIBLE);
            textView8.setText(mPositive);
        }

        if (negativeEmpty){
            textView10.setVisibility(View.GONE);
        }else{
            textView10.setVisibility(View.VISIBLE);
            textView10.setText(mNegative);
        }

        if (positiveEmpty || negativeEmpty){
            view9.setVisibility(View.GONE);

            setBackground(
                    textView10,
                    createDrawable(0x15000000,new float[]{0,0,0,0,dp(12),dp(12),dp(12),dp(12)})
            );

            setBackground(
                    textView8,
                    createDrawable(0x15000000,new float[]{0,0,0,0,dp(12),dp(12),dp(12),dp(12)})
            );

        }else{
            view9.setVisibility(View.VISIBLE);

            setBackground(
                    textView10,
                    createDrawable(0x15000000,new float[]{0,0,0,0,dp(12),dp(12),0,0})
            );

            setBackground(
                    textView8,
                    createDrawable(0x15000000,new float[]{0,0,0,0,0,0,dp(12),dp(12)})
            );
        }

        if (positiveEmpty && negativeEmpty){
            linearLayout7.setVisibility(GONE);
            view6.setVisibility(GONE);

            if (mDuration >= 100){
                alertLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissAlert();
                    }
                },mDuration);
            }
        }else{
            view6.setVisibility(VISIBLE);
        }

        mTitleView = textView2;
        mContentView = textView5;
        mPositiveView = textView8;
        mNegativeView = textView10;

        mPositiveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPositiveListener != null){
                    mPositiveListener.onClick(AlertMsg.this, DialogInterface.BUTTON_POSITIVE);
                }
            }
        });

        mNegativeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mNegativeListener != null){
                    mNegativeListener.onClick(AlertMsg.this, DialogInterface.BUTTON_NEGATIVE);
                }
            }
        });

        return alertLayout;
    }

    private void setBackground(View target, Drawable drawable){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            target.setBackground(drawable);
        }else{
            target.setBackgroundDrawable(drawable);
        }
    }

    private Drawable createDrawable(int color, float[] radii){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadii(radii);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed},drawable);
        return stateListDrawable;
    }

    public AlertMsg setTitle(String title){
        mTitle = title;
        return this;
    }

    public AlertMsg setContent(String content){
        mContent = content;
        return this;
    }

    public AlertMsg setPositiveButton(String text,OnClickListener onClickListener){
        mPositive = text;
        mPositiveListener = onClickListener;
        return this;
    }

    public AlertMsg setNegativeButton(String text,OnClickListener onClickListener){
        mNegative = text;
        mNegativeListener = onClickListener;
        return this;
    }

    public AlertMsg setToast(long duration){
        mDuration = duration;
        return this;
    }

    public View getTitle(){
        return mTitleView;
    }

    public View getContent(){
        return mContentView;
    }

    public View getPositive(){
        return mPositiveView;
    }

    public View getNegative(){
        return mNegativeView;
    }
}
