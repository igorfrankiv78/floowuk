package floowuk.floow.screens.home;

/*** Created by igorfrankiv on 26/01/2018.*/
import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.List;
import floowuk.floow.helpers.DBHelper;
import floowuk.floow.model.UserLocation;
import floowuk.floow.model.UserLocations;
import floowuk.floow.screens.customui.CustomProgressbar;
import floowuk.floow.screens.home.mvp.IHomeView;
import floowuk.floow.screens.listofjourneys.ListOfJourneys;
import floowuk.floow.services.FloowServiceLocator;
import floowuk.floow.screens.home.mvp.HomePresenter;
import floowuk.floow.utils.JSONUtil;
import floowuk.floow.utils.SharedPreferencesUtil;
import flowigor.ie.floowuk.R;

//         To whom it may concern
//         The project consists of three activities (screens). They are FloowHomeActivity,
//         ListOfJourneys, DetailedJourney. The Android Architecture chosen is MVP, which support all of the above screens.
//        The MVP also supports the service class with name “FloowServiceLocator”. I wanted to include the RXJava, Dagger2 with MVP
//        or ViewModel(I have never worked with it. I just started learning this one. It is a MVVM with something similar RXJava implementations ).
//        I decided to go a simple MVP.
//        I will begin with the FloowHomeActivity which is the starting point of the Application.
//        It has 5 Objects;
//        show journey Button,
//        how many meters was traveled tTextview,
//        the time elapsed since the journey was started Textview,
//        start and stop Button which starts the background service,
//        map fragment which shows the map.
//        So when a User presses the start button, it starts the Service “FloowServiceLocator” (this button will be set as pressed with share preferences
//        because if a user closes the App and reopens it. The button will be set as Start Recording Button, while it should show Stop Recording Button util it
//        gets the broadcast from the Service. So that was unacceptable.There were three options; share preference, to show progress dialog or reading the read
//        and write operation file of the Service class. I chose the first one. Actually the Receiver sets this as on and off  ).
//        The operation of Service class starts  with getting latitude, longitude, time and passing them to the Presenter which will process in the chain
//        of read (if a json file is existing or will populate a List (ArrayList) and write it for the first time to the local directory) , will populate a List,
//        convert it a to json format, write it to the local directory as a json format and then the Presenter passes the JSON object to the Service class, Service
//        class passes it the Activity to show the current location.
//        Now about why is read and write each time it gets latitude, longitude, time.
//        For a user can close the app, then the service is reloading and it will lose all of the data it was holding in the List (Array list) before or
//        the mobile phone will be restarted, then the Service will lose all the data
//        And will never be restarted until a user will start it again.
//        So there is the son file with data and object with a  key word string “YES” or “NO”. If the service  was in recording mode “YES”, the app was closed
//        or the phone restarted(the app has the wake up class which restarts the service with the operation “YES”), it will restart the same recording and will
//        continue to do so until a user presses “Stop Button”.
//
//        The read and write operation is sequenced with ReadWriteLock rwlock = new ReentrantReadWriteLock() for avoiding read and write operations (it can read
//        the data and user presses “Stops” write operation “NO”). First I wanted to go with Synchronised multithreading (It was working). But then I decided to go
//        with the first one.
//
//        The stop button in FloowHomeActivity  saves it (JSON file to the Sqlite DB). It was a simple solution to save a List to a DB
//        Table. There are the other solutions.
//
//        If there is at list one recording, it will enable the button which will lead the List view Activity of all journeys and then to Detail View Activity.
//        Each has MVP to the DB Sqlite to bring and populate the require fields. I implemented
//        the delete operation in the Detail View Activity. It is working. But I disable it. Because I needed to remove the item deleted from the recycle view.
//        I know how. But I did not have time to implement. The same with RXJava and Dagger2. It takes time to implement this all sort of things. Also I know a lot of
//        android google map api, LocationListener and LocationManager.
//        There are android.location.LocationListener, and the com.google.android.gms.location.LocationListener is using Google Play Services APIs
//        I am using in this project
//        import android.location.Location;
//        import android.location.LocationManager;
//        LocationListener and LocationManager.
//
//        I know this ones bellow are better ones. I have them on other project and will implement them too
//
//          import com.google.android.gms.location.LocationRequest;
//          import com.google.android.gms.location.LocationListener;
//          import com.google.android.gms.location.LocationRequest;
//          import com.google.android.gms.location.LocationServices;
//
//       However there are bugs and deprecated methods that I have to be aware of.
//        So I decided to start with simple one (the first one ) and then to go with the second one.
//
//
// I think your company is doing something
//        requestLocationUpdates(String provider, long minTime, float minDistance, LocationListener listener)
//        Kilometer per Hour     Meters per Second
//            20kph                  5.56m/s
//            30kph                  8.33m/s
//            40kph 		         11.11m/s
//            50kph  		         13.89m/s
//        But I have to test how this is working in the real life. You may take the speed with requestLocationUpdates.
//        But then check with two different locations and time stamps. Also to play with the canvas or
//        However, I am nowhere near your level of expertise in them. If you take me, I will get your knowledge and mastery in those fields.
//        I was acting in the best of my knowledge in this project. I have never done it before.
//        Whatever the outcome of this test, please give me the feed back what I did wrong and how to improve myself.
//        At least the App is working and there is the fundamental skeleton.
//        For I want to improve this project and my skills in this field of Android.
//        Than you very much!



