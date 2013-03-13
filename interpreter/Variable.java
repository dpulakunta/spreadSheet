/* ClassName : Variable
 * 
 * Created by: Dharmendhar Pulakunta
 * Red ID: 816324464
 * 
 * This class is for operating on variables we return the variable value
 * and extends Postfix expression to implement evaluate method
 */
package com.sdsu.spreadSheet.interpreter;

import java.util.Map;


public class Variable implements PostfixExpression {
	String name;
	public Variable(String varName) {
		name = varName;
	}

	@Override
	
	public double evaluate(Map<String, PostfixExpression> variables) {
		//variable does not have value we are setting it has 0
		if(null==variables.get(name)) return 0; 
        return variables.get(name).evaluate(variables); 
	}

}
