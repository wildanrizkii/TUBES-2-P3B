package com.example.tubes2p3b.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Tag implements Parcelable {
    String tag;
    String tag_id;

    public Tag(Parcel in) {
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