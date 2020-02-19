package com.czl.thread;

public class TestThread {

	public void run() {
		while(true) {
			System.out.println(Thread.currentThread().getName() + " is running!");
		}
	}
}
