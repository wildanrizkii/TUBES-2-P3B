package com.example.tubes2p3b.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class DetailPengumuman implements Parcelable {
    String id;
    String title;
    String content;
    String author_id;
    String create_at;
    String update_at;
    Author author;
    ArrayList<Tags> tags;

    public DetailPengumuman(String id, String title, String content, String author_id, String create_at, String update_at, Author author, ArrayList<Tags> tags) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author_id = author_id;
        this.create_at = create_at;
        this.update_at = update_at;
        this.author = author;
        this.tags = tags;
    }

    protected DetailPengumuman(Parcel in) {
        id = in.readString();
        title = in.readString();
        content = in.readString();
        author_id = in.readString();
        create_at = in.readString();
        update_at = in.readString();
        author = in.readParcelable(Author.class.getClassLoader());
        tags = in.createTypedArrayList(Tags.CREATOR);
    }


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTags() {
        String t ="";
        int i =0;
        for (Tags tag: this.tags ) {
            if(this.tags.size()==1){
                t+=tag.getTag();
            } else if(this.tags.size()>1&&i<this.tags.size()-1){
                t+=tag.getTag()+ ", ";
            } else{
                t+=tag.getTag();
            }
            i ++;
        }
        return t;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
         parcel.writeString(this.title);
         parcel.writeString(this.content);
         parcel.writeString(getTags());
    }
    public static final Creator<DetailPengumuman> CREATOR = new Creator<DetailPengumuman>() {
        @Override
        public DetailPengumuman createFromParcel(Parcel in) {
            return new DetailPengumuman(in);
        }
        @Override
        public DetailPengumuman[] newArray(int size) {
            return new DetailPengumuman[size];
        }
    };
}
