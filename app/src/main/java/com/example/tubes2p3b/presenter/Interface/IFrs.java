package com.example.tubes2p3b.presenter.Interface;

import android.app.Activity;
import android.content.Context;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.fragment.app.FragmentResultOwner;

public interface IFrs {
    interface UI{
        Context getContext();
        Activity getActivity();
        FragmentResultOwner getParentFragmentManager();
        void setContainer(ListAdapter listAdapter);
        void setItemOnClick();
    }
}
