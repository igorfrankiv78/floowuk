package flowigor.ie.floowuk.model;

import org.junit.Test;
import org.junit.Before;
import floowuk.floowrx.model.MyObservable;
import static org.junit.Assert.assertEquals;
/*** Created by igorfrankiv on 07/04/2018.*/

public class TestMyObservable {

    private MyObservable<String> mMyObservable;
    private String time = "03/18/2018 22:42:04";

    @Before
    public void setUp(){
        mMyObservable = new MyObservable<String>( );
    }

    @Test
    public void testMyObservable() {
        mMyObservable.getObservable().subscribe( this::testObservable );
        mMyObservable.add(time);
    }

    public void testObservable( String str ){
        assertEquals( time , str );
    }
}
