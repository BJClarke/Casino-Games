package com.casino.blackjack;


import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

@SuppressWarnings("serial")
public class Game extends JFrame implements ActionListener
{
	private Deck deck;
	private DiscardPile pile;
	private JPanel center, control;
	private Player player, dealer;
	private InfoPanel info;
	private JButton dealButton, hitButton, discardButton,
		standButton, resetButton;
	
	public static void main( String [] args )
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				new Game();
			}
		});
	}
	
	public Game()
	{
		getContentPane().setLayout(new GridBagLayout());
		setupControl();
		setupContent();
		setTitle("Test Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public void setupControl()
	{
		control = new JPanel(new GridBagLayout());
		control.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		dealButton = new JButton("Deal");
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

		GridBagConstraints gbc_dealButton = new GridBagConstraints();
		gbc_dealButton.insets = new Insets(0, 0, 5, 0);
		gbc_dealButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_dealButton.gridx = 0;
		gbc_dealButton.gridy = 0;
		control.add(dealButton, gbc_dealButton);
		
		GridBagConstraints gbc_hitButton = new GridBagConstraints();
		gbc_hitButton.insets = new Insets(0, 0, 5, 0);
		gbc_hitButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_hitButton.gridx = 0;
		gbc_hitButton.gridy = 1;
		control.add(hitButton, gbc_hitButton);
		
		GridBagConstraints gbc_discardButton = new GridBagConstraints();
		gbc_discardButton.insets = new Insets(0, 0, 5, 0);
		gbc_discardButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_discardButton.gridx = 0;
		gbc_discardButton.gridy = 3;
		control.add(discardButton, gbc_discardButton);
		
		GridBagConstraints gbc_standButton = new GridBagConstraints();
		gbc_standButton.insets = new Insets(0, 0, 5, 0);
		gbc_standButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_standButton.gridx = 0;
		gbc_standButton.gridy = 2;
		control.add(standButton, gbc_standButton);
		
		GridBagConstraints gbc_resetButton = new GridBagConstraints();
		gbc_resetButton.insets = new Insets(0, 0, 5, 0);
		gbc_resetButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_resetButton.gridx = 0;
		gbc_resetButton.gridy = 4;
		control.add(resetButton, gbc_resetButton);
	}
	
	public void setupContent()
	{	
		player = new Player();
		dealer = new Player();
		center = new JPanel(new GridBagLayout());
		deck = new Deck();
		deck.setBorder(new EmptyBorder(5, 5, 5, 5));
		pile = new DiscardPile();
		pile.setBorder(new EmptyBorder(5, 5, 5, 5));
		info = new InfoPanel();
		
		GridBagConstraints gbc_info = new GridBagConstraints();
		gbc_info.gridx = 0;
		gbc_info.gridy = 0;
		gbc_info.gridheight = 3;
		
		GridBagConstraints gbc_dealer = new GridBagConstraints();
		gbc_dealer.gridx = 1;
		gbc_dealer.gridy = 0;
		
		GridBagConstraints gbc_center = new GridBagConstraints();
		gbc_center.gridx = 1;
		gbc_center.gridy = 1;
		
		GridBagConstraints gbc_player = new GridBagConstraints();
		gbc_player.gridx = 1;
		gbc_player.gridy = 2;
		
		GridBagConstraints gbc_control = new GridBagConstraints();
		gbc_control.gridx = 2;
		gbc_control.gridy = 0;
		gbc_control.gridheight = 3;
		
		dealer.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		center.add(deck);
		center.add(pile);
		info.update(deck, player);
		
		getContentPane().add(center, gbc_center);
		getContentPane().add(dealer, gbc_dealer);
		getContentPane().add(player, gbc_player);
		getContentPane().add(info, gbc_info);
		getContentPane().add(control, gbc_control);
	}
	
	public void deal()
	{
		if (deck.getDeck().size() >= 4)
		{
			deck.deal(player);
			deck.deal(dealer);
			dealer.getHand().get(0).flip();
			dealer.refreshImage();
			info.update(deck, player);
			dealButton.setEnabled(false);
			hitButton.setEnabled(true);
			standButton.setEnabled(true);
		}
	}
	
	public void hit()
	{
		deck.hit(player);
		info.update(deck, player);
		if (player.getHand().size() >= 5)
		{
			hitButton.setEnabled(false);
		}
		if (player.checkBust())
		{
			hitButton.setEnabled(false);
			JOptionPane.showMessageDialog(this, "Bust!!");
		}
	}
	
	public void stand()
	{
		dealButton.setEnabled(false);
		hitButton.setEnabled(false);
		standButton.setEnabled(false);
		discardButton.setEnabled(true);
		while(dealer.calculateTotal() < 17)
		{
			deck.hit(dealer);
		}
		dealer.getHand().get(0).flip();
		dealer.refreshImage();
	}
	
	public void discard()
	{
		dealer.discard(pile);
		player.discard(pile);
		info.update(deck, player);
		discardButton.setEnabled(false);
		hitButton.setEnabled(false);
		standButton.setEnabled(false);
		resetButton.setEnabled(true);
		if (deck.getDeck().size() >= 4)
		{
			dealButton.setEnabled(true);
		}
	}
	
	public void reset()
	{
		resetButton.setEnabled(false);
		pile.reset(deck);
		pile.refreshImage();
		info.update(deck, player);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		JButton selection = (JButton)(event.getSource());
		if (selection.getText().equals("Deal"))
		{
			deal();
		}
		if (selection.getText().equals("Hit"))
		{
			hit();
		}
		if (selection.getText().equals("Stand"))
		{
			stand();
		}
		if (selection.getText().equals("Discard"))
		{
			discard();
		}
		if (selection.getText().equals("Reset"))
		{
			reset();
		}
	}
}
