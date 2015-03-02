package cn.itape.algorithm;

import java.util.LinkedList;

/**
 * ɽ����mֻ����Ҫѡ����,ѡ�ٰ취���£����к��Ӵ�1��m���б�Ų�Χ��һȦ,�ӵ�һ�ſ�ʼ��˳��1,2,...n��������,���Ǳ�n�ŵĺ��Ӷ��˳���Ȧ��,
 * �մ�ѭ������,ֱ��Ȧ��ֻʣ��һֻ����ʱ,��ֻ���Ӿ��Ǵ���.��������ı�š�
 * 
 * @author winger
 *
 */
public class MonkeySelectKing {

	public static void main(String[] args) {
		if (args != null && args.length == 2) {
			int monkeys = Integer.valueOf(args[0]);
			int interval = Integer.valueOf(args[1]);
			if (monkeys > 1 && interval > 1) {
				select(monkeys, interval);
			} else {
				System.err.println("�����������");
			}
		} else {
			System.err.println("�����������");
		}

	}

	private static void select(int monkeys, int interval) {
		// ������к���
		LinkedList<Integer> monkeyList = new LinkedList<>();
		for (int i = 1; i <= monkeys; i++) {
			monkeyList.add(i);
		}

		int start = 0; // ��ʼλ��
		while (monkeyList.size() > 1) {// ��ֻ��1ֻ����ʱֹͣ
			start = start + interval;
			int size = monkeyList.size();
			if (start > size) { // �����ʼλ�ô��ڵ�ǰ���Ӹ���������Ҫ������飬���������������ǵڼ�ֻ����
				start = start % size;
			}
			if (start == 0) { // Ҫע�⣬�������Ľ���պ���0��˵���պù�����Ȧ�����ԣ���Ҫɾ���������һֻ����
				start = size;
			}
			// start-1��ʾҪɾ���ĺ��ӡ�ɾ��start-1��ֻ���Ӻ�start����1����Ϊ��������һ�������������ܼ���ʼλ��ʱ��Ҫ��1
			System.out.println("remove:" + monkeyList.remove(--start)); 
		}
		System.out.println("ѡ�����Ĵ����ǣ�" + monkeyList.get(0));
	}
}
