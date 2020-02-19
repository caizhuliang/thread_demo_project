package com.czl.testfinally;


public class TestFinally {

	public static void main(String[] args) {
		int i = 0, j = 5;
		try {
			try {
				int k = j / i;
				System.out.println(k);
			} catch (Exception e) {
				System.out.println("111111111111");
				e.printStackTrace();
			} finally {
				System.out.println("in");
			}
		} catch (Exception e) {
			System.out.println("22222222222");
			e.printStackTrace();
		} finally {
			System.out.println("out");
		}
	}

}
