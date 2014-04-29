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
	private int total;
	private int money;
	private String name;
	
	public Player()
	{
		super();
		hand = new ArrayList<Card>();
		setOpaque(false);
		resetTotal();
		refreshImage();
	}
	
	public Player(int money, String name)
	{
		super();
		this.money = money;
		this.name = name;
		hand = new ArrayList<Card>();
		setOpaque(false);
		resetTotal();
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
	
	public void changeAce()
	{
		for (int i = 0; i < hand.size(); i++)
		{
			if (hand.get(i).getValue() == 11)
			{
				hand.get(i).setValue(1);
				break;
			}
		}
	}
	
	public int calculateTotal()
	{
		total = 0;
		for (int i = 0; i < hand.size(); i++)
		{
			total+= hand.get(i).getValue();
		}
		return total;
	}
	
	public boolean checkBust()
	{
		return getTotal() > 21;
	}
	
	public void resetTotal()
	{
		total = 0;
	}
	
	public List<Card> getHand()
	{
		return hand;
	}
	
	public int getTotal()
	{
		calculateTotal();
		if (total > 21)
		{
			changeAce();
			calculateTotal();
		}
		return total;
	}
	
	public int getMoney()
	{
		return money;
	}
	
	public void addMoney(int moreMoney)
	{
		money += moreMoney;
	}
	
	public void placeBet(int bet)
	{
		money -= bet;
	}
	
	public String getName()
	{
		return name;
	}
}
