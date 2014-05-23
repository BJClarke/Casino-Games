package com.casino.slots;

import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Symbol extends JLabel {

	private int id;
	private int value;

	public Symbol() {
		setLayout(new BorderLayout());
		id = 0;
		value = 0;
		this.setIcon(new ImageIcon(getClass().getResource("resources/X.png")));
		this.setOpaque(false);
	}

	/**
	 * changes the ID of the symbol
	 * 
	 * @param newID
	 */
	public void setID(int newID) {
		id = newID;

	}

	public int getID() {
		return id;
	}

	/**
	 * turns the wheel
	 */
	public void turnSymbol() {
		Random generator = new Random();
		int x = generator.nextInt(100);
		if (x < 10) // 10% chance will get 7
		{
			this.setID(1);

			this.setIcon(new ImageIcon(getClass().getResource(
					"resources/Seven2.png")));

			value = 10;

		} else if (x < 25) // 15% chance will get Bar
		{
			this.setID(2);

			this.setIcon(new ImageIcon(getClass().getResource(
					"resources/BAR.png")));

			value = 7;

		} else if (x < 45) // 20% chance will get Orange
		{
			this.setID(3);

			this.setIcon(new ImageIcon(getClass().getResource(
					"resources/orange.png")));

			value = 5;

		} else if (x < 70) // 25% chance will get Cherry
		{
			this.setID(4);

			this.setIcon(new ImageIcon(getClass().getResource(
					"resources/cherry.png")));

			value = 3;

		} else // 30% chance will get Lemon
		{
			this.setID(5);
			this.setIcon(new ImageIcon(getClass().getResource(
					"resources/lemon.png")));

			value = 1;

		}
	}

	public int getValue() {
		return value;
	}

}
