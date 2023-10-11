package Model;



public class Player {

    private String name;
    private Sprite sprite;
    private int health;

    private int score;

    static int MAX_SCORE = 10;

    public Player(String name, String spriteName, int health) {
        this.name = name;
        this.sprite = new Sprite(spriteName);
        this.health = health;
        this.score = MAX_SCORE;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getScore() { return score;}

    public void subScore(int amount) { score -= amount;}

}