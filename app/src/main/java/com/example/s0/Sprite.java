package com.example.s0;

// choice 1: yellowBuzz: https://imgbin.com/png/m66Xp6uU/georgia-institute-of-technology-georgia-tech-yellow-jackets-football-vespula-bee-hornet-png
// choice 2: purpleWizard: https://www.pngwing.com/en/free-png-xnlnd
// choice 3: greenArcher: https://dinopixel.com/pixel-art/hooded-archer/24958
public class Sprite {

    private String image;
    public Sprite(String name) {
        if (name.equals("Buzz")) {
            image = "buzz.png";
        } else if (name.equals("Wizard")) {
            image = "app/src/main/res/drawable/purple_wizard.png";
        } else if (name.equals("Archer")) {
            image = "app/src/main/res/drawable/green_archer.png.png";
        }
    }

}
