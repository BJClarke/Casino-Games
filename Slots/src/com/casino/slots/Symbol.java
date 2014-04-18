package com.casino.slots;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Symbol extends JLabel {

	private int id;
	
	public Symbol()
	{
		id = 0; 
	}
	/**
	 * changes the ID of the symbol 
	 * @param newID
	 */
	public void setID(int newID)
	{
		id = newID; 
	}
	
	public int getID()  
	{
		return id; 
	}

	
}
