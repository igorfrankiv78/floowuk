package floowuk.floowrx.screens.detail;

import android.os.Build;
import android.os.Bundle;
import android.Manifest;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.graphics.Color;
import butterknife.ButterKnife;
import floowuk.floowrx.app.RxMvpApp;
import android.content.pm.PackageManager;
import floowuk.floowrx.model.MyObservable;
import floowuk.floowrx.model.UserLocation;
import floowuk.floowrx.model.UserLocations;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import floowuk.floowrx.screens.detail.dagger.DaggerIDetailedJourneyComponent;
import floowuk.floowrx.screens.detail.dagger.DetailedJourneyModule;
import floowuk.floowrx.screens.detail.mvp.DetailPresenter;
import floowuk.floowrx.screens.detail.mvp.IDetailView;
import floowuk.floowrx.screens.listofjourneys.ListOfJourneys;
import floowuk.floowrx.screens.listofjourneys.viewadapt.ListOfJourneysAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.jakewharton.rxbinding.view.RxView;
import floowuk.floowrx.utils.TimeUtil;
import flowigor.ie.floowuk.R;
import rx.Observable;
/*** Created by igorfrankiv on 22/01/2018.*/

public class DetailedJourney extends FragmentActivity implements OnMapReadyCallback, IDetailView {

    private List<UserLocation> mListOfUserLocationsIn = new ArrayList<>();
    private PolylineOptions currPolylineOptions;
    public Integer  mId = 2;
    public static final String STARTED = " STARTED AT ";
    public static final String FINISHED = " FINISHED AT ";
    private final static String ERROR = "Error!";

    @BindView(R.id.tvStartAndFinishTimes)
    public TextView tvStartAndFinishTimes;

    @BindView(R.id.tvDuration)
    public TextView tvDuration;

    @BindView(R.id.btnDeletePath)
    public Button btnDeleteUserJourney;

    private GoogleMap nMap;
    private Boolean isRemoved = false;
    MyObservable<Integer> olist = new MyObservable<>();

    @Inject
    DetailPresenter mDetailPresenter;

