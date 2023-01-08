package com.example.tubes2p3b.view.pengumuman;

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

import com.example.tubes2p3b.adapter.Spinner;
import com.example.tubes2p3b.databinding.FragmentPengumumanBinding;
import com.example.tubes2p3b.model.ListPengumuman;
import com.example.tubes2p3b.presenter.Interface.IPengumuman;
import com.example.tubes2p3b.presenter.PengumumanPresenter;

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
        presenter = new PengumumanPresenter(this, getActivity());
        return binding.getRoot();
    }

//    @Override
    public void spinnerFilter(ArrayAdapter<String> adapter) {
        binding.spFilter.setAdapter(adapter);
        boolean[] first = {true};
        binding.spFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(first[0]){
                    first[0] = false;
                }else{
                    if(i == 0){
                        Toast.makeText(getContext(),"Silahkan pilih Role yang lain!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getContext(), Spinner.ROLE[i]+ " Dipilih !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.loadPengumuman(binding.listItem);
        presenter.itemClick();
    }

}
