package model;

public abstract class AbilityDecorator implements PlayerAbility{
    protected PlayerAbility decoratedPlayer;

    public AbilityDecorator(PlayerAbility decoratedPlayer) {
        this.decoratedPlayer = decoratedPlayer;
    }

    @Override
    public void useAbility() {
        decoratedPlayer.useAbility();
    }
}
