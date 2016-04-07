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
	 * <p>
	 * ѭ�����Ƶڶ��ַ�����
	 * <p>
	 * ѭ��K�ν���
	 * <p>
	 * �ʱ�临�Ӷȣ�n*k(����k=n-1)
	 * <p>
	 * ƽ�����Ӷȣ�����k��n����󣬶���0<=k<n����k�����Ǿ��ȵģ����ԣ�ÿ��k�ĸ���Ϊ1/n��ÿһ�ֵĵ�������Ϊn-1.
	 * �ܴ���Ϊ ��1/n*((1*(n-1)) + 2*(n-1)+...+(n-1)*(n-1))=1/n*(n-1)*(1+2+n-1) = (n-1)*(n-1)/2
	 * <p>ƽ�����Ӷ�ΪO(n*n)
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
	 * ѭ�����Ƶ�һ���㷨������k��n�����Լ���������ƶ�������
	 * ÿһ���ƶ������ӵ�ǰλ�ÿ�ʼ�����Ƽ���ԭλ�ã���ԭλ���ƶ����ݵ���ǰλ�ã�Ȼ����ԭλ��Ϊ��ǰ���ظ�������ֱ�����Ƴ���ԭλ�ú���ʼλ����ͬ��
	 * ��ʼ��һ���ƶ���ֱ����������ƶ�����������
	 * <p>
	 * �����Լ����ʱ�临�Ӷ�O(n)��nΪ�������ֵ�λ���������Լ��Ϊg�����ܴ���Ӧ����n+g��
	 * nΪ���鳤�ȣ����Ǳ���Ҫ�ƶ��ĳ���,��g������Ϊ����g�οռ佻����������������ʱ�临�Ӷ�ΪO(n)
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
		int groupLength = maxCommonDivisor(n, k);// ���鳤��
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
