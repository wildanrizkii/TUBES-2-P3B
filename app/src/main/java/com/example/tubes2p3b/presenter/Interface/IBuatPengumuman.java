package com.example.tubes2p3b.presenter.Interface;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.FragmentResultOwner;

public interface IBuatPengumuman {
    interface UI{
        Context getContext();
        Activity getActivity();
        FragmentResultOwner getParentFragmentManager();
        void clear();

    }
}
