package flowigor.ie.floowuk.screen.list.reactivex;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import org.mockito.Mockito;
import rx.Observable;
import rx.observers.TestSubscriber;
import rx.subjects.PublishSubject;
import static org.mockito.Mockito.when;
import floowuk.floowrx.model.UserLocationsDB;
import floowuk.floowrx.screens.listofjourneys.mvp.IListView;
import floowuk.floowrx.screens.listofjourneys.mvp.ListModel;
import flowigor.ie.floowuk.screen.list.model.ListPresenterModelTest;
/*** Created by igorfrankiv on 31/03/2018.*/

public class ListOfJourneysObservableSubsTest {

    private IListView mIListView ;
    private ListModel mListModel ;
    private ListPresenterModelTest mListPresenterModelTest;

    @Before
    public void setUp() {
        mListPresenterModelTest = new ListPresenterModelTest();
        mIListView = Mockito.mock(IListView.class);
        mListModel = Mockito.mock(ListModel.class);
    }

    @Test
    public void testListItemClicks() throws Exception {
//    Observable<UserLocationsDB> ();
        when( mIListView.listItemClicks() ).thenReturn(Observable.just( mListPresenterModelTest.getUserLocation()  ));

        TestSubscriber<UserLocationsDB> testSubscriber = new TestSubscriber<>();

        mIListView.listItemClicks().subscribe( testSubscriber );

        testSubscriber.assertNoErrors();

        testSubscriber.assertValue( mListPresenterModelTest.getUserLocation() );

        testSubscriber.assertValueCount( 1 );

        testSubscriber.unsubscribe();
    }

    @Test
    public void testGetAllJourneys() throws Exception {
    //    Observable<List<UserLocationsDB>> getAllJourneys()
        when( mListModel.getAllJourneys() ).thenReturn(Observable.just( mListPresenterModelTest.getUserLocationsDB() ));

        TestSubscriber<List<UserLocationsDB> >testSubscriber = new TestSubscriber<>();

        mListModel.getAllJourneys().subscribe( testSubscriber );

        testSubscriber.assertNoErrors();

        testSubscriber.assertValue( mListPresenterModelTest.getUserLocationsDB() );

        testSubscriber.assertValueCount( 1 );

        testSubscriber.unsubscribe();
    }

    @Test
    public void testDoSomethingTestSubscriptionSucessTest() throws Exception {
        ArrangeBuilder builder = new ArrangeBuilder();
        TestSubscriber<List<UserLocationsDB>> testSubscriber = new TestSubscriber<>();

        this.mListModel. getAllJourneys().subscribe(testSubscriber);

        builder.withUserLocationsDB( mListPresenterModelTest.getUserLocationsDB() );

        testSubscriber.assertNoErrors();

        testSubscriber.assertValue( mListPresenterModelTest.getUserLocationsDB() );

        testSubscriber.assertValueCount(1);
        testSubscriber.unsubscribe();
    }

    private class ArrangeBuilder {
//        AsyncSubject: only emits the last value of the source Observable
//        BehaviorSubject: emits the most recently emitted item and all the subsequent items of the source Observable when a observer subscribe to it
//        PublishSubject: emits all the subsequent items of the source Observable at the time of the subscription
//        ReplaySubject: emits all the items of the source Observable, regardless of when the subscriber subscribes.
//        http://www.juanmendez.info/2015/11/when-to-use-publishsubject-or.html
        private PublishSubject<List<UserLocationsDB>> mUserLocationsDBSubject = PublishSubject.create();

        public ArrangeBuilder() {
            when( mListModel. getAllJourneys()).thenReturn(mUserLocationsDBSubject);
        }

        ArrangeBuilder withUserLocationsDB(List<UserLocationsDB> locationData) {
            mUserLocationsDBSubject.onNext(locationData);
            return this;
        }

    }
}