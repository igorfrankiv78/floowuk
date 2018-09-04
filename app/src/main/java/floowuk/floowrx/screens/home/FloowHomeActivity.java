package floowuk.floowrx.screens.home;

/*** Created by igorfrankiv on 26/01/2018.*/
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.BroadcastReceiver;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.jakewharton.rxbinding.view.RxView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import floowuk.floowrx.app.RxMvpApp;
import floowuk.floowrx.model.JourneyDATA;
import floowuk.floowrx.model.LastKnownResults;
import floowuk.floowrx.model.UserLocation;
import floowuk.floowrx.model.UserLocations;
import floowuk.floowrx.screens.customui.CustomProgressbar;
//import floowuk.floowrx.screens.home.dagger.DaggerIHomeActivityComponent;
import floowuk.floowrx.screens.home.dagger.DaggerIHomeActivityComponent;
import floowuk.floowrx.screens.home.dagger.HomeModule;
import floowuk.floowrx.screens.home.mvp.HomePresenter;
import floowuk.floowrx.screens.home.mvp.IHomeView;
import floowuk.floowrx.screens.listofjourneys.ListOfJourneys;
import floowuk.floowrx.services.FloowServiceLocator;
import floowuk.floowrx.utils.JSONUtil;
import floowuk.floowrx.utils.SharedPreferencesUtil;
import floowuk.floowrx.utils.TimeUtil;
import flowigor.ie.floowuk.R;
import rx.Observable;

public class FloowHomeActivity extends FragmentActivity implements OnMapReadyCallback, IHomeView
{
    public static final String IS_RECORDED_POSITIVE = "YES";
    public static final String IS_RECORDED_NEGATIVE = "NO";
    public static final String ERROR_MESSAGE_SIZE = "No recordings!!! Please, record a journey!";
    public static final String ERROR_MESSAGE_WRITE = "Could not save your current journey!";
    public static final String SUCCESS_MESSAGE = "Your journey has been successfully safed!";

    public List<UserLocation> mListOfUserLocations;

    @BindView(R.id.buttonstartService)
     Button buttonstartService;

    @BindView(R.id.buttonstopService)
     Button buttonstopService;

    @BindView(R.id.btnShowJourneysRX)
     Button btnShowJourneys;

    @BindView(R.id.tvDistance)
     TextView tvDistance;

    @BindView(R.id.tvDuration)
     TextView tvDuration;

    private GoogleMap mMap;
    private Marker mCurrLocationMarker;
    private PolylineOptions currPolylineOptions;
    private Observable<Void> btnShowJourneysObservable;
    private Observable<Void> btnStartServiceObservable;
    private Observable<JourneyDATA> btnStopServiceObservable;

