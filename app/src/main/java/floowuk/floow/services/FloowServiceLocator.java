package floowuk.floow.services;
//    requestLocationUpdates(String provider, long minTime, float minDistance, LocationListener listener)
//    The minDistance parameter can also be used to control the frequency of location updates. If it is greater
//    than 0 then the location provider will only send your application an update when the location has changed
//    by at least minDistance meters, AND at least minTime milliseconds have passed
//    A kilometre has 1000 metres, and an hour has 3600 seconds, so a kilometre per hour is:
//    1000 / 3600 = 0.277
//Kilometer per Hour     Meters per Second
//     20kph                  5.56m/s
//     30kph                  8.33m/s
//     40kph 		          11.11m/s
//     50kph  		          13.89m/s
/*** Created by igorfrankiv on 26/01/2018.*/
    import java.text.SimpleDateFormat;
    import java.util.Date;
    import android.app.Activity;
    import android.app.Service;
    import android.content.Context;
    import android.content.Intent;
    import android.location.Location;
    import android.location.LocationManager;
    import android.os.Bundle;
    import android.os.IBinder;
    import android.util.Log;
    import floowuk.floow.services.mvp.IServiceView;
    import floowuk.floow.services.mvp.ServicePresenter;
    import floowuk.floow.utils.TimeUtil;

public class FloowServiceLocator extends Service implements IServiceView {

    private int result = Activity.RESULT_CANCELED;
    private static final String TAG = "FLOOW_LOCATION";
    public static final String LISTOFUSERLOCATIONS = "listOfUserLocations";
    public static final String RESULT = "result";
    public static final String TIME = "time";
    public static final String DISTANCE = "distance";
    public static final String NOTIFICATION = "FLOOW_USER_LOCATION";
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 1000;// MINIMUM TIME IN MILISECONS 1000 = 1second
    private static final float LOCATION_DISTANCE = 1f;// MINIMUM DISTANCE IN METERS

    private ServicePresenter mServicePresenter;

    private void publishResults(String time, String distance, int result, String jsonObj) {
         Intent intent = new Intent( NOTIFICATION );
                intent.putExtra( RESULT, result);
                intent.putExtra( LISTOFUSERLOCATIONS,  jsonObj);
                intent.putExtra( TIME, time );
                intent.putExtra( DISTANCE, distance );
        sendBroadcast(intent);
    }

    // IServiceView
    @Override
    public void showError(String reason) {

    }

    @Override
    public void readyDataForBroadcast(String jsonListOfUserLocationsObj, String sizeOfList, String durrationOfJourney) {
            result = Activity.RESULT_OK;
        Log.e(TAG, "Locations.size(): " + sizeOfList);
        publishResults(durrationOfJourney, sizeOfList, result, jsonListOfUserLocationsObj);
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

         mServicePresenter.setLocationData( latitude, longitude, time );

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

        mServicePresenter = new ServicePresenter( this, this );

    }

    @Override
    public void onDestroy()
    {   Log.e(TAG, "onDestroy");

        publishResults("", "", Activity.RESULT_CANCELED, "");
        mServicePresenter.writeIOdata(  );

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
