package com.example.s0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class GameScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        Button gameButton =  findViewById(R.id.finishBtn);

        gameButton.setOnClickListener(v -> {
            Intent game = new Intent(this, EndScreen.class);
            startActivity(game);
        });
    }
}