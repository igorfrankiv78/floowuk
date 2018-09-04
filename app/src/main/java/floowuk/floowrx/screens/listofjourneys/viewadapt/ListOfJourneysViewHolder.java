package floowuk.floowrx.screens.listofjourneys.viewadapt;

import android.support.v7.widget.RecyclerView;
import floowuk.floowrx.model.UserLocationsDB;

public class ListOfJourneysViewHolder extends RecyclerView.ViewHolder {

    private final ListOfJourneysView listOfJourneysView;

    public ListOfJourneysViewHolder(ListOfJourneysView listOfJourneysView,
                                    IPositionClickListener authorIPositionClickListener) {
        super( listOfJourneysView );
        this.listOfJourneysView = listOfJourneysView;
        listOfJourneysView.setOnAuthorClickListener((view) -> authorIPositionClickListener.onClick(view, getAdapterPosition()));
    }

    public void setUserLocationsDBItem( UserLocationsDB userLocationsDB ){
        listOfJourneysView.setUserLocationsDBItem( userLocationsDB );
    }
}
