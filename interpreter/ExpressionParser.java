package com.sdsu.spreadSheet.interpreter;

import java.util.Map;
import java.util.Stack;


public class ExpressionParser implements PostfixExpression {
	private PostfixExpression syntaxTree;

	public ExpressionParser(String expression) {
		Stack<PostfixExpression> expressionStack = new Stack<PostfixExpression>();
		for (String token : expression.split(" ")) {
			if  (token.equals("+")) {
				PostfixExpression right = expressionStack.pop();
				PostfixExpression left = expressionStack.pop();
				PostfixExpression subExpression = new Plus(left, right);
				expressionStack.push( subExpression );
			}
			else if (token.equals("-")) {
				PostfixExpression right = expressionStack.pop();
				PostfixExpression left = expressionStack.pop();
				PostfixExpression subExpression = new Minus(left, right);
				expressionStack.push( subExpression );
			}
			else if (token.equals("*")) {
				PostfixExpression right = expressionStack.pop();
				PostfixExpression left = expressionStack.pop();
				PostfixExpression subExpression = new Multiply(left, right);
				expressionStack.push( subExpression );
			}
			else if (token.equals("/")) {
				PostfixExpression right = expressionStack.pop();
				PostfixExpression left = expressionStack.pop();
				PostfixExpression subExpression = new Divide(left, right);
				expressionStack.push( subExpression );
			}
			else if (token.equals("log")) {
				PostfixExpression operand = expressionStack.pop();
				PostfixExpression subExpression = new LogBase2(operand);
				expressionStack.push(subExpression);
			}
			else if (token.equals("cos")) {
				PostfixExpression operand = expressionStack.pop();
				PostfixExpression subExpression = new Cosine(operand);
				expressionStack.push(subExpression);
			}
			else if(isNumeric(token)){
				expressionStack.push(new Number(token));
			}
			else   {    
				expressionStack.push( new Variable(token) );
			}
		}
		syntaxTree = expressionStack.pop();
	}
	private boolean isNumeric(String input) {
		try {
			//Integer.parseInt(input);
			Double.parseDouble(input);
			return true;
		}
		catch (NumberFormatException e) {
			// s is not numeric
			return false;
		}
	}
	@Override
	public double evaluate(Map<String, PostfixExpression> variables) {
		return syntaxTree.evaluate(variables);
	}
}
