package com.example.tubes2p3b.model;

public class Matakuliah {
    String id;
    String code;
    String name;
    int semester;
    String srchived_at;

    public Matakuliah(String id, String code, String name, int semester, String srchived_at) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.semester = semester;
        this.srchived_at = srchived_at;
    }
}
