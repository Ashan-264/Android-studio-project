package model;


public class GameObject {

    private Player player;

    private String difficulty;

    private static GameObject gameObject;

    private LeaderBoard leaderboard;

    private GameObject() {
        player = null;
        difficulty = null;
        leaderboard = LeaderBoard.getLeaderBoard();
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

    public LeaderBoard getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard() {
        leaderboard.addPlayer(player.copy());
    }

}
