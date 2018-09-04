package floowuk.floowrx.services.mvp;

import floowuk.floowrx.model.DataForBroadcast;
import floowuk.floowrx.model.DistanceResult;
import rx.Observable;
import floowuk.floowrx.model.LocationData;
import floowuk.floowrx.model.LocationRXData;
/*** Created by igorfrankiv on 28/01/2018.*/

public interface IServiceModel {

    Boolean finalWriteIODataRX( String isRecorded);

    Observable <LocationRXData> readIOdataRX (LocationData locationData );

    DistanceResult calculateDistanceBetween2pointsRX(LocationRXData locationRXData);

    DataForBroadcast writeIODataRX2(DistanceResult distanceResult );
}