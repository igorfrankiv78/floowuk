package floowuk.floow.screens.home;

/*** Created by igorfrankiv on 26/01/2018.*/
import android.Manifest;
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
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.List;
import floowuk.floow.helpers.DBHelper;
import floowuk.floow.model.UserLocation;
import floowuk.floow.model.UserLocations;
import floowuk.floow.screens.home.mvp.IHomeView;
import floowuk.floow.screens.listofjourneys.ListOfJourneys;
import floowuk.floow.services.FloowServiceLocator;
import floowuk.floow.screens.home.mvp.HomePresenter;
import floowuk.floow.utils.JSONUtil;
import flowigor.ie.floowuk.R;

public class FloowHomeActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
                                                            GoogleApiClient.OnConnectionFailedListener, IHomeView
{
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

        btnShowJourneys = (Button) findViewById(R.id.btnShowJourneys);
        buttonstartService = (Button) findViewById(R.id.buttonstartService);
        buttonstopService = (Button) findViewById(R.id.buttonstopService);
        tvDistance = (TextView) findViewById(R.id.tvDistance );
        tvDuration = (TextView) findViewById(R.id.tvDuration );

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
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

            Log.e(" onReceive resultCode", String.valueOf( RESULT_OK ) );
            if (bundle != null) {

                int resultCode = bundle.getInt(FloowServiceLocator.RESULT);
                if (resultCode == RESULT_OK) {

                    String listOfUserLocationsStr = bundle.getString( FloowServiceLocator.LISTOFUSERLOCATIONS );
                    String timeStr = bundle.getString( FloowServiceLocator.TIME );
                    String distanceStr = bundle.getString( FloowServiceLocator.DISTANCE );

                    tvDuration.setText(timeStr);
                    tvDistance.setText(distanceStr);

                    UserLocations userLocations = JSONUtil.readJsonString(  listOfUserLocationsStr);
                        mListOfUserLocations = userLocations.getListOfUserLocations();

                    String IsRecorded = userLocations.getIsRecorded();

                    if(IsRecorded.contains("YES")) {
                        buttonstopService.setVisibility(View.VISIBLE);
                        buttonstartService.setVisibility(View.GONE);
                    }
                    if(IsRecorded.contains("NO")) {
                        buttonstopService.setVisibility(View.GONE);
                        buttonstartService.setVisibility(View.VISIBLE);
                    }
                 // ---------------------------------------------------

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
                //  ----------------------------------------------------
                }
            }
        }
    };

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
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
                mMap.moveCamera(CameraUpdateFactory.zoomIn());
                mMap.animateCamera(CameraUpdateFactory.zoomTo(19));
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //----  Here we can request the Location requests
        //---   We do this in the Service class
    }

    @Override
    public void onConnectionSuspended(int i) {
        //---- We don't use it yet
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //---- We don't use it yet
    }

    // ------ Location Request Permissions from Android Manifest Block --------------------------------
    // ------ This method triggers onRequestPermissionsResults method bellow (call back)
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
                Log.e("+onMapReady ", "+ if checkLocationPermission ");
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
                Log.e("+onMapReady ", "+ else checkLocationPermission ");
            }
            return false;
        } else {
            return true;
        }
    }

    // ----- response to the above (The async call back)
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,  Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                            Log.e("+onMapReady ", "+ if onRequestPermissionsResult ");
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {
                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }

// -------- Location mannager
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    // ------ Start and Stop Services Block --------------------------------
    public void startService(View view) {
        Intent intent = new Intent(getApplicationContext(), FloowServiceLocator.class);
        startService(intent);
    }

    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), FloowServiceLocator.class));
        homePresenter.writeJoutneyInDB( mListOfUserLocations,  "YES");
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
