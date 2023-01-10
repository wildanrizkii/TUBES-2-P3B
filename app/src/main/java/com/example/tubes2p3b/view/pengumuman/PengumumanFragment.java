package com.example.tubes2p3b.view.pengumuman;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.tubes2p3b.databinding.FragmentPengumumanBinding;
import com.example.tubes2p3b.model.ListPengumuman;
import com.example.tubes2p3b.presenter.Interface.IPengumuman;
import com.example.tubes2p3b.presenter.PengumumanPresenter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PengumumanFragment extends Fragment implements IPengumuman.UI {
    FragmentPengumumanBinding binding;
    PengumumanPresenter presenter;
    String [] nama;
    String [] id;
    Spinner spinner;

    private ArrayList<ListPengumuman> listPengumumam ;
    public static PengumumanFragment newInstance() {
        PengumumanFragment fragment = new PengumumanFragment();
        return fragment;
    }

    public PengumumanFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPengumumanBinding.inflate(inflater,container,false);
        presenter = new PengumumanPresenter(this, getActivity());
        this.spinner = (Spinner) this.binding.spFilter;
        APISpinner();
        return binding.getRoot();
    }

    public void APISpinner()
    {
        String url= "https://ifportal.labftis.net/api/v1/tags";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    onSuccess(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onFailed(error);
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

    public void onSuccess(String response) throws JSONException {
        JSONArray jsonArray = new JSONArray(response);
        this.nama = new String[jsonArray.length() ];
        this.id = new String[jsonArray.length() ];
//        nama[0] = "none";
//        id[0] = "";
        for (int i = 0; i < jsonArray.length(); i++)
        {
            nama[i] = jsonArray.getJSONObject(i).getString("tag");
            id[i] = jsonArray.getJSONObject(i).getString("id");
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter (this.getActivity(), android.R.layout.simple_spinner_dropdown_item,nama);
        this.binding.spFilter.setAdapter(spinnerAdapter);
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int j = spinner.getSelectedItemPosition();
                SharedPreferences sp = getActivity().getSharedPreferences("TUBES-2-P3B", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("id", id[j]);
                editor.apply();
                presenter.loadPengumuman(binding.listItem);
                presenter.itemClick();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onFailed(VolleyError error) {
        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.loadPengumuman(binding.listItem);
        presenter.itemClick();
    }
}
