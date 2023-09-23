package com.example.s0;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;



import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BlankScreen extends AppCompatActivity {

    public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.blank_screen);
            Button exitBtn =  findViewById(R.id.exitButton3);
            exitBtn.setOnClickListener(v -> {
                finishAffinity();  // Close all activities and exit the app
                System.exit(0);
            });

        }
    }
}
