package com.casino.blackjack;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class InfoPanel extends JPanel
{
	
	private JLabel total;
	private JLabel deckSize;
	private GridBagConstraints gbc_total;
	private GridBagConstraints gbc_size;
	
	public InfoPanel()
	{
		super();
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new GridBagLayout());
		setup();
	}
	
	public void setup()
	{
		removeAll();
		
		GridBagConstraints gbc_name = new GridBagConstraints();
		JLabel name = new JLabel("Player");
		gbc_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_name.gridx = 0;
		gbc_name.gridy = 0;
		
		GridBagConstraints gbc_totalDisplay = new GridBagConstraints();
		JLabel totalDisplay = new JLabel("Total: ");
		gbc_totalDisplay.fill = GridBagConstraints.HORIZONTAL;
		gbc_totalDisplay.gridx = 0;
		gbc_totalDisplay.gridy = 1;
		
		GridBagConstraints gbc_deck = new GridBagConstraints();
		JLabel cardsLeft = new JLabel("Deck: ");
		gbc_deck.fill = GridBagConstraints.HORIZONTAL;
		gbc_deck.gridx = 0;
		gbc_deck.gridy = 2;
		
		gbc_total = new GridBagConstraints();
		total = new JLabel("0");
		gbc_total.fill = GridBagConstraints.HORIZONTAL;
		gbc_total.gridx = 1;
		gbc_total.gridy = 1;
		
		gbc_size = new GridBagConstraints();
		deckSize = new JLabel("52");
		gbc_size.fill = GridBagConstraints.HORIZONTAL;
		gbc_size.gridx = 1;
		gbc_size.gridy = 2;
		
		add(name, gbc_name);
		add(totalDisplay, gbc_totalDisplay);
		add(cardsLeft, gbc_deck);
		add(total, gbc_total);
		add(deckSize, gbc_size);
		
		revalidate();
		repaint();
	}
	
	public void updateTotal(Player player)
	{
		remove(total);
		int playerTotal = 0;
		for (int i = 0; i < player.getHand().size(); i++)
		{
			playerTotal += player.getHand().get(i).getValue();
		}
		total = new JLabel(""+playerTotal);
		add(total, gbc_total);
	}
	
	public void updateSize(Deck deck)
	{
		remove(deckSize);
		deckSize = new JLabel(""+deck.getDeck().size());
		add(deckSize, gbc_size);
	}
	
	public void update(Deck deck, Player player)
	{
		updateTotal(player);
		updateSize(deck);
		revalidate();
		repaint();
	}
	
	public int getTotal()
	{
		return Integer.parseInt(total.getText());
	}
	
}
