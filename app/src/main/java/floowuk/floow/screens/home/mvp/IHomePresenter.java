package floowuk.floow.screens.home.mvp;

import android.support.annotation.NonNull;
import java.util.List;
import floowuk.floow.model.UserLocation;
/*** Created by igorfrankiv on 27/01/2018.*/

public interface IHomePresenter {
    void getSizeOfDB();
    void writeJoutneyInDB( @NonNull List<UserLocation> mListOfUserLocations,  @NonNull String isRecorded);
}
