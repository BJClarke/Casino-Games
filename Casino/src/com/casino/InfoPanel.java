package com.casino;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class InfoPanel extends JPanel {

	private JLabel name, money;
	private GridBagConstraints gbc_name, gbc_money;

	public InfoPanel(String name, int money) {
		super();
		setOpaque(false);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new GridBagLayout());
		setup();
		updateMoney(money);
		updateName(name);
	}

	public InfoPanel() {
		super();
		setOpaque(false);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new GridBagLayout());
		setup();
	}

	public void setup() {
		removeAll();

		name = new JLabel("--- ");
		money = new JLabel(" --- ");

		JLabel player = new JLabel("Player: ");
		GridBagConstraints gbc_player = new GridBagConstraints();
		gbc_player.fill = GridBagConstraints.HORIZONTAL;
		gbc_player.gridx = 0;
		gbc_player.gridy = 0;

		JLabel cash = new JLabel("Money: $");
		GridBagConstraints gbc_cash = new GridBagConstraints();
		gbc_cash.fill = GridBagConstraints.HORIZONTAL;
		gbc_cash.gridx = 2;
		gbc_cash.gridy = 0;

		gbc_name = new GridBagConstraints();
		gbc_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_name.gridx = 1;
		gbc_name.gridy = 0;

		gbc_money = new GridBagConstraints();
		gbc_money.fill = GridBagConstraints.HORIZONTAL;
		gbc_money.gridx = 3;
		gbc_money.gridy = 0;

		add(name, gbc_name);
		add(money, gbc_money);
		add(player, gbc_player);
		add(cash, gbc_cash);
		revalidate();
		repaint();
	}

	public void updateMoney(int money) {
		remove(this.money);
		this.money = new JLabel("" + money);
		add(this.money, gbc_money);
		revalidate();
		repaint();
	}

	public void updateName(String name) {
		remove(this.name);
		this.name = new JLabel(name + " ");
		add(this.name, gbc_name);
		revalidate();
		repaint();
	}

}
