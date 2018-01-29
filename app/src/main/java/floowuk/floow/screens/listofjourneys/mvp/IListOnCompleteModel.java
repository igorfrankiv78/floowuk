package floowuk.floow.screens.listofjourneys.mvp;

import android.support.annotation.NonNull;

import floowuk.floow.model.UserLocationsDB;

/*** Created by igorfrankiv on 28/01/2018.*/

public interface IListOnCompleteModel {
    void showError(@NonNull String reason);
    void provideAllJourneys(@NonNull UserLocationsDB userLocationsDB);
}
