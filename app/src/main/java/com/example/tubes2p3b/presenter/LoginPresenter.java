package com.example.tubes2p3b.presenter;

import static com.example.tubes2p3b.model.WebService.AUTHENTICATE;
import static com.example.tubes2p3b.model.WebService.JSON;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import com.example.tubes2p3b.model.User;
import com.example.tubes2p3b.adapter.Dropdown;
import com.example.tubes2p3b.model.UserRes;
import com.example.tubes2p3b.model.WebService;
import com.example.tubes2p3b.presenter.Interface.ILogin;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LoginPresenter implements ILogin.Websevice{
    private Gson gson;
    private Dropdown dropdown;
    private User user;
    private UserRes token;
    private WebService webService;
    private ILogin.UI ui;
    private AnimationDrawable animationDrawable;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public LoginPresenter(ILogin.UI ui){
        webService = new WebService();
        gson = new Gson();
        dropdown =new Dropdown();
        token = new UserRes();
        this.ui = ui;
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
        e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(email.matches(emailPattern) && email.length() > 0){
//                    Toast.makeText(ui.getContext(),"format email tidak sesuai",Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(ui.getContext(), "format email tidak sesuai",Toast.LENGTH_SHORT).show();
                }
            }
        });
        this.user = new User(email,password,role);
        String json = gson.toJson(this.user);
        ConnectivityManager connectivityManager = (ConnectivityManager) ui.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo isOnlien = connectivityManager.getActiveNetworkInfo();
        if(isOnlien == null){
            Toast.makeText(ui.getContext(), "Anda sedang offline",Toast.LENGTH_LONG).show();
        } else{
            webConncetAuth(json);
        }
    }

    @Override
    public void webConncetAuth(String json) {
        Bundle token = new Bundle();
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


    public void getResponse(String response){
        this.token = gson.fromJson(response, UserRes.class);
        Bundle res = new Bundle();
        res.putString("token",this.token.getToken());
        res.putString("pages","home");
        ui.getParentFragmentManager().setFragmentResult("changePage",res);
    }

    public void getErrResponse(VolleyError response)  {
        String body;
        String statusCode = String.valueOf(response.networkResponse.statusCode);
        if(response.networkResponse.data!=null) {
            try {
                body = new String(response.networkResponse.data,"UTF-8");
                JSONObject object = new JSONObject(body);
                System.out.println(object);
                if (object.get("errcode").toString().equals("E_AUTH_FAILED")){
                    Toast.makeText(ui.getContext(),"Data tidak sesuai",Toast.LENGTH_LONG).show();
                } else if(object.get("errcode").toString().equals("E_UNKNOWN_FATAL")){
                    Toast.makeText(ui.getContext(),"Server sedang mengalami masalah!!!",Toast.LENGTH_LONG).show();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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
