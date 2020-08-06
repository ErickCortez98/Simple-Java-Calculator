package CalculatorPackage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicArrowButton;

// TODO: Error catching when an input doesn't make sense

public class Calculator implements ActionListener{
	JTextField results;
	String numberInResults;
	String operator;
	boolean operationInProgress;
	boolean percentageActive;
	
	// Constructor of our class
	public Calculator() {
		numberInResults = "";
		operationInProgress = false;
		percentageActive = false;
		
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
		CalculatorButton buttonBack = new CalculatorButton("<");
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
		// If = is pressed
		if(e.getActionCommand().equals("=")) {
			// If = is typed, we need to calculate the results of the operation based on what the string numberInResults is,
			// We need to loop in the string until finding a non integer, all numbers before this non integer is the first number, all the numbers after the non-integer is the second number
			StringBuilder firstNumber = new StringBuilder();
			StringBuilder secondNumber = new StringBuilder();
			// We don't need the operator because we have it stored in the global 'operator' variable
			for(int i = 0; i < numberInResults.length(); i++) {
				char checking = numberInResults.charAt(i);
				
				// If the first symbol in the number is '-' signaling a negative number
				if(i == 0 && numberInResults.charAt(0) == '-') {
					firstNumber.append(numberInResults.charAt(i));
					continue;
				}
				
				if((numberInResults.charAt(i) >= '0' && numberInResults.charAt(i) <= '9') || numberInResults.charAt(i) == '.') {
					// We create an string that will hold this number to then later convert it to an integer
					firstNumber.append(numberInResults.charAt(i));
				}else {
					break;
				}
			}
			for(int i = firstNumber.length()+1; i < numberInResults.length(); i++) {
				// We know that from here all elements in the numberInResults string are numbers so we don't check for other characters
				if((i == numberInResults.length()-1) && (numberInResults.charAt(i) == '%')) {
					percentageActive = true;
					break;
				}
				secondNumber.append(numberInResults.charAt(i));
			}
			
			// Now we convert both numbers to floats to be able to perform the operation
			float firstNumberInt = Float.parseFloat(firstNumber.toString());
			float secondNumberInt = Float.parseFloat(secondNumber.toString());
			
			numberInResults = Float.toString(performOperation(firstNumberInt, secondNumberInt, percentageActive));
			
			operationInProgress = false;
		}
		// If any of the operations is pressed +, -, * or /
		else if(e.getActionCommand().equals("+") || e.getActionCommand().equals("-") || e.getActionCommand().equals("*") || e.getActionCommand().equals("/")) { 
			//if any of the operators is typed for the first time 
			if(operationInProgress == false) {
				operator = e.getActionCommand();
				numberInResults = numberInResults + operator;
				operationInProgress = true;
			}
			// if any of the operators is typed a second time instead of an equal sign
			else {
				// We need to loop in the string until finding a non integer, all numbers before this non integer is the first number, all the numbers after the non-integer is the second number
				StringBuilder firstNumber = new StringBuilder();
				StringBuilder secondNumber = new StringBuilder();
				// We don't need the operator because we have it stored in the global 'operator' variable
				for(int i = 0; i < numberInResults.length(); i++) {
					char checking = numberInResults.charAt(i);
					
					// If the first symbol in the number is '-' signaling a negative number
					if(i == 0 && numberInResults.charAt(0) == '-') {
						firstNumber.append(numberInResults.charAt(i));
						continue;
					}
					
					if((numberInResults.charAt(i) >= '0' && numberInResults.charAt(i) <= '9') || numberInResults.charAt(i) == '.') {
						// We create an string that will hold this number to then later convert it to an integer
						firstNumber.append(numberInResults.charAt(i));
					}else {
						break;
					}
				}
				for(int i = firstNumber.length()+1; i < numberInResults.length(); i++) {
					// We know that from here all elements in the numberInResults string are numbers so we don't check for other characters
					if((i == numberInResults.length()-1) && (numberInResults.charAt(i) == '%')) {
						percentageActive = true;
						break;
					}
					
					secondNumber.append(numberInResults.charAt(i));
				}
				// Now we convert both numbers to floats to be able to perform the operation
				float firstNumberInt = Float.parseFloat(firstNumber.toString());
				float secondNumberInt = Float.parseFloat(secondNumber.toString());
				
				numberInResults = Float.toString(performOperation(firstNumberInt, secondNumberInt, percentageActive));
				
				operator = e.getActionCommand();
				numberInResults = numberInResults + operator;
				
			}
		}
		// If C is pressed to clean the screen
		else if(e.getActionCommand().equals("C")) {
			numberInResults = "";
			operationInProgress = false;
		}
		// If plus minus is pressed to change the sign
		else if(e.getActionCommand().equals("±")) {
			if(numberInResults.charAt(0) == '-') {
				StringBuilder newNumberInResults = new StringBuilder();
				for(int i = 1; i < numberInResults.length(); i++) {
					newNumberInResults.append(numberInResults.charAt(i));
				}
				numberInResults = newNumberInResults.toString();
			}else {
				numberInResults = "-" + numberInResults;
			}
		}
		// If the back arrow button is pressed
		else if(e.getActionCommand().equals("<")) {
			StringBuilder newNumberInResults = new StringBuilder();
			
			if(numberInResults.charAt(numberInResults.length()-1) == '*' || numberInResults.charAt(numberInResults.length()-1) == '-' || numberInResults.charAt(numberInResults.length()-1) == '+' || numberInResults.charAt(numberInResults.length()-1) == '/') {
				operationInProgress = false;
			}
			
			for(int i = 0; i < numberInResults.length()-1; i++) {
				newNumberInResults.append(numberInResults.charAt(i));
			}
			
			numberInResults = newNumberInResults.toString();
		}
		// If % is pressed
		else if(e.getActionCommand().equals("%")) {
			if(operationInProgress) {
				numberInResults = numberInResults + e.getActionCommand();
			}else {
				numberInResults = "";
			}
		}
		// If any of the numbers is pressed
		else {
			numberInResults = numberInResults + e.getActionCommand();
		}
		
		results.setText(numberInResults);
	}
	
	//  @ Description: Function that takes in two numbers, checks the current operator and performs the operation based on these three inputs
	//	@ Param: firstNumber and secondNumber to perform the operation on
	//	@ Output: the result of performing the operation
	public float performOperation(float firstNumber, float secondNumber, boolean percentageActive) {
		float result = 0;
		if(percentageActive) {
			this.percentageActive = false;
			switch(operator) {
			case "+":
				// Divide second number by first number, multiply by 100, then add to first number
				result = firstNumber + (secondNumber/100)*firstNumber;
				break;
			case "-":
				result = firstNumber - (secondNumber/100)*firstNumber;
				break;
			case "*":
				result = firstNumber * (secondNumber/100)*firstNumber;
				break;
			case "/":
				result = firstNumber / ((secondNumber/100)*firstNumber);
				break;
			}
		}else {
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
