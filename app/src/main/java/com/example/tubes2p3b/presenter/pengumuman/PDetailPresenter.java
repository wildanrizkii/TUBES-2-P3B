package com.example.tubes2p3b.presenter.pengumuman;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentResultOwner;
import androidx.lifecycle.LifecycleOwner;

import com.example.tubes2p3b.model.DetailPengumuman;
import com.example.tubes2p3b.presenter.Interface.IPDetail;

public class PDetailPresenter {
    IPDetail.UI ui;
    DetailPengumuman detailPengumuman;

    public PDetailPresenter(IPDetail.UI ui) {
        this.ui = ui;
    }

    private FragmentResultOwner getParentFragmentManager() {
        return ui.getParentFragmentManager();
    }

    public void setDetailContent(){
        ui.getParentFragmentManager().setFragmentResultListener("detailPengumuman", (LifecycleOwner) ui, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                detailPengumuman = result.getParcelable("detail");
                updateVisual(detailPengumuman);
            }
        });
    }

    void updateVisual(DetailPengumuman detailPengumuman){
        String title = detailPengumuman.getTitle();
        String tags = detailPengumuman.getTags();
        String deskripsi = detailPengumuman.getContent();
        ui.setContent(title,tags,deskripsi);
    }
}
