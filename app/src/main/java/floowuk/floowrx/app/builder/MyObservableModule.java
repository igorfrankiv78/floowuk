package floowuk.floowrx.app.builder;

import dagger.Module;
import dagger.Provides;
import floowuk.floowrx.model.MyObservable;

/**
 * Created by igorfrankiv on 09/05/2018.
 */
@Module
public class MyObservableModule {

    @IAppScope
    @Provides
    public MyObservable  myObservable() {
        return new MyObservable<>();
    }
}
