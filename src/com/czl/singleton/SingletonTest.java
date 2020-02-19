package com.czl.singleton;

public class SingletonTest {

	private SingletonTest() {
	}

	private static SingletonTest onlyTest = new SingletonTest();

	private String change;

	public static SingletonTest getTest() {
		return onlyTest;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getChange() {
		return change;
	}

}
