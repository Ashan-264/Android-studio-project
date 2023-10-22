package model;

import ViewModel.ScoreObserver;

public interface Subject {
    void addScoreObserver(ScoreObserver observer);

    void removeScoreObserver(ScoreObserver observer);

    void notifyScoreObservers(int newScore);
}
