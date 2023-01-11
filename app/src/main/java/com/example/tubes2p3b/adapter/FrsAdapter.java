package com.example.tubes2p3b.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tubes2p3b.databinding.ListAcademicYearsFrsBinding;
import com.example.tubes2p3b.model.AcademicYears;
import com.example.tubes2p3b.model.ListPengumuman;

import java.util.ArrayList;

public class FrsAdapter extends BaseAdapter {
    ListAcademicYearsFrsBinding binding;
    ArrayList<String> academicYears;
    private Context context;

    public FrsAdapter(Context context) {
        this.academicYears = new ArrayList<>();
        this.context=context;
    }

    public void setListAcademic(ArrayList<String> academicYears){
        this.academicYears = academicYears;
    }

    @Override
    public int getCount() {
        return academicYears.size();
    }

    @Override
    public Object getItem(int i) {
        return this.academicYears.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        binding = ListAcademicYearsFrsBinding.inflate(LayoutInflater.from(viewGroup.getContext()));
        String tahun = this.academicYears.get(i);
        String tahuntulisan = tahunTulisan(tahun);
        int tahunangka = tahunAngka(tahun);
        binding.tvJudulSemester.setText("Semester "+tahuntulisan);
        binding.tvTahunSemester.setText((tahunangka)+" - "+ (tahunangka+1));
        return binding.getRoot();
    }

    String tahunTulisan(String years){
        int tahun = Integer.valueOf(years);
        String ganjilGenapPendek="";
        if(tahun%10==1){
            ganjilGenapPendek+="Ganjil";
        }else if(tahun%10==2){
            ganjilGenapPendek+="Genap";
        }else{
            ganjilGenapPendek+="Pendek";
        }
        return ganjilGenapPendek;
    }

    int tahunAngka(String years){
        int tahun = Integer.valueOf(years);
        return (tahun/10);
    }
}
