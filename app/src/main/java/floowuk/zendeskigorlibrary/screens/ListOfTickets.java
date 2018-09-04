package floowuk.zendeskigorlibrary.screens;
/*** Created by igor on 04/06/2017. ***/

import android.os.Bundle;
import android.util.Log;
import javax.inject.Inject;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import flowigor.ie.floowuk.R;
import floowuk.floowrx.app.RxMvpApp;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import floowuk.zendeskigorlibrary.model.TicketsResults;
import floowuk.zendeskigorlibrary.screens.mvp.IListView;
import floowuk.zendeskigorlibrary.screens.mvp.ListPresenter;
import floowuk.zendeskigorlibrary.screens.dagger.ListOfTicketsModule;
import floowuk.zendeskigorlibrary.screens.viewadapt.ZendeskListViewAdapter;
import floowuk.zendeskigorlibrary.screens.dagger.DaggerIListOfTicketsComponent;

public class ListOfTickets extends Activity implements IListView {

     @Inject
     ListPresenter mListPresenter;
    private RecyclerView mRecyclerViewVirtical;
    private ZendeskListViewAdapter mZendeskListViewAdapter;

    @BindView(R.id.errorText)
    TextView errorText;

    private final String errorStr = "Error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_testr);

        ButterKnife.bind(this);

        DaggerIListOfTicketsComponent.builder()
                .iRxMvpAppComponent(RxMvpApp.get(this).component())
                .listOfTicketsModule(new ListOfTicketsModule(this))
                .build().inject(this);

        mListPresenter.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListPresenter.unsubscribe();
    }

    @Override
    public void successInGettingTickets(TicketsResults ticketsResults) {

        if( ticketsResults != null ){
            errorText.setVisibility(View.GONE);
            mZendeskListViewAdapter = new ZendeskListViewAdapter(ticketsResults, this);
            mRecyclerViewVirtical = (RecyclerView) findViewById(R.id.recycler_view);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            mRecyclerViewVirtical.setLayoutManager(layoutManager);
            mRecyclerViewVirtical.setAdapter(mZendeskListViewAdapter);
        } else {
            errorText.setText(errorStr);
            errorText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void errorInGettingTickets(String reason) {
        errorText.setVisibility(View.VISIBLE);
        errorText.setText(reason);
        Toast.makeText(getApplicationContext(), reason, Toast.LENGTH_LONG).show();
    }
}