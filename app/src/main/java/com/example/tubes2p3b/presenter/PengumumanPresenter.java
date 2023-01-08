package com.example.tubes2p3b.presenter;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentResultOwner;
import androidx.lifecycle.LifecycleOwner;

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
import com.example.tubes2p3b.model.RouterAPI;
import com.example.tubes2p3b.presenter.Interface.IPengumuman;
import com.example.tubes2p3b.presenter.Interface.IRouterAPI;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PengumumanPresenter{
    PengumumanAdapter adapter;
    private ArrayList<ListPengumuman> listPengumuman;
    IPengumuman.UI ui;
    RouterAPI api;
    String next;
    ListView container;
    Gson gson = new Gson();
    DetailPengumuman detailPengumuman;
    public PengumumanPresenter(IPengumuman.UI ui) {
        this.ui = ui;
        listPengumuman = new ArrayList<>();
    }

    public void loadPengumuman(ListView view){
        container = view;
        getAnnouncement();

    }
    public FragmentResultOwner getParentFragmentManager() {
        return ui.getParentFragmentManager();
    }

    public void itemClick(){
        container.setOnItemClickListener(this::onClickItem);
    }

    private void onClickItem(AdapterView<?> adapterView, View view, int i, long l) {
        getDetailAnnouncement(listPengumuman.get(i).getId());
    }

    public void getAnnouncement(){
        String Base_URL = "https://ifportal.labftis.net/api/v1/announcements?limit=10";
        RequestQueue queue = Volley.newRequestQueue(ui.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Base_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    getResponseAnnounce(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getErrResponse(error);
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
    }public void getAnnouncement(String str){
        String Base_URL = "https://ifportal.labftis.net/api/v1/announcements?limit=10&cursor="+str;
        RequestQueue queue = Volley.newRequestQueue(ui.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Base_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    getResponseAnnounce(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getErrResponse(error);
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

    public void getDetailAnnouncement(String id){
        String Base_URL = "https://ifportal.labftis.net/api/v1/announcements/"+id;
        RequestQueue queue = Volley.newRequestQueue(ui.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Base_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    getResponseDetailAnnounce(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getErrResponse(error);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7InVzZXJfaWQiOiIwOWU2N2M3Zi1iODNhLTQzMjgtYTMxMS03ZWVjZTA5MGI1MTUiLCJyb2xlIjoic3R1ZGVudCJ9LCJpYXQiOjE2NzMwNTI4NjV9.DVVZGOwoNjje5zVlhIrzeRkmpfMcru62IHgggyw_4PU");
                return map;
            }
        };
        queue.add(stringRequest);
    }

    private void getResponseDetailAnnounce(String response) throws JSONException {
        detailPengumuman = gson.fromJson(response,DetailPengumuman.class);
        Bundle res = new Bundle();
        Bundle page = new Bundle();
        res.putParcelable("detail", (Parcelable) detailPengumuman);
        page.putString("pages","dPengumuman");
        ui.getParentFragmentManager().setFragmentResult("changePage",page);
        getParentFragmentManager().setFragmentResult("detailPengumuman",res);
    }
    private void getResponseAnnounce(String response) throws JSONException {
        this.next ="";
        ArrayList<ListPengumuman> simpan;
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        this.next = jsonObject.getJSONObject("metadata").getString("next");
        simpan = gson.fromJson(jsonArray.toString(),new TypeToken <ArrayList<ListPengumuman>>(){}.getType());
        System.out.println(jsonArray);
        for (ListPengumuman list: simpan) {
            listPengumuman.add(list);
        }
        if(this.next.length()>0&&!this.next.equals("null")){
            getAnnouncement(this.next);
        }
        adapter = new PengumumanAdapter(ui.getContext());
        adapter.setListPengumumen(listPengumuman);
        container.setAdapter(adapter);
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
                System.out.println(object.get("errcode"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
