package zendeskigorlibrary.reactivex;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import floowuk.zendeskigorlibrary.model.TicketsResults;
import floowuk.zendeskigorlibrary.screens.mvp.IListView;
import floowuk.zendeskigorlibrary.screens.mvp.ListModel;
import rx.Observable;
import rx.observers.TestSubscriber;
import zendeskigorlibrary.model.TicketsResultsTest;

import static org.mockito.Mockito.when;

/*** Created by igorfrankiv on 04/05/2018.*/

public class ListOfTicketsObservableSubsTest {

    private IListView mIListView ;
    private ListModel mListModel ;
    private TicketsResultsTest mTicketsResultsTest;

    @Before
    public void setUp() {
        mTicketsResultsTest = new TicketsResultsTest();
        mIListView = Mockito.mock(IListView.class);
        mListModel = Mockito.mock(ListModel.class);
    }

    @Test
    public void testListItemClicks() throws Exception {
//    Observable<TicketsResults> ();
        when( mListModel.getAllTheTickets() ).thenReturn(Observable.just( mTicketsResultsTest.getTicketsResults()  ));

        TestSubscriber<TicketsResults> testSubscriber = new TestSubscriber<>();

        mListModel.getAllTheTickets().subscribe( testSubscriber );

        testSubscriber.assertNoErrors();

        testSubscriber.assertValue( mTicketsResultsTest.getTicketsResults() );

        testSubscriber.assertValueCount( 1 );

        testSubscriber.unsubscribe();
    }
}
