package com.example.tubes2p3b.view.pengumuman;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes2p3b.databinding.FragmentPengumumanBinding;
import com.example.tubes2p3b.model.ListPengumuman;
import com.example.tubes2p3b.presenter.Interface.IPengumuman;
import com.example.tubes2p3b.presenter.pengumuman.PengumumanPresenter;

import java.util.ArrayList;

public class PengumumanFragment extends Fragment implements IPengumuman.UI {
    FragmentPengumumanBinding binding;
    PengumumanPresenter presenter;
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
        presenter = new PengumumanPresenter(this);
        binding.btnTambahPengumuman.setOnClickListener(this::onClickTambah);
        return binding.getRoot();
    }

    private void onClickTambah(View view) {
        presenter.tambahPengumuman();
    }


    @Override
    public void onStart() {
        super.onStart();
        presenter.loadPengumuman(binding.listItem);
        presenter.itemClick();
    }

    @Override
    public void visibleTombol() {
        binding.containerBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void invisibleButton() {
        binding.containerBtn.setVisibility(View.INVISIBLE);
    }
}
