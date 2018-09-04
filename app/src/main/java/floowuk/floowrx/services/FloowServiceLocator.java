package floowuk.floowrx.services;
//    requestLocationUpdates(String provider, long minTime, float minDistance, LocationListener listener)
//    The minDistance parameter can also be used to control the frequency of location updates. If it is greater
//    than 0 then the location provider will only send your application an update when the location has changed
//    by at least minDistance meters, AND at least minTime milliseconds have passed
//    A kilometre has 1000 metres, and an hour has 3600 seconds, so a kilometre per hour is:
//    1000 / 3600 = 0.277
//    requestLocationUpdates(String provider, long minTime, float minDistance, LocationListener listener)
//    Kilometer per Hour     Meters per Second
//        3kph                   0.83/s         3000 meters / 3600 seconds = 0.083m/s
//        4kph                   1.11/s         4000 meters / 3600 seconds = 1.11m/s
//        4.5kph                 1.25/s         4500 meters / 3600 seconds = 1.25m/s
//        5kph                   1.38/s         5000 meters / 3600 seconds = 1.38m/s
//        10kph                  2.78/s         10000 meters / 3600 seconds = 2.78m/s
//        20kph                  5.56m/s        20000 meters / 3600 seconds = 5.555m/s
//        30kph                  8.33m/s        30000 meters / 3600 seconds = 8.33m/s
//        40kph 		        11.11m/s        40000 meters / 3600 seconds = 11.11m/s
//        50kph  		        13.89m/s        50000 meters / 3600 seconds = 13.888m/s
// Although walking speeds can vary greatly depending on many factors such as height, weight, age, terrain,
// surface, load, culture, effort, and fitness, the average human walking speed is about 5.0 kilometres
// per hour (km/h), or about 3.1 miles per hour (mph). Specific studies have found pedestrian walking speeds
// ranging from 4.51 kilometres per hour (2.80 mph) to 4.75 kilometres per hour (2.95 mph) for older individuals
// and from 5.32 kilometres per hour (3.31 mph) to 5.43 kilometres per hour (3.37 mph) for younger individuals;
// [2][3] a brisk walking speed can be around 6.5 kilometres per hour (4.0 mph).
// [4] Champion racewalkers can average more than 14 kilometres per hour (8.7 mph) over a distance of 20 kilometres
// (12 mi).
/*** Created by igorfrankiv on 26/01/2018.*/
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import floowuk.floowrx.app.RxMvpApp;
import floowuk.floowrx.model.DataForBroadcast;
import floowuk.floowrx.model.LocationData;
import floowuk.floowrx.services.dagger.DaggerIServiceComponent;
import floowuk.floowrx.services.dagger.ServiceModule;
import floowuk.floowrx.services.mvp.IServiceView;
import floowuk.floowrx.model.MyObservable;
import floowuk.floowrx.services.mvp.ServicePresenter;
import floowuk.floowrx.utils.TimeUtil;
import rx.Observable;

public class FloowServiceLocator extends Service implements IServiceView {

    private int result = Activity.RESULT_CANCELED;
    private static final String TAG = "FLOOW_LOCATION";
    public static final String LISTOFUSERLOCATIONS = "listOfUserLocations";
    public static final String RESULT = "result";
    public static final String TIME = "time";
    public static final String DISTANCE = "distance";
    public static final String NOTIFICATION = "FLOOW_USER_LOCATION";
    public static final String ERROR_MESSAGE = "Error!!!";
    public static final String SUCCESS_MESSAGE = "Success!!!";
    public static final String ON_DESTROY = "onDestroy";
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 2000;// MINIMUM TIME IN MILISECONS 1000 = 1second
    private static final float LOCATION_DISTANCE = 1.25f;// MINIMUM DISTANCE IN METERS

    MyObservable<LocationData> olist = new MyObservable<>();
    MyObservable<String> writeIOdata = new MyObservable<>();

