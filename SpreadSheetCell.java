package com.sdsu.spreadSheet;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
	String newValue = new String();


	JTextField cell = new JTextField();
	public String value = new String();
	public String intVal = new String();
	ArrayList<SpreadSheetCell> observers = new ArrayList<SpreadSheetCell>();
	Map<String,SpreadSheetCell> cellValues = new HashMap<String, SpreadSheetCell>();
	static ArrayList<SpreadSheetCell> visited = new ArrayList<SpreadSheetCell>();
	CommandManager commandController;



	public SpreadSheetCell(Map<String, SpreadSheetCell> nameToValMap, CommandManager cmdController) {
		cellValues = nameToValMap;
		cell.addActionListener(textEntered);
		commandController = cmdController;
		currState = eqnState;
	}


	Observer listener = new Observer() {

		@Override
		public void update(Observable o, Object arg) {
			//String updateVal = ((SpreadSheetCell)arg).intVal; 
			Double result = parseExp(value);
			intVal=result.toString();
			System.out.println("Re-eval:"+result.toString());
			setChanged();
			notifyObservers(this);
			clearChanged();
			display();
		}

	};
	Observable subject = new Observable();


	ActionListener textEntered = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			newValue = cell.getText();
			performCommand();
		}

	};
	private void performCommand(){
		commandController.executeCommand(new EnterPressedCommand(this));
	}

	public void updateCell(String currValue){
		System.out.println(currValue);
		if(isDigit(currValue)){
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
		System.out.println("No.. of observers: " +countObservers());

	}

	private double parseExp(String expression){
		SpreadSheetCell currentCell = null;
		ExpressionParser sentence = new ExpressionParser(expression);
		Map<String,PostfixExpression> variables = new HashMap<String,PostfixExpression>();
		for (String token : expression.split(" ")) {
			if(token.equals("+") || token.equals("-")||token.equals("*")|| token.equals("/") ||token.equals("log") || token.equals("cos") )
				continue;
			currentCell = cellValues.get(token);
			variables.put(token, new Number(Double.parseDouble(currentCell.intVal)));
			currentCell.observers.add(this);
			visited.clear();
			checkCyclic();
			currentCell.addObserver(listener);
			
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
					System.exit(0);
				}
				visited.add(currentCell);
				currentCell.checkCyclic();
			}
		}
		
	}

	private boolean isDigit(String input){
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
		if(currState instanceof EquationState){
			for (SpreadSheetCell eqnV : cellValues.values()) {
				if(!eqnV.value.equals("")){
					eqnV.cell.setText(eqnV.value);
				}
			}
		}
		else{
			for (SpreadSheetCell eqnV : cellValues.values()) {
				if(!eqnV.intVal.equals("")){
					eqnV.cell.setText(eqnV.intVal);
				}
			}
		}
	}
}