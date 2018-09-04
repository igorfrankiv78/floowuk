package floowuk.floowrx.app.builder;

/*** Created by igorfrankiv on 26/02/2018.*/
import android.content.Context;
import dagger.Module;
import dagger.Provides;

@Module
public class RxMvpAppModule {

    private final Context context;

    public RxMvpAppModule(Context context) {
        this.context = context;
    }

    @Provides
    @IAppScope
    public Context context() {
        return context;
    }
}

