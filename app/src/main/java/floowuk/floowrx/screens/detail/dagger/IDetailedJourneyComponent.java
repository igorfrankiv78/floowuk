package floowuk.floowrx.screens.detail.dagger;

/*** Created by igorfrankiv on 28/02/2018. */
import dagger.Component;
import floowuk.floowrx.app.builder.IRxMvpAppComponent;
import floowuk.floowrx.screens.detail.DetailedJourney;

@IDetailedJourneyScope
@Component(modules = DetailedJourneyModule.class, dependencies = IRxMvpAppComponent.class)
public interface IDetailedJourneyComponent {
    void inject(DetailedJourney detailedJourney);
}
