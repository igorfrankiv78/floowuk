package flowigor.ie.floowuk.screen.detail.reactivex;

import rx.Observable;
import org.junit.Test;
import org.junit.Before;
import rx.observers.TestSubscriber;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import floowuk.floowrx.model.UserLocations;
import floowuk.floowrx.screens.detail.mvp.DetailModel;
import floowuk.floowrx.screens.detail.mvp.IDetailView;
import flowigor.ie.floowuk.screen.detail.model.DetailPresenterModelTest;
/*** Created by igorfrankiv on 14/04/2018.*/

public class DetailedJourneyObservableSubsTest {
    
    private IDetailView iDetailView;
    private DetailModel detailModel;
    private DetailPresenterModelTest mDetailPresenterModelTest;
    private Integer mId = 1;

    @Before
    public void setUp() throws Exception {
        detailModel = mock(DetailModel.class);
        iDetailView = mock(IDetailView.class);
        mDetailPresenterModelTest = new DetailPresenterModelTest();
    }

    @Test
    public void testBtnDeleteUserJourneyObservable() throws Exception {
        // Observable<Integer> btnDeleteUserJourneyObservable();
        when( iDetailView.btnDeleteUserJourneyObservable() ).thenReturn(Observable.just( mId ));

        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();

        iDetailView.btnDeleteUserJourneyObservable().subscribe( testSubscriber );

        testSubscriber.assertNoErrors();

        testSubscriber.assertValue( mId );

        testSubscriber.assertValueCount( mId );

        testSubscriber.unsubscribe();
    }

    @Test
    public void testReadIOdataRX() throws Exception {
        // Observable<Integer> readIOdataRX();
        when( iDetailView.readIOdataRX() ).thenReturn(Observable.just( 1 ));

        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();

        iDetailView.readIOdataRX().subscribe( testSubscriber );

        testSubscriber.assertNoErrors();

        testSubscriber.assertValue( mId );

        testSubscriber.assertValueCount( mId );

        testSubscriber.unsubscribe();

    }

    @Test
    public void testDeleteRecordDB() throws Exception {
//    Observable<String> deleteRecordDB ( Integer id );
      when( detailModel.deleteRecordDB( mId ) ).thenReturn( Observable.just( DetailModel.SUCCESS_MESSAGE ));
        
             TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        
             detailModel.deleteRecordDB( mId ) .subscribe( testSubscriber );
        
             testSubscriber.assertNoErrors();
        
             testSubscriber.assertValue( DetailModel.SUCCESS_MESSAGE );
        
             testSubscriber.assertValueCount( mId );
        
             testSubscriber.unsubscribe();
    }

    @Test
    public void testGetDetailedRecord() throws Exception {
//        Observable<UserLocations> getDetailedRecord ( Integer id );
          when( detailModel.getDetailedRecord( mId ) ).thenReturn( Observable.just( mDetailPresenterModelTest.getUserLocations() ));
                                                                                                                         
                 TestSubscriber<UserLocations> testSubscriber = new TestSubscriber<>();
                                                                                                                         
                 detailModel.getDetailedRecord( mId ) .subscribe( testSubscriber );
                                                                                                                         
                 testSubscriber.assertNoErrors();                                                                        
                                                                                                                         
                 testSubscriber.assertValue( mDetailPresenterModelTest.getUserLocations() );
                                                                                                                         
                 testSubscriber.assertValueCount( mId );
                                                                                                                         
                 testSubscriber.unsubscribe();                                                                           
    }
}
