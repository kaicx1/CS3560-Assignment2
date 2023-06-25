package app;

// Made by Kai Xue 
// Assignment 2 - Mini Twitter

public class Driver {
	public static void main(String[] args) {
		SingletonAdminPanel adminPanel = SingletonAdminPanel.getInstance();
		adminPanel.init();
	}
}
