package com.sdsu.spreadSheet.interpreter;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;


public class PostfixExpTest {
	private static final double DELTA = 1e-15;
	@Test
	public void test() {
		
		String expression = "w x + c + ";
        ExpressionParser sentence = new ExpressionParser(expression);
        Map<String,PostfixExpression> variables = new HashMap<String,PostfixExpression>();
        variables.put("w", new Number(5));
        variables.put("x", new Number(10));
        variables.put("c", new Number(8));
        
        //Test 1: addition of numbers with variables
        double result = sentence.evaluate(variables);
		assertEquals(23.0, result,DELTA);
		
		//Test : multiplication of numbers with variables
		expression = "2 c *";
		sentence = new ExpressionParser(expression);
        result = sentence.evaluate(variables);
		assertEquals(16.0, result,DELTA);
	}

}
