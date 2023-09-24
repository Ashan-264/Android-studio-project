package com.example.s0;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

// choice 1: yellowBuzz: https://imgbin.com/png/m66Xp6uU/georgia-institute-of-technology-georgia-tech-yellow-jackets-football-vespula-bee-hornet-png
// choice 2: purpleWizard: https://www.pngwing.com/en/free-png-xnlnd
// choice 3: greenArcher: https://dinopixel.com/pixel-art/hooded-archer/24958
public class Sprite {

    private String image;



    public Sprite(String name) {
//        if (name.equals("Buzz")) {
//            image = "buzz2.png";
//        } else if (name.equals("Wizard")) {
//            image = "purple_wizard.png";
//        } else if (name.equals("Archer")) {
//            image = "green_archer.png";
//        }
        image = name;
    }

    public String getImageName() {
        return image;
    }

}
