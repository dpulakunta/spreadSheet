/* ClassName : Number
 * 
 * Created by: Dharmendhar Pulakunta
 * Red ID: 816324464
 * 
 * This class is for operating on numbers we just return the value
 * and extends Postfix expression to implement evaluate method
 */
package com.sdsu.spreadSheet.interpreter;

import java.util.Map;

public class Number implements PostfixExpression {

	double number;
	public Number(String num) {
		number = Double.parseDouble(num);
	}
	public Number(double n) {
		number = n;
	}
	@Override
	public double evaluate(Map<String, PostfixExpression> variables) {
		return number;
	}

}
