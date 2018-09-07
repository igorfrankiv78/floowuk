package floowuk.floowrx.app.builder;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import floowuk.floowrx.helpers.DBHelper;
/*** Created by igorfrankiv on 01/03/2018.*/

@Module
public class DBHelperModule {

    @IAppScope
    @Provides
    public DBHelper dbHelper( Context context ) {
        return new DBHelper( context );
    }
}