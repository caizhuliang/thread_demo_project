package com.czl.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 线程内部共享数据demo
 */
public class ThreadScopeShareDataTest {

	private static Map<Thread, Integer> map = new HashMap<Thread, Integer>();

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			// 起新的线程
			new Thread(new Runnable() {

				@Override
				public void run() {
					// 获得一个int类型的随机数，这个例子要线程内部共享该数据。线程相同时，该数据相同；反则反之
					int data = new Random().nextInt();
					map.put(Thread.currentThread(), data);
					System.out.println(Thread.currentThread().getName()
							+ ":data is " + data);

					// 打印A类获得当前线程的共享数据
					System.out.println(Thread.currentThread().getName()
							+ "-A:data is " + new A().getData());

					// 打印B类获得当前线程的共享数据
					System.out.println(Thread.currentThread().getName()
							+ "-B:data is " + new B().getData());
				}
			}).start();
		}
	}

	// 创建内部类A
	static class A {
		public int getData() {
			return map.get(Thread.currentThread());
		}
	}

	// 创建内部类B
	static class B {
		public int getData() {
			return map.get(Thread.currentThread());
		}
	}

}
