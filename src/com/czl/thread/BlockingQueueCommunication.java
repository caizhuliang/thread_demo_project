package com.czl.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * TestThreadCondition用线程锁和线程通讯实现子线程和主线程有序的交替执行。这里将用阻塞队列来实现相同的效果
 */
public class BlockingQueueCommunication {

	private static final int count = 2;

	public static void main(String[] args) {
		final Business1 business = new Business1();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < count; i++) {
					business.sub(i + 1);// 执行子线程的业务逻辑
				}
			}
		}).start();
		for (int i = 0; i < count; i++) {
			business.main(i + 1);// 执行主线程的业务逻辑
		}
	}

}

// 先子线程运行10次，再主线程运行100次，这样的套路重复2次
class Business1 {

	BlockingQueue<Integer> queueSub = new ArrayBlockingQueue<Integer>(1);// 这里限制了队列只有1个空间，放满就阻塞。一旦不是满的了，立马就又放入（控制子线程）
	BlockingQueue<Integer> queueMain = new ArrayBlockingQueue<Integer>(1);// 这里限制了队列只有1个空间，放满就阻塞。一旦不是满的了，立马就又放入（控制主线程）

	// 这是匿名构造方法，匿名构造方法运行在任何的构造方法之前，即在调用任何构造方法（静态代码块除外）之前都会先执行匿名构造方法
	{
		try {
			queueMain.put(1);// 先塞满主线程队列，让主线程阻塞
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 子线程用的方法
	public void sub(int loop) {
		try {
			queueSub.put(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 10; i++) {
			System.out.println("sub:内部循环第" + (i + 1) + "次，外部循环第" + loop + "次");
		}
		try {
			queueMain.take();// 清空主线程队列，让主线程执行
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 主线程用的方法
	public void main(int loop) {
		try {
			queueMain.put(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 100; i++) {
			System.out.println("main:内部循环第" + (i + 1) + "次，外部循环第" + loop + "次");
		}
		try {
			queueSub.take();// 清空子线程队列，让子线程执行
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}