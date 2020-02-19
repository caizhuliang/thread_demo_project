package com.czl.thread;

public class TestThread2 extends Thread {

	public void run() {
		while(true) {
			System.out.println(Thread.currentThread().getName() + " is running!");
		}
	}
}
