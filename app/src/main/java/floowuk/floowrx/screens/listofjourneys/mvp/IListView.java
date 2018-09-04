package floowuk.floowrx.screens.listofjourneys.mvp;

import android.support.annotation.NonNull;
import java.util.List;
import floowuk.floowrx.model.DeleteItemLocationsDB;
import floowuk.floowrx.model.UserLocationsDB;
import rx.Observable;
/*** Created by igorfrankiv on 28/01/2018.*/

public interface IListView {

    void showError();

    Observable<UserLocationsDB> listItemClicks();

    void startDetailActivity(UserLocationsDB userLocationsDB);

    void setUserLocationsDBItems(List<UserLocationsDB> items);

    void provideAllJourneys(@NonNull List<UserLocationsDB> userLocationsDB);

    Observable<DeleteItemLocationsDB> getPositionToRemove();

    void provideNumToBeRemoved( Integer position);

}