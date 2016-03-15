package com.apelover.algorithm;

public class RightShiftOneSpace {

	public static void main(String[] args) {
		int n = 10, k = 12;
		Object[] orgArr = new Object[n];
		System.out.print("���齻��ǰ��");
		for (int i = 0; i < n; i++) {
			orgArr[i] = new Integer(i);
			System.out.print(orgArr[i] + " ");
		}
		System.out.print("\n");

		RightShiftOneSpace rsos = new RightShiftOneSpace();
		Object[] result = rsos.rightShiftKTimes(orgArr, k);

		System.out.print("ѭ��K�����齻����");
		for (int i = 0; i < n; i++) {
			System.out.print(result[i] + " ");
		}
		System.out.print("\n");

	}

	/**
	 * ѭ��K�ν���
	 * */
	private Object[] rightShiftKTimes(Object[] org, int k) {
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

	/**�ܷ� ������*/
	private Object[] rightShift(Object[] org, int k) {
		Object space = null;
		if (org == null || org.length == 1) {
			return org;
		}
		int n = org.length;
		if (k % n == 0) {
			return org;
		}
		k = k % n;
		space = org[0];
		int i = 0;
		do {
			int startPos = (i >= k) ? (i - k) : (n + (i - k));
			if (startPos != 0) {
				org[i] = org[startPos];
				i = startPos;
			} else {
				org[i] = space;
				break;/**�˳���������*/
			}
		} while (true);
		return org;
	}
}
