package Model;

public class Player {

    private String name;
    private Sprite sprite;
    private int health;

    public Player(String name, String spriteName, int health) {
        this.name = name;
        this.sprite = new Sprite(spriteName);
        this.health = health;
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

}