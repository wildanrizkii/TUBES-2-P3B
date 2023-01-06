package com.example.tubes2p3b.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes2p3b.databinding.FragmentBuatPertemuanBinding;

public class BuatPertemuanFragment extends Fragment {
    FragmentBuatPertemuanBinding binding;

    public static BuatPertemuanFragment newInstance() {
        BuatPertemuanFragment fragment = new BuatPertemuanFragment();
        return fragment;
    }

    public BuatPertemuanFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBuatPertemuanBinding.inflate(inflater, container, false);
        binding.btnSimpan.setOnClickListener(this::onClickSimpan);

        return binding.getRoot();
    }

    private void onClickSimpan(View view) {

    }
}
