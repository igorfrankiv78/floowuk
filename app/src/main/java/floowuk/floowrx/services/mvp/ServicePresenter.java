package floowuk.floowrx.services.mvp;

import floowuk.floowrx.model.DataForBroadcast;
import floowuk.floowrx.model.LocationData;
import com.twistedequations.mvl.rx.AndroidRxSchedulers;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
/*** Created by igorfrankiv on 28/01/2018.*/

public class ServicePresenter {

    private IServiceView mIServiceView;
    private ServiceModel mServiceModell;
    public static final String STATE_OF_RECORDDING_YES = "YES";
    public static final String STATE_OF_RECORDDING_NO = "NO";
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();
    private AndroidRxSchedulers schedulers;

    @Inject
    public ServicePresenter(IServiceView iServiceView , ServiceModel serviceModel, AndroidRxSchedulers schedulers )
    {
        this.mIServiceView = iServiceView;
        this.mServiceModell = serviceModel;
        this.schedulers = schedulers;
    }

    public void onCreate() {
        compositeSubscription.add( startService() );
        compositeSubscription.add( writeIOdataRX());
    }

    public void unsubscribe(){
        compositeSubscription.unsubscribe();
    }
    //=================================================
     private Observable<DataForBroadcast> startChainOffuncs(LocationData locationData ) {

         return   mServiceModell.readIOdataRX(locationData)   // ========== 1 read the io data if any
                 .subscribeOn(schedulers.io()).observeOn(schedulers.mainThread())

                 .map( mServiceModell::calculateDistanceBetween2pointsRX )// ========== 2 calculate DistanceBetween two points
                 .subscribeOn(schedulers.computation()) .observeOn(schedulers.mainThread())

                 .map( mServiceModell::writeIODataRX2 )                        // ========== 3 write the io data and create the data for the broadcast
                 .subscribeOn(schedulers.io()).observeOn(schedulers.mainThread())

                 .doOnError(throwable -> {
                     throwable.printStackTrace(); //Log errors
                     this.mIServiceView.showError(); // show error dialog on error
                 })
                .onErrorReturn(throwable2 -> {
                   return  null; // show error dialog on error
                }); // eat the error and return show an error
     }

    private Subscription startService() {
        return this.mIServiceView.getLocationDataObservable() // ========== 1 Service class set and send Location Data
                .flatMap( this::startChainOffuncs )          // ========== 2 Start the chain of methods and
                                                              // return Observable DataForBroadcast for the View
                .doOnError(throwable -> {
                    throwable.printStackTrace();              // Log errors
                    this.mIServiceView.showError();           // show error dialog on error
                })
                .subscribe(mIServiceView::readyDataForBroadcastRX); // ========== 3 send data to the View
    }
//=================================================
    public Subscription writeIOdataRX( ){
        return this.mIServiceView.finalWriteIODataRX() //
                .map( mServiceModell::finalWriteIODataRX  )   //
                //
                .doOnError(throwable -> {
                    throwable.printStackTrace();  //
                    this.mIServiceView.showError(); //
                })
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .subscribe(this.mIServiceView::onResultMessage);//
    }
//=================================================
}
