/* ClassName : PostfixExpTest
 * 
 * Created by: Dharmendhar Pulakunta
 * Red ID: 816324464
 * This is junit test class.
 * checking the interpreter pattern and postfix expression working 
 */

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

		//Test 2: divison of numbers with variables
		expression = "2 c /";
		sentence = new ExpressionParser(expression);
		result = sentence.evaluate(variables);
		assertEquals(0.25, result,DELTA);

		//Test 3: performing cos addition and multiplication
		expression = "1967 21 + 3 cos *";
		sentence = new ExpressionParser(expression);
		result = sentence.evaluate(variables);
		assertEquals(-1968.1050832416854, result,DELTA);

		//Test 3: performing log subtraction and multiplication
		expression = "x w - 8 log *";
		sentence = new ExpressionParser(expression);
		result = sentence.evaluate(variables);
		assertEquals(15, result,DELTA);

		//Test 4: log 0
		expression = "0 log";
		sentence = new ExpressionParser(expression);
		result = sentence.evaluate(variables);
		assertEquals(Double.NEGATIVE_INFINITY, result,DELTA);
	}
}
