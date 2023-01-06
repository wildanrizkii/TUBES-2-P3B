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
import com.example.tubes2p3b.view.FrsFragment;
import com.example.tubes2p3b.view.HomeFragment;
import com.example.tubes2p3b.view.LoginFragment;
import com.example.tubes2p3b.view.PengumumanFragment;
import com.example.tubes2p3b.view.PertemuanFragment;

public class MainPresenter {
    public FragmentManager fragmentManager;
    public UserToken userToken;
    HomeFragment homeFragment;
    LoginFragment loginFragment;
    FrameLayout container;
    PertemuanFragment pertemuanFragment;
    FrsFragment frsFragment;
    PengumumanFragment pengumumanFragment;
    IMain.UI ui;

    public MainPresenter(IMain.UI ui){
        this.ui = ui;
        userToken = new UserToken();
        this.homeFragment = HomeFragment.newInstance();
        this.loginFragment = LoginFragment.newInstance();
        this.frsFragment = FrsFragment.newInstance();
        this.pertemuanFragment = PertemuanFragment.newInstance();
        this.pengumumanFragment = PengumumanFragment.newInstance();
        fragmentManager = ui.getSupportFragmentManager();
    }

    public void inittransaction(FrameLayout container){
        this.container = container;
        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.add(container.getId(),this.loginFragment,"login")
//                .commit();
//        ft.add(container.getId(),this.homeFragment,"login")
        ft.add(container.getId(),this.pengumumanFragment,"")
                .commit();
    }

    public void setFragmentManagerResultListener(){
        this.fragmentManager.setFragmentResultListener("changePage", (LifecycleOwner) ui, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                userToken.setToken(result.getString("token"));
                changePage(result.getString("pages"));
            }
        });
    }

    void changePage(String page){
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page.equals("home")){
            ft.remove(this.loginFragment);
            ft.add((container.getId()),this.homeFragment);
        }else if(page.equals("frs")){
            ft.remove(this.homeFragment);
            ft.add((container.getId()),this.frsFragment);
        } else if (page.equals("pertemuan")) {
            ft.remove(this.homeFragment);
            ft.add((container.getId()),this.pertemuanFragment);
        }else if (page.equals("pengumuman")) {
            ft.remove(this.homeFragment);
            ft.add((container.getId()),this.pengumumanFragment);
        }
        ft.commit();
    }

}
