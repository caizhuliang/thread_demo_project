package com.czl.thread;

/**
 * 这里介绍了同步代码块和同步函数
 */
public class ThreadTest2 implements Runnable {

	private static int tickets = 100;
	String method = "";

	@Override
	public void run() {
		if (method.equals("method")) {
			while (true) {
				sale();// 同步函数
			}
		} else {
			while (true) {
				// 同步代码块
				synchronized (ThreadTest2.class) {// synchronized里面的对象可以随意放，只要保证对象是同一个，就能实现同步
					if (tickets > 0) {
						System.out.print("1:");
						System.out.println(Thread.currentThread().getName()
								+ " is saling ticket " + tickets--);
					}
				}
			}
		}
	}

	/**
	 * 这里同步引入的对象是这个方法的实例对象this
	 */
	private synchronized void sale() {
		if (tickets > 0) {
			System.out.print("2:");
			System.out.println(Thread.currentThread().getName()
					+ " is saling ticket " + tickets--);
		}
	}

	/**
	 * 虚拟机会先运行静态方法，而不产生实例，这时这个方法同步引入的对象就是 ThreadTest2.class
	 */
	static synchronized void sale2() {
		if (tickets > 0) {
			System.out.print("3:");
			System.out.println(Thread.currentThread().getName()
					+ " is saling ticket " + tickets--);
		}
	}
}
