package com.example.tubes2p3b.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.tubes2p3b.databinding.ActivityMainBinding;
import com.example.tubes2p3b.view.LoginFragment;
import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {
    LoginFragment loginFragment;
    ActivityMainBinding binding;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.loginFragment = LoginFragment.newInstance();

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(binding.container.getId(),this.loginFragment,"login")
                .setReorderingAllowed(true)
                .commit();


    }
}