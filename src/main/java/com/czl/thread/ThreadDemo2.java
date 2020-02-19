package com.czl.thread;

public class ThreadDemo2 {

	public static void main(String[] args) {
		ThreadTest2 t = new ThreadTest2();
		ThreadTest2 t2 = new ThreadTest2();
//		t.method = "method";
		new Thread(t).start();// 创建线程1
		new Thread(t2).start();// 创建线程2
	}

}
