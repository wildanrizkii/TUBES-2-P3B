package com.example.tubes2p3b.presenter.Interface;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.fragment.app.FragmentResultOwner;

import com.example.tubes2p3b.adapter.SpFilter;

public interface IPengumuman {
    interface UI {
        Context getContext();
        Activity getActivity();
        FragmentResultOwner getParentFragmentManager();
        void visibleTombol();
        void invisibleButton();

    }
}
