package com.sdsu.spreadSheet;

import java.awt.event.ActionListener;
import java.util.Map;


public class EnterPressedCommand extends UndoableCommand {
	private SpreadSheetCell currCell;
	private String previousVal;
	private String newValue;
	
	

	public EnterPressedCommand(SpreadSheetCell cell) {
		currCell = cell;
		if(currCell.value.equals("")){
			previousVal = currCell.intVal;
		}
		else{
			previousVal = currCell.value;
		}
		newValue = currCell.newValue;
	}

	@Override
	public void undo() {
		if(previousVal.equals(null))
			return;
		currCell.updateCell(previousVal);
		
	}

	@Override
	public void execute() {
		currCell.updateCell(newValue);
		
	}

}
