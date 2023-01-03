package com.example.tubes2p3b;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tubes2p3b.databinding.WelcomeScreen2Binding;

public class ScreenWelcome2 extends AppCompatActivity
{

    WelcomeScreen2Binding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = WelcomeScreen2Binding.inflate(getLayoutInflater());
        setContentView(R.layout.welcome_screen_2);
    }
}
