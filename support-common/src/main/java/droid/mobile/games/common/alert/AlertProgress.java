package droid.mobile.games.common.alert;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class AlertProgress extends Alert {
    public AlertProgress(Context context) {
        super(context);
        setLayout(MATCH_PARENT, WRAP_CONTENT);
        setViewCreator(null);
        setGravity(Gravity.BOTTOM);
        setDimAmount(0.0f);
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

    private View createContentView() {
        Context ctx = context();

        RelativeLayout relativeLayout = new RelativeLayout(ctx);
        relativeLayout.setPadding(dp(16), dp(0), dp(16), dp(16));
        ProgressBar progressBar = new ProgressBar(ctx);
        relativeLayout.addView(progressBar, MATCH_PARENT, dp(8));
        return relativeLayout;
    }

    private class ProgressBar extends View {
        int mProgress = 50;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        public ProgressBar(Context context) {
            this(context, null);
        }

        public ProgressBar(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public ProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);

            paint.setStyle(Paint.Style.FILL);
            GradientDrawable drawable = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[]{0xFFEFEAE4, 0xFFFEFEFC}
            );
            drawable.setStroke(1, 0xffC3BEB8);
            drawable.setCornerRadius(dp(2));

            setBackgroundDrawable(drawable);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            drawProgressBar(canvas);
        }

        protected void drawProgressBar(Canvas canvas) {
            int s = dp(3);

            int x = s / 2;
            int y = s / 2;

            int w = canvas.getWidth() - s / 2;
            int h = canvas.getHeight() - s / 2;

            int layerId = canvas.saveLayer(0, 0, w, h, paint, Canvas.ALL_SAVE_FLAG);

            canvas.drawBitmap(createBackground(w,h),new Rect(0,0,w,h),new RectF(x,y,w,h),paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
            canvas.drawBitmap(createLines(w,h),new Rect(0,0,w,h),new RectF(x,y,w,h),paint);
            paint.setXfermode(null);
            canvas.restoreToCount(layerId);

        }

        protected Bitmap createBackground(int w,int h){
            Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            Paint scrPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

            scrPaint.setColor(0xFFE04438);
            scrPaint.setStyle(Paint.Style.FILL);
            scrPaint.setStrokeWidth(1);
            canvas.drawRoundRect(new RectF(0,0,w / 100f * mProgress,h),dp(2),dp(2),scrPaint);

            scrPaint.setColor(0xffA89CB4);
            scrPaint.setStyle(Paint.Style.STROKE);
            scrPaint.setStrokeWidth(1);
            canvas.drawRoundRect(new RectF(0,0,w / 100f * mProgress,h),dp(2),dp(2),scrPaint);
            return bitmap;
        }

        protected Bitmap createLines(int w,int h){
            Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            Paint scrPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

            scrPaint.setColor(0xFFfde5e1);
            scrPaint.setStyle(Paint.Style.FILL);
            for (int i = 0;i < w;i += dp(8)){
                Path path = new Path();
                path.moveTo(i,0);
                path.lineTo(i + dp(4),0);
                path.lineTo(i,h);
                path.lineTo(i - dp(4),h);
                path.close();
                canvas.drawPath(path,scrPaint);
            }

            scrPaint.setColor(0xffA89CB4);
            scrPaint.setStyle(Paint.Style.STROKE);
            scrPaint.setStrokeWidth(1);
            canvas.drawRoundRect(new RectF(0,0,w / 100f * mProgress,h),dp(2),dp(2),scrPaint);
            return bitmap;
        }
    }
}
