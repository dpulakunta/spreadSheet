/* ClassName : SpreadSheetCell
 * 
 * Created by: Dharmendhar Pulakunta
 * Red ID: 816324464
 * 
 * This is the main class with all the functionality
 * It acts both has observer and subject 
 * 
 */
package com.sdsu.spreadSheet;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextField;
import com.sdsu.spreadSheet.interpreter.ExpressionParser;
import com.sdsu.spreadSheet.interpreter.Number;
import com.sdsu.spreadSheet.interpreter.PostfixExpression;

public class SpreadSheetCell extends Observable {

	CellState eqnState = new EquationState();
	CellState valState = new ValueState();
	CellState currState;

	JTextField cell = new JTextField();

	private String value = new String();
	private String intVal = new String();
	private String newValue = new String();
	
	Map<String,SpreadSheetCell> otherCellValues = new HashMap<String, SpreadSheetCell>();

	ArrayList<SpreadSheetCell> observers = new ArrayList<SpreadSheetCell>();
	static ArrayList<SpreadSheetCell> visited = new ArrayList<SpreadSheetCell>();
	CommandManager commandController;
	

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIntVal() {
		return intVal;
	}

	public void setIntVal(String intVal) {
		this.intVal = intVal;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}


	public SpreadSheetCell(Map<String, SpreadSheetCell> nameToValMap, CommandManager cmdController) {
		otherCellValues = nameToValMap;
		cell.addActionListener(textEntered);
		commandController = cmdController;
		currState = eqnState;
	}


	Observer listener = new Observer() {
		@Override
		public void update(Observable o, Object arg) { 
			Double result = parseExp(value);
			intVal=result.toString();
			setChanged();
			notifyObservers(this);
			clearChanged();
			display();
		}
	};

	ActionListener textEntered = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			newValue = cell.getText();
			performCommand();
		}
	};
	
	public void performCommand(){
		commandController.executeCommand(new EnterPressedCommand(this));
	}

	public void updateCell(String currValue){
		System.out.println(currValue);
		if(isNumeric(currValue)){
			intVal = currValue;
		}else{
			value = currValue;
			deleteObservers();
			Double result = parseExp(value);
			intVal=result.toString();
		}
		setChanged();
		notifyObservers(this);
		clearChanged();
	}

	private double parseExp(String expression){
		SpreadSheetCell currentCell = null;
		ExpressionParser sentence = new ExpressionParser(expression);
		Map<String,PostfixExpression> variables = new HashMap<String,PostfixExpression>();
		for (String token : expression.split(" ")) {
			if(token.equals("+") || token.equals("-")||token.equals("*")|| token.equals("/") ||token.equals("log") || token.equals("cos") )
				continue;
			currentCell = otherCellValues.get(token);
			if(!token.equals("")){
				if( !isNumeric(token)){
					variables.put(token, new Number(Double.parseDouble(currentCell.intVal)));
					currentCell.observers.add(this);
					visited.clear();
					checkCyclic();
					currentCell.addObserver(listener);
				}
			}
		}
		Double result = sentence.evaluate(variables);
		return result;
	}

	private void checkCyclic() {
		if(observers.isEmpty())
			return;
		else{
			for(SpreadSheetCell currentCell: observers){
				if(visited.contains(currentCell)){
					for(SpreadSheetCell visitedCells:visited){
						visitedCells.setIntVal("error");
					}
					return;
				}
				visited.add(currentCell);
				currentCell.checkCyclic();
			}
		}
	}

	private boolean isNumeric(String input){
		try { 
			Double.parseDouble(input);
		} 
		catch(NumberFormatException e) { 
			return false;
		}
		return true;
	}


	public void setState(CellState presentState){
		currState = presentState;
	}

	public void setState(String presentState){
		if(presentState.equals("equation"))
			setState(eqnState);
		else
			setState(valState);
	}

	public void display(){
		for (SpreadSheetCell eqnV : otherCellValues.values()) {
			currState.display(eqnV);
		}
	}
}