package com.example.tubes2p3b.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes2p3b.databinding.ActivityMainBinding;
import com.example.tubes2p3b.model.AddLecture;
import com.example.tubes2p3b.model.AddStudent;
import com.example.tubes2p3b.model.User;
import com.example.tubes2p3b.presenter.IMain;
import com.example.tubes2p3b.presenter.LoginPresenter;
import com.example.tubes2p3b.presenter.MainPresenter;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements IMain.UI {
    MainPresenter presenter;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new MainPresenter(this);

        presenter.inittransaction(binding.container);
        presenter.setFragmentManagerResultListener();

    }
}