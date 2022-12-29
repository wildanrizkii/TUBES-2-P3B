package com.example.tubes2p3b;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes2p3b.databinding.ActivityMainBinding;
import com.example.tubes2p3b.databinding.FragmentLoginBinding;
import com.google.gson.Gson;

import org.json.JSONException;


public class MainActivity extends AppCompatActivity {
    LoginFragment loginFragment;
    ActivityMainBinding binding;
    Gson gson;

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