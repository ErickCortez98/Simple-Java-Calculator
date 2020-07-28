package CalculatorPackage;

import javax.swing.JButton;

public class CalculatorButton {
	private JButton button;
	// Each button will either be a number of an operation button
	private int buttonNumber;
	private String buttonOperation;
	
	public CalculatorButton(int buttonNumber) {
		this.buttonNumber = buttonNumber;
		button = new JButton(Integer.toString(this.buttonNumber));
	}

	public CalculatorButton(String buttonOperation) {
		this.buttonOperation = buttonOperation;
		button = new JButton(this.buttonOperation);
	}
	
	public JButton getButton() {
		return this.button;
	}
	
	public int getButtonNumber() {
		return this.buttonNumber;
	}
	
	public String getButtonOperation() {
		return this.buttonOperation;
	}
}
