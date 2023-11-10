package viewmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import model.GameObject;
import model.Player;

public class ConfigScreen extends AppCompatActivity {

    private String name;
    private String sprite;
    private String difficulty;
    private int health;

    private int damage;

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
                health = 20;
                damage = 4;
            } else if (difficulty.equals("Medium")) {
                health = 25;
                damage = 2;
            } else if (difficulty.equals("Easy")) {
                health = 30;
                damage = 1;
            }
            if (validName && health != 0 && spriteRadioID != -1) {

                Player player = Player.getPlayer();

                player.setPlayer(name, sprite, health, damage);

                GameObject gameObject = GameObject.getGameObject();

                gameObject.configGame(player, difficulty);

                Intent game = new Intent(this, GameScreen.class);
                startActivity(game);
            }
        });
    }
};
