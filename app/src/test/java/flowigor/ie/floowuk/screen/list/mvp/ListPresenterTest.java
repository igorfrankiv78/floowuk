package flowigor.ie.floowuk.screen.list.mvp;

import com.twistedequations.mvl.rx.AndroidRxSchedulers;
import com.twistedequations.mvl.rx.TestAndroidRxSchedulers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import java.util.List;
import floowuk.floowrx.model.UserLocationsDB;
import floowuk.floowrx.screens.listofjourneys.mvp.IListView;
import floowuk.floowrx.screens.listofjourneys.mvp.ListModel;
import floowuk.floowrx.screens.listofjourneys.mvp.ListPresenter;
import flowigor.ie.floowuk.screen.list.model.ListPresenterModelTest;
import rx.Observable;
import rx.observers.TestSubscriber;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

/*** Created by igorfrankiv on 17/03/2018.*/

public class ListPresenterTest {

     ListPresenter listPresenter;
     IListView mIListView;
     ListModel mListModel;
     private AndroidRxSchedulers schedulers = new TestAndroidRxSchedulers();
     ListPresenterModelTest mListPresenterModelTest;

    @Before
    public void setUp() throws Exception {
        mListModel = mock(ListModel.class);
        mIListView = mock(IListView.class);
        listPresenter = new ListPresenter ( mIListView, mListModel, schedulers );
        mListPresenterModelTest = new ListPresenterModelTest();
    }

    @Test
    public void listPresenterTesSubscriptionSucessTest() throws Exception {

        when( this.mListModel. getAllJourneys() ).thenReturn(Observable.just( mListPresenterModelTest.getUserLocationsDB() ));
        TestSubscriber<List<UserLocationsDB>> testSubscriber = new TestSubscriber<>();

        mListModel.getAllJourneys().subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(mListPresenterModelTest.getUserLocationsDB());
        testSubscriber.assertValueCount(1);
        testSubscriber.unsubscribe();
    }

    @Test
    public void listPresenterObservablesSubscriptionSucessTest() throws Exception {
        // --------------- when  --------------- when  ---------------
        // ---------------   getAllJourneys()          ---------------
        when( this.mListModel. getAllJourneys() ).thenReturn(Observable.just( mListPresenterModelTest.getUserLocationsDB() ));
        // ---------------   listItemClicks()        ---------------
        when( this.mIListView. listItemClicks() ).thenReturn(Observable.just( mListPresenterModelTest.getUserLocation() ));
        // ---------------   getPositionToBeRemoveD()   ---------------
        when( this.mIListView. getPositionToRemove( ) ).thenReturn(Observable.just( mListPresenterModelTest.getDeleteItemLocationsDB() ));
        when( this.mListModel. getPositionToRemove( mListPresenterModelTest.getDeleteItemLocationsDB()) ).
                thenReturn( mListPresenterModelTest.getIdRemoved() );
        // ---------------------------------------------
        listPresenter.onCreate();
        // ---------------  verify ---------------------
        // ------------------------------ getAllJourneys()  ---------------
        verify( this.mIListView, times(1)).provideAllJourneys( mListPresenterModelTest.getUserLocationsDB());
        // ------------------------------ listItemClicks()  ---------------
        verify( this.mIListView, times(1)).startDetailActivity( mListPresenterModelTest.getUserLocation());
        // ---------------   getPositionToBeRemoveD()   ----------------------
        verify( this.mIListView, times(1)).provideNumToBeRemoved( mListPresenterModelTest.getPosition() );
        // ---------------------------------------------
        listPresenter.unsubscribe();
    }

    @Test
    public void listPresenterObservablesSubscriptionSucessFullTest() throws Exception {
        // --------------- when  --------------- when  ---------------
        // ---------------   getAllJourneys()          ---------------
        when( this.mListModel. getAllJourneys() ).thenReturn(Observable.just( mListPresenterModelTest.getUserLocationsDB() ));

        Mockito.doAnswer((Answer<Void>) invocation ->
        { System.out.print("provideAllJourneys test \n"); return null; }).
                when(mIListView).provideAllJourneys( mListPresenterModelTest.getUserLocationsDB() );
        // ---------------   listItemClicks()        ---------------
        when( this.mIListView. listItemClicks() ).thenReturn(Observable.just( mListPresenterModelTest.getUserLocation() ));

        Mockito.doAnswer((Answer<Void>) invocation ->
        { System.out.print("startDetailActivity test \n"); return null; }).
                when(mIListView).startDetailActivity( mListPresenterModelTest.getUserLocation() );
        // ---------------   getPositionToBeRemoveD()   ---------------
        when( this.mIListView. getPositionToRemove( ) ).thenReturn(Observable.just( mListPresenterModelTest.getDeleteItemLocationsDB() ));
        when( this.mListModel. getPositionToRemove( mListPresenterModelTest.getDeleteItemLocationsDB()) ).
                thenReturn( mListPresenterModelTest.getIdRemoved() );

        Mockito.doAnswer((Answer<Void>) invocation ->
        { System.out.print("provideNumToBeRemoved test \n"); return null; }).
                when(mIListView).provideNumToBeRemoved( mListPresenterModelTest.getPosition() );
        // ---------------------------------------------
        listPresenter.onCreate();
        // ---------------  verify ---------------------
        // ------------------------------ getAllJourneys()  ---------------
        verify( this.mListModel).getAllJourneys();
        verify( this.mIListView, times(1)).provideAllJourneys( mListPresenterModelTest.getUserLocationsDB());
        // ------------------------------ listItemClicks()  ---------------
        verify( this.mIListView).listItemClicks(  );
        verify( this.mIListView, times(1)).startDetailActivity( mListPresenterModelTest.getUserLocation());
        // ---------------   getPositionToBeRemoveD()   ---------------
        verify( this.mIListView).getPositionToRemove();
        verify( this.mListModel).getPositionToRemove( mListPresenterModelTest.getDeleteItemLocationsDB());
        verify( this.mIListView, times(1)).provideNumToBeRemoved( mListPresenterModelTest.getPosition() );
        // ---------------------------------------------
        listPresenter.unsubscribe();
    }
}
