package com.example.tubes2p3b.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;

import com.example.tubes2p3b.databinding.ActivityMainBinding;
import com.example.tubes2p3b.model.RouterAPI;
import com.example.tubes2p3b.presenter.IMain;
import com.example.tubes2p3b.presenter.IRouterAPI;
import com.example.tubes2p3b.presenter.MainPresenter;


public class MainActivity extends AppCompatActivity implements IMain.UI, IRouterAPI.UI {
    MainPresenter presenter;
    HomeFragment homeFragment;
    LoginFragment loginFragment;
    ActivityMainBinding binding;
    FragmentManager fragmentManager;
    RouterAPI routerAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        routerAPI = new RouterAPI(this);

        // Announcement
//        routerAPI.getAnnouncement();

        // User
//        routerAPI.getUser();

        // Courses
//        routerAPI.getMatkul();

        

        presenter = new MainPresenter(this);
        System.out.println(binding.container);
        presenter.inittransaction(binding.container);
        presenter.setFragmentManagerResultListener();


    }



//    private void getUser(){
//        String Base_URL = "https://ifportal.labftis.net/api/v1/users";
//        RequestQueue queue = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET,
//                Base_URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
////                GetResponse(response);
//                System.out.println(response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                System.out.println(error);
//            }
//        }){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> map = new HashMap<>();
//                map.put("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7InVzZXJfaWQiOiJhMzhjODJiNS1jYjNlLTRhZWUtOGZjOS0xNTVhNjA3MTEyYTgiLCJyb2xlIjoibGVjdHVyZXIifSwiaWF0IjoxNjcyNzMxNTgyfQ.AILXUwWboT2UaUw5xkAiDM4LDsjGvvqQJKdATGj6GPM");
//                return map;
//            }
//        };
//        queue.add(stringRequest);
//    }

//    private void getAnnouncement(){
//        String Base_URL = "https://ifportal.labftis.net/api/v1/announcements/";
//        RequestQueue queue = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET,
//                Base_URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
////                GetResponse(response);
//                System.out.println(response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                getErrResponse(error);
//            }
//        }){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> map = new HashMap<>();
//                map.put("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7InVzZXJfaWQiOiI2ZTY2ODZmMC0yOTZlLTRjNzItOGE0NS1hNmFjMWVkNDhlNDQiLCJyb2xlIjoiYWRtaW4ifSwiaWF0IjoxNjcyMzYwOTQ4fQ.KF5P7d9EBpH62c8y9cTccV9NIs3qZmInzLUp5SnjZqI");
//                return map;
//            }
//        };
//        queue.add(stringRequest);
//    }
//
//    public void getErrResponse(VolleyError response)  {
//        String body;
//        //get status code here
//        String statusCode = String.valueOf(response.networkResponse.statusCode);
//        //get response body and parse with appropriate encoding
//        if(response.networkResponse.data!=null) {
//            try {
//                body = new String(response.networkResponse.data,"UTF-8");
//                JSONObject object = new JSONObject(body);
//                System.out.println(object.get("errcode"));
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }



    @Override
    public Context getContext() {
        return this;
    }
}