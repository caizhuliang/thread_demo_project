package com.czl.thread;

import java.util.Random;

/**
 * 使用ThreadLocal来完成线程内部数据共享
 */
public class TestThreadLocal {

	private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

	public static void main(String[] args) {
		TestThreadLocal instance = new TestThreadLocal();
		instance.startThread("A");
		instance.startThread("B");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main data = " + instance.threadLocal.get());
	}

	public void startThread(final String name) {
		// 起新的线程
		new Thread(() -> {
      // 获得一个int类型的随机数，这个例子要线程内部共享该数据。线程相同时，该数据相同；反则反之
      int data = new Random().nextInt();
      // 装载需要共享的数据
      threadLocal.set(data);
      System.out.println(Thread.currentThread().getName() + ":data is " + data);

      // 打印A类获得当前线程的共享数据
      System.out.println(Thread.currentThread().getName() + "-"+name+":data is " + new A().getData());

      // 打印B类获得当前线程的共享数据
      System.out.println(Thread.currentThread().getName() + "-"+name+":data is " + new B().getData());
    }).start();
	}

	// 创建内部类A
	class A {
		public int getData() {
			return threadLocal.get();
		}
	}

	// 创建内部类B
	class B {
		public int getData() {
			return threadLocal.get();
		}
	}

}
