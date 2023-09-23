package com.example.s0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConfigScreen extends AppCompatActivity {

    private String name;
    private Sprite sprite;
    private int health;

    EditText nameInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);

        // name
        nameInput = (EditText) findViewById(R.id.nameInput);


        // sprite



        // difficulty + health




        Button gameButton =  findViewById(R.id.gameBtn);


        gameButton.setOnClickListener(v -> {
            name = nameInput.getText().toString();
            Intent game = new Intent(this, GameScreen.class);
            startActivity(game);
        });
    }


//        Intent game = new Intent(MainActivity.this, ConfigScreen.class);
//        startActivity(game);
};
