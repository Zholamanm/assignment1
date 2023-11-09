package test;

import java.util.ArrayList;
import java.util.List;

public class TestNotificationSubject implements Subject{
    private List<Observer> observers = new ArrayList<>();
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update("Ваш анализ готов!");
        }
    }
}
