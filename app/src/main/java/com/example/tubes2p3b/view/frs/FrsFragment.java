package com.example.tubes2p3b.view.frs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tubes2p3b.databinding.FragmentFrsBinding;
import com.example.tubes2p3b.presenter.FrsPresenter;
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
        presenter.initYears();
        return binding.getRoot();
    }

    @Override
    public void setContainer(ListAdapter listAdapter) {
        binding.containerItem.setAdapter(listAdapter);
    }

    @Override
    public void setItemOnClick() {
        binding.containerItem.setOnItemClickListener(this::setItemOnClick);
    }

    private void setItemOnClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
