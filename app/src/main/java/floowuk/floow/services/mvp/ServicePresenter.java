package floowuk.floow.services.mvp;

import android.content.Context;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import floowuk.floow.model.UserLocation;
import floowuk.floow.utils.TimeUtil;

/*** Created by igorfrankiv on 28/01/2018.*/

public class ServicePresenter implements IServicePresenter, IServiceOnCompleteModel {

    private IServiceView mIServiceView;
    private ServiceModel mServiceModell;
    private Double mLatitude;
    private Double mLongitude;
    private String mTime;
    private List<UserLocation> mListOfUserLocations;

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

        if( listOfUserLocations != null)
            mListOfUserLocations = listOfUserLocations;

        mListOfUserLocations.add( new UserLocation(  mLatitude, mLongitude, mTime ) );
        this.mServiceModell.writeIOData(mListOfUserLocations, this, STATE_OF_RECORDDING_YES );
    }
    // Presenter Third Method to be triggered
    @Override
    public void readyDataForBroadcast ( String  jsonListOfUserLocationsObj ){

     String durrationOfJourney = "";
        if(mListOfUserLocations.size() >1)
            durrationOfJourney = TimeUtil.durationOfJourney ( mListOfUserLocations.get(0).getTime(),  mTime);

        this.mIServiceView.readyDataForBroadcast( jsonListOfUserLocationsObj, String.valueOf(mListOfUserLocations.size() ),  durrationOfJourney);
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
