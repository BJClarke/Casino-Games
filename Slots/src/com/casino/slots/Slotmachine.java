package com.casino.slots;

public class Slotmachine {

	// Fields ------------------------------------------------------
	private Wheel wheel1;
	private Wheel wheel2;
	private Wheel wheel3;

	// Constructor --------------------------------------------------
	public Slotmachine() {
		wheel1 = new Wheel();
		wheel2 = new Wheel();
		wheel3 = new Wheel();
	}

	// Methods-------------------------------------------------------

	/**
	 * this just turns all three wheels
	 */
	public void turnAll() {
		wheel1.turnWheel();
		wheel2.turnWheel();
		wheel3.turnWheel();

	}

	/**
	 * boolean to determine if you got a 3 match
	 * 
	 * @return true if there is a 3 image match false otherwise
	 */
	public boolean match() {

		if (wheel1.getType() == wheel2.getType()
				&& wheel2.getType() == wheel3.getType()
				&& wheel3.getType() == wheel1.getType()) {
			return true;
		}

		return false;
	}

}
