package com.example.tubes2p3b.presenter;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.fragment.app.FragmentResultOwner;

public interface WebServiceContract {
    interface UI{
        void spinnerRole(ArrayAdapter<String> adapter);
        Context getContext();
        FragmentResultOwner getParentFragmentManager();
    }
    interface Websevice{
        void webConncetAuth(Activity activity, String json);
    }
}
