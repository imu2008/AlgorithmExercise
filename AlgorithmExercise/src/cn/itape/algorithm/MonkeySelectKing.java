package cn.itape.algorithm;

import java.util.LinkedList;

/**
 * 山上有m只猴子要选大王,选举办法如下：所有猴子从1到m进行编号并围坐一圈,从第一号开始按顺序1,2,...n继续报数,凡是报n号的猴子都退出到圈外,
 * 照此循环报数,直到圈内只剩下一只猴子时,这只猴子就是大王.输出大王的编号。
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
				System.err.println("输入参数有误！");
			}
		} else {
			System.err.println("输入参数有误！");
		}

	}

	private static void select(int monkeys, int interval) {
		// 存放所有猴子
		LinkedList<Integer> monkeyList = new LinkedList<>();
		for (int i = 1; i <= monkeys; i++) {
			monkeyList.add(i);
		}

		int start = 0; // 起始位置
		while (monkeyList.size() > 1) {// 当只有1只猴子时停止
			start = start + interval;
			int size = monkeyList.size();
			if (start > size) { // 如果起始位置大于当前猴子个数，则需要反复多遍，求余可以立即算出是第几只猴子
				start = start % size;
			}
			if (start == 0) { // 要注意，如果求余的结果刚好是0，说明刚好够数整圈，所以，需要删除的是最后一只猴子
				start = size;
			}
			// start-1表示要删除的猴子。删除start-1这只猴子后，start减少1是因为猴子少了一个，所以我们总计起始位置时需要减1
			System.out.println("remove:" + monkeyList.remove(--start)); 
		}
		System.out.println("选出来的大王是：" + monkeyList.get(0));
	}
}
