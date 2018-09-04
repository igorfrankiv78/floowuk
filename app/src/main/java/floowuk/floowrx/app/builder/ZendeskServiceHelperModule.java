package floowuk.floowrx.app.builder;

import dagger.Module;
import dagger.Provides;
import floowuk.zendeskigorlibrary.helpers.ZendeskServiceHelper;
import floowuk.zendeskigorlibrary.zendesk.ZendeskService;

/*** Created by igorfrankiv on 03/05/2018.*/
@Module
public class ZendeskServiceHelperModule {
    @IAppScope
    @Provides
    public ZendeskServiceHelper dbHelper(ZendeskService zendeskService ) {
        return new ZendeskServiceHelper(  zendeskService );
    }
}
