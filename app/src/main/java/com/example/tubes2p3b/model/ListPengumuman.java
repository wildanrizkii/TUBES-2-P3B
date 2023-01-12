package com.example.tubes2p3b.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ListPengumuman implements Parcelable {
    String id;
    String title;
    String update_at;
    String create_at;
    Author author;
    ArrayList<Tags> tags;

    protected ListPengumuman(Parcel in) {
        id = in.readString();
        title = in.readString();
        update_at = in.readString();
        create_at = in.readString();
        author = in.readParcelable(Author.class.getClassLoader());
        tags = in.createTypedArrayList(Tags.CREATOR);
    }

    public static final Creator<ListPengumuman> CREATOR = new Creator<ListPengumuman>() {
        @Override
        public ListPengumuman createFromParcel(Parcel in) {
            return new ListPengumuman(in);
        }

        @Override
        public ListPengumuman[] newArray(int size) {
            return new ListPengumuman[size];
        }
    };

    public String getId() {
        return id;
    }

    public ListPengumuman(String id, String title, String update_at, String create_at, Author author, ArrayList<Tags> tags) {
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
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(update_at);
        parcel.writeString(create_at);
        parcel.writeParcelable(author, i);
        parcel.writeTypedList(tags);
    }

}

class Author implements Parcelable{
    String id;
    String author;

    protected Author(Parcel in) {
        id = in.readString();
        author = in.readString();
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(author);
    }
}

class Tags implements Parcelable{
    String tag;
    String tag_id;

    protected Tags(Parcel in) {
        tag = in.readString();
        tag_id = in.readString();
    }

    public static final Creator<Tags> CREATOR = new Creator<Tags>() {
        @Override
        public Tags createFromParcel(Parcel in) {
            return new Tags(in);
        }

        @Override
        public Tags[] newArray(int size) {
            return new Tags[size];
        }
    };

    public String getTag() {
        return tag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tag);
        parcel.writeString(tag_id);
    }
}
