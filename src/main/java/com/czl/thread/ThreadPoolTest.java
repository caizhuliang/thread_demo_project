package com.czl.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池demo
 */
public class ThreadPoolTest {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();// 获取开始时间

		// method
		ThreadPoolTest threadPooltest = new ThreadPoolTest();
		threadPooltest.fixedPool();
//		threadPooltest.cachedPool();
//		threadPooltest.timerPool();

		long endTime = System.currentTimeMillis();// 获取结束时间
		System.out.println("程序运行时间： " + (endTime - startTime) + "ms");// 执行该方法所需的时间
	}

	/**
	 * 线程池里面的提供服务的线程的数量是固定的
	 */
	public void fixedPool() {
		ExecutorService threadPool = Executors.newFixedThreadPool(3);// 创建一个线程池，里面有3个线程提供服务。如果线程空闲下来，则进入休眠状态
		doTask(threadPool);// 执行等待线程服务的任务
	}

	/**
	 * 缓存线程池，内部线程个数是不固定的，根据需要调整
	 */
	public void cachedPool() {
		ExecutorService threadPool = Executors.newCachedThreadPool();// 创建一个缓存的线程池
		doTask(threadPool);// 执行等待线程服务的任务
	}

	/**
	 * 单一线程池，即线程池里面只有一个线程，但是会保证线程池里面至少有一个线程（如果线程死了，会马上起一个新的）
	 */
	public void singlePool() {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		doTask(threadPool);// 执行等待线程服务的任务
	}

	/**
	 * 线程池的定时器
	 */
	public void timerPool() {
		// 该线程池里只有3个线程,10是从触发到执行任务的时间长度，TimeUnit.SECONDS是时间长度的单位
		/*
		 * Executors.newScheduledThreadPool(3).schedule(new Runnable() {
		 * 
		 * @Override public void run() { System.out.println("bombing!"); } },
		 * 10, TimeUnit.SECONDS);
		 */

		// 该线程池里只有3个线程,10是从触发到执行任务的时间长度，每隔2个时间长度就再执行一次，TimeUnit.SECONDS是时间长度的单位
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				System.out.println("bombing!");
			}
		}, 10, 2, TimeUnit.SECONDS);
	}

	public void doTask(ExecutorService threadPool) {
		for (int i = 0; i < 10; i++) {// 这里循环10次，代表有10个任务需要线程提供服务
			final int task = i + 1;
			threadPool.execute(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 10; j++) {// 每个任务让线程遍历10次
						int loop = j + 1;
						try {
							Thread.sleep(20);// 让当前线程沉睡20毫秒
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName() + " is loop " + loop + ",the task is " + task);
					}
				}
			});
		}
		System.out.println("All of task is over!");
		threadPool.shutdown();// 关闭线程池，完成已提交的10个任务，不接受新任务
		// threadPool.shutdownNow();// 关闭线程池，不接受新任务并尝试停止当前未完成的任务
		if (threadPool.isShutdown())// 判断线程池是否成功关闭
			System.out.println("线程池已关闭");
	}

}
