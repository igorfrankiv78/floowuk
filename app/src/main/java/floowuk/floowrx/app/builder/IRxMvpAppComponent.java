package floowuk.floowrx.app.builder;

/*** Created by igorfrankiv on 26/02/2018.*/
import dagger.Component;
import android.content.Context;
//import com.squareup.picasso.Picasso;
import floowuk.floowrx.helpers.DBHelper;
import com.twistedequations.mvl.rx.AndroidRxSchedulers;

import floowuk.floowrx.model.MyObservable;
import floowuk.zendeskigorlibrary.helpers.ZendeskServiceHelper;
import floowuk.zendeskigorlibrary.zendesk.ZendeskService;

@IAppScope
@Component(modules = {  RxMvpAppModule.class, NetworkModule.class,
                        RestServiceModule.class, RxModule.class, DBHelperModule.class,
                        ZendeskServiceHelperModule.class, GsonModule.class, MyObservableModule.class})
public interface IRxMvpAppComponent {
// Floow Project
    Context context();

    AndroidRxSchedulers rxSchedulers();

    DBHelper dBHelper();

//    Picasso picasso();
// Zendesk Project
    ZendeskService zendeskService();

    ZendeskServiceHelper zendeskServiceHelper();

    MyObservable myObservable();
}
