package dagger.android.mvp.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by DPM on 10-02-2018.
 */

public class MyProgressDialog {

    private static ProgressDialog progressDoalog;

    public static void showProgressDialog(Context context, String message) {
        progressDoalog = new ProgressDialog(context);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Progress");
        progressDoalog.setTitle(message);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDoalog.show();
    }

    public static void cancelProgressDialog() {
        progressDoalog.cancel();
    }
}
