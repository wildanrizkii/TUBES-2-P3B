package com.example.tubes2p3b.view.pengumuman;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes2p3b.databinding.FragmentDetailPengumumanBinding;
import com.example.tubes2p3b.model.UserToken;
import com.example.tubes2p3b.presenter.Interface.IPDetail;
import com.example.tubes2p3b.presenter.Interface.IPengumuman;
import com.example.tubes2p3b.presenter.PDetailPresenter;
import com.example.tubes2p3b.presenter.PengumumanPresenter;

public class DetailPengumumanFragment extends Fragment implements IPDetail.UI {
    FragmentDetailPengumumanBinding binding;
    PDetailPresenter presenter;
    public DetailPengumumanFragment() {
    }

    public static DetailPengumumanFragment newInstance() {
        DetailPengumumanFragment fragment = new DetailPengumumanFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailPengumumanBinding.inflate(getLayoutInflater(),container,false);
        presenter = new PDetailPresenter(this);
        presenter.setDetailContent();
        return binding.getRoot();
    }


    @Override
    public void setContent(String title, String tags, String deskripsi) {
        binding.tvTitle.setText(title+"");
        binding.tvTag.setText(tags+"");
        binding.tvContent.setText(deskripsi+"");
    }
}
