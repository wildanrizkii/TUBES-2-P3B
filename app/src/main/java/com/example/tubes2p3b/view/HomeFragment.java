package com.example.tubes2p3b.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes2p3b.databinding.FragmentHomeBinding;
import com.example.tubes2p3b.presenter.HomePresenter;
import com.example.tubes2p3b.presenter.Interface.IHome;

public class HomeFragment extends Fragment implements IHome.UI{
    FragmentHomeBinding binding;
    HomePresenter presenter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    public HomeFragment() {   }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        presenter = new HomePresenter(this);
        binding.bigBoxFRS.setOnClickListener(this::onClickPage);
        binding.smallBoxPertemuan.setOnClickListener(this::onClickPage);
        binding.smallBoxPengumuman.setOnClickListener(this::onClickPage);

        return binding.getRoot();
    }

    private void onClickPage(View view) {
        presenter.cangePage(view,binding.bigBoxFRS,binding.smallBoxPertemuan,binding.smallBoxPengumuman);
    }
}
