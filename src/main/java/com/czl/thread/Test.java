package com.czl.thread;

public class Test {

	static String str = "";

	public static void main(String[] args) {
		new Thread(() -> {
      synchronized (str) {
        while (true) {
          TestThread t = new TestThread();
          t.run();
        }
      }
    }).start();
	}
}
