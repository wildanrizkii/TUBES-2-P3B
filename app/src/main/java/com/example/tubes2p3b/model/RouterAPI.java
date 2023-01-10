package com.example.tubes2p3b.model;

import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.example.tubes2p3b.presenter.Interface.IRouterAPI;

import org.json.JSONException;
import org.json.JSONObject;


public class RouterAPI {
    IRouterAPI.UI ui;
    SharedPreferences sp;

    public RouterAPI(IRouterAPI.UI ui){
        this.ui = ui;

    }


    public void getAnnouncement(){
        String Base_URL = "https://ifportal.labftis.net/api/v1/announcements/";
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
//                System.out.println(response);
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

    public void getDetailAnnouncement(){
        String Base_URL = "https://ifportal.labftis.net/api/v1/announcements/cc7bb324-8819-4150-af0f-2a737aee3ece";
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
//                System.out.println(response);
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

    public void getUser(){
        String email = "studentunpar@mail.com";
        String Base_URL = "https://ifportal.labftis.net/api/v1/users/email/"+email;
        RequestQueue queue = Volley.newRequestQueue(ui.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Base_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                getResponse(response);
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

    public void getMatkul(){
        String Base_URL = "https://ifportal.labftis.net/api/v1/courses/";
        RequestQueue queue = Volley.newRequestQueue(ui.getContext());
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
                map.put("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7InVzZXJfaWQiOiJhMzhjODJiNS1jYjNlLTRhZWUtOGZjOS0xNTVhNjA3MTEyYTgiLCJyb2xlIjoibGVjdHVyZXIifSwiaWF0IjoxNjcyNzMxNTgyfQ.AILXUwWboT2UaUw5xkAiDM4LDsjGvvqQJKdATGj6GPM");
                return map;
            }
        };
        queue.add(stringRequest);
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

    private void getResponseAcademicYears(String response) throws  JSONException{
        JSONObject jsonObject = new JSONObject(response);
        System.out.println(jsonObject);
    }

    private void getResponseAnnounce(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        System.out.println(jsonObject);
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
