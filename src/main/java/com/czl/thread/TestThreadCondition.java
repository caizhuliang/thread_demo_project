package com.czl.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 关于线程同步通讯的demo
 */
public class TestThreadCondition {

	public static void main(String[] args) {
		final Business business = new Business();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 2; i++) {
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					business.sub(i + 1);// 执行子线程的业务逻辑
				}
			}
		}).start();
		for (int i = 0; i < 2; i++) {
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			business.main(i + 1);// 执行主线程的业务逻辑
		}
	}

}

// 先子线程运行10次，再主线程运行100次，这样的套路重复2次
class Business {

	private int conditionFlag = 0;// 0:该子线程运行；1：该主线程运行
	private Lock lock = new ReentrantLock();// 创建线程锁的实例对象
	private Condition conditionSub = lock.newCondition();// 创建执行条件的实例
	private Condition conditionMain = lock.newCondition();// 创建执行条件的实例

	// 子线程用的方法
	public void sub(int loop) {
		lock.lock();// 上锁
		try {
			// 防止假唤醒
			while (conditionFlag != 0) {// 0:该子线程运行；1：该主线程运行
				conditionSub.await();// 子线程等待
			}
			for (int i = 0; i < 10; i++) {
				System.out.println("sub:内部循环第" + (i + 1) + "次，外部循环第" + loop
						+ "次");
			}
			conditionFlag = 1;
			conditionMain.signal();// 唤醒主线程
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();// 释放锁
		}
	}

	// 主线程用的方法
	public void main(int loop) {
		lock.lock();// 上锁
		try {
			// 防止假唤醒
			while (conditionFlag != 1) {// 0:该子线程运行；1：该主线程运行
				conditionMain.await();// 主线程等待
			}
			for (int i = 0; i < 100; i++) {
				System.out.println("main:内部循环第" + (i + 1) + "次，外部循环第" + loop
						+ "次");
			}
			conditionFlag = 0;
			conditionSub.signal();// 唤醒子线程
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();// 释放锁
		}
	}

}