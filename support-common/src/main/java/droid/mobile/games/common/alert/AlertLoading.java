package droid.mobile.games.common.alert;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import droid.mobile.games.common.log.LogRecord;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static droid.mobile.games.common.alert.AlertLayout.dp;

public class AlertLoading extends Alert{
    private String mTitle;
    private TextView mTitleView;

    public AlertLoading(Context context) {
        this(context,android.R.style.Theme_Dialog);
    }

    public AlertLoading(Context context, int themeResId) {
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

    public AlertLoading setTitle(String title){
        mTitle = title;
        if (mTitleView != null){
            try{
                if (TextUtils.isEmpty(mTitle)){
                    mTitleView.setVisibility(GONE);
                }else{
                    mTitleView.setVisibility(VISIBLE);
                    mTitleView.setText(mTitle);
                }
            }catch (Throwable e){

            }
        }
        return this;
    }

    private View createContentView() {
        Context ctx = context();
        AlertLayout alertLayout = AlertLayout.create(ctx);
        LinearLayout linearLayout = (LinearLayout) alertLayout.getChildAt(0);
        linearLayout.setPadding(0,dp(24),0,dp(24));

        LoadingView loadingView = new LoadingView(ctx);
        LinearLayout.LayoutParams layoutParam2 = new LinearLayout.LayoutParams(dp(40),dp(40));
        loadingView.setLayoutParams(layoutParam2);
        linearLayout.addView(loadingView);

        TextView textView3 = new TextView(ctx);
        LinearLayout.LayoutParams layoutParam3 = new LinearLayout.LayoutParams(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView3.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        textView3.setTypeface(Typeface.DEFAULT_BOLD);
        layoutParam3.topMargin= dp(4) ;
        textView3.setTextColor(0xff111111);
        textView3.setGravity(Gravity.CENTER);
        textView3.setText("游戏正在初始化中");
        textView3.setLayoutParams(layoutParam3);
        linearLayout.addView(textView3);
        linearLayout.setClickable(true);
        textView3.setPadding(dp(8),dp(2),dp(8),0);

        if (TextUtils.isEmpty(mTitle)){
            textView3.setVisibility(GONE);
        }else{
            textView3.setVisibility(VISIBLE);
            textView3.setText(mTitle);
        }

        mTitleView = textView3;
        return alertLayout;
    }

    private class LoadingView extends View{
        private ValueAnimator valueAnimator;
        private Paint mPaint;
        private long mDuration = 1000;
        private int mLoadingColor = 0xff333333;

        public LoadingView(Context context) {
            super(context);
        }

        public LoadingView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            if (mPaint == null){
                mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setStrokeCap(Paint.Cap.ROUND);
                mPaint.setStrokeWidth(dp(2));
            }

            int color = mLoadingColor;

            for (int i = 0; i < 12; i++){
                mPaint.setColor(Color.argb((int)(Color.alpha(color) * (1-i * 0.065f)),Color.red(color),Color.green(color),Color.blue(color)));
                canvas.drawLine(canvas.getWidth() / 2,dp(3),canvas.getWidth() / 2,dp(8),mPaint);
                canvas.rotate(30,canvas.getWidth() / 2, canvas.getHeight() / 2);
            }
        }

        @Override
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();

            try {
                if (valueAnimator == null) {
                    valueAnimator = ValueAnimator.ofFloat(0, 360);
                    valueAnimator.setDuration(mDuration);
                    valueAnimator.setInterpolator(new LinearInterpolator());
                    valueAnimator.setRepeatCount(-1);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            try {
                                float roatation = (float) valueAnimator.getAnimatedValue();
                                roatation = roatation / 30;
                                setRotation(((int) roatation) * 30);
                            }catch (Throwable e){
                                LogRecord.w(e);
                            }
                        }
                    });
                }
                valueAnimator.start();
            }catch (Throwable e){
                LogRecord.w(e);
            }

        }

        @Override
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();

            try {
                if (valueAnimator != null) {
                    valueAnimator.cancel();
                }
            }catch (Throwable e){
                LogRecord.w(e);
            }

        }
    }
}
