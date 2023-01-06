package com.example.tubes2p3b.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tubes2p3b.model.ListrPengumuman;

import java.util.ArrayList;

public class AdapterPengumuman extends BaseAdapter {
    private ArrayList<ListrPengumuman> listrPengumumen = new ArrayList<>();

    @Override
    public int getCount() {
        return listrPengumumen.size();
    }

    @Override
    public Object getItem(int i) {
        return listrPengumumen.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return ad;
    }
}
