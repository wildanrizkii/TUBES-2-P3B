package com.example.tubes2p3b.presenter.Interface;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.fragment.app.FragmentResultOwner;

public interface ILogin {
    interface UI{
        void spinnerRole(ArrayAdapter<String> adapter);
        Context getContext();
        Activity getActivity();
        FragmentResultOwner getParentFragmentManager();
    }
    interface Websevice{
        void webConncetAuth(String json);
    }
}
