package floowuk.floowrx.model;

import java.util.List;

/*** Created by igorfrankiv on 13/03/2018.*/

public final class DeleteItemLocationsDB {

    private final List<UserLocationsDB> userLocationsDB;
    private final Integer idRemoved;

    public DeleteItemLocationsDB(List<UserLocationsDB> userLocationsDB, Integer idRemoved ){
        this.userLocationsDB = userLocationsDB;
        this.idRemoved = idRemoved;
    }

    public List<UserLocationsDB> getUserLocationsDB() {
        return userLocationsDB;
    }

    public Integer getIdRemoved() {
        return idRemoved;
    }
}
