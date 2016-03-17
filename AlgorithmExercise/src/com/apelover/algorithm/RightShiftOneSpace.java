package com.apelover.algorithm;

public class RightShiftOneSpace {

	public static void main(String[] args) {
		int n = 10, k = 2;
		Object[] orgArr = new Object[n];
		System.out.print("���齻��ǰ��");
		for (int i = 0; i < n; i++) {
			orgArr[i] = new Integer(i);
			System.out.print(orgArr[i] + " ");
		}
		System.out.print("\n");

		RightShiftOneSpace rsos = new RightShiftOneSpace();
		Object[] result = rsos.rightShiftWay1(orgArr, k);

		System.out.print("ѭ��K�����齻����");
		for (int i = 0; i < n; i++) {
			System.out.print(result[i] + " ");
		}
		System.out.print("\n");

	}

	/**
	 * ѭ��K�ν���
	 * */
	Object[] rightShiftKTimes(Object[] org, int k) {
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

	Object[] rightShiftWay1(Object[] org, int k) {
		Object space = null;
		if (org == null || org.length == 1) {
			return org;
		}
		int n = org.length;
		k = k % n;
		if (k == 0) {
			return org;
		}
		int groupLength = maxCommonDivisor(n, k);//���鳤��
		for (int time = 0; time < groupLength; time++) {
			int targetIndex = time;
			space = org[targetIndex];
			do {
				int startPos = (targetIndex >= k) ? (targetIndex - k)
						: (n + (targetIndex - k));
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
		if (m < n) {// ��֤m>n,��m<n,��������ݽ���
			int temp = m;
			m = n;
			n = temp;
		}
		while (m % n != 0) {// ����������Ϊ0ʱ,����ѭ��
			int temp = m % n;
			m = n;
			n = temp;
		}
		return n;// �������Լ��
	}
}
