package floowuk.floow.screens.customui;

/*** Created by igorfrankiv on 29/01/2018.*/
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import flowigor.ie.floowuk.R;

public class CustomProgressbar extends Dialog {
    private static CustomProgressbar mCustomProgressbar;
    private CustomProgressbar 		 mProgressbar;
    private OnDismissListener 		 mOnDissmissListener;

    public static void showProgressBar(Context context, boolean cancelable) {
        showProgressBar(context, cancelable, null);
    }

    public static void showProgressBar(Context context, boolean cancelable, String message) {
        if (mCustomProgressbar != null && mCustomProgressbar.isShowing()) {
            mCustomProgressbar.cancel();
        }
        mCustomProgressbar = new CustomProgressbar(context);
        mCustomProgressbar.setCancelable(cancelable);
        mCustomProgressbar.getWindow().setGravity(Gravity.TOP);
        mCustomProgressbar.getWindow().setDimAmount(0);
        mCustomProgressbar.show();
    }

    private CustomProgressbar(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_progressbar);
        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    public CustomProgressbar(Context context, Boolean instance) {
        super(context);
        mProgressbar = new CustomProgressbar(context);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mOnDissmissListener != null) {
            mOnDissmissListener.onDismiss(this);
        }
    }

    public static void showProgressBar(Context context, OnDismissListener listener) {

        if (mCustomProgressbar != null && mCustomProgressbar.isShowing()) {
            mCustomProgressbar.cancel();
        }
        mCustomProgressbar = new CustomProgressbar(context);
        mCustomProgressbar.setListener(listener);
        mCustomProgressbar.setCancelable(Boolean.TRUE);
        mCustomProgressbar.getWindow().setGravity(Gravity.TOP);
        mCustomProgressbar.getWindow().setDimAmount(0);
        mCustomProgressbar.show();
    }

    public static void hideProgressBar() {
        if (mCustomProgressbar != null) {
            mCustomProgressbar.dismiss();
        }
    }

    private void setListener(OnDismissListener listener) {
        mOnDissmissListener = listener;

    }

    public static void showListViewBottomProgressBar(View view) {
        if (mCustomProgressbar != null) {
            mCustomProgressbar.dismiss();
        }

        view.setVisibility(View.VISIBLE);
    }

    public static void hideListViewBottomProgressBar(View view) {
        if (mCustomProgressbar != null) {
            mCustomProgressbar.dismiss();
        }

        view.setVisibility(View.GONE);
    }

    public void showProgress(Context context, boolean cancelable, String message) {

        if (mProgressbar != null && mProgressbar.isShowing()) {
            mProgressbar.cancel();
        }
        mProgressbar.setCancelable(cancelable);
        mProgressbar.show();
    }

}
