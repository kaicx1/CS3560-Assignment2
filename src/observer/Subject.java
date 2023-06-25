package observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
	private List<Observer> observers = new ArrayList<Observer>();

	// add observer
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	// notifying observer used when posting
	protected void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(this);
		}
	}
}
