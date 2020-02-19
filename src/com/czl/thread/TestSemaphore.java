package com.czl.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore:信号灯 用于控制访问自身的线程的数量，并提供同步机制
 */
public class TestSemaphore {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();// 创建一个缓存的线程池
		final Semaphore semaphore = new Semaphore(3);// 创建3个信号灯
		// 这里起10个线程，但是并发量控制为3
		for (int i = 0; i < 10; i++) {
			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					try {
						semaphore.acquire();// 线程一进来就先拿走一个信号灯。当3个全被拿走时，只能等待已经拿到的释放信号灯
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程" + Thread.currentThread().getName() + "开始,当前并发量为"
							+ (3 - semaphore.availablePermits()));// semaphore.availablePermits()空闲的信号灯的数量
					try {
						Thread.sleep((long) (Math.random() * 10000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程" + Thread.currentThread().getName() + "即将离开");
					semaphore.release();// 释放信号灯
					// 下面代码有时候执行不准确，因为它没有和上面的代码合成原子单元
					System.out.println("线程" + Thread.currentThread().getName()
							+ "离开，当前并发量为" + (3 - semaphore.availablePermits()));
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
	}
}
