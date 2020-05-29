package com.example.androidpuzzlegame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity  extends AppCompatActivity {

    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void Play(View view) {
        Intent intent = new Intent(this, PuzzleMain.class);
        startActivity(intent);
    }

    public void Settings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }



}