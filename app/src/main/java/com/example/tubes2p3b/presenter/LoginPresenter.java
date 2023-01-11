package com.example.tubes2p3b.presenter;

import static com.example.tubes2p3b.model.WebService.AUTHENTICATE;
import static com.example.tubes2p3b.model.WebService.JSON;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.icu.util.Output;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.FragmentResultOwner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes2p3b.adapter.PengumumanAdapter;
import com.example.tubes2p3b.model.DetailPengumuman;
import com.example.tubes2p3b.model.ListPengumuman;
import com.example.tubes2p3b.model.LoadingProgress;
import com.example.tubes2p3b.model.TokenPreferences;
import com.example.tubes2p3b.model.User;
import com.example.tubes2p3b.adapter.Dropdown;
import com.example.tubes2p3b.model.UserRes;
import com.example.tubes2p3b.model.WebService;
import com.example.tubes2p3b.presenter.Interface.ILogin;
import com.example.tubes2p3b.presenter.Interface.IPengumuman;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginPresenter implements ILogin.Websevice{
    private Gson gson;
    private Dropdown dropdown;
    private User user;
    private WebService webService;
    private ILogin.UI ui;
    private AnimationDrawable animationDrawable;
    private LoadingProgress loadingProgress;
    private TokenPreferences tokenPreferences;

    public LoginPresenter(ILogin.UI ui){
        webService = new WebService();
        gson = new Gson();
        dropdown =new Dropdown();
        this.ui = ui;
        tokenPreferences = new TokenPreferences(ui.getActivity());
        showspinner();
    }

    public FragmentResultOwner getParentFragmentManager() {
        return ui.getParentFragmentManager();
    }

    void showspinner(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ui.getContext(), android.R.layout.simple_spinner_dropdown_item, dropdown.ROLE);
        this.ui.spinnerRole(adapter);
    }

    public void cekdata(EditText e, EditText p, Spinner r){
        String email = e.getText().toString().trim();
        String password = p.getText().toString().trim();
        String role = r.getSelectedItem().toString();
        tokenPreferences.saveRole(role);
        if(email.length() == 0 || password.length() == 0 || role.equals("Pilih role")){
            Toast.makeText(ui.getContext(), "Harap isi email, password dan role dahulu!",Toast.LENGTH_SHORT).show();
        } else {
            this.user = new User(email,password,role);
            String json = gson.toJson(this.user);
            ConnectivityManager connectivityManager = (ConnectivityManager) ui.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo isOnlien = connectivityManager.getActiveNetworkInfo();
            if(isOnlien == null){
                Toast.makeText(ui.getContext(), "Anda sedang offline",Toast.LENGTH_LONG).show();
            } else{
                loadingProgress = new LoadingProgress(ui.getActivity());
                loadingProgress.loadingDialog();
                webConncetAuth(json);
            }
        }
    }

    @Override
    public void webConncetAuth(String json) {
        RequestQueue queue = Volley.newRequestQueue(ui.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AUTHENTICATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getErrResponse(error);
            }
        }){
            @Override
            public byte[] getBody() throws AuthFailureError {
                return json.getBytes();
            }
            @Override
            public String getBodyContentType() {
                return JSON;
            }
        };
        queue.add(stringRequest);
    }

    public FragmentResultOwner getParentFragmentManager() {
        return ui.getParentFragmentManager();
    }


    public void getResponse(String response){
        UserRes token = gson.fromJson(response, UserRes.class);
        Bundle res = new Bundle();
        this.tokenPreferences.saveToken(token.getToken());
        res.putString("pages","home");
        ui.getParentFragmentManager().setFragmentResult("changePage",res);
        loadingProgress.dialogDismiss();
    }

    public void getErrResponse(VolleyError response)  {
        String body;
        //get status code here
        String statusCode = String.valueOf(response.networkResponse.statusCode);
        //get response body and parse with appropriate encoding
        if(response.networkResponse.data!=null) {
            try {
                body = new String(response.networkResponse.data,"UTF-8");
                JSONObject object = new JSONObject(body);
                if (object.get("errcode").toString().equals("E_AUTH_FAILED")){
                    Toast.makeText(ui.getContext(),"Data tidak sesuai",Toast.LENGTH_LONG).show();
                } else if(object.get("errcode").toString().equals("E_UNKNOWN_FATAL")){
                    Toast.makeText(ui.getContext(),"Server sedang mengalami masalah!",Toast.LENGTH_LONG).show();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        loadingProgress.dialogDismiss();
    }


    public void animationBg(FrameLayout frameLayout){
        animationDrawable = (AnimationDrawable) frameLayout.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();
    }

    public Context getContext() {
        return getContext();
    }
}
