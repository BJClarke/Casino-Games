package com.casino.slots;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Wheel extends JPanel {

	private Symbol one;
	private Symbol two;
	private Symbol three;
	private float profit;

	private float moneyWon;

	public Wheel() {
		this.setOpaque(false);
		one = new Symbol();
		two = new Symbol();
		three = new Symbol();
		add(one);
		add(two);
		add(three);
	}

	/**
	 * returns the type of the wheel
	 * 
	 * @return type the type of the wheel aka the image on it
	 */
	public int getOneType() {
		return one.getID();

	}

	public int getTwoType() {
		return two.getID();

	}

	public int getThreeType() {
		return three.getID();

	}

	public void turnAll(float bet) {
		one.turnSymbol();
		two.turnSymbol();
		three.turnSymbol();
		moneyWon = this.returnProfit(bet);

	}

	public float returnProfit(float bet) {
		int sumValue = one.getValue() + two.getValue() + three.getValue();

		if (sumValue == 30) {
			// JACKPOT
			profit = bet * 2;
		} else if (sumValue >= 20) {
			profit = (float) (bet * 1.5);
		} else if (sumValue >= 14) {
			profit = bet;
		} else if (sumValue >= 8) {
			profit = bet / 2;

		} else // sumValue < 8
		{
			profit = 0;
		}
		return profit;
	}

	public float getMoneyWon() {
		return moneyWon;
	}
}
