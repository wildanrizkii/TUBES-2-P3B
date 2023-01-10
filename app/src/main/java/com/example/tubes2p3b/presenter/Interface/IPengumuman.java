package com.example.tubes2p3b.presenter.Interface;

import android.content.Context;

import androidx.fragment.app.FragmentResultOwner;

public interface IPengumuman {
    interface UI {
        Context getContext();
        FragmentResultOwner getParentFragmentManager();
    }
}
