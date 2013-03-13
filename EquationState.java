/* ClassName : EquationState
 * 
 * Created by: Dharmendhar Pulakunta
 * Red ID: 816324464
 * class implementing the interface CellState
 * we print the expression value of the cell.
 */
package com.sdsu.spreadSheet;

public class EquationState implements CellState {
	
	@Override
	public void display(SpreadSheetCell completeCell) {
		if(!completeCell.getValue().equals(""))
			completeCell.cell.setText(completeCell.getValue());
	}
}
