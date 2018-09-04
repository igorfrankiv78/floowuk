package floowuk.floowrx.model;

import android.support.annotation.NonNull;
import java.util.List;
/*** Created by igorfrankiv on 28/02/2018.*/

public final class JourneyDATA {

    private final List<UserLocation> mListOfUserLocations;
    private final String isRecorded;

    public JourneyDATA(@NonNull List<UserLocation> mListOfUserLocations, @NonNull String isRecorded){
        this.mListOfUserLocations = mListOfUserLocations;
        this.isRecorded = isRecorded;
    }

    @NonNull
    public List<UserLocation> getmListOfUserLocations() {
        return mListOfUserLocations;
    }

    @NonNull
    public String getIsRecorded() {
        return isRecorded;
    }
}
