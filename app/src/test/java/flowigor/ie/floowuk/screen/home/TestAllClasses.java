package flowigor.ie.floowuk.screen.home;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import flowigor.ie.floowuk.screen.home.mvp.HomePresenterTest;
import flowigor.ie.floowuk.screen.home.reactivex.FloowHomeActivityObservableSubsTest;

/*** Created by igorfrankiv on 15/04/2018.*/

public class TestAllClasses {

    @Test
    public void testClasses() {

        Result result = JUnitCore.runClasses(
           flowigor.ie.floowuk.screen.home.dagger2.FloowHomeActivityTest.class,
           HomePresenterTest.class,
           FloowHomeActivityObservableSubsTest.class,
           flowigor.ie.floowuk.screen.home.view.FloowHomeActivityTest.class
        );

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
