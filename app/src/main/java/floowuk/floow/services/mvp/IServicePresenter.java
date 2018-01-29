package floowuk.floow.services.mvp;

import android.support.annotation.NonNull;

/*** Created by igorfrankiv on 28/01/2018.*/

public interface IServicePresenter {

    void setLocationData(@NonNull Double latitude, @NonNull Double longitude, @NonNull String time );

    void writeIOdata(  );
}
