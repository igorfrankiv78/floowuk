package floowuk.floowrx.screens.home;

/*** Created by igorfrankiv on 13/03/2018.*/

public class Notes {
    //         To whom it may concern
//         The project consists of three activities (screens). They are FloowHomeActivity,
//         ListOfJourneys, DetailedJourney. The Android Architecture chosen is MVP, which support all of the above screens.
//        The MVP also supports the service class with name “FloowServiceLocator”. I wanted to include the RXJava, Dagger2 with MVP
//        or ViewModel(I have never worked with it. I just started learning this one. It is a MVVM with something similar RXJava implementations ).
//        I decided to go a simple MVP.
//        I will begin with the FloowHomeActivity which is the starting point of the Application.
//        It has 5 Objects;
//        show journey Button,
//        how many meters was traveled tTextview,
//        the time elapsed since the journey was started Textview,
//        start and stop Button which starts the background service,
//        map fragment which shows the map.
//        So when a User presses the start button, it starts the Service “FloowServiceLocator” (this button will be set as pressed with share preferences
//        because if a user closes the App and reopens it. The button will be set as Start Recording Button, while it should show Stop Recording Button util it
//        gets the broadcast from the Service. So that was unacceptable.There were three options; share preference, to show progress dialog or reading the read
//        and write operation file of the Service class. I chose the first one. Actually the Receiver sets this as on and off  ).
//        The operation of Service class starts  with getting latitude, longitude, time and passing them to the Presenter which will process in the chain
//        of read (if a json file is existing or will populate a List (ArrayList) and write it for the first time to the local directory) , will populate a List,
//        convert it a to json format, write it to the local directory as a json format and then the Presenter passes the JSON object to the Service class, Service
//        class passes it the Activity to show the current location.
//        Now about why is read and write each time it gets latitude, longitude, time.
//        For a user can close the app, then the service is reloading and it will lose all of the data it was holding in the List (Array list) before or
//        the mobile phone will be restarted, then the Service will lose all the data
//        And will never be restarted until a user will start it again.
//        So there is the son file with data and object with a  key word string “YES” or “NO”. If the service  was in recording mode “YES”, the app was closed
//        or the phone restarted(the app has the wake up class which restarts the service with the operation “YES”), it will restart the same recording and will
//        continue to do so until a user presses “Stop Button”.
//
//        The read and write operation is sequenced with ReadWriteLock rwlock = new ReentrantReadWriteLock() for avoiding read and write operations (it can read
//        the data and user presses “Stops” write operation “NO”). First I wanted to go with Synchronised multithreading (It was working). But then I decided to go
//        with the first one.
//
//        The stop button in FloowHomeActivity  saves it (JSON file to the Sqlite DB). It was a simple solution to save a List to a DB
//        Table. There are the other solutions.
//
//        If there is at list one recording, it will enable the button which will lead the List view Activity of all journeys and then to Detail View Activity.
//        Each has MVP to the DB Sqlite to bring and populate the require fields. I implemented
//        the delete operation in the Detail View Activity. It is working.
//        Also I know a lot of
//        android google map api, LocationListener and LocationManager.
//        There are android.location.LocationListener, and the com.google.android.gms.location.LocationListener is using Google Play Services APIs
//        I am using in this project
//        import android.location.Location;
//        import android.location.LocationManager;
//        LocationListener and LocationManager.
//
//        I know this ones bellow are better ones. I have them on other project and will implement them too
//
//          import com.google.android.gms.location.LocationRequest;
//          import com.google.android.gms.location.LocationListener;
//          import com.google.android.gms.location.LocationRequest;
//          import com.google.android.gms.location.LocationServices;
//
//       However there are bugs and deprecated methods that I have to be aware of.
//        So I decided to start with simple one (the first one ) and then to go with the second one.
//
//        I think your company is doing something
//        requestLocationUpdates(String provider, long minTime, float minDistance, LocationListener listener)
//        Kilometer per Hour     Meters per Second
//            20kph                  5.56m/s
//            30kph                  8.33m/s
//            40kph 		         11.11m/s
//            50kph  		         13.89m/s
//        But I have to test how this is working in the real life. You may take the speed with requestLocationUpdates.
//        But then check with two different locations and time stamps. Also to play with the canvas or
//        However, I am nowhere near your level of expertise in them. If you take me, I will get your knowledge and mastery in those fields.
//        I was acting in the best of my knowledge in this project. I have never done it before.
//        Whatever the outcome of this test, please give me the feed back what I did wrong and how to improve myself.
//        At least the App is working and there is the fundamental skeleton.
//        For I want to improve this project and my skills in this field of Android.
//        Thank you very much!
}
