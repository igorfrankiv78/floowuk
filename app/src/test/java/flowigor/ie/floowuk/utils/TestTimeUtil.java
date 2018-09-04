package flowigor.ie.floowuk.utils;

import org.junit.Test;
import floowuk.floowrx.utils.TimeUtil;
import static org.junit.Assert.assertEquals;
/*** Created by igorfrankiv on 09/04/2018. */

public class TestTimeUtil {

    private TimeUtil mTimeUtil;

    @Test
    public void testTimeUtilseconds(){

        String dateStart = "03/18/2018 22:42:04";

        String dateStop  = "03/18/2018 22:43:03";

        String expectedValue  = "59s ";

        String durationOfJourney = mTimeUtil.durationOfJourney( dateStart, dateStop );

        assertEquals( expectedValue, durationOfJourney );
    }

    @Test
    public void testTimeUtilMinutes(){

        String dateStart = "03/18/2018 22:33:04";

        String dateStop  = "03/18/2018 22:43:03";

        String expectedValue  = "9m 59s ";

        String durationOfJourney = mTimeUtil.durationOfJourney( dateStart, dateStop );

        assertEquals( expectedValue, durationOfJourney );
    }

    @Test
    public void testTimeUtilHour(){

        String dateStart = "03/18/2018 21:42:04";

        String dateStop  = "03/18/2018 22:43:03";

        String expectedValue  = "1h 59s ";

        String durationOfJourney = mTimeUtil.durationOfJourney( dateStart, dateStop );

        assertEquals( expectedValue, durationOfJourney );
    }

    @Test
    public void testTimeUtilDay(){

        String dateStart = "03/17/2018 22:42:04";

        String dateStop  = "03/18/2018 22:43:03";

        String expectedValue  = "1d 59s ";

        String durationOfJourney = mTimeUtil.durationOfJourney( dateStart, dateStop );

        assertEquals( expectedValue, durationOfJourney );
    }
}
