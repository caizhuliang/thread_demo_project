package com.czl.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用线程锁来实现同步的效果
 */
public class ThreadLock {

	public static void main(String[] args) {
		final OutputName outputName = new ThreadLock().new OutputName();
		// 线程1使用打印方法1
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputName.printName1();;
				}
			}
		}).start();
		// 线程2使用打印方法2
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputName.printName2();
				}
			}
		}).start();
	}

	// 内部类里若要增加静态方法，则内部类必须为静态类，如果是最外层的类则不需要
	class OutputName {
		private Lock lock = new ReentrantLock();// 创建线程锁的实例
		public void printName1() {
			lock.lock();// 已进入该方法后就上锁，CPU无法再跳到别的线程
			try {
				String name = "zhangsan!";
				for (int i = 0; i < name.length(); i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();// 解锁
			}
		}

		public void printName2() {
			lock.lock();// 已进入该方法后就上锁，CPU无法再跳到别的线程
			try {
				String name = "lisi!";
				for (int i = 0; i < name.length(); i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();// 解锁
			}
		}

	}

}
