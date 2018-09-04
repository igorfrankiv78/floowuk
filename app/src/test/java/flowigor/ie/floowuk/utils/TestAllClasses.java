package flowigor.ie.floowuk.utils;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by igorfrankiv on 11/04/2018.
 */

public class TestAllClasses {

    @Test
    public void testClasses() {

        Result result = JUnitCore.runClasses(
                TestCalculationByDistance.class,
                TestFloowUtil.class,
                TestJSONUtil.class,
                TestSharedPreferencesUtil.class,
                TestTimeUtil.class
        );

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }

}
