package model;

import java.util.ArrayList;

public class GameObject {

    private Player player;

    private String difficulty;

    private static GameObject gameObject;

    private ArrayList<Player> leaderboard;

    private GameObject() {
        player = null;
        difficulty = null;
        leaderboard = new ArrayList<>();
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

    public ArrayList<Player> getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard() {
        leaderboard.add(player.copy());
    }
}