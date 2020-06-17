package com.example.androidpuzzlegame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity  extends AppCompatActivity {

    Button notify;
    String tkn;

    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tkn= FirebaseInstanceId.getInstance().getToken();

        notify=findViewById(R.id.button);




        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Notify().execute();
            }
        });


    }


    public void Play(View view) {
        Intent intent = new Intent(this, PuzzleMain.class);

        startActivity(intent);
    }

    public void Settings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public class Notify extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {


            try {

                URL url = new URL("https://fcm.googleapis.com/fcm/send");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Authorization", "key=AAAAYdkNg9M:APA91bFfY8UKJvJwYBQAI9r2r3GrH869alOs-XHa2e-Bzr0kPaCMaDxVF-dItiZTxAYylDhOOYOB3M2hxDK4zMFPsq9dq8pdYIawaeW8WKE8Gc6x3wtWacsv7DDBsvmR4YO1sIPRll3S");
                conn.setRequestProperty("Content-Type", "application/json");

                JSONObject json = new JSONObject();

                json.put("to", tkn);


                JSONObject info = new JSONObject();
                info.put("title", "Puzzle Exam");   // Notification title
                info.put("body", "Поздравляю, ты нашел пасхалку"); // Notification body

                json.put("notification", info);

                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(json.toString());
                wr.flush();
                conn.getInputStream();

            }
            catch (Exception e)
            {
                Log.d("Error",""+e);
            }


            return null;
        }
    }


}