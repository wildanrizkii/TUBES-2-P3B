package com.example.tubes2p3b.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes2p3b.adapter.PengumumanAdapter;
import com.example.tubes2p3b.databinding.FragmentPengumumanBinding;
import com.example.tubes2p3b.presenter.IPengumuman;
import com.example.tubes2p3b.presenter.IRouterAPI;
import com.example.tubes2p3b.presenter.PengumumanPresenter;

public class PengumumanFragment extends Fragment implements IPengumuman.UI {
    FragmentPengumumanBinding binding;

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
        PengumumanPresenter presenter = new PengumumanPresenter(this);
        presenter.loadPengumuman(binding.listItem);
//        binding.listItem.setOnClickListener(this::onClickList);
        return binding.getRoot();
    }

    private void onClickList(View view) {

    }
}
