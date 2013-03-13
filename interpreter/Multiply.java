/* ClassName : Multiply
 * 
 * Created by: Dharmendhar Pulakunta
 * Red ID: 816324464
 * 
 * To evaluate the multiplication of values. This the class which
 * implements the expression for multiply rules and extends Postfix expression to 
 * implement evaluate method
 */
package com.sdsu.spreadSheet.interpreter;

import java.util.Map;

public class Multiply implements PostfixExpression {
	//divide takes two operands order does not matter
	PostfixExpression leftHandVar;
	PostfixExpression rightHandVar;
	
	public Multiply(PostfixExpression leftOperand, PostfixExpression rightOperand) {
		leftHandVar = leftOperand;
		rightHandVar = rightOperand; 
	}

	@Override
	//In evaluate we are retrieving the values and multiplying them
	public double evaluate(Map<String, PostfixExpression> exp) {
		return leftHandVar.evaluate(exp) * rightHandVar.evaluate(exp);
	}

}
