package CalculatorPackage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


// TODO: Support for floats instead of only ints
// TODO: After typing a number, an operator, the second number and then another operator we need to clear the TextField and make sure to first perform the first operation to later perform the second one 
// TODO: Support for the other button such as % . +- and B
public class Calculator implements ActionListener{
	JTextField results;
	String numberInResults;
	String operator;
	String secondNumber;
	Boolean operationInProgress;
	
	// Constructor of our class
	public Calculator() {
		numberInResults = "";
		operationInProgress = false;
		
		// Setting up the frame - creating frame, setSize, setCloseOperation, setVisible
		JFrame calculatorFrame = new JFrame("La Calculadora");
		calculatorFrame.setLayout(new BorderLayout());
		calculatorFrame.setSize(400, 600);
		calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calculatorFrame.setVisible(true);
		
		// Create top JPanel and bottom JPanel
		// When creating a new FlowLayout(), you can pass an int as an argument from 0 to 2
		// 0 is left, 1 is center, 2 is right, 3 is leading and 4 is trailing
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(5, 4, 4, 5));
		
		// Creating JTextField
		results = new JTextField();
		
		// Creating a font that will be in the TextField 
		Font font1 = new Font("SansSerif", Font.BOLD, 25);
		results.setFont(font1);
		
		// Setting the alignment of the text inside the TextField to the right
		results.setHorizontalAlignment(JTextField.RIGHT);
		topPanel.add(results, BorderLayout.CENTER);
		
		// Creating all the buttons and adding them to the bottomLayout 
		CalculatorButton buttonBack = new CalculatorButton("B");
		buttonBack.getButton().addActionListener(this);
		buttonBack.getButton().setFont(font1);
		
		CalculatorButton buttonC = new CalculatorButton("C");
		buttonC.getButton().addActionListener(this);
		buttonC.getButton().setFont(font1);
		
		CalculatorButton buttonPerCent = new CalculatorButton("%");
		buttonPerCent.getButton().addActionListener(this);
		buttonPerCent.getButton().setFont(font1);
		
		CalculatorButton buttonPlus = new CalculatorButton("+");
		buttonPlus.getButton().addActionListener(this);
		buttonPlus.getButton().setFont(font1);
		
		CalculatorButton button7 = new CalculatorButton(7);
		button7.getButton().addActionListener(this);
		button7.getButton().setFont(font1);
		
		CalculatorButton button8 = new CalculatorButton(8);
		button8.getButton().addActionListener(this);
		button8.getButton().setFont(font1);
		
		CalculatorButton button9 = new CalculatorButton(9);
		button9.getButton().addActionListener(this);
		button9.getButton().setFont(font1);
		
		CalculatorButton buttonMinus = new CalculatorButton("-");
		buttonMinus.getButton().addActionListener(this);
		buttonMinus.getButton().setFont(font1);
		
		CalculatorButton button4 = new CalculatorButton(4);
		button4.getButton().addActionListener(this);
		button4.getButton().setFont(font1);
		
		CalculatorButton button5 = new CalculatorButton(5);
		button5.getButton().addActionListener(this);
		button5.getButton().setFont(font1);
		
		CalculatorButton button6 = new CalculatorButton(6);
		button6.getButton().addActionListener(this);
		button6.getButton().setFont(font1);
		
		CalculatorButton buttonMul = new CalculatorButton("*");
		buttonMul.getButton().addActionListener(this);
		buttonMul.getButton().setFont(font1);
		
		CalculatorButton button1 = new CalculatorButton(1);
		button1.getButton().addActionListener(this);
		button1.getButton().setFont(font1);
		
		CalculatorButton button2 = new CalculatorButton(2);
		button2.getButton().addActionListener(this);
		button2.getButton().setFont(font1);
		
		CalculatorButton button3 = new CalculatorButton(3);
		button3.getButton().addActionListener(this);
		button3.getButton().setFont(font1);
		
		CalculatorButton buttonDiv = new CalculatorButton("/");
		buttonDiv.getButton().addActionListener(this);
		buttonDiv.getButton().setFont(font1);
		
		CalculatorButton button0 = new CalculatorButton(0);
		button0.getButton().addActionListener(this);
		button0.getButton().setFont(font1);
		
		CalculatorButton buttonDot = new CalculatorButton(".");
		buttonDot.getButton().addActionListener(this);
		buttonDot.getButton().setFont(font1);
		
		CalculatorButton buttonPlusMinus = new CalculatorButton("±");
		buttonPlusMinus.getButton().addActionListener(this);
		buttonPlusMinus.getButton().setFont(font1);
		
		CalculatorButton buttonEqual = new CalculatorButton("=");
		buttonEqual.getButton().addActionListener(this);
		buttonEqual.getButton().setFont(font1);
		
