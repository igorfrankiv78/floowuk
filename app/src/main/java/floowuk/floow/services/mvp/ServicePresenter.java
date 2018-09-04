package floowuk.floow.services.mvp;

import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
import floowuk.floow.model.UserLocation;
import floowuk.floow.utils.CalculationByDistance;
import floowuk.floow.utils.TimeUtil;
/*** Created by igorfrankiv on 28/01/2018.*/

public class ServicePresenter implements IServicePresenter, IServiceOnCompleteModel {

    private IServiceView mIServiceView;
    private ServiceModel mServiceModell;
    private Double mLatitude;
    private Double mLongitude;
    private String mTime;
    private List<UserLocation> mListOfUserLocations;
    private String mFinalResultInMeters = "0";
    private double mValueResult = 0;

    public static final String STATE_OF_RECORDDING_YES = "YES";
    public static final String STATE_OF_RECORDDING_NO = "NO";

    public ServicePresenter ( IServiceView iServiceView, Context context)
    {
        this.mIServiceView = iServiceView;
        this.mServiceModell = new ServiceModel( context );
        mListOfUserLocations = new ArrayList<>();
    }

    // Presenter First Method to be triggered
    @Override
    public void setLocationData(@NonNull Double latitude, @NonNull Double longitude, @NonNull String time )
    {
        mLatitude = latitude;
        mLongitude = longitude;
        mTime = time;
        this.mServiceModell.readIOdata(this);
    }
    // Presenter Second Method to be triggered
    @Override
    public void readIODataReceived( List<UserLocation> listOfUserLocations ){

        if( listOfUserLocations != null) {
            mListOfUserLocations = listOfUserLocations;
            Log.e("if != null = : ", "if != null = : ");
        } else
            Log.e("else != null = : ", "else != null = : ");

// ----------------------------------------------------
      if(listOfUserLocations != null && listOfUserLocations.size() > 1 ) {
          double startPlatitude = listOfUserLocations.get(listOfUserLocations.size() - 1).getLatitude();
          Log.e("startPlatitude = : " , String.valueOf( startPlatitude ));
          double startPlongitude = listOfUserLocations.get(listOfUserLocations.size() - 1).getLongitude();
          Log.e("startPlongitude = : " , String.valueOf( startPlongitude ));
          double endPlatitude = mLatitude;
          Log.e("endPlatitude = : " , String.valueOf( endPlatitude ));
          double endPlongitude = mLongitude;
          Log.e("endPlongitude = : " , String.valueOf( endPlongitude ));
//          double valueResult = CalculationByDistance.calculationByDistance(startPlatitude, startPlongitude,
//                                                                           endPlatitude, endPlongitude);
//           Log.e("valueResult1 = : " , String.valueOf( valueResult ));
//          mValueResult = CalculationByDistance.calculateDistanceInMeters ( valueResult );
//          Log.e("mValueResult2 = : " , String.valueOf( mValueResult ));
//          mValueResult = listOfUserLocations.get(listOfUserLocations.size() - 1).getDistance() + mValueResult;
//          Log.e("mValueResult3 = : " , String.valueOf( mValueResult ));
//          mFinalResultInMeters = String.valueOf( mValueResult );

//          LatLng location = new LatLng(latitude, longitude);
          LatLng latLngA = new LatLng(startPlatitude,startPlongitude);
          LatLng latLngB = new LatLng(endPlatitude,endPlongitude);

          Location locationA = new Location("point A");
          locationA.setLatitude(latLngA.latitude);
          locationA.setLongitude(latLngA.longitude);
          Location locationB = new Location("point B");
          locationB.setLatitude(latLngB.latitude);
          locationB.setLongitude(latLngB.longitude);

          double valueResult = locationA.distanceTo(locationB);
           mValueResult = valueResult;
//            Log.e("valueResult1 = : " , String.valueOf( valueResult ));
//           mValueResult = CalculationByDistance.calculateDistanceInMeters ( valueResult );
//           Log.e("mValueResult2 = : " , String.valueOf( mValueResult ));
           mValueResult = listOfUserLocations.get(listOfUserLocations.size() - 1).getDistance() + mValueResult;
//           Log.e("mValueResult3 = : " , String.valueOf( mValueResult ));

          mFinalResultInMeters = String.valueOf( mValueResult ); 
           String[] strTemp =  mFinalResultInMeters.split(".") ;
            mFinalResultInMeters =  strTemp[0];

//          mFinalResultInMeters = String.valueOf( distance );
//            String[] latlong =  "-34.8799074,174.7565664".split(",");
//            double latitude = Double.parseDouble(latlong[0]);
//            double longitude = Double.parseDouble(latlong[1]);


      }
// ----------------------------------------------------
        mListOfUserLocations.add( new UserLocation(  mLatitude, mLongitude, mTime, mValueResult) );
        this.mServiceModell.writeIOData(mListOfUserLocations, this, STATE_OF_RECORDDING_YES );
    }
    // Presenter Third Method to be triggered
    @Override
    public void readyDataForBroadcast ( String  jsonListOfUserLocationsObj ){

     String durrationOfJourney = "";
        if(mListOfUserLocations.size() >1)
            durrationOfJourney = TimeUtil.durationOfJourney ( mListOfUserLocations.get(0).getTime(),  mTime);

        this.mIServiceView.readyDataForBroadcast( jsonListOfUserLocationsObj, String.valueOf( mFinalResultInMeters ),  durrationOfJourney);
    }
    // Presenter Fourth Method to be triggerd ( last  when a user triggers stop button)
    @Override
    public void writeIOdata(  ){
        if(mListOfUserLocations != null && mListOfUserLocations.size() > 0 ) {
            this.mServiceModell.writeIOData(mListOfUserLocations, this, STATE_OF_RECORDDING_NO );
        }
    }

    @Override
    public void showError( String reason ) {
        this.mIServiceView.showError( reason );
    }

}
