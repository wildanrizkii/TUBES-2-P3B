package com.example.tubes2p3b.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes2p3b.databinding.ActivityMainBinding;
import com.example.tubes2p3b.presenter.IMain;
import com.example.tubes2p3b.presenter.LoginPresenter;
import com.example.tubes2p3b.presenter.MainPresenter;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements IMain.UI {
    MainPresenter presenter;
    HomeFragment homeFragment;
    LoginFragment loginFragment;
    ActivityMainBinding binding;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new MainPresenter(this);
        this.loginFragment = LoginFragment.newInstance();
        this.homeFragment = HomeFragment.newInstance();
        fragmentManager = getSupportFragmentManager();

        System.out.println(fragmentManager);

        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.add(binding.container.getId(),this.loginFragment,"login")
//                .commit();
        this.fragmentManager.setFragmentResultListener("changePage", this,
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        presenter.userToken.setToken(result.getString("token"));
                        changePage(result.getString("pages"));
                    }
                });
    }

    void changePage(String page){
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page.equals("home")){
            ft.remove(this.loginFragment);
            ft.add(binding.container.getId(),this.homeFragment);
        }
        ft.commit();
    }

    private void getUser(){
        String Base_URL = "https://ifportal.labftis.net/api/v1/users";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Base_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                GetResponse(response);
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7InVzZXJfaWQiOiI2ZTY2ODZmMC0yOTZlLTRjNzItOGE0NS1hNmFjMWVkNDhlNDQiLCJyb2xlIjoiYWRtaW4ifSwiaWF0IjoxNjcyMzYwOTQ4fQ.KF5P7d9EBpH62c8y9cTccV9NIs3qZmInzLUp5SnjZqI");
                return map;
            }
        };
        queue.add(stringRequest);
    }
}