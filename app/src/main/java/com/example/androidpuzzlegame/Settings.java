package com.example.androidpuzzlegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class Settings extends AppCompatActivity {
    private StorageReference mStorageReference;

    Button Light;
    Button Medium;
    Button Hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Light = (Button) findViewById(R.id.Light);
        Medium = (Button) findViewById(R.id.Medium);
        Hard = (Button) findViewById(R.id.Hard);

        Hard.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                PuzzleActivity.piecesNumber = 30;
                PuzzleActivity.cols = 5;
                PuzzleActivity.rows = 6;
                Hard.setBackgroundResource(R.color.colorAccent);
                Light.setBackgroundResource(R.color.yellow);
                Medium.setBackgroundResource(R.color.yellow);
            }
        });

        Medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PuzzleActivity.piecesNumber = 20;
                PuzzleActivity.cols = 4;
                PuzzleActivity.rows = 5;
                Medium.setBackgroundResource(R.color.colorAccent);
                Hard.setBackgroundResource(R.color.yellow);
                Light.setBackgroundResource(R.color.yellow);
            }
        });

        Light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PuzzleActivity.piecesNumber = 12;
                PuzzleActivity.cols = 3;
                PuzzleActivity.rows = 4;
                Light.setBackgroundResource(R.color.colorAccent);
                Medium.setBackgroundResource(R.color.yellow);
                Hard.setBackgroundResource(R.color.yellow);
            }
        });


        mStorageReference = FirebaseStorage.getInstance().getReference().child("picture/settings.png");

        try {
            final File localFile = File.createTempFile("settings", "png");
            mStorageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    ((ImageView) findViewById(R.id.imageView)).setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
