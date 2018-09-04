package flowigor.ie.floowuk.services;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/*** Created by igorfrankiv on 18/04/2018.*/

public class TestAllClasses {

    @Test
    public void testClasses() {

        Result result = JUnitCore.runClasses(
                flowigor.ie.floowuk.services.dagger2.FloowServiceLocatorTest.class,
                flowigor.ie.floowuk.services.mvp.ServicePresenterTest.class,
                flowigor.ie.floowuk.services.reactivex.FloowServiceLocatorObservableSubsTest.class
        );

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}

