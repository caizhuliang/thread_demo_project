package com.czl.thread;

public class ThreadTest implements Runnable {

  private int tickets = 100;
  String str = new String("");

  @Override
  public void run() {
    while (true) {
//			synchronized(str) {
      if (tickets > 0) {
        try {
          // 让线程睡眠10毫秒再往下执行（人为放大线程不安全的现象）
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " is saling ticket " + tickets--);
      }
//			}
    }

  }

}
