package floowuk.floow.screens.listofjourneys.mvp;

import android.support.annotation.NonNull;
/*** Created by igorfrankiv on 28/01/2018.*/

public interface IListModel {
    void getAllJourneys( @NonNull IListOnCompleteModel iIOnCompleteModel);
}
