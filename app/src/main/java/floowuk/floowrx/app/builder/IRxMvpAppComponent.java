package floowuk.floowrx.app.builder;

/*** Created by igorfrankiv on 26/02/2018.*/
import dagger.Component;
import android.content.Context;
import floowuk.floowrx.helpers.DBHelper;
import com.twistedequations.mvl.rx.AndroidRxSchedulers;

@IAppScope
@Component(modules = {  RxMvpAppModule.class,RxModule.class, DBHelperModule.class})
public interface IRxMvpAppComponent {
    Context context();

    AndroidRxSchedulers rxSchedulers();

    DBHelper dBHelper();
}
