package model;


public class AttackBoost extends AbilityDecorator {
    public AttackBoost(PlayerAbility decoratedPlayer) {
        super(decoratedPlayer);
    }
    Player player = Player.getPlayer();
    @Override
    public void useAbility() {
        super.useAbility();
        player.activateAttackPowerup();
    }
}

