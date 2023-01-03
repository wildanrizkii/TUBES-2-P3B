package com.example.tubes2p3b.presenter;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;

import com.example.tubes2p3b.model.UserToken;
import com.example.tubes2p3b.view.HomeFragment;
import com.example.tubes2p3b.view.LoginFragment;

public class MainPresenter {
    HomeFragment homeFragment;
    public FragmentManager fragmentManager;
    LoginFragment loginFragment;
    IMain.UI ui;
    public UserToken userToken;
    FrameLayout container;

    public MainPresenter(IMain.UI ui){
        this.ui = ui;
        userToken = new UserToken();
        this.homeFragment = HomeFragment.newInstance();
        this.loginFragment = LoginFragment.newInstance();
        fragmentManager = ui.getSupportFragmentManager();
    }

    public void inittransaction(FrameLayout container){
        this.container = container;
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(container.getId(),this.loginFragment,"login")
                .commit();
    }

    public void setFragmentManagerResultListener(){
        this.fragmentManager.setFragmentResultListener("changePage", (LifecycleOwner) ui, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                userToken.setToken(result.getString("toke"));
                changePage(result.getString("pages"));
            }
        });
    }

    void changePage(String page){
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page.equals("home")){
            ft.remove(this.loginFragment);
            ft.add((container.getId()),this.homeFragment);
        }
        ft.commit();
    }

}
