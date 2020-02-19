package com.czl.test;

import java.util.concurrent.Exchanger;

public class RunnableTest1 implements Runnable {

	private Exchanger<Integer> exchanger;
	private int count = 0;

	public RunnableTest1() {
	}

	public RunnableTest1(Exchanger<Integer> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		while (count < 10) {
			if (5 == count) {
				try {
					System.out.println("test1的count到5了，交换后的结果是" + exchanger.exchange(count));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("test1:" + ++count);
		}
	}

}
