package com.example.tubes2p3b.presenter.pengumuman;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.example.tubes2p3b.adapter.SpFilter;
import com.example.tubes2p3b.model.DetailPengumuman;
import com.example.tubes2p3b.model.ListPengumuman;
import com.example.tubes2p3b.model.LoadingProgress;
import com.example.tubes2p3b.model.TokenPreferences;
import com.example.tubes2p3b.presenter.Interface.IPengumuman;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
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
    String next;
    ListView container;
    Gson gson = new Gson();
    DetailPengumuman detailPengumuman;
    LoadingProgress loadingProgress;
    TokenPreferences tokenPreferences;
    Spinner spFilter;
    String[] tags;
    String[] id;
    boolean[] first = {true};


    public PengumumanPresenter(IPengumuman.UI ui) {
        this.ui = ui;
        listPengumuman = new ArrayList<>();
        tokenPreferences = new TokenPreferences(ui.getActivity());
        String s =tokenPreferences.getRole();
        if(s.equals("student")||s.equals("lecturer")){
            ui.invisibleButton();
        } else{
            ui.visibleTombol();
        }

    }

    public void loadPengumuman(ListView view){
        container = view;
        loadingProgress = new LoadingProgress(ui.getActivity());
        getAnnouncement();
    }

    public FragmentResultOwner getParentFragmentManager() {
        return ui.getParentFragmentManager();
    }

    public void itemClick(){
        container.setOnItemClickListener(this::onClickItem);
    }

    public void tambahPengumuman() {
        Bundle page = new Bundle();
        page.putString("pages","buatPengumuman");
        getParentFragmentManager().setFragmentResult("changePage",page);
    }

    private void onClickItem(AdapterView<?> adapterView, View view, int i, long l) {
        getDetailAnnouncement(listPengumuman.get(i).getId());
    }

    public void getAnnouncement(){
        loadingProgress.loadingDialog();
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
                map.put("Authorization","Bearer "+tokenPreferences.getToken());
                return map;
            }
        };
        queue.add(stringRequest);
    }

    public void getAnnouncement(String str){
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
                map.put("Authorization","Bearer "+tokenPreferences.getToken());
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
                map.put("Authorization","Bearer "+tokenPreferences.getToken());
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
        getParentFragmentManager().setFragmentResult("changePage",page);
        getParentFragmentManager().setFragmentResult("detailPengumuman",res);
    }

    private void getResponseAnnounce(String response) throws JSONException {
        this.next ="";
        ArrayList<ListPengumuman> simpan;
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        this.next = jsonObject.getJSONObject("metadata").getString("next");
        simpan = gson.fromJson(jsonArray.toString(),new TypeToken<ArrayList<ListPengumuman>>(){}.getType());
        for (ListPengumuman list: simpan) {
            listPengumuman.add(list);
        }

        if(this.next.length()>0&&!this.next.equals("null")){
            getAnnouncement(this.next);
        } else {
            adapter = new PengumumanAdapter(ui.getContext());
            adapter.setListPengumumen(listPengumuman);
            container.setAdapter(adapter);
            loadingProgress.dialogDismiss();
            Toast.makeText(ui.getContext(), "Data berhasil dimuat",Toast.LENGTH_LONG).show();
        }

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
        loadingProgress.dialogDismiss();
        Toast.makeText(ui.getContext(),"Tidak dapat memuat data",Toast.LENGTH_LONG).show();
        //        StyleableToast.makeText(ui.getContext(), "Tidak dapat memuat data", Toast.LENGTH_LONG, R.style.myToastError).show();
    }


    public void getTags(){
        String Base_URL = "https://ifportal.labftis.net/api/v1/tags/";
        RequestQueue queue = Volley.newRequestQueue(ui.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Base_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    getResponseTags(response);
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
                map.put("Authorization","Bearer " + tokenPreferences.getToken());
                return map;
            }
        };
        queue.add(stringRequest);
    }

    private void getResponseTags(String response) throws JSONException {
        JSONArray jsonArray = new JSONArray(response);
        this.tags = new String[jsonArray.length()+1];
        this.id = new String[jsonArray.length()+1];
        id[0]="Pilih filte";
        tags[0]="Pilih filter";
        for (int i = 1; i < jsonArray.length()+1; i++)
        {
            tags[i] = jsonArray.getJSONObject(i-1).getString("tag");
            id[i] = jsonArray.getJSONObject(i-1).getString("id");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(ui.getActivity(),android.R.layout.simple_spinner_dropdown_item,tags);
        spFilter.setAdapter(adapter);
        spFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0&&first[0]==false){
                    listPengumuman.clear();
                    getAnnouncement();
                }else if(i>0){
                    getAnnouncementbyFilter(id[i]);
                    first[0]=false;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void getAnnouncementbyFilter(String id){
            loadingProgress.loadingDialog();
            this.listPengumuman.clear();
            String Base_URL = "https://ifportal.labftis.net/api/v1/announcements?filter[tags][]="+id+"&limit=10";
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
                    map.put("Authorization","Bearer "+tokenPreferences.getToken());
                    return map;
                }
            };
            queue.add(stringRequest);
    }

    public void initTagfilter(Spinner spFilter){
        this.spFilter = spFilter;
        getTags();
    }

}