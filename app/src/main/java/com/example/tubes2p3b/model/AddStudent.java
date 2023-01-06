package com.example.tubes2p3b.model;

public class AddStudent {
    String name;
    String email;
    String password;
    String[] roles;
    String npm;
    int initial_year;


    public AddStudent(String name, String email, String password, String[] roles, String npm, int initial_year) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.npm = npm;
        this.initial_year = initial_year;
    }
}
