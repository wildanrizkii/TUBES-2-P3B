package com.example.tubes2p3b.view.pertemuan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes2p3b.databinding.FragmentPertemuanBinding;

public class PertemuanFragment extends Fragment {
    FragmentPertemuanBinding binding;

    public static PertemuanFragment newInstance() {
        PertemuanFragment fragment = new PertemuanFragment();
        return fragment;
    }

    public PertemuanFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPertemuanBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }
}
