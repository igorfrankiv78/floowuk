package flowigor.ie.floowuk.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import floowuk.floowrx.model.DeleteItemLocationsDB;
import floowuk.floowrx.model.UserLocationsDB;
import floowuk.floowrx.utils.FloowUtil;

import static org.junit.Assert.assertEquals;

/*** Created by igorfrankiv on 09/04/2018. */

public class TestFloowUtil {

    private List<UserLocationsDB> userLocationsDB;
    private Integer idRemoved = 4;

    @Test
    public void testFloowUtil(){
        userLocationsDB = new ArrayList<>();
        userLocationsDB.add( new UserLocationsDB ("1",  "{\"isRecorded\":\"YES\",\"listofuserlocations\":[{\"latitudes\":53.3518636,\"longitude\":-6.2766698,\"time\":" +
                "\"03\\/18\\/2018 22:42:04\",\"distance\":0},{\"latitudes\":53.3517766," +
                "\"longitude\":-6.2767846,\"time\":\"03\\/18\\/2018 22:42:51\",\"distance\":0},{\"latitudes\":53.3517371," +
                "\"longitude\":-6.2767983,\"time\":\"03\\/18\\/2018 22:43:03\",\"distance\":4.489731311798096}]}", "03/21/2018 22:50:43" ) );
        userLocationsDB.add( new UserLocationsDB ("2",  "{\"isRecorded\":\"YES\",\"listofuserlocations\":[{\"latitudes\":53.3518636,\"longitude\":-6.2766698,\"time\":" +
                "\"03\\/18\\/2018 22:42:04\",\"distance\":0},{\"latitudes\":53.3517766," +
                "\"longitude\":-6.2767846,\"time\":\"03\\/18\\/2018 22:42:51\",\"distance\":0},{\"latitudes\":53.3517371," +
                "\"longitude\":-6.2767983,\"time\":\"03\\/18\\/2018 22:43:03\",\"distance\":4.489731311798096}]}", "03/21/2018 22:50:48" ) );
        userLocationsDB.add( new UserLocationsDB ("3",  "{\"isRecorded\":\"YES\",\"listofuserlocations\":[{\"latitudes\":53.3518636,\"longitude\":-6.2766698,\"time\":" +
                "\"03\\/18\\/2018 22:42:04\",\"distance\":0},{\"latitudes\":53.3517766," +
                "\"longitude\":-6.2767846,\"time\":\"03\\/18\\/2018 22:42:51\",\"distance\":0},{\"latitudes\":53.3517371," +
                "\"longitude\":-6.2767983,\"time\":\"03\\/18\\/2018 22:43:03\",\"distance\":4.489731311798096}]}", "03/21/2018 22:50:58" ) );
        userLocationsDB.add( new UserLocationsDB ("4",  "{\"isRecorded\":\"YES\",\"listofuserlocations\":[{\"latitudes\":53.3518636,\"longitude\":-6.2766698,\"time\":" +
                "\"03\\/18\\/2018 22:42:04\",\"distance\":0},{\"latitudes\":53.3517766," +
                "\"longitude\":-6.2767846,\"time\":\"03\\/18\\/2018 22:42:51\",\"distance\":0},{\"latitudes\":53.3517371," +
                "\"longitude\":-6.2767983,\"time\":\"03\\/18\\/2018 22:43:03\",\"distance\":4.489731311798096}]}", "03/21/2018 22:51:24" ) );

        DeleteItemLocationsDB mDeleteItemLocationsDB = new DeleteItemLocationsDB( userLocationsDB, idRemoved );
        assertEquals( 3, FloowUtil.getPositionToBeRemoveD( mDeleteItemLocationsDB ) );
    }
}
