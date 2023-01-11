package com.example.tubes2p3b.view.frs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes2p3b.databinding.FragmentFrsBinding;
import com.example.tubes2p3b.presenter.frs.FrsPresenter;
import com.example.tubes2p3b.presenter.Interface.IFrs;

public class FrsFragment extends Fragment implements IFrs.UI {
    FragmentFrsBinding binding;
    FrsPresenter presenter;
    public static FrsFragment newInstance() {
        FrsFragment fragment = new FrsFragment();
        return fragment;
    }

    public FrsFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFrsBinding.inflate(inflater, container, false);
        presenter = new FrsPresenter(this);
        presenter.initYears(binding.containerItem);
        presenter.itemClick();
        return binding.getRoot();
    }

}
