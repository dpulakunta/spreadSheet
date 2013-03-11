/* ClassName : Cosine
 * 
 * Created by: Dharmendhar Pulakunta
 * Red ID: 816324464
 * 
 * To evaluate the Cosine value in radians. This the class which
 * implements the expression for cos rules and extends Postfix expression to 
 * implement evaluate method
 */
package com.sdsu.spreadSheet.interpreter;

import java.util.Map;

//cosine takes only one operand so we have only one value variable
public class Cosine implements PostfixExpression {

	PostfixExpression variable;
	public Cosine(PostfixExpression var) {
		variable = var;
	}

	@Override
	public double evaluate(Map<String, PostfixExpression> exp) {
		//evaluate function call will get the value
		return (Math.cos(variable.evaluate(exp)));
	}

}
