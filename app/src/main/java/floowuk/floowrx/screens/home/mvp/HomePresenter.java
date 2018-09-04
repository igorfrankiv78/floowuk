package floowuk.floowrx.screens.home.mvp;

    import com.twistedequations.mvl.rx.AndroidRxSchedulers;
    import javax.inject.Inject;
    import rx.Subscription;
    import rx.subscriptions.CompositeSubscription;
    /*** Created by igorfrankiv on 27/01/2018.*/

public class HomePresenter {

    private IHomeView mIHomeView;
    private HomeModel mHomeModel;
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();
    private AndroidRxSchedulers schedulers;

    @Inject
    public HomePresenter (IHomeView mIHomeView, HomeModel homeModel, AndroidRxSchedulers schedulers)
    {
        this.mIHomeView = mIHomeView;
        this.mHomeModel = homeModel;
        this.schedulers = schedulers;
    }

    public void onCreate() {
        compositeSubscription.add( showJourneys() );
        compositeSubscription.add( startServices() );
        compositeSubscription.add( stopServices() );
    }

    public void unsubscribe(){
        compositeSubscription.unsubscribe();
    }

    private Subscription startServices() {
        return mIHomeView.btnStartServiceObservable().subscribe(aVoid -> mIHomeView.startService() );
    }

    private Subscription showJourneys(){
        return this.mIHomeView.btnShowJourneysObservable() //   click
                .map( r ->  this.mHomeModel.getSizeOfDB() ) // get the size of db from data base
                .doOnError(throwable -> {
                    throwable.printStackTrace(); //Log errors
                    this.mIHomeView.showError(); // show error dialog on error
                })
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .subscribe(this.mIHomeView::letUserToSeeHisJourneys, e-> this.mIHomeView.showError());// display data
    }

    private Subscription stopServices() {
                return this.mIHomeView.btnStopServiceObservable() // click on the button and get the current data for saviving db
                .map( mHomeModel::writeJourneyInDB ) // write the data to the data base
                .doOnError(throwable -> {
                throwable.printStackTrace(); //Log errors
                this.mIHomeView.showError(); // show error dialog on error
                })
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .subscribe(this.mIHomeView::onCompleteResult, e-> this.mIHomeView.showError()); //  show on complete result (true or false) message
    }

}
