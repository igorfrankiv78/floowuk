package flowigor.ie.floowuk.utils;

import org.junit.Test;
import org.junit.Before;
import android.content.Context;
import org.junit.runner.RunWith;
import flowigor.ie.floowuk.BuildConfig;
import org.robolectric.annotation.Config;
import org.robolectric.RuntimeEnvironment;
import static org.junit.Assert.assertEquals;
import org.robolectric.RobolectricTestRunner;
import floowuk.floowrx.model.LastKnownResults;
import floowuk.floowrx.utils.SharedPreferencesUtil;
/*** Created by igorfrankiv on 09/04/2018. */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class TestSharedPreferencesUtil {

    private Context mContext;

    @Before
    public void setUp() {
        mContext = RuntimeEnvironment.application;
    }

    @Test
    public void testIsStartButtonTurnOnDefaultFalse(){
        assertEquals( SharedPreferencesUtil.isStartButtonTurnOn( mContext ) , false );
    }

    @Test
    public void testIsStartButtonTurnOnTrueAndFalse(){
        SharedPreferencesUtil.setStartButtonOn( mContext );
        assertEquals( SharedPreferencesUtil.isStartButtonTurnOn( mContext ) , true );

        SharedPreferencesUtil.setStartButtonOff( mContext );
        assertEquals( SharedPreferencesUtil.isStartButtonTurnOn( mContext ) , false );
    }

    @Test
    public void setAndGetLastKnownResults(){
        String timeStr = "03/18/2018 22:42:04" ;
        String distanceStr = "34";

        SharedPreferencesUtil.setLastKnownResults( mContext, timeStr, distanceStr );

        LastKnownResults lastKnownResults = SharedPreferencesUtil.getLastKnownResults( mContext );

        assertEquals( lastKnownResults.getDistance() , distanceStr );

        assertEquals( lastKnownResults.getTime() , timeStr );
    }
}