    private String currentTime = "";
    private Boolean isAlreadyPopulated = false;
    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeUtil.PATTERN);

    @Inject
    HomePresenter homePresenter;

    // ------ onCreate Block --------------------------------
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_service_rx);

        currentTime = simpleDateFormat.format(new Date());
        ButterKnife.bind(this);

        if( SharedPreferencesUtil.isStartButtonTurnOn( this ) ){
            CustomProgressbar.showProgressBar( this, false);
            buttonstopService.setVisibility(View.VISIBLE);
            buttonstartService.setVisibility(View.GONE);

            LastKnownResults lastKnownResults = SharedPreferencesUtil.getLastKnownResults( FloowHomeActivity.this );

            String distance =  lastKnownResults.getDistance();

            if(distance.length() > 5)
            distance = distance.substring(0,6);

            tvDistance.setText( distance );

            String time =  lastKnownResults.getTime();

            if(time.length() > 5)
                time = distance.substring(0,6);

            tvDuration.setText( time );
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                           mapFragment.getMapAsync(this);

        initUIClickElements();

        DaggerIHomeActivityComponent.builder()
                .iRxMvpAppComponent(RxMvpApp.get(this).component())
                .homeModule(new HomeModule(this))
                .build().inject(this);

        homePresenter.onCreate();
    }

    //------- Java RX ------------------------------------------------
    private void initUIClickElements() {
        btnStartServiceObservable = RxView.clicks(buttonstartService).skip(0);
        btnStopServiceObservable = RxView.clicks(buttonstopService).skip(0). map( result ->  stopServiceAndSaveToDB());
        btnShowJourneysObservable = RxView.clicks(btnShowJourneys).skip(0);
    }

    @Override
    public Observable<Void> btnStartServiceObservable() {
        return btnStartServiceObservable;
    }

    @Override
    public void  startService() {
        Log.e("startService", "startService");
        CustomProgressbar.showProgressBar( this, false);
        Intent intent = new Intent(getApplicationContext(), FloowServiceLocator.class);
        startService(intent);
    }

    @Override
    public Observable<Void> btnShowJourneysObservable() {
        return btnShowJourneysObservable;
    }

    @Override
    public Observable<JourneyDATA> btnStopServiceObservable() {
        return btnStopServiceObservable;
    }

    public JourneyDATA stopServiceAndSaveToDB(){
        Log.e("stopService", "stopService");
        System.out.println("stopService: size " +  mListOfUserLocations.size());
        stopService(new Intent(getBaseContext(), FloowServiceLocator.class));
        return new JourneyDATA( mListOfUserLocations,  IS_RECORDED_POSITIVE );
    }

    @Override
    public void letUserToSeeHisJourneys(@NonNull Integer sizeOfDB) {
        Log.e("sizeOfDB ", String.valueOf(sizeOfDB));
        System.out.println("sizeOfDB: " +  sizeOfDB);
        if( sizeOfDB > 0) {
            Intent myIntent = new Intent(FloowHomeActivity.this, ListOfJourneys.class);
            startActivity(myIntent);
        }
    }

    @Override
    public void showError( ) {
        Toast.makeText(this, ERROR_MESSAGE_SIZE, Toast.LENGTH_LONG).show();
        buttonstopService.setVisibility(View.GONE);
        buttonstartService.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCompleteResult(Boolean isSaved) {
        Log.e("onCompleteResult ", String.valueOf(isSaved));

        String message = ERROR_MESSAGE_WRITE;

        if(isSaved) {
             message = SUCCESS_MESSAGE;
            buttonstopService.setVisibility(View.GONE);
            buttonstartService.setVisibility(View.VISIBLE);
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
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

                    SharedPreferencesUtil.setLastKnownResults(FloowHomeActivity.this,  timeStr,  distanceStr );

                    UserLocations userLocations = JSONUtil.readJsonString(  listOfUserLocationsStr);
                        mListOfUserLocations = userLocations.getListOfUserLocations();

                    String IsRecorded = userLocations.getIsRecorded();

                    settingUIelement( IsRecorded,  timeStr,  distanceStr);

                    activateLocationMapping( timeStr );

                }
                else {
//                    Toast.makeText(FloowHomeActivity.this, ERROR_START_SERVICE,  Toast.LENGTH_LONG).show();
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

        try {

        if(mListOfUserLocations.size() > 0 ) {

            Date date1 = simpleDateFormat.parse(currentTime);
            Date date2 = simpleDateFormat.parse(mListOfUserLocations.get(0).getTime());

            if (date1.compareTo(date2) > 0 && isAlreadyPopulated == false)
            {// take the whole array  currentTime = "01/31/2018 12:19:05"; serviceTime = "01/31/2018 12:19:04"
                Log.e(" if = ",  "take the whole array");
                  isAlreadyPopulated = true;
                    LatLng latLng1 = new LatLng(mListOfUserLocations.get(0).getLatitude(), mListOfUserLocations.get(0).getLongitude());

                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng1));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(19));

                    if (currPolylineOptions == null)
                        currPolylineOptions = new PolylineOptions(). geodesic(true).color(Color.GREEN).width(5);

                    for (UserLocation s : mListOfUserLocations) {
                        LatLng latLng = new LatLng(s.getLatitude(), s.getLongitude());
                        currPolylineOptions.add(latLng);
                    }
                    mMap.addPolyline(currPolylineOptions);
//                }
            }
            else
//                if (date1.compareTo(date2) < 0)
            {// take one by one from array currentTime = "01/31/2018 12:19:05";  serviceTime = "01/31/2018 12:19:06";
                Log.e(" else if = ",  " take one by one from array ");
            UserLocation userLocation = mListOfUserLocations.get(mListOfUserLocations.size() - 1);
            // Place current location marker
            LatLng latLng = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(timeStr);
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker( BitmapDescriptorFactory.HUE_MAGENTA ));
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
        } catch (ParseException e){

        }
    }

    public void settingUIelement(String IsRecorded, String timeStr, String distanceStr){

        if(timeStr.length() > 5)
            timeStr = timeStr.substring(0,6);

        if(distanceStr.length() > 5)
            distanceStr = distanceStr.substring(0,6);

        tvDuration.setText( timeStr );
        tvDistance.setText( distanceStr );

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
        homePresenter.unsubscribe();
    }

    // ------ GoogleMap Block --------------------------------
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //Initialize Google Play Services
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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

}
