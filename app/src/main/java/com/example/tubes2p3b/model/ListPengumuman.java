package com.example.tubes2p3b.model;

import java.util.ArrayList;

public class ListPengumuman {
    String id;
    String title;
    String update_at;
    String create_at;
    Author author;
    ArrayList<Tags> tags;

    public ListPengumuman(String id, String title, String update_at, String create_at,Author author, ArrayList<Tags> tags) {
        this.id = id;
        this.title = title;
        this.update_at = update_at;
        this.create_at = create_at;
        this.author = author;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public String getTags() {
        String t ="";
        for (Tags tag: this.tags ) {
            t+=tag.getTag()+ " ";
        }

        return t;
    }
}

class Author{
    String id;
    String author;
}

class Tags{
    String tag;
    String tag_id;

    public String getTag() {
        return tag;
    }
}
