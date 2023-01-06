package com.example.tubes2p3b.model;

import java.util.ArrayList;

public class ListrPengumuman {
    String id;
    String title;
    String update_at;
    String create_at;
    author author;
    ArrayList<tags> tags;

    public ListrPengumuman(String id, String title, String update_at, String create_at, com.example.tubes2p3b.model.author author, ArrayList<tags> tags) {
        this.id = id;
        this.title = title;
        this.update_at = update_at;
        this.create_at = create_at;
        this.author = author;
        this.tags = tags;
    }
}

class author{
    String id;
    String author;
}

class tags{
    String tag;
    String tag_id;
}
