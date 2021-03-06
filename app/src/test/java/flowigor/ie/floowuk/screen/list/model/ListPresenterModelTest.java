package flowigor.ie.floowuk.screen.list.model;

import java.util.List;
import java.util.ArrayList;
import floowuk.floowrx.model.UserLocationsDB;
import floowuk.floowrx.model.DeleteItemLocationsDB;
/*** Created by igorfrankiv on 18/03/2018. ***/

public class ListPresenterModelTest {

    private final List<UserLocationsDB> userLocationsDB;
    private final UserLocationsDB userLocation;
    private final Integer idRemoved;
    private final Integer position;
    private final DeleteItemLocationsDB deleteItemLocationsDB;

    public ListPresenterModelTest() {
        userLocationsDB = new ArrayList<>();

        userLocationsDB.add( new UserLocationsDB ("1",
                "{\"isRecorded\":\"YES\",\"listofuserlocations\":[{\"latitudes\":53.3518636,\"longitude\":-6.2766698,\"time\":\"03\\/18\\/2018 22:42:04\",\"distance\":0},{\"latitudes\":53.3517766,\"longitude\":-6.2767846,\"time\":\"03\\/18\\/2018 22:42:51\",\"distance\":0},{\"latitudes\":53.3517371,\"longitude\":-6.2767983,\"time\":\"03\\/18\\/2018 22:43:03\",\"distance\":4.489731311798096}]}",
                "18 Mar 2018 10:43:05 PM" ) );
        userLocationsDB.add( new UserLocationsDB ("2", "{\"isRecorded\":\"YES\",\"listofuserlocations\":[{\"latitudes\":53.3517643,\"longitude\":-6.2767974,\"time\":\"03\\/18\\/2018 22:44:17\",\"distance\":0},{\"latitudes\":53.3518313,\"longitude\":-6.2767219,\"time\":\"03\\/18\\/2018 22:44:23\",\"distance\":0},{\"latitudes\":53.3518636,\"longitude\":-6.2766698,\"time\":\"03\\/18\\/2018 22:44:28\",\"distance\":4.995865821838379},{\"latitudes\":53.351878,\"longitude\":-6.2766789,\"time\":\"03\\/18\\/2018 22:44:33\",\"distance\":6.709220051765442}]}",
                "18 Mar 2018 10:47:06 PM" ) );
        userLocationsDB.add( new UserLocationsDB ("3", "{\"isRecorded\":\"YES\",\"listofuserlocations\":[{\"latitudes\":53.3518131,\"longitude\":-6.276708,\"time\":\"03\\/21\\/2018 22:50:43\",\"distance\":0},{\"latitudes\":53.351808,\"longitude\":-6.2767363,\"time\":\"03\\/21\\/2018 22:50:48\",\"distance\":0},{\"latitudes\":53.3517953,\"longitude\":-6.2767135,\"time\":\"03\\/21\\/2018 22:50:58\",\"distance\":2.074333667755127},{\"latitudes\":53.3517943,\"longitude\":-6.2767325,\"time\":\"03\\/21\\/2018 22:51:24\",\"distance\":3.3444337844848633}]}",
                "21 Mar 2018 10:51:30 PM" ) );
        userLocation = new UserLocationsDB ("1", "{\"isRecorded\":\"YES\",\"listofuserlocations\":[{\"latitudes\":53.3518636,\"longitude\":-6.2766698,\"time\":\"03\\/18\\/2018 22:42:04\",\"distance\":0},{\"latitudes\":53.3517766,\"longitude\":-6.2767846,\"time\":\"03\\/18\\/2018 22:42:51\",\"distance\":0},{\"latitudes\":53.3517371,\"longitude\":-6.2767983,\"time\":\"03\\/18\\/2018 22:43:03\",\"distance\":4.489731311798096}]}",
                "18 Mar 2018 10:43:05 PM" );

        idRemoved = 1;
        position = 1;

        deleteItemLocationsDB = new DeleteItemLocationsDB( userLocationsDB, idRemoved );
    }

    public List<UserLocationsDB> getUserLocationsDB() {
        return userLocationsDB;
    }

    public UserLocationsDB getUserLocation() {
        return userLocation;
    }

    public Integer getIdRemoved() {
        return idRemoved;
    }

    public Integer getPosition() {
        return position;
    }

    public DeleteItemLocationsDB getDeleteItemLocationsDB() {
        return deleteItemLocationsDB;
    }
}
