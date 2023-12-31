package com.example.s0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ConfigScreen extends AppCompatActivity {

    private String name;
    private String sprite;
    private String difficulty;
    private int health;

    private EditText nameInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);

        // name
        nameInput = (EditText) findViewById(R.id.nameInput);

        // sprite
        RadioGroup spriteRadio = findViewById(R.id.characterInput);


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

                Player player = new Player(name, sprite, health);

                GameObject gameObject = GameObject.getGameObject();

                gameObject.configGame(player, difficulty);

                Intent game = new Intent(this, GameScreen.class);
                startActivity(game);
            }
        });
    }
};
