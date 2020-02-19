package com.czl.test;

import java.util.concurrent.Exchanger;

public class RunnableTest2 implements Runnable {

	private Exchanger<Integer> exchanger;

	public RunnableTest2() {
	}

	public RunnableTest2(Exchanger<Integer> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		Integer count = 0;
		try {
			count = exchanger.exchange(count);
			System.out.println("test2:" + count);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
