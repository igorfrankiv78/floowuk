package floowuk.floowrx.screens.listofjourneys;

/*** Created by igorfrankiv on 27/01/2018.*/
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import flowigor.ie.floowuk.R;
import floowuk.floowrx.app.RxMvpApp;
import floowuk.floowrx.model.MyObservable;
import floowuk.floowrx.model.UserLocationsDB;
import floowuk.floowrx.model.DeleteItemLocationsDB;
import android.support.v7.widget.LinearLayoutManager;
import floowuk.floowrx.screens.detail.DetailedJourney;
import floowuk.floowrx.screens.listofjourneys.mvp.IListView;
import floowuk.floowrx.screens.listofjourneys.mvp.ListPresenter;
import floowuk.floowrx.screens.listofjourneys.dagger.ListOfJourneysModule;
import floowuk.floowrx.screens.listofjourneys.viewadapt.ListOfJourneysAdapter;
import floowuk.floowrx.screens.listofjourneys.dagger.DaggerIListOfJourneysComponent;

public class ListOfJourneys extends Activity implements IListView
{
    private final static String ERROR = "Error!";
    public ListOfJourneysAdapter listOfJourneysAdapter;
    public static final int REQUEST_GET_ACCESS_TOKEN = 3338;
    public static final String ID_REMOVED = "idremoved";
    public static final String IS_REMOVED = "isremoved";
    MyObservable<DeleteItemLocationsDB> deleteItemLocationsDBObs = new MyObservable<>();

    @Inject
    ListPresenter mListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_of_journeys);

        listOfJourneysAdapter = new ListOfJourneysAdapter(this );
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(listOfJourneysAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        DaggerIListOfJourneysComponent.builder()
                .iRxMvpAppComponent(RxMvpApp.get(this).component())
                .listOfJourneysModule(new ListOfJourneysModule(this))
                .build().inject(this);

        mListPresenter.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListPresenter.unsubscribe();
    }

    @Override
    public void provideAllJourneys(@NonNull List<UserLocationsDB> userLocationsDB) {
        listOfJourneysAdapter.setdata( userLocationsDB );
    }

    @Override
    public void showError() {
        Toast.makeText(this, ERROR, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startDetailActivity(UserLocationsDB userLocationsDB) {
        if( userLocationsDB.getId() != null ) {
            Intent intent = new Intent(ListOfJourneys.this, DetailedJourney.class );
                   intent.putExtra( ListOfJourneysAdapter.ID, userLocationsDB.getId() );
            startActivityForResult(intent, REQUEST_GET_ACCESS_TOKEN);
        }
    }

    @Override
    public Observable<UserLocationsDB> listItemClicks() {
        return listOfJourneysAdapter.authorObserveClicks().map( listOfJourneysAdapter::getUserLocationsDBItem );
    }

    @Override
    public void setUserLocationsDBItems(List<UserLocationsDB> items) {

    }

    @Override
    public Observable<DeleteItemLocationsDB> getPositionToRemove(){
        return deleteItemLocationsDBObs.getObservable();
    }

    @Override
    public void provideNumToBeRemoved( Integer position){
        listOfJourneysAdapter.removeCell(  position );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data != null ){

            String idRemoved = data.getStringExtra(ID_REMOVED);
            String isRemoved = data.getStringExtra(IS_REMOVED);

            if (requestCode == REQUEST_GET_ACCESS_TOKEN) {

               if ( Boolean.valueOf(isRemoved) &&  idRemoved != null ) {
                   deleteItemLocationsDBObs.add(  new DeleteItemLocationsDB ( listOfJourneysAdapter.getUserLocationsDB(),  Integer.valueOf(idRemoved)  ) );
               }
                }
        }
    }
}
