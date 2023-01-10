package com.example.tubes2p3b.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class TokenPreferences {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public static final String SESSION = "session";
    public static final String TOKEN = "token";
    public static final String ROLE = "role";

    public TokenPreferences(Activity activity) {
        this.sharedPreferences = activity.getSharedPreferences(SESSION,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveToken(String token){
        editor.putString(TOKEN,token)
                .commit();
        System.out.println(token);
    }

    public String getToken(){
        String token = sharedPreferences.getString(TOKEN,null);
        return token;
    }

    public String getRole(){
        String role = sharedPreferences.getString(ROLE,null);
        return role;
    }


    public void saveRole(String role){
        editor.putString(ROLE,role)
                .commit();
    }

    public void delete(){
        editor.clear()
                .commit();
    }
}
