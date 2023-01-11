package com.example.tubes2p3b.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

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
import com.example.tubes2p3b.view.pengumuman.BuatPengumumanFragment;
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
    BuatPengumumanFragment buatPengumumanFragment;
    IMain.UI ui;
    ConnectivityManager connectivityManager ;

    public MainPresenter(IMain.UI ui){
        this.ui = ui;
//        userToken = new UserToken();
        this.loginFragment = LoginFragment.newInstance();
        this.homeFragment = HomeFragment.newInstance();
        this.frsFragment = FrsFragment.newInstance();
        this.pertemuanFragment = PertemuanFragment.newInstance();
        this.pengumumanFragment = PengumumanFragment.newInstance();
        this.detailPengumumanFragment = DetailPengumumanFragment.newInstance();
        this.buatPengumumanFragment = BuatPengumumanFragment.newInstance();
        this.frsFragment = FrsFragment.newInstance();
        fragmentManager = ui.getSupportFragmentManager();
    }

    public void inittransaction(FrameLayout container){
        this.container = container;
        FragmentTransaction ft = fragmentManager.beginTransaction();
        connectivityManager = (ConnectivityManager) ui.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo isOnlien = connectivityManager.getActiveNetworkInfo();
        if(isOnlien == null){
            Toast.makeText(ui.getContext(), "Anda sedang offline",Toast.LENGTH_LONG).show();
        }
//        ft.add(container.getId(),this.loginFragment,"login")
//        ft.add(container.getId(),this.homeFragment,"login")
        ft.add(container.getId(),this.pengumumanFragment,"")
//        ft.add(container.getId(),this.buatPengumumanFragment,"")
//        ft.add(container.getId(),this.frsFragment,"")
                .setReorderingAllowed(true)
                .commit();
    }


    public void setFragmentManagerResultListener(){
        this.fragmentManager.setFragmentResultListener("changePage", (LifecycleOwner) ui, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
//                userToken.setToken(result.getString("token"));
                changePage(result.getString("pages"));
            }
        });

    }

    void changePage(String page){
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page.equals("home")){
            ft.replace(container.getId(),this.homeFragment).addToBackStack(null).setReorderingAllowed(true);
        } else if (page.equals("frs")) {
            ft.replace(container.getId(),this.frsFragment).addToBackStack(null).setReorderingAllowed(true);
        } else if (page.equals("pertemuan")) {
            ft.replace(container.getId(),this.pertemuanFragment).addToBackStack(null).setReorderingAllowed(true);
        } else if (page.equals("pengumuman")) {
            ft.replace(container.getId(),this.pengumumanFragment).addToBackStack(null).setReorderingAllowed(true);
        } else if (page.equals("dPengumuman")){
            ft.replace(container.getId(),this.detailPengumumanFragment).addToBackStack(null).setReorderingAllowed(true);
        } else if (page.equals("buatPengumuman")){
            ft.replace(container.getId(),this.buatPengumumanFragment).addToBackStack(null).setReorderingAllowed(true);
        }
        ft.commit();
    }

}