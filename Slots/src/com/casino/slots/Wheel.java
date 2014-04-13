package com.casino.slots;

import java.util.Random;

public class Wheel {

	Image type;

	private enum Image {
		A, B, C, D, E,
	}

	public Wheel() {
		type = null;
	}

	/**
	 * turns the wheel
	 */
	public void turnWheel() {
		Random generator = new Random();
		int x = generator.nextInt(100);

		if (x < 10) // 10% chance
		{
			type = Image.A;
		} else if (x < 25) // 15% chance
		{
			type = Image.B;
		} else if (x < 45) // 20% chance
		{
			type = Image.C;
		} else if (x < 70) // 25% chance
		{
			type = Image.D;
		} else // 30% chance
		{
			type = Image.E;
		}
	}

	/**
	 * returns the type of the wheel
	 * 
	 * @return type the type of the wheel aka the image on it
	 */
	public Image getType() {
		return type;

	}
}
