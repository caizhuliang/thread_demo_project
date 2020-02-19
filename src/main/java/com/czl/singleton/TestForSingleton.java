package com.czl.singleton;

public class TestForSingleton {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SingletonTest t1 = SingletonTest.getTest();
		t1.setChange("success");
		SingletonTest t2 = SingletonTest.getTest();
		String str = t2.getChange();
		System.out.println(str);
	}

}
