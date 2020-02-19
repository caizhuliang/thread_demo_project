package com.czl.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 缓存系统的demo。读写锁，读锁与读锁不互斥，但与写锁互斥；写锁与读锁、写锁均互斥
 */
public class CacheDemo {

	private static Map<Object, Object> cache = new HashMap<Object, Object>();// 用map来实现缓存
	private ReadWriteLock lock = new ReentrantReadWriteLock();// 创建读写锁

	public Object queryCacheData(Object key) {
		lock.readLock().lock();// 上读锁；上写锁的话，与其他锁均互斥会影响系统性能
		try {
			Object value = cache.get(key);// 通过key查找缓存里是否有要找的对象
			if (null == value) {// 缓存里没有对应的数据，就到数据库找
				lock.readLock().unlock();// 释放读锁
				lock.writeLock().lock();// 上写锁
				try {
					value = cache.get(key);// 检查是否在释放读锁的瞬间被其他线程填充了缓存
					if (null == value) {
						value = "obj";// 这里实际为去数据库取数据的代码
						cache.put(key, value);// 在数据库找到后写入缓存
					}
					lock.readLock().lock();// 重上读锁。这是个特例:如果是对象自己本身上了写锁，再上读锁，这时写锁会降级为更新锁
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					lock.writeLock().unlock();// 释放写锁
				}
			}
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.readLock().unlock();// 释放读锁
		}
		return null;
	}
}
