package com.example.tubes2p3b.presenter.Interface;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

public interface IMain {
    interface UI{
        FragmentManager getSupportFragmentManager();
        Context getContext();
    }
}
