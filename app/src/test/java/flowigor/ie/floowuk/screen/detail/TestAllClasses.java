package flowigor.ie.floowuk.screen.detail;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import flowigor.ie.floowuk.screen.detail.mvp.DetailPresenterTest;
import flowigor.ie.floowuk.screen.detail.reactivex.DetailedJourneyObservableSubsTest;
/*** Created by igorfrankiv on 15/04/2018. */

public class TestAllClasses {

 @Test
    public void testClasses() {

        Result result = JUnitCore.runClasses(
                flowigor.ie.floowuk.screen.detail.dagger2.DetailedJourneyTest.class,
                DetailPresenterTest.class,
                DetailedJourneyObservableSubsTest.class,
                flowigor.ie.floowuk.screen.detail.view.DetailedJourneyTest.class
        );

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}