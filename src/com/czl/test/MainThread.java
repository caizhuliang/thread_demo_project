package com.czl.test;

public class MainThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		while (true) {
			// 线程1
			new Thread(new Runnable() {

				@Override
				public void run() {
					int num = new SubTest().add(1);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (this) {
						System.out.println("线程1:" + num);
					}
				}
			}).start();

			// 线程2
			new Thread(new Runnable() {

				@Override
				public void run() {
					int num = new SubTest().add(2);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (this) {
						System.out.println("线程2:" + num);
					}
				}
			}).start();

			// 线程3
			new Thread(new Runnable() {

				@Override
				public void run() {
					int num = new SubTest().add(3);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (this) {
						System.out.println("线程3:" + num);
					}
				}
			}).start();
		}
	}

}
