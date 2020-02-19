package com.czl.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class TestMap {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "zhangsan");
		for (int i = 0; i < 2; i++) {
			String name = map.get("name");
			System.out.println(name);
			map.put("name", "lisi");
		}
	}

	private static void test1() {
		// 用HashMap装载，再取出来是和值栈一样，先进后出
		// Map<String, Integer> map = new HashMap<String, Integer>();
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();// 用LinkedHashMap装载，再取出来是和队列一样，先进先出
		for (int i = 0; i < 5; i++) {
			Integer j = i + 1;
			map.put(j.toString(), i);
		}
		// method1(map);
		method2(map);
	}

	// 遍历方法一
	private static void method1(Map<String, Integer> map) {
		Set<String> mapKeys = map.keySet();
		Iterator<String> it = mapKeys.iterator();// 迭代器
		while (it.hasNext()) {
			System.out.println(map.get(it.next()));
		}
	}

	// 遍历方法二
	private static void method2(Map<String, Integer> map) {
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

}
