package flowigor.ie.floowuk.screen.list.dagger2;

import dagger.Component;
import floowuk.floowrx.app.builder.IRxMvpAppComponent;
import floowuk.floowrx.screens.listofjourneys.dagger.IListOfJourneysScope;
import floowuk.floowrx.screens.listofjourneys.dagger.ListOfJourneysModule;
import floowuk.floowrx.screens.listofjourneys.dagger.IListOfJourneysComponent;
/*** Created by igorfrankiv on 25/03/2018. ***/

@IListOfJourneysScope
@Component(modules = ListOfJourneysModule.class, dependencies = IRxMvpAppComponent.class)
public interface ITestListOfJourneysComponent extends IListOfJourneysComponent
{
    void inject( ListOfJourneysTest listOfJourneys );
}