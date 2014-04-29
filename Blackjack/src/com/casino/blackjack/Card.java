package com.casino.blackjack;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Card extends JLabel
{	
	
	private int id;
	private int value = 0;
	private boolean faceUp;
	
	public Card()
	{
		super();
	}
	
	public Card(int id, boolean faceUp)
	{
		super();
		this.id = id;
		this.faceUp = faceUp;
		idToValue();
		setImage(id, faceUp);
	}
	
	public void idToValue()
	{
		int valueList[] = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10,
				11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10,
				11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10,
				11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };
		
		for (int i = 0; i < 52; i++)
		{
			if (id == i)
			{
				value = valueList[i];
				break;
			}
		}
	}
	
	public void setImage(int id, boolean faceUp)
	{
		if (faceUp)
		{
			setIcon(new ImageIcon(getClass().getResource("resources/"+id+".png")));
		}
		else
		{
			setIcon(new ImageIcon(getClass().getResource("resources/52.png")));
		}
		revalidate();
		repaint();
	}
	
	public void flip()
	{
		faceUp = !faceUp;
		setImage(id, faceUp);
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setValue(int value)
	{
		this.value = value;
	}
	
	public boolean getOrientation()
	{
		return faceUp;
	}

}
