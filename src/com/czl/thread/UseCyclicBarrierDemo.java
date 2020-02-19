package com.czl.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier 线程各自运行，运行完以后在某个点上等待没运行完的线程；都完成后，再各自继续往下运行。
 * 举个例子：班级活动，班级里的每个同学都好比一个线程。大家约好到某个地点集合，再各自活动
 */
public class UseCyclicBarrierDemo {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();// 创建动态线程池
		final CyclicBarrier cb = new CyclicBarrier(3);// 要等待的线程数目为3
		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程" + Thread.currentThread().getName()
								+ "即将到达集合地点1，当前已有" + (cb.getNumberWaiting() + 1) + "个已经到达，"
								+ (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
						cb.await();// 等到3个线程都到达，否者阻塞

						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程" + Thread.currentThread().getName()
								+ "即将到达集合地点2，当前已有" + (cb.getNumberWaiting() + 1) + "个已经到达，"
								+ (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
						cb.await();// 等到3个线程都到达，否者阻塞

						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程" + Thread.currentThread().getName()
								+ "即将到达集合地点3，当前已有" + (cb.getNumberWaiting() + 1) + "个已经到达，"
								+ (cb.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
						cb.await();// 等到3个线程都到达，否者阻塞
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);// 起线程
		}
		service.shutdown();// 关闭线程池，不再接受新任务
	}

}
