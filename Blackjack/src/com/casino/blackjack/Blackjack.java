package com.casino.blackjack;


import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Blackjack extends JFrame implements ActionListener
{
	private Deck deck;
	private DiscardPile pile;
	private JPanel center, control;
	private Player player, dealer;
	private InfoPanel info;
	private JButton dealButton, hitButton, discardButton,
		standButton, resetButton, betButton, doubleButton,
		quitButton;
	private JLabel background;
	private int money, bet;
	private String name;
	
	public static void main( String [] args )
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				new Blackjack(500, "Bryan");
			}
		});
	}
	
	public Blackjack(int money, String name)
	{
		this.money = money;
		this.name = name;
		bet = 0;
		getContentPane().setLayout(new GridBagLayout());
		setupControl();
		setupContent();
		setTitle("Blackjack");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public void setupControl()
	{
		control = new JPanel(new GridBagLayout());
		control.setOpaque(false);
		control.setBorder(new EmptyBorder(10, 10, 10, 10));

		dealButton = new JButton("Deal");
		dealButton.setEnabled(false);
		dealButton.addActionListener(this);
		
		hitButton = new JButton("Hit");
		hitButton.setEnabled(false);
		hitButton.addActionListener(this);
		
		discardButton = new JButton("Discard");
		discardButton.setEnabled(false);
		discardButton.addActionListener(this);
		
		standButton = new JButton("Stand");
		standButton.setEnabled(false);
		standButton.addActionListener(this);
		
		resetButton = new JButton("Reset");
		resetButton.setEnabled(false);
		resetButton.addActionListener(this);
		
		betButton = new JButton("Place Bet");
		betButton.addActionListener(this);
		
		doubleButton = new JButton("Double Down");
		doubleButton.setEnabled(false);
		doubleButton.addActionListener(this);
		
		quitButton = new JButton("Quit Game");
		quitButton.addActionListener(this);

		GridBagConstraints gbc_dealButton = new GridBagConstraints();
		gbc_dealButton.insets = new Insets(0, 0, 5, 0);
		gbc_dealButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_dealButton.gridx = 0;
		gbc_dealButton.gridy = 0;
		control.add(dealButton, gbc_dealButton);
		
		GridBagConstraints gbc_hitButton = new GridBagConstraints();
		gbc_hitButton.insets = new Insets(0, 0, 5, 0);
		gbc_hitButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_hitButton.gridx = 1;
		gbc_hitButton.gridy = 0;
		control.add(hitButton, gbc_hitButton);
		
		GridBagConstraints gbc_discardButton = new GridBagConstraints();
		gbc_discardButton.insets = new Insets(0, 0, 5, 0);
		gbc_discardButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_discardButton.gridx = 2;
		gbc_discardButton.gridy = 1;
		control.add(discardButton, gbc_discardButton);
		
		GridBagConstraints gbc_standButton = new GridBagConstraints();
		gbc_standButton.insets = new Insets(0, 0, 5, 0);
		gbc_standButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_standButton.gridx = 2;
		gbc_standButton.gridy = 0;
		control.add(standButton, gbc_standButton);
		
		GridBagConstraints gbc_resetButton = new GridBagConstraints();
		gbc_resetButton.insets = new Insets(0, 0, 5, 0);
		gbc_resetButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_resetButton.gridx = 3;
		gbc_resetButton.gridy = 0;
		control.add(resetButton, gbc_resetButton);
		
		GridBagConstraints gbc_betButton = new GridBagConstraints();
		gbc_betButton.insets = new Insets(0, 0, 5, 0);
		gbc_betButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_betButton.gridx = 0;
		gbc_betButton.gridy = 1;
		control.add(betButton, gbc_betButton);
		
		GridBagConstraints gbc_doubleButton = new GridBagConstraints();
		gbc_doubleButton.insets = new Insets(0, 0, 5, 0);
		gbc_doubleButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_doubleButton.gridx = 1;
		gbc_doubleButton.gridy = 1;
		control.add(doubleButton, gbc_doubleButton);
		
		GridBagConstraints gbc_quitButton = new GridBagConstraints();
		gbc_quitButton.insets = new Insets(0, 0, 5, 0);
		gbc_quitButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_quitButton.gridx = 3;
		gbc_quitButton.gridy = 1;
		control.add(quitButton, gbc_quitButton);
	}
	
	public void setupContent()
	{	
		JPanel empty = new JPanel();
		empty.setBorder(new EmptyBorder(94, 0, 0, 0));
		empty.setOpaque(false);
		player = new Player(money, name);
		player.setBorder(new EmptyBorder(0, 0, 0, 0));
		dealer = new Player();
		dealer.setBorder(new EmptyBorder(0, 0, 0, 0));
		center = new JPanel(new GridBagLayout());
		center.setBorder(new EmptyBorder(0, 0, 0, 0));
		center.setOpaque(false);
		deck = new Deck();
		deck.setBorder(new EmptyBorder(5, 5, 5, 5));
		pile = new DiscardPile();
		pile.setBorder(new EmptyBorder(5, 5, 5, 5));
		info = new InfoPanel(player, this);
		background = new JLabel(new ImageIcon(getClass().getResource("resources/background.png")));
		
		GridBagConstraints gbc_background = new GridBagConstraints();
		gbc_background.gridx = 0;
		gbc_background.gridy = 0;
		gbc_background.gridheight = 6;
		
		GridBagConstraints gbc_empty = new GridBagConstraints();
		gbc_empty.gridx = 0;
		gbc_empty.gridy= 0;
		
		GridBagConstraints gbc_info = new GridBagConstraints();
		gbc_info.gridx = 0;
		gbc_info.gridy = 1;
		
		GridBagConstraints gbc_dealer = new GridBagConstraints();
		gbc_dealer.gridx = 0;
		gbc_dealer.gridy = 2;
		
		GridBagConstraints gbc_center = new GridBagConstraints();
		gbc_center.gridx = 0;
		gbc_center.gridy = 3;
		
		GridBagConstraints gbc_player = new GridBagConstraints();
		gbc_player.gridx = 0;
		gbc_player.gridy = 4;
		
		GridBagConstraints gbc_control = new GridBagConstraints();
		gbc_control.anchor = GridBagConstraints.NORTH;
		gbc_control.gridx = 0;
		gbc_control.gridy = 5;
		
		dealer.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		center.add(deck);
		center.add(pile);
		info.update(deck);
		
		getContentPane().add(empty, gbc_empty);
		getContentPane().add(center, gbc_center);
		getContentPane().add(dealer, gbc_dealer);
		getContentPane().add(player, gbc_player);
		getContentPane().add(info, gbc_info);
		getContentPane().add(control, gbc_control);
		getContentPane().add(background, gbc_background);
	}
	
	public void quit()
	{
		
	}
	
	public void deal()
	{
		if (deck.getDeck().size() >= 4)
		{
			deck.deal(player);
			deck.deal(dealer);
			dealer.getHand().get(0).flip();
			dealer.refreshImage();
			info.update(deck);
			dealButton.setEnabled(false);
			hitButton.setEnabled(true);
			standButton.setEnabled(true);
			if (player.getMoney() >= bet)
				doubleButton.setEnabled(true);
		}
	}
	
	public void hit()
	{
		deck.hit(player);
		info.update(deck);
		if (player.getHand().size() >= 5)
		{
			hitButton.setEnabled(false);
		}
		if (player.checkBust())
		{
			hitButton.setEnabled(false);
			JOptionPane.showMessageDialog(this, "Bust!! No money collected.");
			stand();
		}
		doubleButton.setEnabled(false);
	}
	
	public void stand()
	{
		dealButton.setEnabled(false);
		hitButton.setEnabled(false);
		standButton.setEnabled(false);
		doubleButton.setEnabled(false);
		discardButton.setEnabled(true);
		while(dealer.getTotal() < 17)
		{
			deck.hit(dealer);
		}
		dealer.getHand().get(0).flip();
		dealer.refreshImage();
		if (!player.checkBust())
		{
			if (player.getHand().size() == 5)
			{
				bet = (3 * bet)/2;
				JOptionPane.showMessageDialog(this, "You win $" + bet);
				player.addMoney(bet);
			}
			else 
			{
				if (dealer.checkBust())
				{
					bet = (3 * bet)/2;
					JOptionPane.showMessageDialog(this, "Dealer bust!! You win $" + bet);
					player.addMoney(bet);
				}
				else
				{
					if (player.getTotal() > dealer.getTotal())
					{
						bet = (3 * bet)/2;
						JOptionPane.showMessageDialog(this, "You win $" + bet);
						player.addMoney(bet);
					}
					else if (player.getTotal() == dealer.getTotal())
					{
						JOptionPane.showMessageDialog(this, "You win $" + bet);
						player.addMoney(bet);
					}
					else if (player.getTotal() < dealer.getTotal())
					{
						JOptionPane.showMessageDialog(this, "Dealer has " + dealer.getTotal()
								+ ". No money collected.");
					}
				}
			}
		}
		bet = 0;
		info.update(deck);
	}
	
	public void discard()
	{
		dealer.discard(pile);
		player.discard(pile);
		info.update(deck);
		discardButton.setEnabled(false);
		hitButton.setEnabled(false);
		standButton.setEnabled(false);
		resetButton.setEnabled(true);
		betButton.setEnabled(true);
		doubleButton.setEnabled(false);
	}
	
	public void reset()
	{
		resetButton.setEnabled(false);
	}
	
	public void bet()
	{
		try{
			bet = Integer.parseInt( JOptionPane.showInputDialog(this, "Place a bet: "));
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(this, "Please enter a valid number");
			bet = 0;
		}
		if (bet > player.getMoney())
		{
			bet = 0;
			JOptionPane.showMessageDialog(this, "You don't have that much money.");
		}
		if (bet != 0)
		{
			player.placeBet(bet);
			info.update(deck);
			if (deck.getDeck().size() >= 4)
			{
				dealButton.setEnabled(true);
			}
			betButton.setEnabled(false);
		}
		else
		{
			bet();
		}
	}
	
	public void doubleDown()
	{
		JOptionPane.showMessageDialog(this, "Placing down $" + bet + 
				" more, hitting once, and standing.");
		player.placeBet(bet);
		bet *= 2;
		info.update(deck);
		hit();
		stand();
	}
	
	public int getBet()
	{
		return bet;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		JButton selection = (JButton)(event.getSource());
		if (selection.getText().equals("Deal"))
			deal();
		if (selection.getText().equals("Hit"))
			hit();
		if (selection.getText().equals("Stand"))
			stand();
		if (selection.getText().equals("Discard"))
			discard();
		if (selection.getText().equals("Reset"))
			reset();
		if (selection.getText().equals("Place Bet"))
			bet();
		if (selection.getText().equals("Double Down"))
			doubleDown();
		if (selection.getText().equals("Quit Game"))
			quit();
	}
}
