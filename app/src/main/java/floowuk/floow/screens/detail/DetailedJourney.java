package floowuk.floow.screens.detail;

    import android.Manifest;
    import android.content.pm.PackageManager;
    import android.graphics.Color;
    import android.os.Build;
    import android.os.Bundle;
    import android.support.v4.app.FragmentActivity;
    import android.support.v4.content.ContextCompat;
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
    import floowuk.floow.screens.listofjourneys.adapters.MapsRecViewAdapter;
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

        tvStartAndFinishTimes = (TextView) findViewById(R.id.tvStartAndFinishTimes);
        tvDuration = (TextView) findViewById(R.id.tvDuration);
        btnDeleteUserJourney = (Button) findViewById(R.id.btnDeletePath);
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
        map.setContentDescription(getString(R.string.polyline_demo_description));
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

            tvStartAndFinishTimes.setText( dateStart+"=>"+ dateStop);
            tvDuration.setText(TimeUtil.durationOfJourney ( dateStart,  dateStop));
            LatLng latLng1 = new LatLng(mListOfUserLocationsIn.get(0).getLatitude(), mListOfUserLocationsIn.get(0).getLongitude());
            nMap.moveCamera(CameraUpdateFactory.newLatLng(latLng1));
            nMap.animateCamera(CameraUpdateFactory.zoomTo(19));

            if (currPolylineOptions == null)
                currPolylineOptions = new PolylineOptions().width(5);

            currPolylineOptions.color(Color.BLUE);
            //        LatLng target = mMap.getCameraPosition().target;
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

