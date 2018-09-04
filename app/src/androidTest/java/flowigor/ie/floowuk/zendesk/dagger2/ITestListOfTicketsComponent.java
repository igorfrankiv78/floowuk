package flowigor.ie.floowuk.zendesk.dagger2;

import dagger.Component;
import floowuk.floowrx.app.builder.IRxMvpAppComponent;
import floowuk.zendeskigorlibrary.screens.dagger.IListOfTicketsScope;
import floowuk.zendeskigorlibrary.screens.dagger.ListOfTicketsModule;
import flowigor.ie.floowuk.zendesk.helpers.ZendeskServiceHelperTest;

/*** Created by igorfrankiv on 03/05/2018.*/
@IListOfTicketsScope
@Component(modules = ListOfTicketsModule.class, dependencies = IRxMvpAppComponent.class)
public interface ITestListOfTicketsComponent {
    void inject( ListOfTicketsTest listOfTicketsTest );
    void inject( ZendeskServiceHelperTest zendeskServiceHelperTest );
}