    private Observable<Integer> btnDeleteUserJourneyObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_journey);

        Bundle bundle = getIntent().getExtras();

        try {
            if (bundle.getString(ListOfJourneysAdapter.ID) != null)
                mId = Integer.valueOf(bundle.getString(ListOfJourneysAdapter.ID));
        } catch (java.lang.NullPointerException e){

        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        DaggerIDetailedJourneyComponent.builder()
                .iRxMvpAppComponent(RxMvpApp.get(this).component())
                .detailedJourneyModule(new DetailedJourneyModule(this))
                .build().inject(this);

        ButterKnife.bind(this);

        btnDeleteUserJourney.setClickable(true);

        btnDeleteUserJourneyObservable = RxView.clicks(btnDeleteUserJourney).skip(0). map( result ->{ isRemoved = true; return mId; }   );

        mDetailPresenter.onCreate();
    }

    @Override
    public Observable<Integer> btnDeleteUserJourneyObservable() {
        return btnDeleteUserJourneyObservable;
    }

    @Override
    public void onDestroy() {
        mDetailPresenter.unsubscribe();
        super.onDestroy();
    }

    @Override
    public Observable<Integer> readIOdataRX(){
            return olist.getObservable();
    }

    @Override
    public void onMapReady(GoogleMap map) {
      // Override the default content description on the view, for accessibility mode.
        map.setContentDescription(getString(R.string.map_description));
        nMap = map;

        nMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //Initialize Google Play Services
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                nMap.setMyLocationEnabled(true);
                nMap.moveCamera(CameraUpdateFactory.zoomIn());
                nMap.animateCamera(CameraUpdateFactory.zoomTo(19));
            }
        }
        else {
            nMap.setMyLocationEnabled(true);
        }
        olist.add( mId );
    }


    @Override
    public void getDetailedJourney(UserLocations userLocations) {
        //  A geodesic polyline that goes around the world.
        //  move map camera
        if(userLocations != null && userLocations.getListOfUserLocations().size() > 0 ) {
            mListOfUserLocationsIn = userLocations.getListOfUserLocations();

            UserLocation userLocation = userLocations.getListOfUserLocations().get(userLocations.getListOfUserLocations().size() - 1);
            String dateStart = userLocations.getListOfUserLocations().get(0).getTime();
            String dateStop = userLocation.getTime();
//--------------------------uncomment this------------------------------------------------------------
//            for(int i = 0; i < userLocations.getListOfUserLocations().size(); i++){
//    Log.e("----------" , "-----Start-----");
//    Log.e("Latitude = : " , String.valueOf( userLocations.getListOfUserLocations().get(i).getLatitude() ));
//    Log.e("Longitude = : " , String.valueOf( userLocations.getListOfUserLocations().get(i).getLongitude() ));
//    Log.e("----------" , "-----End-----");
//            }
//--------------------------uncomment this------------------------------------------------------------
//            LatLng latLngA = new LatLng(userLocations.getListOfUserLocations().get(0).getLatitude(),
//                     userLocations.getListOfUserLocations().get(0).getLongitude() );
//            LatLng latLngB = new LatLng(userLocations.getListOfUserLocations().get(
//                        userLocations.getListOfUserLocations().size() - 1).getLatitude(),
//                    userLocations.getListOfUserLocations().get
//                            (userLocations.getListOfUserLocations().size() - 1).getLongitude());
//
//            Location locationA = new Location("point A");
//            locationA.setLatitude(latLngA.latitude);
//            locationA.setLongitude(latLngA.longitude);
//            Location locationB = new Location("point B");
//            locationB.setLatitude(latLngB.latitude);
//            locationB.setLongitude(latLngB.longitude);
//---------------------------uncomment this-----------------------------------------------------------
//            double valueResult = locationA.distanceTo(locationB);
//            Log.e("dist-----" , String.valueOf( valueResult ));
//            // dateStart dateStop
//            String durationOfJourney = TimeUtil.durationOfJourney( dateStart,  dateStop);
//            Log.e("time-----" , String.valueOf( durationOfJourney ));
//--------------------------------------------------------------------------------------
//            String[] latlong =  "-34.8799074,174.7565664".split(",");
//            double latitude = Double.parseDouble(latlong[0]);
//            double longitude = Double.parseDouble(latlong[1]);
//            02-12 00:07:47.133 16738-16738/flowigor.ie.floowuk E/dist-----: 217.34776306152344
//            02-12 00:07:47.133 16738-16738/flowigor.ie.floowuk E/dist-----: 2m 10s
//--------------------------------------------------------------------------------------
            tvStartAndFinishTimes.setText(STARTED +dateStart+FINISHED+ dateStop);
            tvDuration.setText(TimeUtil.durationOfJourney ( dateStart,  dateStop));
            LatLng latLng1 = new LatLng(mListOfUserLocationsIn.get(0).getLatitude(), mListOfUserLocationsIn.get(0).getLongitude());

            if(nMap != null) {
                nMap.moveCamera(CameraUpdateFactory.newLatLng(latLng1));
                nMap.animateCamera(CameraUpdateFactory.zoomTo(19));

                if (currPolylineOptions == null)
                    currPolylineOptions = new PolylineOptions().
                            geodesic(true).
                            color(Color.BLUE).
                            width(5);  // LatLng target = mMap.getCameraPosition().target;

                     Marker newMarker;

                for (UserLocation s : mListOfUserLocationsIn) {
                    LatLng latLng = new LatLng(s.getLatitude(), s.getLongitude());
        //------------------------------------------------------------------------------
                       newMarker = nMap.addMarker(new MarkerOptions()
                                       .position(latLng)
                                       .snippet(latLng.toString()));

                              newMarker.setTitle( s.getTime() ); // newMarker.getId()
        //------------------------------------------------------------------------------
                    currPolylineOptions.add(latLng);
                }

                nMap.addPolyline(currPolylineOptions);
            }
        }
    }

    @Override
    public void showError() {
        Toast.makeText(this, ERROR, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccefulMessage(String reason) {
        Toast.makeText(this, reason, Toast.LENGTH_LONG).show();
        btnDeleteUserJourney.setVisibility( View.GONE );

        Intent intent = new Intent();
        intent.putExtra(ListOfJourneys.ID_REMOVED,  String.valueOf( mId ));
        intent.putExtra(ListOfJourneys.IS_REMOVED,  String.valueOf( isRemoved ));
        setResult(RESULT_OK, intent);
        finish();
    }

}

