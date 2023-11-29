package model;

public class ScoreBoost extends AbilityDecorator {
    public ScoreBoost(PlayerAbility decoratedPlayer) {
        super(decoratedPlayer);
    }
    private Player player = Player.getPlayer();
    @Override
    public void useAbility() {
        super.useAbility();
        player.activateScorePowerup();
    }
}

