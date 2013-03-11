package com.sdsu.spreadSheet.interpreter;

import java.util.Map;


public class Variable implements PostfixExpression {
	String name;
	public Variable(String varName) {
		name = varName;
	}

	@Override
	public double evaluate(Map<String, PostfixExpression> variables) {
		if(null==variables.get(name)) return 0; //Either return new Number(0).
        return variables.get(name).evaluate(variables); 
	}

}
