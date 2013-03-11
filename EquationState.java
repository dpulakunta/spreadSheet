package com.sdsu.spreadSheet;

import javax.swing.JTextField;

public class EquationState extends CellState {


	@Override
	public void display(JTextField cell) {
		System.out.println("We will display the eqn");
		if(!getValue().equals(""))
			cell.setText(getValue());
	}

}
