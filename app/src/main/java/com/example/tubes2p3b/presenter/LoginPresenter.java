package com.example.tubes2p3b.presenter;

import static com.example.tubes2p3b.model.WebService.AUTHENTICATE;
import static com.example.tubes2p3b.model.WebService.JSON;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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
import com.example.tubes2p3b.adapter.Spinner;
import com.example.tubes2p3b.model.UserRes;
import com.example.tubes2p3b.model.WebService;
import com.example.tubes2p3b.presenter.Interface.ILogin;
import com.example.tubes2p3b.presenter.Interface.IPengumuman;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LoginPresenter implements ILogin.Websevice{
    Gson gson;
    public Spinner spinner;
    public User user;
    public UserRes token;
    WebService webService;
    ILogin.UI ui;

    public LoginPresenter(ILogin.UI ui){
        webService = new WebService();
        gson = new Gson();
        spinner=new Spinner();
        token=new UserRes();
        this.ui = ui;
        showspinner();
    }


    void showspinner(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ui.getContext(), android.R.layout.simple_spinner_dropdown_item,spinner.ROLE);
        this.ui.spinnerRole(adapter);
    }

    @Override
    public void webConncetAuth(Activity activity, String json) {
        Bundle token = new Bundle();
        RequestQueue queue = Volley.newRequestQueue(activity);
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
        this.token = gson.fromJson(response, UserRes.class);
        Bundle res = new Bundle();
        res.putString("token",this.token.getToken());
        res.putString("pages","home");
        System.out.println(this.token.getToken());
        ui.getParentFragmentManager().setFragmentResult("changePage",res);
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
                    Toast.makeText((ui.getContext()),"Data tidak sesuai",Toast.LENGTH_LONG).show();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public Context getContext() {
        return getContext();
    }
}
