package com.example.tubes2p3b.view.login;

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

import com.example.tubes2p3b.databinding.FragmentLoginBinding;
import com.example.tubes2p3b.model.User;
import com.example.tubes2p3b.adapter.Spinner;
import com.example.tubes2p3b.presenter.LoginPresenter;
import com.example.tubes2p3b.presenter.Interface.ILogin;
import com.google.gson.Gson;

public class LoginFragment extends Fragment implements ILogin.UI{
    FragmentLoginBinding binding;
    Gson gson;
    LoginPresenter presenter;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }
    public LoginFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        this.presenter = new LoginPresenter(this);
        gson = new Gson();
        binding.btnLogin.setOnClickListener(this::onClickLogin);
        return binding.getRoot();
    }

    private void onClickLogin(View view) {
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        String role = binding.spRole.getSelectedItem().toString();
        presenter.user = new User(email,password,role);
        String json = gson.toJson(presenter.user);
        this.presenter.webConncetAuth(getActivity(),json);
    }

    @Override
    public void spinnerRole(ArrayAdapter<String> adapter) {
        binding.spRole.setAdapter(adapter);
        boolean[] first = {true};
        binding.spRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(first[0]){
                    first[0] = false;
                }else{
                    if(i == 0){
                        Toast.makeText(getContext(),"Silahkan pilih Role yang lain!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getContext(),Spinner.ROLE[i]+ " Dipilih !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}























