package com.example.tubes2p3b.model;

import java.util.ArrayList;

public class BuatPengumuman {
    String title;
    String content;
    ArrayList<String> tags;

    public BuatPengumuman(String title, String content) {
        this.title = title;
        this.content = content;
        this.tags = new ArrayList<>();
    }

    public void addTags(String id) {
        this.tags.add(id);
    }

}

