package floowuk.floow.screens.detail;

    import android.Manifest;
    import android.content.pm.PackageManager;
    import android.graphics.Color;
    import android.location.Location;
    import android.os.Build;
    import android.os.Bundle;
    import android.support.v4.app.FragmentActivity;
    import android.support.v4.content.ContextCompat;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;
    import android.widget.Toast;
    import com.google.android.gms.maps.CameraUpdateFactory;
    import com.google.android.gms.maps.GoogleMap;
    import com.google.android.gms.maps.OnMapReadyCallback;
    import com.google.android.gms.maps.SupportMapFragment;
    import com.google.android.gms.maps.model.LatLng;
    import com.google.android.gms.maps.model.PolylineOptions;
    import floowuk.floow.helpers.DBHelper;
    import floowuk.floow.model.UserLocation;
    import floowuk.floow.model.UserLocations;
    import floowuk.floow.screens.detail.mvp.DetailPresenter;
    import floowuk.floow.screens.detail.mvp.IDetailView;
    import floowuk.floow.screens.listofjourneys.viewadapt.MapsRecViewAdapter;
    import floowuk.floow.utils.TimeUtil;
    import flowigor.ie.floowuk.R;
    import java.util.ArrayList;
    import java.util.List;
/*** Created by igorfrankiv on 22/01/2018.*/

public class DetailedJourney extends FragmentActivity implements OnMapReadyCallback, IDetailView {

    private DetailPresenter mDetailPresenter;
    private List<UserLocation> mListOfUserLocationsIn=new ArrayList<>();
    private TextView tvStartAndFinishTimes, tvDuration;
    private Button btnDeleteUserJourney;
    private GoogleMap nMap;
    private DBHelper mDBHelper;
    private PolylineOptions currPolylineOptions;
    private Integer  mId;
    public static final String STARTED = " STARTED AT ";
    public static final String FINISHED = " FINISHED AT ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_journey);

        Bundle bundle = getIntent().getExtras();
                  mId = Integer.valueOf( bundle.getString(MapsRecViewAdapter.ID) );

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mDBHelper = new DBHelper(this);
        mDetailPresenter = new DetailPresenter( mDBHelper, this );

        tvStartAndFinishTimes = findViewById(R.id.tvStartAndFinishTimes);
        tvDuration = findViewById(R.id.tvDuration);
        btnDeleteUserJourney = findViewById(R.id.btnDeletePath);
        btnDeleteUserJourney.setClickable(false);
        btnDeleteUserJourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDetailPresenter.deleteRecordDB( mId );
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap map) {
      // Override the default content description on the view, for accessibility mode.
        map.setContentDescription(getString(R.string.map_description));
        nMap = map;

        nMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                nMap.setMyLocationEnabled(true);
                nMap.moveCamera(CameraUpdateFactory.zoomIn());
                nMap.animateCamera(CameraUpdateFactory.zoomTo(19));
            }
        }
        else {
            nMap.setMyLocationEnabled(true);
        }
        mDetailPresenter.getDetailedRecord( mId );
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
//--------------------------------------------------------------------------------------
            for(int i = 0; i < userLocations.getListOfUserLocations().size(); i++){
    Log.e("----------" , "-----Start-----");
    Log.e("Latitude = : " , String.valueOf( userLocations.getListOfUserLocations().get(i).getLatitude() ));
    Log.e("Longitude = : " , String.valueOf( userLocations.getListOfUserLocations().get(i).getLongitude() ));
    Log.e("----------" , "-----End-----");
            }

            LatLng latLngA = new LatLng(userLocations.getListOfUserLocations().get(0).getLatitude(),
                     userLocations.getListOfUserLocations().get(0).getLongitude() );
            LatLng latLngB = new LatLng(userLocations.getListOfUserLocations().get(
                        userLocations.getListOfUserLocations().size() - 1).getLatitude(),
                    userLocations.getListOfUserLocations().get
                            (userLocations.getListOfUserLocations().size() - 1).getLongitude());

            Location locationA = new Location("point A");
            locationA.setLatitude(latLngA.latitude);
            locationA.setLongitude(latLngA.longitude);
            Location locationB = new Location("point B");
            locationB.setLatitude(latLngB.latitude);
            locationB.setLongitude(latLngB.longitude);

            double valueResult = locationA.distanceTo(locationB);

            Log.e("dist-----" , String.valueOf( valueResult ));
            // dateStart dateStop
            String durationOfJourney = TimeUtil.durationOfJourney( dateStart,  dateStop);
            Log.e("time-----" , String.valueOf( durationOfJourney ));

//            String[] latlong =  "-34.8799074,174.7565664".split(",");
//            double latitude = Double.parseDouble(latlong[0]);
//            double longitude = Double.parseDouble(latlong[1]);
//            02-12 00:07:47.133 16738-16738/flowigor.ie.floowuk E/dist-----: 217.34776306152344
//            02-12 00:07:47.133 16738-16738/flowigor.ie.floowuk E/dist-----: 2m 10s
//--------------------------------------------------------------------------------------
            tvStartAndFinishTimes.setText(STARTED +dateStart+FINISHED+ dateStop);
            tvDuration.setText(TimeUtil.durationOfJourney ( dateStart,  dateStop));
            LatLng latLng1 = new LatLng(mListOfUserLocationsIn.get(0).getLatitude(), mListOfUserLocationsIn.get(0).getLongitude());

            nMap.moveCamera(CameraUpdateFactory.newLatLng(latLng1));
            nMap.animateCamera(CameraUpdateFactory.zoomTo(19));

            if (currPolylineOptions == null)
                currPolylineOptions = new PolylineOptions().
                        geodesic(true).
                        color(Color.BLUE).
                        width(5);  //        LatLng target = mMap.getCameraPosition().target;

            for (UserLocation s : mListOfUserLocationsIn) {
                LatLng latLng = new LatLng(s.getLatitude(), s.getLongitude());
                currPolylineOptions.add(latLng);
            }

            nMap.addPolyline(currPolylineOptions);
        }
    }

    @Override
    public void showError(String reason) {
        Toast.makeText(this, reason, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccefulMessage(String reason) {
        Toast.makeText(this, reason, Toast.LENGTH_LONG).show();
    }

}

