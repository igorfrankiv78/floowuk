package floowuk.floowrx.model;

/*** Created by igorfrankiv on 21/01/2018.*/

public final class UserLocationsDB {
    private final String id;
    private final String locations;
    private final String timeStamp;

    public UserLocationsDB (String id, String locations, String timeStamp ){
        this.id = id;
        this.locations = locations;
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public String getLocations() {
        return locations;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

}
