package floowuk.floowrx.screens.listofjourneys.viewadapt;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import flowigor.ie.floowuk.R;
import floowuk.floowrx.model.UserLocationsDB;

public class ListOfJourneysView extends FrameLayout
{
    private final static String TEXT_FIELD_1 = "IDS";
    private final static String TEXT_FIELD_2 = "TIME STAMP";

    public ListOfJourneysView(Context context) {
        super(context);
        init();
    }

    public ListOfJourneysView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ListOfJourneysView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ListOfJourneysView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @BindView(R.id.textTitle1)
     TextView textTitle1;

    @BindView(R.id.textTitle2)
     TextView textTitle2;

    @BindView(R.id.textDesc1)
     TextView textDesc1;

    @BindView(R.id.textDesc2)
     TextView textDesc2;

    @BindView(R.id.linearLayoutMainContainer)
     LinearLayout linearLayoutMainContainer;

    private void init() {
        inflate(getContext(), R.layout.activity_user_journey_cell_row, this);
        int[] attrs = new int[]{R.attr.selectableItemBackground};
        TypedArray ta = getContext().obtainStyledAttributes(attrs);
        Drawable selectableItemBackground = ta.getDrawable(0);
        ta.recycle();

        setForeground(selectableItemBackground);
        ButterKnife.bind(this);
    }

    public void setOnAuthorClickListener(OnClickListener l) {
        linearLayoutMainContainer.setOnClickListener(l);
    }

    public void setUserLocationsDBItem(UserLocationsDB userLocationsDB) {
        textTitle1.setText( TEXT_FIELD_1 );
        textDesc1.setText(String.valueOf(userLocationsDB.getId()));
        textTitle2.setText( TEXT_FIELD_2 );
        textDesc2.setText( String.valueOf(userLocationsDB.getTimeStamp() ) );
    }
}
