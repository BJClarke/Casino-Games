package com.casino.blackjack;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Player extends JPanel
{
	private List<Card> hand;
	
	public Player()
	{
		super();
		hand = new ArrayList<Card>();
		refreshImage();
	}
	
	public void refreshImage()
	{
		removeAll();
		for (int i = 0; i < 5; i++)
		{
			if (i >= hand.size())
			{
				add(new JLabel(new ImageIcon(getClass().getResource("resources/53.png"))));
			}
			else
			{
				add(hand.get(i));
			}
		}
		revalidate();
		repaint();
	}
	
	public void discard(DiscardPile pile)
	{
		while(hand.size() > 0)
		{
			pile.getDeck().add(hand.get(0));
			hand.remove(0);
		}
		pile.refreshImage();
		refreshImage();
	}
	
	public int calculateTotal()
	{
		int total = 0;
		for (int i = 0; i < hand.size(); i++)
		{
			total += hand.get(i).getValue();
		}
		return total;
	}
	
	public boolean checkBust()
	{
		int total = 0;
		for (int i = 0; i < hand.size(); i++)
		{
			total += hand.get(i).getValue();
		}
		return total > 21;
	}
	
	public List<Card> getHand()
	{
		return hand;
	}
}
