package com.example.tubes2p3b.model;

public class AddLecture {
    String name;
    String email;
    String password;
    String[] roles;

    public AddLecture(String name, String email, String password, String[] roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}

