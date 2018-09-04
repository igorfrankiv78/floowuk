package flowigor.ie.floowuk.screen.list.dagger2;

import rx.Observable;
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

import floowuk.floowrx.screens.listofjourneys.mvp.ListModel;
import floowuk.floowrx.screens.listofjourneys.mvp.IListView;
import floowuk.floowrx.screens.listofjourneys.ListOfJourneys;
import flowigor.ie.floowuk.screen.list.model.ListPresenterModelTest;
import floowuk.floowrx.screens.listofjourneys.mvp.ListPresenter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*** Created by igorfrankiv on 25/03/2018.*/
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ListOfJourneysTest {

    @Inject ListPresenter mListPresenter;
            IListView mIListView = Mockito.mock(IListView.class);
            ListModel mListModel = Mockito.mock(ListModel.class);
            ListPresenterModelTest mListPresenterModelTest;

    @Before
    public void setUp() {

        mListPresenterModelTest = new ListPresenterModelTest();

        ListOfJourneys listOfJourneys = Robolectric.setupActivity(ListOfJourneys.class);
        DaggerITestListOfJourneysComponent.builder()
                .iRxMvpAppComponent(RxMvpApp.get(listOfJourneys).component())
                .listOfJourneysModule(new TestListOfJourneysModule(mIListView))
                .build().inject(this);
    }
//
    @Test
    public void testListOfJourneysTest() {
        // --------------- listItemClicks() ---------------
        // --------------- when  --------------- when  ---------------
        // ---------------   getAllJourneys()          ---------------
        when( this.mListModel. getAllJourneys() ).thenReturn(Observable.just( mListPresenterModelTest.getUserLocationsDB() ));
        // ---------------   listItemClicks()        ---------------
        when( this.mIListView. listItemClicks() ).thenReturn(Observable.just( mListPresenterModelTest.getUserLocation() ));
        // ---------------   getPositionToBeRemoveD()   ---------------
        when( this.mIListView. getPositionToRemove( ) ).thenReturn(Observable.just( mListPresenterModelTest.getDeleteItemLocationsDB() ));
        when( this.mListModel. getPositionToRemove( mListPresenterModelTest.getDeleteItemLocationsDB()) ).
                thenReturn( mListPresenterModelTest.getIdRemoved() );
        mListPresenter.onCreate();
// ---------------  verify ------------------------
//        // ------------------------------ getAllJourneys()  ---------------
//        verify( this.mIListView, times(1)).provideAllJourneys( mListPresenterModelTest.getUserLocationsDB());
//        // ------------------------------ listItemClicks()  ---------------
//        verify( this.mIListView, times(1)).startDetailActivity( mListPresenterModelTest.getUserLocation());
//        // ---------------   getPositionToBeRemoveD()   ----------------------
//        verify( this.mIListView, times(1)).provideNumToBeRemoved( mListPresenterModelTest.getPosition() );
// // ---------------------------------------------
        mListPresenter.unsubscribe();
    }

}
