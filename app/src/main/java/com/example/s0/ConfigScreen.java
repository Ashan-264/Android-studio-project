package com.example.s0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ConfigScreen extends AppCompatActivity {

    private String name;
    private String sprite;
    private String difficulty;
    private int health;

    EditText nameInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);

        // name
        nameInput = (EditText) findViewById(R.id.nameInput);

        // sprite
        RadioGroup spriteRadio = findViewById(R.id.difficultyInput);


        // difficulty + health
        RadioGroup difficultyRadio = findViewById(R.id.difficultyInput);



        Button gameButton =  findViewById(R.id.gameBtn);


        gameButton.setOnClickListener(v -> {
            name = nameInput.getText().toString();
            boolean validName = false;
            if (name != null && !name.isEmpty() && !name.trim().isEmpty()) {
                validName = true;
            }

            int spriteRadioID = spriteRadio.getCheckedRadioButtonId();
            RadioButton selectedSpriteRadio = findViewById(spriteRadioID);
            sprite = (String) selectedSpriteRadio.getText();

            int difficultRadioID = difficultyRadio.getCheckedRadioButtonId();
            RadioButton selectedDifficlutyRadio = findViewById(difficultRadioID);
            difficulty = (String) selectedDifficlutyRadio.getText();

            if (difficulty.equals("Hard")) {
                health = 10;
            } else if (difficulty.equals("Medium")) {
                health = 20;
            } else if (difficulty.equals("Easy")) {
                health = 30;
            }
            if (validName && health != 0 && spriteRadioID != -1) {
                //CHANGE THIS WE NEED TO SAVE THIS PLAYER IN SOMETHING THAT WILL KEEP TRACK OF IT
                Player player = new Player(name, sprite, health);

                Intent game = new Intent(this, GameScreen.class);
                startActivity(game);
            }
        });
    }


//        Intent game = new Intent(MainActivity.this, ConfigScreen.class);
//        startActivity(game);
};
