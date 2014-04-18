package com.casino.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;


@SuppressWarnings("serial")
public class Deck extends JPanel
{
	private List<Card> deck;
	
	public Deck()
	{
		super();
		setOpaque(false);
		setLayout(new OverlayLayout(this));
		deck = new ArrayList<Card>();
		setup();
		shuffle();
		refreshImage();
	}
	
	public void setup()
	{
		for (int i = 51; i >= 0; i--)
		{
			deck.add(new Card(i, false));
		}
	}
	
	public void refreshImage()
	{
		removeAll();
		if(deck.size() <= 0)
		{
			add(new JLabel(new ImageIcon(getClass().getResource("resources/53.png"))));
		}
		else
		{
			for(int i = deck.size()-1; i >= 0; i--)
			{
				add(deck.get(i));
			}
		}
		revalidate();
		repaint();
	}
	
	public void hit(Player player)
	{
		if (player.getHand().size() < 5 && deck.size() > 0)
		{
			deck.get(deck.size()-1).flip();
			player.getHand().add(deck.get(deck.size()-1));
			deck.remove(deck.size()-1);
			player.refreshImage();
			refreshImage();
		}
	}
	
	public void deal(Player player)
	{
		hit(player);
		hit(player);
	}
	
	public List<Card> getDeck()
	{
		return deck;
	}
	
	public void shuffle()
	{
		Card placeholder = new Card();
		int j;
		Random generator = new Random();
		for (int i = deck.size()-1; i > 0; i--)
		{
			j = generator.nextInt(i+1);
			placeholder = deck.get(j);
			deck.set(j, deck.get(i));
			deck.set(i, placeholder);
		}
	}
	
	
}
