package com.example.tubes2p3b.adapter;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes2p3b.model.ListPengumuman;
import com.example.tubes2p3b.model.Tag;
import com.example.tubes2p3b.presenter.Interface.IPengumuman;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

public class Spinner {
    IPengumuman.UI ui;
    Gson gson = new Gson();


    public final static String[] ROLE={"role","admin","lecturer","student"};

    public final static String[] TAG={};

    
}
