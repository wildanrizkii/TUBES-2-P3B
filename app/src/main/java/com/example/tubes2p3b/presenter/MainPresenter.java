package com.example.tubes2p3b.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;

import com.example.tubes2p3b.model.UserToken;
import com.example.tubes2p3b.presenter.Interface.IMain;
import com.example.tubes2p3b.view.frs.FrsFragment;
import com.example.tubes2p3b.view.home.HomeFragment;
import com.example.tubes2p3b.view.login.LoginFragment;
import com.example.tubes2p3b.view.pengumuman.DetailPengumumanFragment;
import com.example.tubes2p3b.view.pengumuman.PengumumanFragment;
import com.example.tubes2p3b.view.pertemuan.PertemuanFragment;

public class MainPresenter {
    public FragmentManager fragmentManager;
    UserToken userToken;
    HomeFragment homeFragment;
    LoginFragment loginFragment;
    FrameLayout container;
    FrsFragment frsFragment;
    PertemuanFragment pertemuanFragment;
    PengumumanFragment pengumumanFragment;
    DetailPengumumanFragment detailPengumumanFragment;
    IMain.UI ui;
    SharedPreferences sp;

    public MainPresenter(IMain.UI ui, Activity activity){
        this.ui = ui;
        userToken = new UserToken();
        this.loginFragment = LoginFragment.newInstance();
        this.homeFragment = HomeFragment.newInstance();
        this.frsFragment = FrsFragment.newInstance();
        this.pertemuanFragment = PertemuanFragment.newInstance();
        this.pengumumanFragment = PengumumanFragment.newInstance();
        this.detailPengumumanFragment = DetailPengumumanFragment.newInstance();
        fragmentManager = ui.getSupportFragmentManager();
        this.sp = activity.getSharedPreferences("TUBES-2-P3B", Context.MODE_PRIVATE);
    }

    public void inittransaction(FrameLayout container){
        this.container = container;
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(container.getId(),this.loginFragment,"login")
                .commit();
//        ft.add(container.getId(),this.homeFragment,"login")
//        ft.add(container.getId(),this.pengumumanFragment,"")
//                .commit();
    }


    public void setFragmentManagerResultListener(){
        this.fragmentManager.setFragmentResultListener("changePage", (LifecycleOwner) ui, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String getStringToken = result.getString("token");
                userToken.setToken(getStringToken);
                changePage(result.getString("pages"));
                sp.edit().putString("token", getStringToken).apply();
            }
        });

    }

    void changePage(String page){
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page.equals("home")){
            ft.remove(this.loginFragment);
            ft.add((container.getId()),this.homeFragment);
        } else if (page.equals("frs")) {
            ft.hide(this.homeFragment);
            ft.add((container.getId()), this.frsFragment).addToBackStack(null);
        } else if (page.equals("pertemuan")) {
            ft.hide(this.homeFragment);
            ft.add((container.getId()),this.pertemuanFragment).addToBackStack(null);
        } else if (page.equals("pengumuman")) {
            ft.hide(this.homeFragment);
            ft.add((container.getId()),this.pengumumanFragment).addToBackStack(null);
        } else if(page.equals("dPengumuman")){
            ft.hide(this.pengumumanFragment);
            ft.add((container.getId()),this.detailPengumumanFragment).addToBackStack(null);
        }
        ft.commit();
    }

}