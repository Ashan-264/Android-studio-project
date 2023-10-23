package model;

public class MoveUpStrategy implements PlayerMovement {
    @Override
    public void move(Player player, int moveSpeed) {
        player.moveUp(moveSpeed);
    }
}
