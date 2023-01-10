package com.example.tubes2p3b.presenter.Interface;

import android.content.Context;

import androidx.fragment.app.FragmentResultOwner;

public interface IPDetail {
    interface UI{
        Context getContext();
        FragmentResultOwner getParentFragmentManager();
        void setContent(String title,String tags,String deskripsi);
    }
}
