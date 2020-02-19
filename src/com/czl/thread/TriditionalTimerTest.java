package com.czl.thread;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器的demo
 */
public class TriditionalTimerTest {

	public static void main(String[] args) {
		// 创建一个定时器
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println("bombing");// 1000毫秒后打印第一次，以后每隔3000毫秒打印一次
			}
		}, 1000, 3000);

		while (true) {
//			System.out.println(new Date().getSeconds());// 打印当前机器的时间的秒
			System.out.println(Calendar.getInstance().get(Calendar.SECOND));// 打印当前机器的时间的秒
			try {
				Thread.sleep(1000);// 让线程睡眠1000毫秒
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
