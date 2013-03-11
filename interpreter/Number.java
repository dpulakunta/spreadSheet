package com.sdsu.spreadSheet.interpreter;

import java.util.Map;

public class Number implements PostfixExpression {

	double number;
	public Number(int num) {
		number = num;
	}
	public Number(String num) {
		number = Integer.parseInt(num);
	}
	public Number(double n) {
		number = n;
	}
	@Override
	public double evaluate(Map<String, PostfixExpression> variables) {
		// TODO Auto-generated method stub
		return number;
	}

}
