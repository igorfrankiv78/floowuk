package floowuk.floow.screens.home.mvp;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import floowuk.floow.model.UserLocation;

/*** Created by igorfrankiv on 27/01/2018.*/
public interface IHomeModel {
    void getSizeOfDB( @NonNull IOnCompleteModel iIOnCompleteModel);
    void writeJourneyInDB( @NonNull List<UserLocation> mListOfUserLocations, @NonNull IOnCompleteModel iOnCompleteModel, @NonNull String isRecorded);
}
