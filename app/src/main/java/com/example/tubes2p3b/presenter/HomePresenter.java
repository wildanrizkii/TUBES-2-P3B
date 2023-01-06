package com.example.tubes2p3b.presenter;

import android.os.Bundle;
import android.view.View;

import com.example.tubes2p3b.presenter.Interface.IHome;

public class HomePresenter {
    IHome.UI ui;

    public HomePresenter (IHome.UI ui){
        this.ui = ui;
    }

    public void cangePage(View view,View v1,View v2,View v3){
        Bundle res = new Bundle();
        if(view == v1){
            res.putString("pages","frs");
        } else if(view == v2){
            res.putString("pages","pertemuan");
        }else if(view == v3){
            res.putString("pages","pengumuman");
        }
        ui.getParentFragmentManager().setFragmentResult("changePage",res);


    }
}
