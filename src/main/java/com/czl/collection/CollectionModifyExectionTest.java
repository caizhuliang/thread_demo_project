package com.czl.collection;

import com.czl.entity.User;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Map,HashMap,Set,HashSet,List,ArrayList，这些是线程不安全的，而且在迭代时，是不能修改的（否则报错）。
 * ConcurrentHashMap，ConcurrentSkipListMap，ConcurrentSkipListSet，CopyOnWriteArrayList，CopyOnWriteArraySet是线程安全的。
 * ConcurrentHashMap:不可以指定key的顺序
 * ConcurrentSkipListMap:可以指定key的顺序
 * CopyOnWriteArrayList(CopyOnWriteArraySet)：如果要一边迭代，一边修改，可以用CopyOnWriteArrayList(CopyOnWriteArraySet)
 */
public class CollectionModifyExectionTest {

	public static void main(String[] args) {
		// List<User> users = new CopyOnWriteArrayList<User>();
		Collection<User> users = new CopyOnWriteArrayList<User>();// 用List一样能在迭代时修改
		users.add(new User("张三", 23));
		users.add(new User("李四", 24));
		users.add(new User("王五", 25));
		Iterator<User> its = users.iterator();
		while (its.hasNext()) {
			User user = its.next();
			if (user.getName().equals("张三")) {
				users.remove(user);
			} else {
				System.out.println(user);// 如果去掉User里面的toString的方法，则打印别的标识
			}
		}
	}

}