		bottomPanel.add(buttonBack.getButton());
		bottomPanel.add(buttonC.getButton());
		bottomPanel.add(buttonPerCent.getButton());
		bottomPanel.add(buttonPlus.getButton());
		bottomPanel.add(button7.getButton());
		bottomPanel.add(button8.getButton());
		bottomPanel.add(button9.getButton());
		bottomPanel.add(buttonMinus.getButton());
		bottomPanel.add(button4.getButton());
		bottomPanel.add(button5.getButton());
		bottomPanel.add(button6.getButton());
		bottomPanel.add(buttonMul.getButton());
		bottomPanel.add(button1.getButton());
		bottomPanel.add(button2.getButton());
		bottomPanel.add(button3.getButton());
		bottomPanel.add(buttonDiv.getButton());
		bottomPanel.add(button0.getButton());
		bottomPanel.add(buttonDot.getButton());
		bottomPanel.add(buttonPlusMinus.getButton());
		bottomPanel.add(buttonEqual.getButton());

		
		// Adding panels to frame
		calculatorFrame.add(topPanel, BorderLayout.NORTH);
		calculatorFrame.add(bottomPanel, BorderLayout.CENTER);
		
		// After having added the top panel we change its size to make the TextField taller
		topPanel.setPreferredSize(new Dimension(10, 55));
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("=")) {
			// If = is typed, we need to calculate the results of the operation based on what the string numberInResults is,
			// We need to loop in the string until finding a non integer, all numbers before this non integer is the first number, all the numbers after the non-integer is the second number
			StringBuilder firstNumber = new StringBuilder();
			StringBuilder secondNumber = new StringBuilder();
			// We don't need the operator because we have it stored in the global 'operator' variable
			for(int i = 0; i < numberInResults.length(); i++) {
				char checking = numberInResults.charAt(i);
				if(numberInResults.charAt(i) >= '0' && numberInResults.charAt(i) <= '9') {
					// We create an string that will hold this number to then later convert it to an integer
					firstNumber.append(numberInResults.charAt(i));
				}else {
					break;
				}
			}
			for(int i = firstNumber.length()+1; i < numberInResults.length(); i++) {
				// We know that from here all elements in the numberInResults string are numbers so we don't check for other characters
				secondNumber.append(numberInResults.charAt(i));
			}
			
			// Now we convert both numbers to integers to be able to perform the operation
			int firstNumberInt = Integer.parseInt(firstNumber.toString());
			int secondNumberInt = Integer.parseInt(secondNumber.toString());
			
			numberInResults = Integer.toString(performOperation(firstNumberInt, secondNumberInt));
		}else if(e.getActionCommand().equals("+") || e.getActionCommand().equals("-") || e.getActionCommand().equals("*") || e.getActionCommand().equals("/")) { //if any of the operators is typed 
			operator = e.getActionCommand();
			numberInResults = numberInResults + operator;
		}else if(e.getActionCommand().equals("C")) {
			numberInResults = "";
		}else {
			numberInResults = numberInResults + e.getActionCommand();
		}
		/*if(e.getActionCommand().equals("1")) {
			numberInResults = numberInResults + "1";
		}else if(e.getActionCommand().equals("2")) {
			numberInResults = numberInResults + "2";
		}else if(e.getActionCommand().equals("3")) {
			numberInResults = numberInResults + "3";
		}else if(e.getActionCommand().equals("4")) {
			numberInResults = numberInResults + "4";
		}else if(e.getActionCommand().equals("5")) {
			numberInResults = numberInResults + "5";
		}else if(e.getActionCommand().equals("6")) {
			numberInResults = numberInResults + "6";
		}else if(e.getActionCommand().equals("7")) {
			numberInResults = numberInResults + "7";
		}else if(e.getActionCommand().equals("8")) {
			numberInResults = numberInResults + "8";
		}else if(e.getActionCommand().equals("9")) {
			numberInResults = numberInResults + "9";
		}else if(e.getActionCommand().equals("0")) {
			numberInResults = numberInResults + "0";
		}else if(e.getActionCommand().equals(".")) {
			numberInResults = numberInResults + ".";
		}*/
		
		results.setText(numberInResults);
	}
	
	//  @ Description: Function that takes in two numbers, checks the current operator and performs the operation based on these three inputs
	//	@ Param: firstNumber and secondNumber to perform the operation on
	//	@ Output: the result of performing the operation
	public int performOperation(int firstNumber, int secondNumber) {
		int result = 0;
		switch(operator) {
			case "+":
				result = firstNumber + secondNumber;
				break;
			case "-":
				result = firstNumber - secondNumber;
				break;
			case "*":
				result = firstNumber * secondNumber;
				break;
			case "/":
				result = firstNumber / secondNumber;
				break;
		}
		return result;
	}	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Calculator();
			}
			
		});
	}


}
