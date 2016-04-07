package com.apelover.algorithm;

public class RightShiftOneSpace {

	public static void main(String[] args) {
		int n = 10, k = 2;
		Object[] orgArr = new Object[n];
		System.out.print("数组交换前：");
		for (int i = 0; i < n; i++) {
			orgArr[i] = new Integer(i);
			System.out.print(orgArr[i] + " ");
		}
		System.out.print("\n");

		RightShiftOneSpace rsos = new RightShiftOneSpace();
		Object[] result = rsos.rightShiftWay1(orgArr, k);

		System.out.print("循环K次数组交换后：");
		for (int i = 0; i < n; i++) {
			System.out.print(result[i] + " ");
		}
		System.out.print("\n");

	}

	/**
	 * <p>
	 * 循环右移第二种方法：
	 * <p>
	 * 循环K次交换
	 * <p>
	 * 最坏时间复杂度：n*k(其中k=n-1)
	 * <p>
	 * 平均复杂度：由于k求n求余后，都有0<=k<n，且k机会是均等的，所以，每个k的概率为1/n。每一轮的迭代次数为n-1.
	 * 总次数为 ：1/n*((1*(n-1)) + 2*(n-1)+...+(n-1)*(n-1))=1/n*(n-1)*(1+2+n-1) = (n-1)*(n-1)/2
	 * <p>平均复杂度为O(n*n)
	 */
	Object[] rightShiftWay1(Object[] org, int k) {
		Object space = null;
		if (org == null || org.length == 1) {
			return org;
		}
		int n = org.length;
		if (k % n == 0) {
			return org;
		}
		k = k % n;
		do {
			space = org[n - 1];
			for (int i = n - 1; i > 0; i--) {
				org[i] = org[i - 1];
			}
			org[0] = space;
			k--;
		} while (k > 0);
		return org;
	}

	/**
	 * <p>
	 * 循环右移第一种算法：求最k和n的最大公约数，即总移动轮数；
	 * 每一轮移动，都从当前位置开始，逆推计算原位置，从原位置移动数据到当前位置，然后置原位置为当前，重复操作，直到逆推出的原位置和起始位置相同，
	 * 开始下一轮移动。直到最后所有移动次数结束。
	 * <p>
	 * 求最大公约数的时间复杂度O(n)，n为输入数字的位数。设最大公约数为g，则总次数应该是n+g。
	 * n为数组长度，这是必须要移动的长度,加g次是因为多了g次空间交换操作。所以最终时间复杂度为O(n)
	 */
	Object[] rightShiftWay0(Object[] org, int k) {
		Object space = null;
		if (org == null || org.length == 1) {
			return org;
		}
		int n = org.length;
		k = k % n;
		if (k == 0) {
			return org;
		}
		int groupLength = maxCommonDivisor(n, k);// 分组长度
		for (int time = 0; time < groupLength; time++) {
			int targetIndex = time;
			space = org[targetIndex];
			do {
				int startPos = (targetIndex >= k) ? (targetIndex - k) : (n + (targetIndex - k));
				if (startPos != time) {
					org[targetIndex] = org[startPos];
					targetIndex = startPos;
				} else {
					org[targetIndex] = space;
					break;
				}
			} while (true);
		}

		return org;
	}

	public static int maxCommonDivisor(int m, int n) {
		if (m < n) {// 保证m>n,若m<n,则进行数据交换
			int temp = m;
			m = n;
			n = temp;
		}
		while (m % n != 0) {// 在余数不能为0时,进行循环
			int temp = m % n;
			m = n;
			n = temp;
		}
		return n;// 返回最大公约数
	}
}
