package com.example.tubes2p3b.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.example.tubes2p3b.databinding.ActivityMainBinding;
import com.example.tubes2p3b.model.RouterAPI;
import com.example.tubes2p3b.presenter.Interface.IMain;
import com.example.tubes2p3b.presenter.Interface.IRouterAPI;
import com.example.tubes2p3b.presenter.MainPresenter;


public class MainActivity extends AppCompatActivity implements IMain.UI, IRouterAPI.UI {
    MainPresenter presenter;
    ActivityMainBinding binding;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
        presenter = new MainPresenter(this);
        presenter.inittransaction(binding.container);
        presenter.setFragmentManagerResultListener();

        RouterAPI api = new RouterAPI(this);
//        api.getUser();
//        api.getAcademicYears();
//        api.getUser();
//        api.getDetailAnnouncement();
//        api.postUser();
//        api.getMatkul();
//        api.deleteTags();
//        api.getTags();

    }

    @Override
    public Context getContext() {
        return this;
    }
}