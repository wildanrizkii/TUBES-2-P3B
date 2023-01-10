package com.example.tubes2p3b.model;

public class Tag {
    String tag;
    String tag_id;

    public Tag(String tag, String tag_id)
    {
        this.tag = tag;
        this.tag_id = tag_id;
    }

    public String getTag()
    {
        return tag;
    }

    public void setTag(String tag)
    {
        this.tag = tag;
    }

    public String getTag_id()
    {
        return tag_id;
    }

    public void setTag_id(String tag_id)
    {
        this.tag_id = tag_id;
    }
}