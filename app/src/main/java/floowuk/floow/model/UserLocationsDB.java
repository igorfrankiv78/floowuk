package floowuk.floow.model;

import java.util.List;

/**
 * Created by igorfrankiv on 21/01/2018.
 */

public final class UserLocationsDB {
    private final List<String> listOfIds;
    private final List<String> listOflocations;
    private final List<String> listOfTimeStamps;

    public UserLocationsDB (List<String> listOfIds, List<String> listOflocations, List<String> listOfTimeStamps ){
        this.listOfIds = listOfIds;
        this.listOflocations = listOflocations;
        this.listOfTimeStamps = listOfTimeStamps;
    }

    public List<String> getListOfIds() {
        return listOfIds;
    }

    public List<String> getListOflocations() {
        return listOflocations;
    }

    public List<String> getListOfTimeStamps() {
        return listOfTimeStamps;
    }
}
