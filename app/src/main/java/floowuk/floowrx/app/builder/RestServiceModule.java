package floowuk.floowrx.app.builder;

import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import retrofit2.Retrofit;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import com.google.gson.Gson;
import retrofit2.converter.gson.GsonConverterFactory;
import floowuk.zendeskigorlibrary.constants.UserParam;
import com.twistedequations.mvl.rx.AndroidRxSchedulers;
import floowuk.zendeskigorlibrary.zendesk.ZendeskService;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import floowuk.zendeskigorlibrary.helpers.AuthenticationInterceptor;
/*** Created by igorfrankiv on 27/04/2018. ***/
@Module
public class RestServiceModule {

    @IAppScope
    @Provides
    @Named("ZendeskServiceRetrofit")
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson, AndroidRxSchedulers androidSchedulers) {
        return new Retrofit.Builder()
                .baseUrl("https://www.reddit.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(androidSchedulers.network()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

//    @IAppScope
//    @Provides
//    public ZendeskService zendeskService(@Named("ZendeskServiceRetrofit") Retrofit retrofit) {
//        return retrofit.create(ZendeskService.class);
//    }
//--------------------------------------------------------------------------------------------------
    @IAppScope
    @Provides
    @Named("ZendeskServiceNew")
    public OkHttpClient okHttpClient(@Named("ZendeskServiceNew") AuthenticationInterceptor authenticationInterceptor) {
        return new OkHttpClient.Builder().addNetworkInterceptor( authenticationInterceptor ).build() ;
    }

    @IAppScope
    @Provides
    @Named("ZendeskServiceNew")
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor( Credentials.basic(UserParam.USERNAME,  UserParam.PASSWORD) );
    }

    @IAppScope
    @Provides
    public ZendeskService zendeskService(@Named("ZendeskServiceNew") OkHttpClient okHttpClient, AndroidRxSchedulers androidSchedulers) {
        return  new Retrofit.Builder().
                baseUrl(  UserParam.API_BASE_URL ).
                addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(androidSchedulers.network())).
                addConverterFactory(GsonConverterFactory.create()).
                client(  okHttpClient  ).
                build().create(ZendeskService.class);
    }
//--------------------------------------------------------------------------------------------------
}