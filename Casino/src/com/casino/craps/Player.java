package com.casino.craps;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Player extends JPanel {
	private int money;
	private String name;

	public Player() {
		super();
		setOpaque(false);
	}

	public Player(int money, String name) {
		super();
		this.money = money;
		this.name = name;
		setOpaque(false);
	}

	public int getMoney() {
		return money;
	}

	public void addMoney(int moreMoney) {
		money += moreMoney;
	}

	public void loseMoney(int moreMoney) {
		money -= moreMoney;
	}

	public String getName() {
		return name;
	}
}
