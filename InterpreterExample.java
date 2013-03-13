package com.sdsu.spreadSheet;
import java.util.Map;
import java.util.HashMap;

import com.sdsu.spreadSheet.interpreter.ExpressionParser;
import com.sdsu.spreadSheet.interpreter.Number;
import com.sdsu.spreadSheet.interpreter.PostfixExpression;
 
public class InterpreterExample {
    public static void main(String[] args) {
        String expression = "w x + c + ";
        ExpressionParser sentence = new ExpressionParser(expression);
        Map<String,PostfixExpression> variables = new HashMap<String,PostfixExpression>();
        variables.put("w", new Number(5));
        variables.put("x", new Number(10));
        variables.put("c", new Number(8));
        
        double result = sentence.evaluate(variables);
        System.out.println("Answer:"+result);
        SpreadSheet spreadSheetGui = new SpreadSheet();
        spreadSheetGui.showGui();

    }
}
