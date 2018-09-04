package floowuk.floowrx.services.mvp;

import floowuk.floowrx.model.DataForBroadcast;
import floowuk.floowrx.model.LocationData;
import rx.Observable;
/*** Created by igorfrankiv on 28/01/2018.*/

public interface IServiceView {
    void showError( );
    void readyDataForBroadcastRX( DataForBroadcast dataForBroadcast );
    Observable<LocationData> getLocationDataObservable();
    Observable<String> finalWriteIODataRX();
    void onResultMessage(Boolean result);
}
