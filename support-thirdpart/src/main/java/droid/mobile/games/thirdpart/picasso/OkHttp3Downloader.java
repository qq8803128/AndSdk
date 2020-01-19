/*
 * Copyright (C) 2013 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package droid.mobile.games.thirdpart.picasso;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import droid.mobile.games.thirdpart.okhttp3.Cache;
import droid.mobile.games.thirdpart.okhttp3.CacheControl;
import droid.mobile.games.thirdpart.okhttp3.OkHttpClient;
import droid.mobile.games.thirdpart.okhttp3.Request;
import droid.mobile.games.thirdpart.okhttp3.ResponseBody;


/** A {@link Downloader} which uses OkHttp to download images. */
public class OkHttp3Downloader implements Downloader {
  private static OkHttpClient defaultOkHttpClient(final File cacheDir, final long maxSize) {
    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(Utils.DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .readTimeout(Utils.DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .writeTimeout(Utils.DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .cache(new Cache(cacheDir,maxSize))
            .build();
    return client;
  }

  private final OkHttpClient client;

  /**
   * Create new downloader that uses OkHttp. This will install an image cache into your application
   * cache directory.
   */
  public OkHttp3Downloader(final Context context) {
    this(Utils.createDefaultCacheDir(context));
  }

  /**
   * Create new downloader that uses OkHttp. This will install an image cache into the specified
   * directory.
   *
   * @param cacheDir The directory in which the cache should be stored
   */
  public OkHttp3Downloader(final File cacheDir) {
    this(cacheDir, Utils.calculateDiskCacheSize(cacheDir));
  }

  /**
   * Create new downloader that uses OkHttp. This will install an image cache into your application
   * cache directory.
   *
   * @param maxSize The size limit for the cache.
   */
  public OkHttp3Downloader(final Context context, final long maxSize) {
    this(Utils.createDefaultCacheDir(context), maxSize);
  }

  /**
   * Create new downloader that uses OkHttp. This will install an image cache into the specified
   * directory.
   *
   * @param cacheDir The directory in which the cache should be stored
   * @param maxSize The size limit for the cache.
   */
  public OkHttp3Downloader(final File cacheDir, final long maxSize) {
    this(defaultOkHttpClient(cacheDir, maxSize));
  }

  /**
   * Create a new downloader that uses the specified OkHttp instance. A response cache will not be
   * automatically configured.
   */
  public OkHttp3Downloader(OkHttpClient client) {
    this.client = client;
  }

  protected final OkHttpClient getClient() {
    return client;
  }

  @Override public Response load(Uri uri, int networkPolicy) throws IOException {
    CacheControl cacheControl = null;
    if (networkPolicy != 0) {
      if (NetworkPolicy.isOfflineOnly(networkPolicy)) {
        cacheControl = CacheControl.FORCE_CACHE;
      } else {
        CacheControl.Builder builder = new CacheControl.Builder();
        if (!NetworkPolicy.shouldReadFromDiskCache(networkPolicy)) {
          builder.noCache();
        }
        if (!NetworkPolicy.shouldWriteToDiskCache(networkPolicy)) {
          builder.noStore();
        }
        cacheControl = builder.build();
      }
    }

    Request.Builder builder = new Request.Builder().url(uri.toString());
    if (cacheControl != null) {
      builder.cacheControl(cacheControl);
    }

    droid.mobile.games.thirdpart.okhttp3.Response response = client.newCall(builder.build()).execute();
    int responseCode = response.code();
    if (responseCode >= 300) {
      response.body().close();
      throw new ResponseException(responseCode + " " + response.message(), networkPolicy,
          responseCode);
    }

    boolean fromCache = response.cacheResponse() != null;

    ResponseBody responseBody = response.body();
    return new Response(responseBody.byteStream(), fromCache, responseBody.contentLength());
  }

  @Override public void shutdown() {
    Cache cache = client.cache();
    if (cache != null) {
      try {
        cache.close();
      } catch (IOException ignored) {
      }
    }
  }
}
