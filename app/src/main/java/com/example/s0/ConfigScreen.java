package com.example.s0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConfigScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);
        Button gameButton =  findViewById(R.id.gameBtn);

        gameButton.setOnClickListener(v -> {
            Intent game = new Intent(this, BlankScreen.class);
            startActivity(game);
        });
    }


//        Intent game = new Intent(MainActivity.this, ConfigScreen.class);
//        startActivity(game);
};
