package model;

import viewmodel.HealthObserver;

public interface Subject2 {
    void addHealthObserver(HealthObserver observer);

    void removeHealthObserver(HealthObserver observer);

    void notifyHealthObservers(int newHealth);
}
