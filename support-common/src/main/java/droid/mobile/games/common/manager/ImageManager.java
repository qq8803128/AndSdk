package droid.mobile.games.common.manager;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageManager {
    public static final int CLEAR_ALL_CACHE = 1;
    public static final int CLEAR_MEMORY_CACHE = 2;
    public static final int CLEAR_DISK_CACHE = 3;

    public static final ImageManager manager(){
        return Holder.holder;
    }

    private IImageLoader mLoader;

    private ImageManager(){

    }

    public final void initialize(IImageLoader loader){
        mLoader = loader;
    }

    public final IImageLoader loader(){
        return mLoader;
    }

    private static class Holder{
        private static final ImageManager holder = new ImageManager();
    }

    public interface ExtendedOptions {
        /**
         * @param option 配置对象
         *               Glide    com.bumptech.glide.request.RequestOptions
         *               Picasso  com.squareup.picasso.RequestCreator
         *               Fresco   com.facebook.imagepipeline.request.ImageRequestBuilder
         */
        void onOptionsInit(Object option);
    }

    public static class LoadOption {
        private int mDefaultImage;
        private int mErrorImage;
        private boolean isCircle;
        private boolean isPlayGif;
        private Point mSize;
        private Object mUri;
        private List mTransformations = new ArrayList();

        public LoadOption setDefaultImage(int mDefaultImage) {
            this.mDefaultImage = mDefaultImage;
            return this;
        }

        public LoadOption setErrorImage(int mErrorImage) {
            this.mErrorImage = mErrorImage;
            return this;
        }

        public LoadOption setCircle(boolean circle) {
            isCircle = circle;
            return this;
        }

        public LoadOption setPlayGif(boolean playGif) {
            isPlayGif = playGif;
            return this;
        }

        public LoadOption setSize(Point mSize) {
            this.mSize = mSize;
            return this;
        }

        public LoadOption setUri(Object mUri) {
            this.mUri = mUri;
            return this;
        }

        public LoadOption setTransformations(Object transformation) {
            this.mTransformations.add(transformation);
            return this;
        }
    }

    public interface Callback {
        void onStart();

        void onSuccess(Object result);

        void onFail(Exception error);
    }

    public interface IImageLoader {
        /**
         * 加载图片
         *
         * @param loadOption   加载图片配置
         * @param target       加载目标对象，ImageView or SimpleDraweeView
         * @param callback     加载回调
         * @param extendOption 额外配置接口
         */
        void loadImage(LoadOption loadOption, ImageView target, Callback callback, ExtendedOptions extendOption);

        /**
         * 清除缓存
         *
         * @param type GSYImageConst，清除类型
         */
        void clearCache(int type);

        /**
         * 清除指定缓存
         *
         * @param type       GSYImageConst，清除类型
         * @param loadOption 加载图片配置
         */
        void clearCacheKey(int type, LoadOption loadOption);

        /**
         * 是否已经缓存到本地
         *
         * @param loadOption   加载图片配置
         * @param extendOption 额外配置接口
         * @return Boolean 是否已经缓存到本地
         */
        boolean isCache(LoadOption loadOption, ExtendedOptions extendOption);

        /**
         * 获取本地缓存
         *
         * @param loadOption   加载图片配置
         * @param extendOption 额外配置接口
         * @return File
         */
        File getLocalCache(LoadOption loadOption, ExtendedOptions extendOption);

        /**
         * 获取本地缓存bitmap
         *
         * @param loadOption   加载图片配置
         * @param extendOption 额外配置接口
         * @return Bitmap
         */
        Bitmap getLocalCacheBitmap(LoadOption loadOption, ExtendedOptions extendOption);


        /**
         * 获取本地缓存大小
         *
         * @return Long
         */
        long getCacheSize();


        /**
         * 下载图片
         *
         * @param loadOption   加载图片配置
         * @param callback     加载回调
         * @param extendOption 额外配置接口
         * @return Bitmap
         */
        void downloadOnly(LoadOption loadOption, Callback callback, ExtendedOptions extendOption);
    }
}
