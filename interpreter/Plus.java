/* ClassName : Plus
 * 
 * Created by: Dharmendhar Pulakunta
 * Red ID: 816324464
 * 
 * to evaluate the addition rules, we get the values for leftVar and rightVar
 * and the values obtained.
 */
package com.sdsu.spreadSheet.interpreter;

import java.util.Map;

public class Plus implements PostfixExpression {
	PostfixExpression leftHandVar;
	PostfixExpression rightHandVar;
	
	public Plus(PostfixExpression leftOperand, PostfixExpression rightOperand) {
		leftHandVar = leftOperand;
		rightHandVar = rightOperand; 
	}

	@Override
	public double evaluate(Map<String, PostfixExpression> exp) {
		return leftHandVar.evaluate(exp) + rightHandVar.evaluate(exp);
	}

}
