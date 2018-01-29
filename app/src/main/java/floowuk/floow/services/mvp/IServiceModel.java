package floowuk.floow.services.mvp;

import android.support.annotation.NonNull;

import java.util.List;

import floowuk.floow.model.UserLocation;

/*** Created by igorfrankiv on 28/01/2018.*/

public interface IServiceModel{
    void readIOdata( @NonNull IServiceOnCompleteModel iServiceOnCompleteModel);
    void writeIOData( @NonNull List<UserLocation> mListOfUserLocations,  @NonNull IServiceOnCompleteModel iServiceOnCompleteModel, String isRecorded);
}