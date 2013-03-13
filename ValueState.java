/* ClassName : ValueState
 * 
 * Created by: Dharmendhar Pulakunta
 * Red ID: 816324464
 * class implementing the interface CellState
 * we print the numerical value of the cell.
 */
package com.sdsu.spreadSheet;

public class ValueState implements CellState {

	@Override
	public void display(SpreadSheetCell completeCell) {
		if(!completeCell.getIntVal().equals(""))
			completeCell.cell.setText(completeCell.getIntVal());
	}
}
