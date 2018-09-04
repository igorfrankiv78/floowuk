package flowigor.ie.floowuk.screen.list;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import flowigor.ie.floowuk.screen.list.mvp.ListPresenterTest;
import flowigor.ie.floowuk.screen.list.reactivex.ListOfJourneysObservableSubsTest;
import flowigor.ie.floowuk.screen.list.view.ListOfJourneysUITest;

/*** Created by igorfrankiv on 16/04/2018.*/

public class TestAllClasses {

    @Test
    public void testClasses() {

        Result result = JUnitCore.runClasses(
                flowigor.ie.floowuk.screen.list.dagger2. ListOfJourneysTest.class,
                ListPresenterTest.class,
                ListOfJourneysObservableSubsTest.class,
                ListOfJourneysUITest.class
        );

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
