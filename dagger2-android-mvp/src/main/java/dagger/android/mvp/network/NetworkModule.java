package dagger.android.mvp.network;


import android.app.Application;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * network module,,setting http client, urls, creating singleton classes methods etc.
 */
@Module
public class NetworkModule {

    // constructor injection
    @Inject
    public NetworkModule() {
    }

    @Provides
    @Singleton
    Retrofit provideCall(Cache cache) {
        // create ok http client
        // can set any client in retrofit

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                // set time outs
                .readTimeout(NetworkConfig.READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(NetworkConfig.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        // creating request object
                        Request request = original.newBuilder()
                                .header("Content-Type", "application/json")
                                .removeHeader("Pragma")
                                .header("Cache-Control", String.format("max-age=%d", NetworkConfig.CACHETIME))
                                .build();
                        // getting response
                        okhttp3.Response response = chain.proceed(request);
                        response.cacheResponse();
                        return response;
                    }
                })
                .cache(cache)

                .build();


        return buildRetrofit(okHttpClient);
    }

    private Retrofit buildRetrofit(OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                // bind base URL
                .baseUrl(NetworkConfig.BASEURL)
                // bind okhttp client
                .client(okHttpClient)
                .addConverterFactory(provideGsonConverterFactory())
                .addConverterFactory(provideScalarsConverterFactory())
                .addCallAdapterFactory(provideRxJavaCallAdapterFactory())
                // build all
                .build();
    }

    @Provides
    @Singleton
    public CallAdapter.Factory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    @Singleton
    public Converter.Factory provideScalarsConverterFactory() {
        return ScalarsConverterFactory.create();
    }

    @Provides
    @Singleton
    public Converter.Factory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    public NetworkServiceInterface providesNetworkService(
            Retrofit retrofit) {
        return retrofit.create(NetworkServiceInterface.class);
    }

    @Provides
    @Singleton
    public RxJavaService providesService(
            NetworkServiceInterface networkService) {
        return new RxJavaService(networkService);
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }
}
