package floowuk.floowrx.screens.listofjourneys.dagger;

import dagger.Component;
import floowuk.floowrx.app.builder.IRxMvpAppComponent;
import floowuk.floowrx.screens.listofjourneys.ListOfJourneys;
/*** Created by igorfrankiv on 28/02/2018.*/

@IListOfJourneysScope
@Component(modules = ListOfJourneysModule.class, dependencies = IRxMvpAppComponent.class)
public interface IListOfJourneysComponent {

    void inject(ListOfJourneys listOfJourneys);

}
