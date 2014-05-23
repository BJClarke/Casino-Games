package com.casino.craps;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Dice extends JLabel {

	private int value;

	public Dice() {
		value = 1;
		setIcon(new ImageIcon(getClass().getResource("resources/1.png")));
		revalidate();
		repaint();
	}

	public void roll() {
		value = new Random().nextInt(6) + 1;
		setIcon(new ImageIcon(getClass().getResource(
				"resources/" + value + ".png")));
		revalidate();
		repaint();
	}

	public int combine(Dice dice1) {
		return this.getValue() + dice1.getValue();
	}

	public int getValue() {
		return this.value;
	}

}
