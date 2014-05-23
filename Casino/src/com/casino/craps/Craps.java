package com.casino.craps;

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

import com.casino.Game;

// -------------------------------------------------------------------------
/**
 * This is a new class to make a craps game
 * 
 * @author Brian Clarke
 * @version Apr 9, 2014
 */
@SuppressWarnings("serial")
public class Craps extends JFrame implements ActionListener {

	private int money;
	private String name;
	private int combinedRoll;
	private int count;
	private InfoPanel info;
	private Player player;
	private Dice dice1;
	private Dice dice2;
	private JPanel dice, PassLine, COME, DontPass, PlaceBets, BigBet,
			FieldBets, Roll;
	private JButton roll, pass, come, not, place, big, field, quit;

	// ----------------------------------------------------------

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Craps(100, "Brian");
			}
		});
	}

	/**
	 * Create a new Craps object.
	 * 
	 * @param money
	 *            is the money
	 * @param name
	 *            is the name
	 */
	public Craps(int money, String name) {
		setVisible(true);
		setTitle("Craps");
		setBackground(new Color(0, 153, 51));
		getContentPane().setLayout(new GridBagLayout());
		dice1 = new Dice();
		dice2 = new Dice();
		player = new Player(money, name);
		setUpContent();
		setTitle("Craps");
		this.money = money;
		this.name = name;
		count = 0;
		pack();
		JOptionPane
				.showMessageDialog(
						this,
						"Instructions: \n \n"
								+ "These are the different ways to bet: \n \n"
								+ "The Pass Line: If the next roll is a 7 or 11, you win! If"
								+ " the next roll is a 2, 3, or 12, you lose! \nOtherwise, the"
								+ " dice continue to be rolled until that number appears"
								+ " again which result in a win, or a 7 which results in a"
								+ " loss\n \n"
								+ "COME: Only after the first roll and if a 7 or 11 is rolled,"
								+ " player wins!\n \n"
								+ "Don't Pass Line: Almost opposite of passLine \nWin if roll"
								+ " is 2 or 3 Lose if roll is 7 or 11 \nDraw if roll is 12\n"
								+ "Otherwise roll again\n \n"
								+ "Place Bets: Simple place bets that you can win on next roll\n"
								+ "Minimum bid of 5 for 4, 5, 9, or 10\n"
								+ "Minimum bid of 6 for 6 or 8\n \n"
								+ "Big Bet: Place your bet on a 6 or 8. If that number is rolled"
								+ " before a 7, you win! \n"
								+ "Otherwise, you lose!\n \n"
								+ "Field Bet: A simple field bet that will pay if your number"
								+ " is rolled next\n"
								+ "If that number you select is a 2 or 12 then you win double\n"
								+ "Otherwise, you lose!\n \n" + "GOOD LUCK!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}

	public void setUpContent() {
		getContentPane().setBackground(new Color(0, 153, 51));

		roll = new JButton();
		roll.addActionListener(this);
		// roll.setOpaque(false);
		// roll.setContentAreaFilled(false);
		// roll.setBorderPainted(false);
		roll.setText("Roll");

		pass = new JButton();
		pass.addActionListener(this);
		// pass.setOpaque(false);
		// pass.setContentAreaFilled(false);
		// pass.setBorderPainted(false);
		pass.setText("Pass Line");

		come = new JButton();
		come.addActionListener(this);
		// come.setOpaque(false);
		// come.setContentAreaFilled(false);
		// come.setBorderPainted(false);
		come.setText("COME");

		not = new JButton();
		not.addActionListener(this);
		// not.setOpaque(false);
		// not.setContentAreaFilled(false);
		// not.setBorderPainted(false);
		not.setText("Don't Pass Line");

		place = new JButton();
		place.addActionListener(this);
		// place.setOpaque(false);
		// place.setContentAreaFilled(false);
		// place.setBorderPainted(false);
		place.setText("Place Bet");

		big = new JButton();
		big.addActionListener(this);
		// big.setOpaque(false);
		// big.setContentAreaFilled(false);
		// big.setBorderPainted(false);
		big.setText("Big Bet");

		field = new JButton();
		field.addActionListener(this);
		// field.setOpaque(false);
		// field.setContentAreaFilled(false);
		// field.setBorderPainted(false);
		field.setText("Field Bet");

		quit = new JButton("Quit Game");
		quit.addActionListener(this);

		info = new InfoPanel(player);
		GridBagConstraints gbc_info = new GridBagConstraints();
		gbc_info.gridx = 0;
		gbc_info.gridy = 0;
		gbc_info.gridheight = 3;
		gbc_info.anchor = GridBagConstraints.NORTH;

		dice = new JPanel();
		dice.add(dice1);
		dice.add(dice2);
		dice.setOpaque(false);

		PassLine = new JPanel();
		PassLine.add(pass);
		PassLine.setOpaque(false);

		COME = new JPanel();
		COME.add(come);
		COME.setOpaque(false);

		DontPass = new JPanel();
		DontPass.add(not);
		DontPass.setOpaque(false);

		PlaceBets = new JPanel();
		PlaceBets.add(place);
		PlaceBets.setOpaque(false);

		BigBet = new JPanel();
		BigBet.add(big);
		BigBet.setOpaque(false);

		FieldBets = new JPanel();
		FieldBets.add(field);
		FieldBets.setOpaque(false);

		Roll = new JPanel();
		Roll.add(roll);
		Roll.setOpaque(false);

		GridBagConstraints gbc_dice = new GridBagConstraints();
		gbc_dice.gridx = 0;
		gbc_dice.gridy = 7;
		gbc_dice.anchor = GridBagConstraints.SOUTH;

		GridBagConstraints gbc_PassLine = new GridBagConstraints();
		gbc_PassLine.fill = GridBagConstraints.HORIZONTAL;
		gbc_PassLine.gridx = 1;
		gbc_PassLine.gridy = 0;

		GridBagConstraints gbc_COME = new GridBagConstraints();
		gbc_COME.fill = GridBagConstraints.HORIZONTAL;
		gbc_COME.gridx = 1;
		gbc_COME.gridy = 1;

		GridBagConstraints gbc_DontPass = new GridBagConstraints();
		gbc_DontPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_DontPass.gridx = 1;
		gbc_DontPass.gridy = 2;

		GridBagConstraints gbc_PlaceBets = new GridBagConstraints();
		gbc_PlaceBets.fill = GridBagConstraints.HORIZONTAL;
		gbc_PlaceBets.gridx = 1;
		gbc_PlaceBets.gridy = 3;

		GridBagConstraints gbc_BigBet = new GridBagConstraints();
		gbc_BigBet.fill = GridBagConstraints.HORIZONTAL;
		gbc_BigBet.gridx = 1;
		gbc_BigBet.gridy = 4;

		GridBagConstraints gbc_FieldBets = new GridBagConstraints();
		gbc_FieldBets.fill = GridBagConstraints.HORIZONTAL;
		gbc_FieldBets.gridx = 1;
		gbc_FieldBets.gridy = 5;

		GridBagConstraints gbc_Roll = new GridBagConstraints();
		gbc_Roll.fill = GridBagConstraints.HORIZONTAL;
		gbc_Roll.gridx = 1;
		gbc_Roll.gridy = 6;

		GridBagConstraints gbc_quit = new GridBagConstraints();
		gbc_quit.gridx = 1;
		gbc_quit.gridy = 7;
		gbc_quit.anchor = GridBagConstraints.SOUTH;

		getContentPane().add(dice, gbc_dice);
		getContentPane().add(PassLine, gbc_PassLine);
		getContentPane().add(COME, gbc_COME);
		getContentPane().add(DontPass, gbc_DontPass);
		getContentPane().add(PlaceBets, gbc_PlaceBets);
		getContentPane().add(BigBet, gbc_BigBet);
		getContentPane().add(FieldBets, gbc_FieldBets);
		getContentPane().add(Roll, gbc_Roll);
		getContentPane().add(info, gbc_info);
		getContentPane().add(quit, gbc_quit);

		GridBagConstraints gbc_background = new GridBagConstraints();
		gbc_background.gridx = 0;
		gbc_background.gridy = 0;
		gbc_background.gridheight = 8;

		// last
		getContentPane().add(
				new JLabel(new ImageIcon(getClass().getResource(
						"resources/craps.png"))), gbc_background);
	}

	public void quit() {
		new Game(player.getMoney(), name);
		this.dispose();
	}

	public void actionPerformed(ActionEvent event) {
		JButton button = (JButton) (event.getSource());
		if (button.getText().equals("Pass Line")) {
			String enterBet = JOptionPane.showInputDialog(getContentPane(),
					"Enter Bet: ");
			int bet = Integer.parseInt(enterBet);
			if (money > 0 && bet <= money && bet >= 0) {
				System.out.println(player.getMoney());
				dice1.roll();
				dice2.roll();
				this.passLine(bet);
				System.out.println(player.getMoney());
			} else {
				JOptionPane.showMessageDialog(getContentPane(), "Invalid Bet");
			}
		}
		if (button.getText().equals("Roll")) {
			dice1.roll();
			dice2.roll();
			combinedRoll = dice1.combine(dice2);
		}
		if (button.getText().equals("COME")) {
			String enterBet = JOptionPane.showInputDialog(getContentPane(),
					"Enter Bet: ");
			int bet = Integer.parseInt(enterBet);
			if (money > 0 && bet <= money && bet >= 0 && count != 0) {
				dice1.roll();
				dice2.roll();
				this.COME(bet);
			} else {
				JOptionPane.showMessageDialog(getContentPane(), "Invalid Bet");
			}
		}
		if (button.getText().equals("Don't Pass Line")) {
			String enterBet = JOptionPane.showInputDialog(getContentPane(),
					"Enter Bet: ");
			int bet = Integer.parseInt(enterBet);
			if (money > 0 && bet <= money && bet >= 0) {
				dice1.roll();
				dice2.roll();
				this.dontPassLine(bet);
			} else {
				JOptionPane.showMessageDialog(getContentPane(), "Invalid Bet");
			}
		}
		if (button.getText().equals("Place Bet")) {
			int[] bets = new int[5];
			int numBets = 1;
			String enterBet = JOptionPane.showInputDialog(getContentPane(),
					"Enter Bet: ");
			int bet = Integer.parseInt(enterBet);
			String enterNums = JOptionPane.showInputDialog(getContentPane(),
					"Enter number to bet on: ");
			bets[0] = Integer.parseInt(enterNums);
			int dialogresult = JOptionPane.showConfirmDialog(getContentPane(),
					"Would you like to enter another bet?");
			while (dialogresult == JOptionPane.YES_OPTION) {
				if (money > 0 && bet <= money && bet >= 5) {
					if ((bets[numBets - 1] == 4 || bets[numBets - 1] == 5
							|| (bets[numBets - 1] == 6 || bet >= 6)
							|| (bets[numBets - 1] == 8 && bet >= 6)
							|| bets[numBets - 1] == 9 || bets[numBets - 1] == 10)) {
						enterNums = JOptionPane.showInputDialog(
								getContentPane(), "Enter numbers to bet on: ");
						bets[numBets] = Integer.parseInt(enterNums);
						numBets++;
						dialogresult = JOptionPane.showConfirmDialog(
								getContentPane(),
								"Would you like to bet on another number?");
					}
				} else {
					JOptionPane.showMessageDialog(getContentPane(),
							"Invalid Bet");
					return;
				}
			}
			dice1.roll();
			dice2.roll();
			combinedRoll = dice1.combine(dice2);
			this.placeBets(bet, bets);
		}
		if (button.getText().equals("Big Bet")) {
			int[] bets = new int[2];
			int numBets = 1;
			String enterBet = JOptionPane.showInputDialog(getContentPane(),
					"Enter Bet: ");
			int bet = Integer.parseInt(enterBet);
			String enterNums = JOptionPane.showInputDialog(getContentPane(),
					"Enter either a 6 or 8: ");
			bets[0] = Integer.parseInt(enterNums);
			if (money > 0 && bet <= money) {
				if (bets[numBets - 1] == 6 || bets[numBets - 1] == 8) {
					int dialogresult = JOptionPane.showConfirmDialog(
							getContentPane(),
							"Would you like to bet on another number?");
					if (dialogresult == JOptionPane.YES_OPTION) {
						enterNums = JOptionPane.showInputDialog(
								getContentPane(), "Enter either a 6 or 8: ");
						bets[1] = Integer.parseInt(enterNums);
						numBets++;
						if (bets[numBets - 1] == 6 || bets[numBets - 1] == 8) {
							dice1.roll();
							dice2.roll();
							combinedRoll = dice1.combine(dice2);
							this.bigBet(bet, bets);
						}
					} else {
						dice1.roll();
						dice2.roll();
						combinedRoll = dice1.combine(dice2);
						this.bigBet(bet, bets);
					}

				}
			} else {
				JOptionPane.showMessageDialog(getContentPane(), "Invalid Bet");
			}
		}
		if (button.getText().equals("Field Bet")) {
			int[] bets = new int[6];
			int numBets = 1;
			String enterBet = JOptionPane.showInputDialog(getContentPane(),
					"Enter Bet: ");
			int bet = Integer.parseInt(enterBet);
			String enterNums = JOptionPane.showInputDialog(getContentPane(),
					"Enter number to bet on: ");
			bets[0] = Integer.parseInt(enterNums);
			int dialogresult = JOptionPane.showConfirmDialog(getContentPane(),
					"Would you like to enter another bet?");
			while (dialogresult == JOptionPane.YES_OPTION) {
				if (money > 0 && bet <= money) {
					if ((bets[numBets - 1] == 2 || bets[numBets - 1] == 3
							|| bets[numBets - 1] == 4 || bets[numBets - 1] == 9
							|| bets[numBets - 1] == 10
							|| bets[numBets - 1] == 11 || bets[numBets - 1] == 12)) {
						enterNums = JOptionPane.showInputDialog(
								getContentPane(), "Enter numbers to bet on: ");
						bets[numBets] = Integer.parseInt(enterNums);
						numBets++;
						dialogresult = JOptionPane.showConfirmDialog(
								getContentPane(),
								"Would you like to bet on another number?");
					}
				} else {
					JOptionPane.showMessageDialog(getContentPane(),
							"Invalid Bet");
					return;
				}
			}
			dice1.roll();
			dice2.roll();
			combinedRoll = dice1.combine(dice2);
			this.fieldBet(bet, bets);
		}
		if (button.getText().equals("Quit Game"))
			quit();

	}

	// ----------------------------------------------------------

	// ----------------------------------------------------------
	/**
	 * If the next roll is a 7 or 11, you win! If the next roll is a 2, 3, or
	 * 12, you lose! Otherwise, the dice continue to be rolled until that number
	 * appears again which result in a win, or a 7 which results in a loss
	 * 
	 * @param bet
	 *            is the player's bet
	 */
	public void passLine(int bet) {
		if (money > 0 && bet <= money && bet >= 0) {
			if (combinedRoll == 7 || combinedRoll == 11) {
				player.loseMoney(bet);
				info.updateMoney();
			} else if (combinedRoll == 2 || combinedRoll == 3
					|| combinedRoll == 12) {
				player.addMoney(bet);
				info.updateMoney();
			} else {
				dice1.roll();
				dice2.roll();
				combinedRoll = dice1.combine(dice2);
				this.passLine(bet);
			}
		}
		count++;
	}

	// ----------------------------------------------------------
	/**
	 * Only after the first roll and if a 7 or 11 is rolled, player wins!
	 * 
	 * @param bet
	 *            is the player's bet
	 */
	public void COME(int bet) {
		if (money > 0 && bet <= money && bet >= 0 && count != 0) {
			if (combinedRoll == 7 || combinedRoll == 11) {
				player.addMoney(bet);
				info.updateMoney();
			} else {
				player.loseMoney(bet);
				info.updateMoney();
			}
		}
	}

	// ----------------------------------------------------------
	/**
	 * Almost opposite of passLine Win if roll is 2 or 3 Lose if roll is 7 or 11
	 * Draw if roll is 12 otherwise roll again
	 * 
	 * @param bet
	 *            is the player's bet
	 */
	public void dontPassLine(int bet) {
		if (money > 0 && bet <= money && bet >= 0) {
			if (combinedRoll == 2 || combinedRoll == 3) {
				player.addMoney(bet);
				info.updateMoney();
			} else if (combinedRoll == 7 || combinedRoll == 11) {
				player.loseMoney(bet);
				info.updateMoney();
			} else if (combinedRoll == 12) {
				player.addMoney(0);
				info.updateMoney();
			} else {
				dice1.roll();
				dice2.roll();
				combinedRoll = dice1.combine(dice2);
				this.dontPassLine(bet);
			}
		}
		count++;
	}

	// ----------------------------------------------------------
	/**
	 * Simple place bets that you can win on next roll minimum bid of 5 for 4,
	 * 5, 9, or 10 minimum bid of 6 for 6 or 8
	 * 
	 * @param num
	 *            is number they place a bet on
	 * @param bet
	 *            is the player's bet
	 */
	public void placeBets(int bet, int... num) {
		for (int num1 : num) {
			if ((money > 0 && bet <= money && bet >= 5)
					&& (num1 == 4 || num1 == 5 || (num1 == 6 && bet >= 6)
							|| (num1 == 8 && bet >= 6) || num1 == 9 || num1 == 10)) {
				if (combinedRoll == num1) {
					player.addMoney(bet);
					info.updateMoney();
				} else {
					player.loseMoney(bet);
					info.updateMoney();
				}
			}
		}
		count++;
	}

	// ----------------------------------------------------------
	/**
	 * Place your bet on a 6 or 8 If that number is rolled before a 7, you win!
	 * Otherwise, you lose!
	 * 
	 * @param num
	 *            is the number they place a bet on
	 * @param bet
	 *            is the player's bet
	 */
	public void bigBet(int bet, int... num) {
		for (int num1 : num) {
			if ((money > 0 && bet <= money && bet >= 0)
					&& (num1 == 6 || num1 == 8)) {
				if (combinedRoll == 7) {
					player.loseMoney(bet);
					info.updateMoney();
				} else if (combinedRoll == num1) {
					player.addMoney(bet);
					info.updateMoney();
				} else {
					dice1.roll();
					dice2.roll();
					combinedRoll = dice1.combine(dice2);
					this.bigBet(bet, num1);
				}
			}
		}
		count++;
	}

	// ----------------------------------------------------------
	/**
	 * A simple field bet that will pay if your number is rolled next If that
	 * number you select is a 2 or 12 then you win double Otherwise, you lose!
	 * 
	 * @param num
	 *            is the number they place a bet on
	 * @param bet
	 *            is the player's bet
	 */
	public void fieldBet(int bet, int... num) {
		for (int num1 : num) {
			if ((money > 0 && bet <= money && bet >= 0)
					&& (num1 == 2 || num1 == 3 || num1 == 4 || num1 == 9
							|| num1 == 10 || num1 == 11 || num1 == 12)) {
				if (combinedRoll == num1) {
					if (num1 == 2 || num1 == 12) {
						player.addMoney(bet);
						info.updateMoney();
					}
					player.addMoney(bet);
					info.updateMoney();
				} else {
					player.loseMoney(bet);
					info.updateMoney();
				}
			}
		}
		count++;
	}

}
