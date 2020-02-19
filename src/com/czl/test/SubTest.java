package com.czl.test;

public class SubTest {

	// 使用单例
	private SingleTest singleTest = SingleTest.getSingleTest();

	// 不使用单例
	// SingleTest singleTest = new SingleTest();

	public int add(int x) {
		int num = singleTest.addX(x);
		return num;
	}

}
