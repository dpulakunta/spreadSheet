/* ClassName : EnterPressedCommand
 * 
 * Created by: Dharmendhar Pulakunta
 * Red ID: 816324464
 * 
 * Implementing the command for undo we are storing the previous cell
 * which was changed and the previous value maybe expression or integer
 */
package com.sdsu.spreadSheet;

public class EnterPressedCommand extends UndoAbleCommand {
	private SpreadSheetCell currCell;
	private String previousVal;
	private String newValue;
	
	public EnterPressedCommand(SpreadSheetCell cell) {
		currCell = cell;
		if(currCell.getValue().equals("")){
			previousVal = currCell.getIntVal();
		}
		else{
			previousVal = currCell.getValue();
		}
		newValue = currCell.getNewValue();
	}

	@Override
	public void undo() {
		currCell.updateCell(previousVal);
	}

	@Override
	public void execute() {
		currCell.updateCell(newValue);		
	}
}
