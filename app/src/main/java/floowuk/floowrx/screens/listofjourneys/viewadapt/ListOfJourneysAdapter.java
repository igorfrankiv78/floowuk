package floowuk.floowrx.screens.listofjourneys.viewadapt;

import rx.Observable;
import rx.Subscriber;
import java.util.List;
import android.view.View;
import java.util.ArrayList;
import android.widget.Toast;
import android.view.ViewGroup;
import android.content.Context;
import android.support.annotation.NonNull;
import floowuk.floowrx.model.UserLocationsDB;
import android.support.v7.widget.RecyclerView;

public class ListOfJourneysAdapter extends RecyclerView.Adapter<ListOfJourneysViewHolder> {

    public static final String ID = "ID";
    public List<UserLocationsDB> mUserLocationsDB = new ArrayList<>();
    private final Context mContext;
    private final ClicksAuthorSubscriber clicksAuthorSubscriber = new ClicksAuthorSubscriber();
    private final Observable<Integer> clicks = Observable.create(  clicksAuthorSubscriber ).publish().autoConnect();

    public ListOfJourneysAdapter(@NonNull Context context) {
        this.mContext = context;
    }

    public void setdata( @NonNull List<UserLocationsDB> userLocationsDB ){
        this.mUserLocationsDB = userLocationsDB;
    }

    @Override
    public ListOfJourneysViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListOfJourneysViewHolder(new ListOfJourneysView(mContext), clicksAuthorSubscriber);
    }

    @Override
    public void onBindViewHolder(ListOfJourneysViewHolder holder, int position) {
        holder.setUserLocationsDBItem( mUserLocationsDB.get(position) );
    }

    @Override
    public int getItemCount() {
        return mUserLocationsDB.size();
    }

    public Observable<Integer> authorObserveClicks() {
        return clicks;
    }

    public UserLocationsDB getUserLocationsDBItem(Integer position) {
        return  mUserLocationsDB.get( position );
    }

    public List<UserLocationsDB> getUserLocationsDB(){
        return  mUserLocationsDB;
    }

    public void removeCell( Integer idremoved ) {
        int position = idremoved;

         mUserLocationsDB.remove(position);
         notifyItemRemoved(position);
         notifyItemRangeChanged(position,  mUserLocationsDB.size());

         Toast.makeText(mContext, "Removed : " + String.valueOf( position ), Toast.LENGTH_SHORT).show();
    }

    public class ClicksAuthorSubscriber implements Observable.OnSubscribe<Integer>, IPositionClickListener {

        private Subscriber<? super Integer> subscriber;

        @Override
        public void call(Subscriber<? super Integer> subscriber) {
            this.subscriber = subscriber;
        }

        @Override
        public void onClick(View view, int position) {
            if (subscriber != null && !subscriber.isUnsubscribed()) {
                subscriber.onNext( position );
            }
        }
    }
}
