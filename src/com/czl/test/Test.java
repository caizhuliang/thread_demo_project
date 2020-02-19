package com.czl.test;

import java.util.concurrent.Exchanger;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println(1 & 0);
//		new Test();
		Exchanger<Integer> exchanger = new Exchanger<Integer>();
		new Thread(new RunnableTest1(exchanger)).start();
		new Thread(new RunnableTest2(exchanger)).start();
	}

	// 执行在任何构造方法之前，包括默认的无参构造方法
	{
		System.out.println("匿名构造方法");
	}

	// 类被加载时就会执行
	static {
		System.out.println("静态构造方法");
	}

}
