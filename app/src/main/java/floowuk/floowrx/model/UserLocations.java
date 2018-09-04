package floowuk.floowrx.model;

import java.util.List;

/*** Created by igorfrankiv on 27/01/2018.*/

public final class UserLocations {

    private final List<UserLocation> listOfUserLocations;
    private final String isRecorded;

    public UserLocations(List<UserLocation> listOfUserLocations, String isRecorded) {
       this.listOfUserLocations = listOfUserLocations;
       this.isRecorded = isRecorded;
    }

    public List<UserLocation> getListOfUserLocations() {
        return listOfUserLocations;
    }

    public String getIsRecorded() {
        return isRecorded;
    }
}