public class FloowHomeActivity extends FragmentActivity implements OnMapReadyCallback, IHomeView
{
    public static final String ERROR_START_SERVICE = "Could not the service!";
    public static final String IS_RECORDED_POSITIVE = "YES";
    public static final String IS_RECORDED_NEGATIVE = "NO";
    private List<UserLocation> mListOfUserLocations;
    private Button buttonstartService, buttonstopService, btnShowJourneys;
    private TextView tvDistance, tvDuration;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private GoogleMap mMap;
    private Marker mCurrLocationMarker;
    private PolylineOptions currPolylineOptions;
    private GoogleApiClient mGoogleApiClient;
    private HomePresenter homePresenter;
    private DBHelper mDBHelper;

// ------ onCreate Block --------------------------------
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_service);

        btnShowJourneys = findViewById(R.id.btnShowJourneys);
        buttonstartService = findViewById(R.id.buttonstartService);
        buttonstopService = findViewById(R.id.buttonstopService);
        tvDistance = findViewById(R.id.tvDistance );
        tvDuration = findViewById(R.id.tvDuration );


        if( SharedPreferencesUtil.isStartButtonTurnOn( this ) ){
            buttonstopService.setVisibility(View.VISIBLE);
            buttonstartService.setVisibility(View.GONE);
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                           mapFragment.getMapAsync(this);

        mDBHelper = new DBHelper(this);
        homePresenter = new HomePresenter( mDBHelper , this );
    }

// ------ BroadcastReceiver Block --------------------------------
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Bundle bundle = intent.getExtras();

            CustomProgressbar.hideProgressBar();

            if (bundle != null) {

                int resultCode = bundle.getInt(FloowServiceLocator.RESULT);
                if (resultCode == RESULT_OK) {

                    SharedPreferencesUtil.setStartButtonOn( FloowHomeActivity.this );

                    String listOfUserLocationsStr = bundle.getString( FloowServiceLocator.LISTOFUSERLOCATIONS );
                    String timeStr = bundle.getString( FloowServiceLocator.TIME );
                    String distanceStr = bundle.getString( FloowServiceLocator.DISTANCE );

                    UserLocations userLocations = JSONUtil.readJsonString(  listOfUserLocationsStr);
                        mListOfUserLocations = userLocations.getListOfUserLocations();

                    String IsRecorded = userLocations.getIsRecorded();

                    settingUIelement( IsRecorded,  timeStr,  distanceStr);

                    activateLocationMapping( timeStr );

                }
                else {
                    Toast.makeText(FloowHomeActivity.this, ERROR_START_SERVICE,  Toast.LENGTH_LONG).show();
                    SharedPreferencesUtil.setStartButtonOff( FloowHomeActivity.this );
                    buttonstopService.setVisibility(View.GONE);
                    buttonstartService.setVisibility(View.VISIBLE);
                    mMap.clear();
                }

            }
        }
    };

    public void activateLocationMapping(String timeStr){

        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        if(mListOfUserLocations.size() > 0 ) {
            UserLocation userLocation = mListOfUserLocations.get(mListOfUserLocations.size() - 1);

            // Place current location marker
            LatLng latLng = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title(timeStr);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            mCurrLocationMarker = mMap.addMarker(markerOptions);

            //move map camera
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(19));

            buttonstopService.setClickable(true);
            // Drawing a new line to the current location
            if (currPolylineOptions == null)
                currPolylineOptions = new PolylineOptions().width(5);

            currPolylineOptions.color(Color.BLUE);

            currPolylineOptions.add(latLng);
            mMap.addPolyline(currPolylineOptions);
        }
    }

    public void settingUIelement(String IsRecorded, String timeStr, String distanceStr){

        tvDuration.setText(timeStr);
        tvDistance.setText(distanceStr);

        if(IsRecorded.contains(IS_RECORDED_POSITIVE)) {
            buttonstopService.setVisibility(View.VISIBLE);
            buttonstartService.setVisibility(View.GONE);
        }
        if(IsRecorded.contains(IS_RECORDED_NEGATIVE)) {
            buttonstopService.setVisibility(View.GONE);
            buttonstartService.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter( FloowServiceLocator.NOTIFICATION));
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // ------ GoogleMap Block --------------------------------
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
                mMap.moveCamera(CameraUpdateFactory.zoomIn());
                mMap.animateCamera(CameraUpdateFactory.zoomTo(19));
            }
        }
        else {
            mMap.setMyLocationEnabled(true);
        }
    }

    // ------ Start and Stop Services Block --------------------------------
    public void startService(View view) {
        CustomProgressbar.showProgressBar( this, false);
            Intent intent = new Intent(getApplicationContext(), FloowServiceLocator.class);
            startService(intent);
    }

    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), FloowServiceLocator.class));
        homePresenter.writeJoutneyInDB( mListOfUserLocations,  IS_RECORDED_POSITIVE);
    }

    public void showJourneys(View view) {
         homePresenter.getSizeOfDB();
    }

    @Override
    public void letUserToSeeHisJourneys(@NonNull Integer sizeOfDB) {
        if( sizeOfDB > 0) {
            Intent myIntent = new Intent(FloowHomeActivity.this, ListOfJourneys.class);
            startActivity(myIntent);
        }
    }

    @Override
    public void showError(String reason) {
        Toast.makeText(this, reason, Toast.LENGTH_LONG).show();
        buttonstopService.setVisibility(View.GONE);
        buttonstartService.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSuccess(String reason) {
        Toast.makeText(this, reason, Toast.LENGTH_LONG).show();
        buttonstopService.setVisibility(View.GONE);
        buttonstartService.setVisibility(View.VISIBLE);
    }
}
