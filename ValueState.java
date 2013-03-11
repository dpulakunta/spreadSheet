package com.sdsu.spreadSheet;

import javax.swing.JTextField;

public class ValueState extends CellState {

	@Override
	public void display(JTextField cell) {
		System.out.println("We will display the values");
		if(!getValue().equals(""))
			cell.setText(getValue());
	}

}
