package com.sdsu.spreadSheet;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class  TestGUI extends Observable implements Observer {
	final static JTextField t3 = new JTextField();
	public static void main(String[] args) {
		JFrame mainWindow = new JFrame("SpreadSheet");

		mainWindow.setSize(750, 100);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelToAddThings = new JPanel(new FlowLayout());


		final Observable oA = new Observable();
		final Observable oB = new Observable();
		final Observable oC = new Observable();
		
		final Observer A = new TestGUI();
		final Observer B = new TestGUI();
		final Observer C = new TestGUI();

		final JTextField t1 = new JTextField();
		t1.setColumns(5);
		final JTextField t2 = new JTextField();
		t2.setColumns(5);
		
		t3.setColumns(5);
		
		JTextField t4 = new JTextField();
		t4.setColumns(5);
		JTextField t5 = new JTextField();
		t5.setColumns(5);
		JTextField t6 = new JTextField();
		t6.setColumns(5);
		JTextField t7 = new JTextField();
		t7.setColumns(5);
		JTextField t8 = new JTextField();
		t8.setColumns(5);
		JTextField t9 = new JTextField();
		t9.setColumns(5);



		panelToAddThings.add(t1);
		panelToAddThings.add(t2);
		panelToAddThings.add(t3);
		panelToAddThings.add(t4);
		panelToAddThings.add(t5);
		panelToAddThings.add(t6);
		panelToAddThings.add(t7);
		panelToAddThings.add(t8);
		panelToAddThings.add(t9);

		
		
		t1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {				
				String valueInCell = t1.getText();
				System.out.println(valueInCell+"  "+oA.countObservers());
				
				oA.notifyObservers(valueInCell);
				
			}
		});
		
		t2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {				
				String valueInCell = t2.getText();
				System.out.println(valueInCell);
				oB.notifyObservers(valueInCell);
				
			}
		});
		
		t3.addActionListener(new ActionListener()  {

			@Override
			public void actionPerformed(ActionEvent arg0) {				
				String valueInCell = t3.getText();
				System.out.println(valueInCell);
				oA.addObserver(C);
				oB.addObserver(C);
				System.out.println("hello");
			}
		});

		JButton valueView = new JButton("Value");
		JButton eqnView = new JButton("Equation");
		
		panelToAddThings.add(valueView);
		panelToAddThings.add(eqnView);
		
		
		
		mainWindow.add(panelToAddThings);
		mainWindow.setVisible(true);
	}



	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Trying to check if this is getting called");
		
		t3.setText(arg.toString());
	}


}