    @Inject
    ServicePresenter mServicePresenter;
    //-----------------------------------------------------------
    // IServiceView
    @Override
    public  Observable<LocationData> getLocationDataObservable(){
        return olist.getObservable();
    }

    @Override
    public Observable<String> finalWriteIODataRX(){
        return writeIOdata.getObservable();
    }

    @Override
    public void onResultMessage(Boolean result){

        if(result)
            Toast.makeText(this, SUCCESS_MESSAGE, Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, ERROR_MESSAGE, Toast.LENGTH_LONG).show();
    }
    @Override
    public void showError() {
        Toast.makeText(this, ERROR_MESSAGE, Toast.LENGTH_LONG).show();
    }

    @Override
    public void readyDataForBroadcastRX( DataForBroadcast dataForBroadcast ){
        result = Activity.RESULT_OK;
        Log.e(TAG, "Locations.size(): " + dataForBroadcast.getFinalResultInMeters());
        publishResults( dataForBroadcast.getDurrationOfJourney(),
                dataForBroadcast.getFinalResultInMeters(),
                result, dataForBroadcast.getJsonListOfUserLocationsObj() );
    }
    //-----------------------------------------------------------

    private void publishResults(String time, String distance, int result, String jsonObj) {
         Intent intent = new Intent( NOTIFICATION );
                intent.putExtra( RESULT, result);
                intent.putExtra( LISTOFUSERLOCATIONS,  jsonObj);
                intent.putExtra( TIME, time );
                intent.putExtra( DISTANCE, distance );
        sendBroadcast(intent);
    }

    private class LocationListener implements android.location.LocationListener
 {
     Location mLastLocation;

     public LocationListener(String provider)
     {
         Log.e(TAG, "LocationListener " + provider);
         mLastLocation = new Location(provider);
     }

     @Override
     public void onLocationChanged(Location location)
     {
         //-----INPUT OF THE DATA STARTS HERE AND MVP PRESENTER PROCESS EVERYTHING IN A CHAIN
         final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeUtil.PATTERN);
         final String time = simpleDateFormat.format(new Date());

         final double latitude = location.getLatitude();
         final double longitude = location.getLongitude();

         olist.add( new LocationData ( latitude,  longitude,   time));

         Log.e(TAG, "onLocationChanged = : " + location);
         mLastLocation.set(location);
     }

     @Override
     public void onProviderDisabled(String provider)
     {
         Log.e(TAG, "onProviderDisabled: " + provider);
     }

     @Override
     public void onProviderEnabled(String provider)
     {
         Log.e(TAG, "onProviderEnabled: " + provider);
     }

     @Override
     public void onStatusChanged(String provider, int status, Bundle extras)
     {
         Log.e(TAG, "onStatusChanged: " + provider);
     }
 }

 // Initilize The Above Inner Class
    FloowServiceLocator.LocationListener[] mLocationListeners = new FloowServiceLocator.LocationListener[] {
            new FloowServiceLocator.LocationListener(LocationManager.GPS_PROVIDER)
         , new FloowServiceLocator.LocationListener(LocationManager.NETWORK_PROVIDER)
    };


    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }
    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        // FloowServiceLocatorWakeUp.completeWakefulIntent(intent);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        initializeLocationManager();
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[1]);
        } catch (SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
        } catch (SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }

        DaggerIServiceComponent.builder()
                .iRxMvpAppComponent( RxMvpApp.get(this).component() )
                .serviceModule( new ServiceModule(this) )
                .build().inject(this );

            mServicePresenter.onCreate();
    }

    @Override
    public void onDestroy()
    {
        publishResults("", "", Activity.RESULT_CANCELED, "");

        writeIOdata.add( ServicePresenter.STATE_OF_RECORDDING_NO );

        mServicePresenter.unsubscribe();
        // Clearing Listeners
        if (mLocationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                } catch (Exception ex) {
                    Log.i(TAG, "fail to remove location listners, ignore", ex);
                }
            }
        }
        super.onDestroy();
    }
}
