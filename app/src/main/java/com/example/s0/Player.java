package com.example.s0;

public class Player {

    private String name;
    private Sprite sprite;
    private int health;

    public Player(String name, String spriteName, int health) {
        this.name = name;
        this.sprite = new Sprite(spriteName);
        this.health = health;
    }

}