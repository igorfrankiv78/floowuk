package floowuk.floowrx.screens.listofjourneys.dagger;

/*** Created by igorfrankiv on 28/02/2018.*/
import dagger.Module;
import dagger.Provides;
import floowuk.floowrx.helpers.DBHelper;
import floowuk.floowrx.screens.listofjourneys.mvp.IListView;
import floowuk.floowrx.screens.listofjourneys.mvp.ListModel;
import floowuk.floowrx.screens.listofjourneys.mvp.ListPresenter;
import com.twistedequations.mvl.rx.AndroidRxSchedulers;

@Module
public class ListOfJourneysModule {

    IListView mView;

    public ListOfJourneysModule( IListView view ) {
        mView = view;
    }

    @Provides
    public IListView iListView() {
        return mView;
    }

    @Provides
    @IListOfJourneysScope
    public ListPresenter homePresenter( IListView iListView, ListModel listModel, AndroidRxSchedulers schedulers){
        return new ListPresenter( iListView, listModel, schedulers );
    }

    @Provides
    @IListOfJourneysScope
    public ListModel listModel(DBHelper mDBHelper) {
        return new ListModel( mDBHelper );
    }
}
