package com.czl.test;

public class SingleTest {
	final static SingleTest singleTest = new SingleTest();

	private SingleTest() {

	}

	public int addX(int x) {
		int num = x + 0;
		return num;
	}

	public static SingleTest getSingleTest() {
		return singleTest;
	}

}
