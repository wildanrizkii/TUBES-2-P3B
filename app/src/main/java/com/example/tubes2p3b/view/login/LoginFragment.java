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
import com.example.tubes2p3b.adapter.Dropdown;
import com.example.tubes2p3b.presenter.login.LoginPresenter;
import com.example.tubes2p3b.presenter.Interface.ILogin;

public class LoginFragment extends Fragment implements ILogin.UI{
    FragmentLoginBinding binding;
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
        this.presenter.animationBg(binding.bgAnimation);
        binding.btnLogin.setOnClickListener(this::onClickLogin);
        return binding.getRoot();
    }

    private void onClickLogin(View view) {
        this.presenter.cekdata(binding.etEmail,binding.etPassword,binding.spRole);
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
                        Toast.makeText(getContext(),"Silakan pilih role yang lain!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getContext(), Dropdown.ROLE[i]+ " dipilih!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

}























