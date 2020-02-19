package com.czl.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch 计数器。 这里应用CountDownLatch模拟一个3人赛跑的程序
 */
public class UseCountDownLatchDemo {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();// 创建动态线程池实例
		final CountDownLatch cdStart = new CountDownLatch(1);// 用于通知3个子线程可以起跑
		final CountDownLatch cdEnd = new CountDownLatch(3);// 用于通知主线程3个线程均到达终点
		// 起3个子线程
		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println("线程" + Thread.currentThread().getName() + "准备就绪");
						cdStart.await();// 等待计数器cdStart到0
						// cdStart到0了，往下执行
						System.out.println("线程" + Thread.currentThread().getName() + "到达终点");
//						Thread.sleep((long) (Math.random() * 10000));
						cdEnd.countDown();// 计数器cdEnd减1
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);// 起线程
		}
		try {
			Thread.sleep((long) (Math.random() * 10000));
			System.out.println("准备起跑");
			System.out.println("线程" + Thread.currentThread().getName() + "已发送命令，正在等待结果");
			cdStart.countDown();// 计数器cdStart减1
			cdEnd.await();// 等待计数器cdEnd到0
			service.shutdown();// 关闭线程池，不再接受新任务
			System.out.println("比赛结束");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
