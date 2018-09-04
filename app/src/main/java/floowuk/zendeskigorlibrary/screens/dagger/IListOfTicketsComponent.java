package floowuk.zendeskigorlibrary.screens.dagger;

import dagger.Component;
import floowuk.floowrx.app.builder.IRxMvpAppComponent;
import floowuk.zendeskigorlibrary.screens.ListOfTickets;

/*** Created by igorfrankiv on 26/04/2018.*/
@IListOfTicketsScope
@Component(modules = ListOfTicketsModule.class, dependencies = IRxMvpAppComponent.class)
public interface IListOfTicketsComponent {

    void inject(ListOfTickets listOfTickets);

}
