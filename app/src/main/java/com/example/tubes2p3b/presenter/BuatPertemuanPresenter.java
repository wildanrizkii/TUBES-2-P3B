package com.example.tubes2p3b.presenter;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes2p3b.R;
import com.example.tubes2p3b.model.RouterAPI;
import com.example.tubes2p3b.presenter.Interface.IBuatPertemuan;
import com.example.tubes2p3b.presenter.Interface.IRouterAPI;

import java.util.HashMap;
import java.util.Map;

public class BuatPertemuanPresenter implements IRouterAPI {
    IBuatPertemuan.UI ui;
    RouterAPI api;

    public BuatPertemuanPresenter(IBuatPertemuan.UI ui){
        this.ui = ui;
        api = new RouterAPI(ui);
    }

    public void setAppointment(){
        String Base_URL = "https://ifportal.labftis.net/api/v1/appointments/";
        RequestQueue queue = Volley.newRequestQueue(ui.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
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
                map.put("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7InVzZXJfaWQiOiJhMzhjODJiNS1jYjNlLTRhZWUtOGZjOS0xNTVhNjA3MTEyYTgiLCJyb2xlIjoibGVjdHVyZXIifSwiaWF0IjoxNjcyNzMxNTgyfQ.AILXUwWboT2UaUw5xkAiDM4LDsjGvvqQJKdATGj6GPM");
                return map;
            }
        };
        queue.add(stringRequest);
    }

}
