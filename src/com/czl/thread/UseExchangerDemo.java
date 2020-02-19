package com.czl.thread;

import java.util.concurrent.Exchanger;

/**
 * Exchanger 实现两个线程之间的内部数据交换
 */
public class UseExchangerDemo {

	static Exchanger<String> exchanger = new Exchanger<String>();

	public static void main(String[] args) {
		new Thread(new Merchant()).start();
		new Thread(new Client()).start();
	}

	// 卖早餐的商家
	static class Merchant implements Runnable {

		@Override
		public void run() {
			String haveBef = "油条";// 交易前拥有的东西
			try {
				System.out.println(Thread.currentThread().getName() + "原来:" + haveBef);
				String haveAft = exchanger.exchange(haveBef);// 交易后变成了钱
				System.out.println(Thread.currentThread().getName() + "现在:" + haveAft);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	// 买早餐的客户
	static class Client implements Runnable {

		@Override
		public void run() {
			String haveBef = "money";// 交易前拥有的东西
			try {
				System.out.println(Thread.currentThread().getName() + "原来:" + haveBef);
				String haveAft = exchanger.exchange(haveBef);// 交易后变成了早餐
				System.out.println(Thread.currentThread().getName() + "现在:" + haveAft);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
