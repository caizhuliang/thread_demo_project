package com.czl.thread;

/**
 * 这个例子能看出高并发时，通过同步代码块能避免超卖的情况
 */
public class ThreadDemo2 {

	public static void main(String[] args) {
		ThreadTest2 t = new ThreadTest2();
		ThreadTest2 t2 = new ThreadTest2();
//		t.method = "method";
		new Thread(t).start();// 创建线程1
		new Thread(t2).start();// 创建线程2
	}

}
