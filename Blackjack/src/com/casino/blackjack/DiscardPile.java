package com.casino.blackjack;

@SuppressWarnings("serial")
public class DiscardPile extends Deck
{
	public void setup()
	{
		
	}
	
	public void reset(Deck deck)
	{
		for (Card card: getDeck())
		{
			if (card.getOrientation())
			{
				card.flip();
			}
			deck.getDeck().add(0, card);
		}
		getDeck().clear();
	}

}
