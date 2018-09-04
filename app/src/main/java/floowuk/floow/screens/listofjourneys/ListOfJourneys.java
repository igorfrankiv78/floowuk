package floowuk.floow.screens.listofjourneys;

/*** Created by igorfrankiv on 27/01/2018.*/

import android.os.Bundle;
import android.app.Activity;
import flowigor.ie.floowuk.R;
import android.widget.Toast;
import floowuk.floow.helpers.DBHelper;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import floowuk.floow.model.UserLocationsDB;
import floowuk.floow.screens.listofjourneys.viewadapt.MapsRecViewAdapter;
import floowuk.floow.screens.listofjourneys.mvp.IListView;
import floowuk.floow.screens.listofjourneys.mvp.ListPresenter;

/*** Created by igor on 07/10/2017.*/
public class ListOfJourneys extends Activity implements IListView
{
    private RecyclerView mRecyclerViewVirtical;
    private MapsRecViewAdapter mMapsRecViewAdapter;
    private ListPresenter mListPresenter;
    private DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_journeys);

        mDBHelper = new DBHelper(this);
        mListPresenter = new ListPresenter ( mDBHelper ,  this );
        mListPresenter.getAllIdsTimesStamps();
    }

    @Override
    public void provideAllJourneys(@NonNull UserLocationsDB userLocationsDB) {
        mMapsRecViewAdapter = new MapsRecViewAdapter(userLocationsDB, this);

        mRecyclerViewVirtical = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerViewVirtical.setLayoutManager(layoutManager);
        mRecyclerViewVirtical.setAdapter(mMapsRecViewAdapter);
    }

    @Override
    public void showError(String reason) {
        Toast.makeText(this, reason, Toast.LENGTH_LONG).show();
    }
}
