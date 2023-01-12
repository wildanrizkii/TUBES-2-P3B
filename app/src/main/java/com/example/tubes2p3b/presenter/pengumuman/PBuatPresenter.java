package com.example.tubes2p3b.presenter.pengumuman;

import static com.example.tubes2p3b.model.WebService.JSON;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes2p3b.model.BuatPengumuman;
import com.example.tubes2p3b.model.LoadingProgress;
import com.example.tubes2p3b.model.TokenPreferences;
import com.example.tubes2p3b.model.tagsbuat;
import com.example.tubes2p3b.presenter.Interface.IBuatPengumuman;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class PBuatPresenter {
    IBuatPengumuman.UI ui;
    BuatPengumuman buatPengumuman;
    String tag;
    Gson gson;
    Handler handler;
    boolean error;
    TokenPreferences tokenPreferences;
    private LoadingProgress loadingProgress;
    public PBuatPresenter(IBuatPengumuman.UI ui) {
        this.ui = ui;
        loadingProgress = new LoadingProgress(ui.getActivity());
        tokenPreferences = new TokenPreferences(ui.getActivity());

    }

    public void buatPengumuman(String title, String tag,String content){
        title.trim();
        tag.trim();
        content.trim();
        if(title.length()>0&&tag.length()>0&&tag.length()>0){
            buatPengumuman = new BuatPengumuman(title,content);
            this.ui.clear();
            loadingProgress.loadingDialog();
            post(tag);
        } else{
            Toast.makeText(ui.getContext(), "title, tag, atau deskripsi tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }
        Bundle page = new Bundle();
        page.putString("pages","pengumuman");
        ui.getParentFragmentManager().setFragmentResult("changePage",page);
        loadingProgress.dialogDismiss();
    }

    void post(String tag){
        error = false;
        handler = new Handler();
        String[] str = null;
        gson=new Gson();
        str= tag.split(" ");

        String[] finalStr = str;

        tagsbuat ta = new tagsbuat(tag);
        postTags(gson.toJson(ta));

//        Runnable po = new Runnable() {
//            @Override
//            public void run() {
//                for (String s: finalStr) {
//                    tagsbuat ta = new tagsbuat(s);
//                    postTags(gson.toJson(ta));
//                }
//            }
//        };
//        Runnable ann = new Runnable() {
//            @Override
//            public void run() {
//                postAnnouncement(gson.toJson(buatPengumuman));
//            }
//        };
//
//        Thread thread1 = new Thread(()->{
//            handler.post(po);
//        });
//
//        Thread thread2 = new Thread(()->{
//            handler.post(ann);
//        });
//
//        try {
//            thread2.wait(5000);
//            if(error==false){
//                thread2.start();
//            }
//            loadingProgress.dialogDismiss();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        thread1.start();
//        thread2.start();

    }

    public void postAnnouncement(String json){
        String Base_URL = "https://ifportal.labftis.net/api/v1/announcements/";
        RequestQueue queue = Volley.newRequestQueue(ui.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Base_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    getResponseBuatAnn(response);
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
            public byte[] getBody() throws AuthFailureError {
                return json.getBytes();
            }
            @Override
            public String getBodyContentType() {
                return JSON;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("Authorization","Bearer "+tokenPreferences.getToken());
                return map;
            }
        };
        queue.add(stringRequest);
    }


    public void postTags(String json) {
        String Base_URL = "https://ifportal.labftis.net/api/v1/tags/";
        RequestQueue queue = Volley.newRequestQueue(ui.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Base_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    getResponseBuatTags(response);
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
            public byte[] getBody() throws AuthFailureError {
                return json.getBytes();
            }
            @Override
            public String getBodyContentType() {
                return JSON;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Authorization", "Bearer "+tokenPreferences.getToken());
                return map;
            }
        };
        queue.add(stringRequest);
    }
    private void getResponseBuatTags(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        buatPengumuman.addTags(jsonObject.get("id").toString());
        postAnnouncement(gson.toJson(buatPengumuman));

    }

    private void getResponseBuatAnn(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        System.out.println(response);
    }

    public void getErrResponse(VolleyError response)  {
        this.error = true;
        String body;
        //get status code here
        String statusCode = String.valueOf(response.networkResponse.statusCode);
        //get response body and parse with appropriate encoding
        if(response.networkResponse.data!=null) {
            try {
                body = new String(response.networkResponse.data,"UTF-8");
                JSONObject object = new JSONObject(body);
                String err = object.get("errcode").toString();
                System.out.println(object);
                if (err.equals("E_DUPLICATE")){
                    Toast.makeText(ui.getContext(), "Tag sudah ada",Toast.LENGTH_SHORT).show();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
