/* ClassName : Divide
 * 
 * Created by: Dharmendhar Pulakunta
 * Red ID: 816324464
 * 
 * To evaluate the Divison of values. This the class which
 * implements the expression for divide rules and extends Postfix expression to 
 * implement evaluate method
 */
package com.sdsu.spreadSheet.interpreter;

import java.util.Map;

//divide takes two operands it is important to store them correctly
public class Divide implements PostfixExpression {
	PostfixExpression leftHandVar;
	PostfixExpression rightHandVar;
	
	public Divide(PostfixExpression leftOperand, PostfixExpression rightOperand) {
		leftHandVar = leftOperand;
		rightHandVar = rightOperand; 
	}

	@Override
	//each of the evaluate fn calls will get the value for each expression
	public double evaluate(Map<String, PostfixExpression> exp) {
		return leftHandVar.evaluate(exp) / rightHandVar.evaluate(exp);
	}

}
