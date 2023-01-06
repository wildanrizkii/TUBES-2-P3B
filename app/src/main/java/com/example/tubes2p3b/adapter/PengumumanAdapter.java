package com.example.tubes2p3b.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tubes2p3b.databinding.ListPengumumanBinding;
import com.example.tubes2p3b.model.ListPengumuman;

import java.util.ArrayList;

public class PengumumanAdapter extends BaseAdapter {
    private ArrayList<ListPengumuman> listPengumuman;
    ListPengumumanBinding binding;
    private Context context;

    public PengumumanAdapter(Context context) {
        this.context = context;
        listPengumuman = new ArrayList<>();
    }
    public void setListPengumumen(ArrayList<ListPengumuman> listPengumuman){
        this.listPengumuman = listPengumuman;
    }
    @Override
    public int getCount() {
        return listPengumuman.size();
    }

    @Override
    public Object getItem(int i) {
        return this.listPengumuman.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        binding = ListPengumumanBinding.inflate(LayoutInflater.from(viewGroup.getContext()));
        ListPengumuman listPengumuman = (ListPengumuman) getItem(i);
        binding.tvJudul.setText(listPengumuman.getTitle());
        binding.tvTags.setText(listPengumuman.getTags());
        return binding.getRoot();
    }
}
