package floowuk.floow.screens.listofjourneys.adapters;

    import android.content.Context;
    import android.content.Intent;
    import android.support.annotation.NonNull;
    import android.support.v7.widget.RecyclerView;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.LinearLayout;
    import android.widget.TextView;

    import floowuk.floow.model.UserLocationsDB;
    import floowuk.floow.screens.detail.DetailedJourney;
    import flowigor.ie.floowuk.R;
/*** Created by igor on 08/10/2017.*/

public class MapsRecViewAdapter extends RecyclerView.Adapter<MapsRecViewAdapter.ViewHolder> {

    private final static String TEXT_FIELD_1 = "IDS";
    private final static String TEXT_FIELD_2 = "TIME STAMP";
    public static final String ID = "ID";
    private Context mContext;
    private UserLocationsDB mUserLocationsDB;

    public MapsRecViewAdapter(@NonNull UserLocationsDB userLocationsDB, Context context) {
        this.mUserLocationsDB = userLocationsDB;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_user_journey_cell_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

    holder.textTitle1.setText( TEXT_FIELD_1 );
    holder.textDesc1.setText(String.valueOf(this.mUserLocationsDB.getListOfIds().get(position)));
    holder.textTitle2.setText( TEXT_FIELD_2 );
    holder.textDesc2.setText( String.valueOf(this.mUserLocationsDB.getListOfTimeStamps().get(position) ) );

        holder.linearLayoutMainContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the clicked item label
                String id = mUserLocationsDB.getListOfIds().get(position);

                Intent intent = new Intent(mContext, DetailedJourney.class);
                intent.putExtra(ID, id);
                mContext.startActivity(intent);
            }
        });
 }

    @Override
    public int getItemCount() {
        return this.mUserLocationsDB.getListOfIds().size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

          private TextView textDesc1, textDesc2, textDesc3;
          private TextView textTitle1, textTitle2, textTitle3;
          private LinearLayout linearLayoutMainContainer;

        protected ViewHolder(View view) {
            super(view);
            textTitle1 = (TextView) view.findViewById(R.id.textTitle1);
            textTitle2 = (TextView) view.findViewById(R.id.textTitle2);

            textDesc1 = (TextView) view.findViewById(R.id.textDesc1);
            textDesc2 = (TextView) view.findViewById(R.id.textDesc2);

            linearLayoutMainContainer = (LinearLayout) view.findViewById(R.id.linearLayoutMainContainer);
        }

    }
}