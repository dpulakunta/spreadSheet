/* ClassName : PostfixExpression
 * 
 * Created by: Dharmendhar Pulakunta
 * Red ID: 816324464
 * 
 */
package com.sdsu.spreadSheet.interpreter;
import java.util.Map;

public interface PostfixExpression {
	// this implemented by each of the symbol in the rules
	public double evaluate (Map<String,PostfixExpression> variables);
}
