package floowuk.floow.services.mvp;

import android.support.annotation.NonNull;
import java.util.List;
import floowuk.floow.model.UserLocation;
/*** Created by igorfrankiv on 28/01/2018.*/

public interface IServiceOnCompleteModel {

    void showError(@NonNull String reason);
    void readIODataReceived( List<UserLocation> listOfUserLocations );
    void readyDataForBroadcast( String jsonListOfUserLocationsObj);

}