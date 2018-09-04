package flowigor.ie.floowuk.screen.detail.dagger2;

import dagger.Component;
import floowuk.floowrx.app.builder.IRxMvpAppComponent;
import floowuk.floowrx.screens.detail.dagger.DetailedJourneyModule;
import floowuk.floowrx.screens.detail.dagger.IDetailedJourneyScope;
import floowuk.floowrx.screens.detail.dagger.IDetailedJourneyComponent;
/*** Created by igorfrankiv on 28/03/2018. ***/

@IDetailedJourneyScope
@Component(modules = DetailedJourneyModule.class, dependencies = IRxMvpAppComponent.class)
public interface ITestDetailedJourneyComponent extends IDetailedJourneyComponent {
    void inject( DetailedJourneyTest detailedJourneyTest );
}
