package com.sdsu.spreadSheet;

import javax.swing.JTextField;

public abstract class CellState {
	protected String value = new String();
	protected String intValue = new String();
	public String getIntValue() {
		return intValue;
	}
	public void setIntValue(String intValue) {
		this.intValue = intValue;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	abstract void display(JTextField cell);

}
