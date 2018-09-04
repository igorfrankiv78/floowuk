package floowuk.floowrx.app.builder;

import com.fatboyindustrial.gsonjodatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;

/**
 * Created by igorfrankiv on 28/04/2018.
 */
@Module
public class GsonModule {

    @IAppScope
    @Provides
    public Gson gson() {
        GsonBuilder builder = new GsonBuilder();
        Converters.registerAll(builder);
        return builder.create();
    }
}
