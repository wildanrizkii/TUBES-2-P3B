package com.example.tubes2p3b.presenter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes2p3b.adapter.FrsAdapter;
import com.example.tubes2p3b.model.AcademicYears;
import com.example.tubes2p3b.presenter.Interface.IFrs;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class FrsPresenter {
    IFrs.UI ui;
    AcademicYears academicYears;
    FrsAdapter adapter;
    ListView listView;

    public FrsPresenter(IFrs.UI ui) {
        this.ui = ui;
    }

    public void initYears(ListView view){
        getAcademicYears();
        this.adapter = new FrsAdapter(ui.getContext());
        this.listView = view;
    }

    public void creatAdapater(){
        adapter.setListAcademic(academicYears.getAcademic_years());
        listView.setAdapter(adapter);
    }

    private void onClickItem(AdapterView<?> adapterView, View view, int i, long l) {
        System.out.println(academicYears.getAcademic_years().get(i));
        Bundle page = new Bundle();
        page.putString("pages","tahunFrs");
        ui.getParentFragmentManager().setFragmentResult("changePage",page);
    }

    public void itemClick(){
        listView.setOnItemClickListener(this::onClickItem);
    }

    public void getAcademicYears(){
        String Base_URL = "https://ifportal.labftis.net/api/v1/academic-years";
        RequestQueue queue = Volley.newRequestQueue(ui.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Base_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    getResponseAcademicYears(response);
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
                map.put("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7InVzZXJfaWQiOiJhMzhjODJiNS1jYjNlLTRhZWUtOGZjOS0xNTVhNjA3MTEyYTgiLCJyb2xlIjoibGVjdHVyZXIifSwiaWF0IjoxNjcyNzMxNTgyfQ.AILXUwWboT2UaUw5xkAiDM4LDsjGvvqQJKdATGj6GPM");
                return map;
            }
        };
        queue.add(stringRequest);
    }

    private void getResponseAcademicYears(String response) throws  JSONException{
        JSONObject jsonObject = new JSONObject(response);
        Gson gson = new Gson();
        this.academicYears = gson.fromJson(response,AcademicYears.class);
        creatAdapater();

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
                System.out.println(object);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
