package flowigor.ie.floowuk.screen.detail.dagger2;

import org.junit.Test;
import org.junit.Before;
import org.mockito.Mockito;
import javax.inject.Inject;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import floowuk.floowrx.app.RxMvpApp;
import flowigor.ie.floowuk.BuildConfig;
import org.robolectric.annotation.Config;
import org.robolectric.RobolectricTestRunner;
import floowuk.floowrx.screens.detail.DetailedJourney;
import floowuk.floowrx.screens.detail.mvp.IDetailView;
import floowuk.floowrx.screens.detail.mvp.DetailPresenter;

/*** Created by igorfrankiv on 28/03/2018. ***/
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DetailedJourneyTest {

    @Inject
    DetailPresenter mDetailPresenter;

    IDetailView mIDetailView = Mockito.mock(IDetailView.class);

    DetailedJourney mDetailedJourney;

    @Before
    public void setUp() {
        mDetailedJourney = Robolectric.setupActivity(DetailedJourney.class);
    }

    @Test
    public void daggerTest() throws Exception {
        DaggerITestDetailedJourneyComponent.builder()
                .iRxMvpAppComponent(RxMvpApp.get(mDetailedJourney).component())
                .detailedJourneyModule(new TestDetailedJourneyModule(mIDetailView))
                .build().inject(this);
    }

}
