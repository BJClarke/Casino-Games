package com.casino.slots;

import java.util.Random;

public class Wheel {

	private Symbol type;

	public Wheel() 
	{
		type = new Symbol();
	}

	/**
	 * turns the wheel
	 */
	public void turnWheel() {
		Random generator = new Random();
		int x = generator.nextInt(100);

		if (x < 10) // 10% chance
		{
			type.setID(1);
		} else if (x < 25) // 15% chance
		{
			type.setID(2);
		} else if (x < 45) // 20% chance
		{
			type.setID(3);
		} else if (x < 70) // 25% chance
		{
			type.setID(4);
		} else // 30% chance
		{
			type.setID(5);
		}
	}

	/**
	 * returns the type of the wheel
	 * 
	 * @return type the type of the wheel aka the image on it
	 */
	public int getType() {
		return type.getID();

	}
}
