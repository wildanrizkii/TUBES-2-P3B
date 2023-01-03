package com.example.tubes2p3b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.tubes2p3b.databinding.ActivityMainBinding;
import com.example.tubes2p3b.databinding.WelcomeScreen2Binding;

public class MainActivity extends AppCompatActivity
{

    ActivityMainBinding binding;
    float x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
    }

    public boolean onTouchEvent(MotionEvent touchEvent)
    {
        switch (touchEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if (x1 < x2)
                {
//                Intent i = new Intent(MainActivity.this, SwipeLeft.class);
//                startActivity(i);
                }
                else if (x1 > x2)
                {
                    Intent i = new Intent(MainActivity.this, ScreenWelcome2.class);
                    startActivity(i);
                }
            break;
        }
        return false;
    }
}