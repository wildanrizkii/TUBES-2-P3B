package com.example.tubes2p3b.view;

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
import com.example.tubes2p3b.databinding.FragmentLoginBinding;
import com.example.tubes2p3b.model.Pengguna;
import com.example.tubes2p3b.model.Spinner;
import com.google.gson.Gson;

public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    Gson gson;
    Pengguna pengguna;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }
    public LoginFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, Spinner.ROLE);
        boolean[] first = {true};
        gson = new Gson();
        binding.spRole.setAdapter(adapter);
        binding.spRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(first[0]){
                    first[0] = false;
                }else{
                    if(i == 0){
                        System.out.println("hello");
                        Toast.makeText(getContext(),"Please select appropriate option!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getContext(),Spinner.ROLE[i]+ " Selected !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        binding.btnLogin.setOnClickListener(this::onClickLogin);

        return binding.getRoot();
    }

    private void onClickLogin(View view) {
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        String role = binding.spRole.getSelectedItem().toString();
        pengguna = new Pengguna(email,password,role);
        String Base_URL = "https://ifportal.labftis.net/api/v1/authenticate";
        String json = gson.toJson(pengguna);

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Base_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        }){
            @Override
            public byte[] getBody() throws AuthFailureError {
                return json.getBytes();
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        queue.add(stringRequest);
    }
}























