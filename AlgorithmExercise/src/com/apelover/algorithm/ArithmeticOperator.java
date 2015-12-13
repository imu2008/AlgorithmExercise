package com.apelover.algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * ������������֧�����ŵ��������㣩
 * <p>
 * ���ܣ�����һ�������ŵ�����������ʽ������ֵ
 * <p>
 * �㷨ʵ�֣�1������׺���ʽʹ��ջת��Ϊ��׺���ʽ�����沨�����ʽ��2��ʹ��ջ���沨�����ʽ���м���
 */
public class ArithmeticOperator {

	private String expression;

	private static final Set<Character> OPERATORS = new HashSet<>(
			Arrays.asList(new Character[] { '+', '-', '*', '/' }));

	private Double result;

	public ArithmeticOperator(String expr) {
		this.expression = expr;
	}

	public void compute() {
		String suffix = infix2Suffix(expression);
		computeSuffixExpr(suffix);
	}

	public Double computeSuffixExpr(String suffix) {
		String[] split = suffix.split(" ");
		Stack<String> operandStack = new Stack<>();
		for (String ele : split) {
			if(ele.equals("")){
				continue;
			}
			char c = ele.charAt(0);
			if (ele.length() > 1 || !OPERATORS.contains(c)) {
				operandStack.add(ele);
			} else {
				result = compute(operandStack.pop(), operandStack.pop(), c);
				operandStack.push(result.toString());
			}
		}
		return result;
	}

	private double compute(String rOperand, String lOperand, Character operator) {
		try {
			Double rV = Double.valueOf(rOperand);
			Double lV = Double.valueOf(lOperand);
			switch (operator) {
			case '+': {
				return lV + rV;
			}
			case '-': {
				return lV - rV;
			}
			case '*': {
				return lV * rV;
			}
			case '/': {
				return lV / rV;
			}
			default:
				throw new UnsupportedOperationException("��֧�ֵ������" + operator);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String infix2Suffix(String infix) {
		StringBuilder builder = new StringBuilder();
		Stack<Character> operStack = new Stack<>();
		String operand = "";
		for (int i = 0, length = infix.length(); i < length; i++) {
			char c = infix.charAt(i);
			if (c == ' ') {
				continue;
			}
			int priority = getPriority(c);
			if (priority == -1 && c != ')') {/** ������ */
				operand += c;
			} else {/** ������ */
				builder.append(operand).append(" ");
				operand = "";
				if (c == ')') {/** ���� �����ţ���ջ�˵������� */
					while (!operStack.isEmpty()) {
						Character topOperator = operStack.pop();
						if (topOperator == '(') {
							break;
						}
						builder.append(topOperator).append(" ");
					}
				} else {/** �������������������ȼ������ȼ�����ͣ�����ջ�����У�����ߣ�������ջ */
					while (!operStack.isEmpty()) {
						Character topOperator = operStack.peek();
						int topPrio = getPriority(topOperator);
						if (priority > topPrio || topOperator == '(') {
							break;
						} else {
							builder.append(topOperator).append(" ");
							operStack.pop();
						}
					}
					operStack.push(c);
				}
			}
		}
		if (!operand.equals("")) {
			builder.append(operand).append(" ");
			operand = "";
		}
		while (!operStack.isEmpty()) {
			builder.append(operStack.pop()).append(" ");
		}

		return builder.toString();
	}

	private int getPriority(Character character) {
		switch (character) {
		case '+':
		case '-':
			return 0;
		case '*':
		case '/':
			return 1;
		case '(':
			return Integer.MAX_VALUE;
		default:
			break;
		}
		return -1;
	}

	public double getResult() {
		if (result == null) {
			compute();
		}
		return result;
	}

	public static void main(String[] args) {
		String expr = "12/(20/4-3*5)+5";
		ArithmeticOperator ao = new ArithmeticOperator(expr);
		ao.compute();
		System.out.println(expr + " = " + ao.getResult());
	}

}
