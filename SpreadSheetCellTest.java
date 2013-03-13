/* ClassName : SpreadSheetCellTest
 * 
 * Created by: Dharmendhar Pulakunta
 * Red ID: 816324464
 * 
 * This is to test the working of the spreadSheet JtestCases
 */
package com.sdsu.spreadSheet;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * @author Dharmendhar Pulakunta
 *
 */
public class SpreadSheetCellTest {
	SpreadSheetCell[] nineCells = new SpreadSheetCell[9];
	Map<String,SpreadSheetCell> cellValues = new HashMap<String, SpreadSheetCell>();
	CommandManager commandController = new CommandManager();
	SpreadSheet sheet = new SpreadSheet();
	//private static final double DELTA = 1e-15;
	public static final String  setValueState = "valState";
	public static final String  setEquationState = "Equation";
	public static final String  undo = "undo";
	@Test
	public void test() {
		createCells();

		//Test 1
		nineCells[0].setNewValue("1");
		nineCells[0].performCommand();
		nineCells[1].setNewValue("5");
		nineCells[1].performCommand();
		nineCells[2].setNewValue("$A $B +");
		nineCells[2].performCommand();
		sheet.operationToPerform(setValueState);
		String res = nineCells[2].getIntVal();
		assertEquals("6.0",res);

		//Testing update(observer)
		nineCells[0].setNewValue("3");
		nineCells[0].performCommand();
		res = nineCells[2].getIntVal();
		assertEquals("8.0",res);

		//Testing update 
		nineCells[1].setNewValue("8");
		nineCells[1].performCommand();
		res = nineCells[2].getIntVal();
		assertEquals("11.0",res);

		//changing the equation and states pattern
		sheet.operationToPerform(setEquationState);
		nineCells[2].setNewValue("$A $B *");
		nineCells[2].performCommand();
		sheet.operationToPerform(setValueState);
		res = nineCells[2].getIntVal();
		assertEquals("24.0",res);


		//Checking undo
		sheet.operationToPerform(undo);
		res = nineCells[2].getIntVal();
		//changing the equation back to $A $B +, $A=3 $B=8
		assertEquals("11.0",res);

		//trying for unlimited undo
		sheet.operationToPerform(undo);
		commandController.undo();
		res = nineCells[2].getIntVal();
		String res1 = nineCells[1].getIntVal();
		//undo changes $B = 5 so $C = 8 (observer is tested) 
		assertEquals("5",res1);
		assertEquals("8.0",res);

		sheet.operationToPerform(undo);
		commandController.undo();
		res = nineCells[2].getIntVal();
		res1 = nineCells[0].getIntVal();
		assertEquals("6.0",res);
		assertEquals("1",res1);

		sheet.operationToPerform(undo);
		commandController.undo();
		res = nineCells[2].getIntVal();
		res1 = nineCells[1].getIntVal();
		assertEquals("1.0",res);
		assertEquals("0.0",res1);

		sheet.operationToPerform(undo);
		commandController.undo();
		res = nineCells[2].getIntVal();
		res1 = nineCells[0].getIntVal();
		assertEquals("0.0",res);
		assertEquals("0.0",res1);

		//check for cyclic
		nineCells[0].setIntVal("1");
		nineCells[0].performCommand();
		sheet.operationToPerform(setEquationState);
		nineCells[1].setNewValue("$A $C +");
		nineCells[1].performCommand();
		nineCells[3].setNewValue("$B 2 *");
		nineCells[3].performCommand();
		nineCells[2].setNewValue("$D 1 +");
		nineCells[2].performCommand();
		sheet.operationToPerform(setValueState);
		res = nineCells[0].getIntVal();
		assertEquals("1.0", res);
		res1 = nineCells[1].getIntVal();
		assertEquals("error", res1);
		String res2 = nineCells[2].getIntVal();
		assertEquals("error", res2);
		String res3 = nineCells[3].getIntVal();
		assertEquals("error", res3);

	}

	private void createCells() {
		char alpha = 'A';
		for(int i=0;i<9;i++){
			nineCells[i] = new SpreadSheetCell(cellValues,commandController);
			cellValues.put("$"+alpha, nineCells[i]);
			alpha++;
		}

	}

}
