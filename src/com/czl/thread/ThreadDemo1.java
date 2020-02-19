package com.czl.thread;

public class ThreadDemo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		new TestThread().run();
//		new TestThread2().start();
		ThreadTest t = new ThreadTest();
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();
//		while (true) {
//			System.out.println(Thread.currentThread().getName() + "** is running!");
//		}

	}

}
