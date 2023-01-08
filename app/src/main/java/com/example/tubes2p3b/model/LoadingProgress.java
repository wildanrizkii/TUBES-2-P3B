package com.example.tubes2p3b.model;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.tubes2p3b.R;

public class LoadingProgress {

    Activity activity;
    AlertDialog alertDialog;

    public LoadingProgress (Activity activity){
        this.activity = activity;
    }

    public void loadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_progress, null));
        builder.setCancelable(true);

        alertDialog = builder.create();
        alertDialog.show();
    }

    public void dialogDismiss(){
        alertDialog.dismiss();
    }
}
