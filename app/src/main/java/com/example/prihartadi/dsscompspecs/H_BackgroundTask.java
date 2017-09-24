package com.example.prihartadi.dsscompspecs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by prihartadi on 19/08/2017.
 */

public class H_BackgroundTask extends AsyncTask<Void, Void, Void> {
    C_SimulatedAnnealing sa;
    private ProgressDialog dialog;
    C_State state;
    private final double t0 = 10000.0;
    private final double tn = 0.01;
    private final int n = 100000;
    private final int type = 2;
    Activity test;

    public H_BackgroundTask(A_Main activity, C_State state) {
        dialog = new ProgressDialog(activity);
        this.state = state;
        test = activity;
    }
    public H_BackgroundTask(A_Custom activity, C_State state) {
        dialog = new ProgressDialog(activity);
        this.state = state;
        test = activity;
    }
//    public H_BackgroundTask(A_Custom activity) {
//        dialog = new ProgressDialog(activity);
//    }
//    public H_BackgroundTask(Context context) {
//        Activity activity = (Activity) context;
//        dialog = new ProgressDialog(activity);
//    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage("Mencari Spesifikasi");
        dialog.show();
    }

    @Override
    protected void onPostExecute(Void result) {
//        if (dialog.isShowing()) {
//            dialog.dismiss();
//        }
    }

    @Override
    protected Void doInBackground(Void... params) {
        sa = new C_SimulatedAnnealing();
//        try {
//            Thread.sleep(60000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            sa.findRecomendation(test.getApplicationContext(),state,type,n,t0,tn);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
