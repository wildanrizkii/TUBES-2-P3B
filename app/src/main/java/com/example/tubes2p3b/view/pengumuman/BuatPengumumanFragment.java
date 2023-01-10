package com.example.tubes2p3b.view.pengumuman;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes2p3b.databinding.FragmentBuatPengumumanBinding;
import com.example.tubes2p3b.presenter.Interface.IBuatPengumuman;
import com.example.tubes2p3b.presenter.pengumuman.PBuatPresenter;


public class BuatPengumumanFragment extends Fragment implements IBuatPengumuman.UI {
    FragmentBuatPengumumanBinding binding;
    PBuatPresenter presenter;

    public static BuatPengumumanFragment newInstance() {
        BuatPengumumanFragment fragment = new BuatPengumumanFragment();
        return fragment;
    }

    public BuatPengumumanFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBuatPengumumanBinding.inflate(getLayoutInflater(),container,false);
        presenter = new PBuatPresenter(this);

        binding.btnSimpan.setOnClickListener(this::onClickBuat);

        return binding.getRoot();
    }

    private void onClickBuat(View view) {
        String title = binding.etTitle.getText().toString();
        String tags = binding.etTags.getText().toString();
        String deskripsi = binding.etDeskripsi.getText().toString();
        presenter.buatPengumuman(title,tags,deskripsi);

    }

    @Override
    public void clear() {
        binding.etTitle.setText("");
        binding.etTags.setText("");
        binding.etDeskripsi.setText("");
    }
}
