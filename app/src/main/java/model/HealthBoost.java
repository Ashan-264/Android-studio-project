package model;


public class HealthBoost extends AbilityDecorator {
    public HealthBoost(PlayerAbility decoratedPlayer) {
        super(decoratedPlayer);
    }
    Player player = Player.getPlayer();
    @Override
    public void useAbility() {
        super.useAbility();
        player.activateHeartPowerup();
    }
}
