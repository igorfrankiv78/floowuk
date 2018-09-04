package floowuk.floowrx.app.builder;

/*** Created by igorfrankiv on 27/02/2018.*/
import com.twistedequations.mvl.rx.AndroidRxSchedulers;
import dagger.Module;
import dagger.Provides;

@Module
public class RxModule {

    @IAppScope
    @Provides
    public AndroidRxSchedulers rxSchedulers() {
        return new MyAndroidRxSchedulers();
    }

}