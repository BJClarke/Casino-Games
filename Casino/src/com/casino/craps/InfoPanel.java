package com.casino.craps;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class InfoPanel extends JPanel {

	private JLabel playerName, playerMoney;
	GridBagConstraints gbc_playerMoney;
	Player player;

	public InfoPanel(Player player) {
		super();
		this.player = player;
		setOpaque(false);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new GridBagLayout());
		setup();
	}

	public void setup() {
		removeAll();

		GridBagConstraints gbc_name = new GridBagConstraints();
		JLabel name = new JLabel("Player: ");
		gbc_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_name.gridx = 0;
		gbc_name.gridy = 0;

		GridBagConstraints gbc_money = new GridBagConstraints();
		JLabel money = new JLabel("Money: $");
		gbc_money.fill = GridBagConstraints.HORIZONTAL;
		gbc_money.gridx = 2;
		gbc_money.gridy = 0;

		GridBagConstraints gbc_playerName = new GridBagConstraints();
		playerName = new JLabel(player.getName() + " ");
		gbc_playerName.fill = GridBagConstraints.HORIZONTAL;
		gbc_playerName.gridx = 1;
		gbc_playerName.gridy = 0;

		gbc_playerMoney = new GridBagConstraints();
		playerMoney = new JLabel(player.getMoney() + " ");
		gbc_playerMoney.fill = GridBagConstraints.HORIZONTAL;
		gbc_playerMoney.gridx = 3;
		gbc_playerMoney.gridy = 0;

		add(name, gbc_name);
		add(money, gbc_money);
		add(playerName, gbc_playerName);
		add(playerMoney, gbc_playerMoney);
		revalidate();
		repaint();
	}

	public void updateMoney() {
		remove(playerMoney);
		playerMoney = new JLabel(player.getMoney() + " ");
		add(playerMoney, gbc_playerMoney);
	}

}
