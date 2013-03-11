package com.sdsu.spreadSheet;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SpreadSheet {
	JFrame mainWindow = new JFrame("SpreadSheet");
	JPanel panelToAddThings = new JPanel(new FlowLayout());
	SpreadSheetCell[] nineCells = new SpreadSheetCell[9];
	JButton equationViewButton = new JButton("Equation");
	JButton valueViewButton = new JButton("Value");
	JButton undoButton = new JButton("Undo");
	CellState eqnState = new EquationState();
	CellState valState = new ValueState();
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
			String buttonWaspressedBy = pressed.getSource().toString();
			if(buttonWaspressedBy.contains("Equation")){
				System.out.println("Equation View will be showed");
				for(int i=0;i<9;i++){
					nineCells[i].setState("equation"); 
					nineCells[i].display();
				}
				
			}else if(buttonWaspressedBy.contains("Value")){
				System.out.println("Value View will be showed");
				for(int i=0;i<9;i++){
					nineCells[i].setState("valState");
					nineCells[i].display();
				}
			}else{
				commandController.undo();
			}
		}
	};
}
