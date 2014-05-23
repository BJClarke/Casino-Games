package com.casino.slots;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.casino.Game;

@SuppressWarnings("serial")
public class Slots extends JFrame implements ActionListener {
	// ~ Fields ------------------------------------------------------------
	private Wheel machine;
	// private JFrame thisFrame;
	// private float money;
	private float totalMoney;
	private JLabel statusLabel;
	private String name;

	// ~ Methods -----------------------------------------------------------
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Slots(200, "RUDY");
			}
		});
	}

	public Slots(int initialMoney, String name) {
		setBackground(Color.ORANGE);
		setTitle("Slots");
		machine = new Wheel();
		machine.setBorder(new EmptyBorder(34, 0, 0, 4));
		statusLabel = new JLabel();
		totalMoney = initialMoney;
		this.name = name;
		getContentPane().setBackground(Color.ORANGE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 1.0 };
		getContentPane().setLayout(gridBagLayout);
		setupContent();
		setVisible(true);

		pack();
		setResizable(false);
		statusLabel.setText("Total Money: " + totalMoney);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setupContent() {

		/*
		 * Other stuff Being created here
		 */
		JPanel empty = new JPanel();
		empty.setBorder(new EmptyBorder(0, 0, 20, 0));
		empty.setOpaque(false);
		JPanel info = new JPanel();

		info.add(statusLabel);

		info.setBorder(new EmptyBorder(0, 0, 10, 0));
		info.setOpaque(false);
		JLabel background = new JLabel(new ImageIcon(getClass().getResource(
				"resources/slotMachine2.png")));

		GridBagConstraints gbc_background = new GridBagConstraints();
		gbc_background.gridx = 0;
		gbc_background.gridy = 0;
		gbc_background.gridwidth = 0;
		gbc_background.gridheight = 4;

		GridBagConstraints gbc_info = new GridBagConstraints();
		gbc_info.gridx = 0;
		gbc_info.gridy = 0;

		GridBagConstraints gbc_empty = new GridBagConstraints();
		gbc_empty.gridx = 0;
		gbc_empty.gridy = 1;

		GridBagConstraints gbc_wheel = new GridBagConstraints();
		gbc_wheel.gridx = 0;
		gbc_wheel.gridy = 2;

		getContentPane().add(info, gbc_info);

		/*
		 * Below is the bet and spin button which will prompt for a bet and then
		 * spin if the bet is less than 0 or greater than the user's total money
		 * then it'll have the user bet again. If the user has no money then it
		 * will tell them as well.
		 */
		JButton spinButton = new JButton();
		spinButton.setBackground(Color.ORANGE);
		spinButton.setText("Bet and Spin");
		spinButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String inputValue = JOptionPane
						.showInputDialog("How much would you like to bet?");
				if (totalMoney < 0.01) {
					JOptionPane
							.showMessageDialog(null,
									"You Are Broke Come Back Later When You Have Money!");

				} else {
					float bet = Float.parseFloat(inputValue);
					while (bet <= 0 || bet > totalMoney) {
						inputValue = JOptionPane
								.showInputDialog("Illegal bet, please bet again");
						bet = Float.parseFloat(inputValue);
					}
					bet = Float.parseFloat(inputValue);
					totalMoney -= bet;

					machine.turnAll(bet);

					totalMoney += machine.getMoneyWon();
					statusLabel.setText("Total Money: " + totalMoney);
					machine.revalidate();
					machine.repaint();
				}

			}
		});
		// ---------------------
		info.add(spinButton);
		getContentPane().add(empty, gbc_empty);
		getContentPane().add(machine, gbc_wheel);

		JButton quit = new JButton("Quit Game");
		quit.setBackground(Color.ORANGE);
		quit.addActionListener(this);

		GridBagConstraints gbc_quit = new GridBagConstraints();
		gbc_quit.gridx = 0;
		gbc_quit.gridy = 3;

		getContentPane().add(quit, gbc_quit);
		getContentPane().add(background, gbc_background);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		new Game((int) totalMoney, name);
		this.dispose();
	}

}
