/* ClassName : LogBase2
 * 
 * Created by: Dharmendhar Pulakunta
 * Red ID: 816324464
 * 
 * To evaluate the logBase2 value. This the class which implements 
 * the expression for log rules and extends Postfix expression to 
 * implement evaluate method
 */
package com.sdsu.spreadSheet.interpreter;

import java.util.Map;

//log takes only one operand so we have only one value variable
public class LogBase2 implements PostfixExpression {
	PostfixExpression variable;
	
	public LogBase2(PostfixExpression var) {
		variable = var;
	}
	@Override
	//we are find the log value and then log base 2
	public double evaluate(Map<String, PostfixExpression> exp) {
		//evaluate function call will get the value
		return (Math.log(variable.evaluate(exp))/Math.log(2));
		
	}

}
