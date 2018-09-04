package floowuk.floowrx.app.builder;

import dagger.Module;
import okhttp3.Cache;
import dagger.Provides;
import android.content.Context;
import android.util.Log;
import java.io.File;
import javax.inject.Named;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
//import com.jakewharton.picasso.OkHttp3Downloader;
//import com.squareup.picasso.Picasso;
/*** Created by igorfrankiv on 27/04/2018.*/

@Module
public class  NetworkModule {
    @IAppScope
    @Provides
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(loggingInterceptor)
                .cache(cache)
                .build();
    }

    @IAppScope
    @Provides
    public Cache cache(Context context, @Named("OkHttpCacheDir") String cacheDir, @Named("OkHttpCacheSize") int cacheSize) {
        return new Cache(new File(context.getFilesDir(), cacheDir), cacheSize);
    }

    @IAppScope
    @Provides
    @Named("OkHttpCacheDir")
    public String cacheDir() {
        return "OkHttpCache";
    }


    @IAppScope
    @Provides
    @Named("OkHttpCacheSize")
    public int cacheSize() {
        return 10 * 1024 * 1024; //10MB cache
    }

    @IAppScope
    @Provides
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        return
                new HttpLoggingInterceptor(message -> Log.i("HttpLoggingInterceptor", message))
                .setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

//    @IAppScope
//    @Provides
//    public Picasso picasso(Context context, OkHttpClient okHttpClient) {
//        return new Picasso.Builder(context)
//                .downloader(new OkHttp3Downloader(okHttpClient))
//                .build();
//    }

}
