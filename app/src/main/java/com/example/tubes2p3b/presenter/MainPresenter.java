package com.example.tubes2p3b.presenter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tubes2p3b.model.UserToken;
import com.example.tubes2p3b.view.HomeFragment;
import com.example.tubes2p3b.view.LoginFragment;

public class MainPresenter {
    HomeFragment homeFragment;
    FragmentManager fragmentManager;
    LoginFragment loginFragment;
    IMain.UI ui;
    public UserToken userToken;

    public MainPresenter(IMain.UI ui){
        this.ui = ui;
        userToken = new UserToken();
        this.homeFragment = HomeFragment.newInstance();
        this.loginFragment = LoginFragment.newInstance();
        fragmentManager = ui.getSupportFragmentManager();

    }




}
