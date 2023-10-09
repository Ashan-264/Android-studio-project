package com.example.s0;

public class GameObject {

    private Player player;

    private String difficulty;

    private static GameObject gameObject;

    private GameObject(Player player, String difficulty) {
        this.player = player;
        this.difficulty = difficulty;
    }

    private GameObject() {
        player = null;
        difficulty = null;
    }

    public static GameObject getGameObject() {
        if (gameObject == null) {
            gameObject = new GameObject();
        }
        return gameObject;
    }

    public void configGame(Player player, String difficulty) {
        this.player = player;
        this.difficulty = difficulty;
    }

    public Player getPlayer() {
        return player;
    }

    public String getDifficulty() {
        return difficulty;
    }

}
