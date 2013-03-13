package com.sdsu.spreadSheet;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class SpreadSheet {
	JFrame mainWindow = new JFrame("SpreadSheet");
	JPanel panelToAddThings = new JPanel(new FlowLayout());
	SpreadSheetCell[] nineCells = new SpreadSheetCell[9];
	JButton equationViewButton = new JButton("Equation");
	JButton valueViewButton = new JButton("Value");
	JButton undoButton = new JButton("Undo");

	Map<String,SpreadSheetCell> cellValues = new HashMap<String, SpreadSheetCell>();
	CommandManager commandController = new CommandManager();

	void showGui(){

		mainWindow.setSize(750, 100);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		insertCells();

		panelToAddThings.add(valueViewButton);
		panelToAddThings.add(equationViewButton);
		panelToAddThings.add(undoButton);
		
		equationViewButton.addActionListener(buttonPressed);
		valueViewButton.addActionListener(buttonPressed);
		undoButton.addActionListener(buttonPressed);

		mainWindow.add(panelToAddThings);
		mainWindow.setVisible(true);
	}

	private void insertCells(){
		char alpha = 'A';
		for(int i=0;i<9;i++){
			nineCells[i] = new SpreadSheetCell(cellValues,commandController);
			nineCells[i].cell.setColumns(5);
			panelToAddThings.add(nineCells[i].cell);
			cellValues.put("$"+alpha, nineCells[i]);
			alpha++;
		}
	}
	
	ActionListener buttonPressed = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent pressed) {
			String buttonPressed = pressed.getSource().toString();
			operationToPerform(buttonPressed);
		}

	};
	public void operationToPerform(String buttonPressed) {
		if(buttonPressed.contains("Equation")){
			System.out.println("Equation View will be showed");
			for(int i=0;i<9;i++){
				nineCells[i].setState("equation"); 
			}
			nineCells[0].display();
		}else if(buttonPressed.contains("Value")){
			System.out.println("Value View will be showed");
			for(int i=0;i<9;i++){
				nineCells[i].setState("valState");
			}
			nineCells[0].display();
		}else{
			commandController.undo();
		}
		
	}
}
