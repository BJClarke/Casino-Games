
public class Card
{
	
	private String name;
	private int value;
	private char suit;
	private boolean faceUp;
	
	/**
	 * Default constructor for Card
	 * 
	 * Sets Card to Blank with a
	 * value of 0
	 */
	public Card()
	{
		name = "Blank Card";
		value = 0;
		suit = ' ';
		faceUp = false;
	}
	
	/**
	 * Card constructor that takes a
	 * type in the form '5C' and a
	 * an orientation of the Card
	 * 
	 * @param type String in the form '6D'
	 * @param faceUp Is the card face up?
	 */
	public Card(String type, boolean faceUp)
	{
		if (type.length() == 2)
		{
			value = convertValue((int)type.charAt(0));
			suit = type.charAt(1);
			name = convertName((int)type.charAt(0), suit);
		}
		else if (type.length() == 3)
		{
			value = 10;
			suit = type.charAt(2);
			name = "10 of " + convertSuit(suit);
		}
		else
		{
			name = "Blank Card";
			value = 0;
			suit = ' ';
		}
		this.faceUp = faceUp;
	}
	
	/**
	 * Allows the user to use the name
	 * 
	 * @return String of the current Name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Allows the user to use the value
	 * 
	 * @return Integer of the current value
	 */
	public int getValue()
	{
		return value;
	}
	
	/**
	 * Allows the user to use the suit
	 * 
	 * @return Char of the current suit
	 */
	public char getSuit()
	{
		return suit;
	}
	
	/**
	 * Allows the see if the Card
	 * is face up
	 * 
	 * @return Is the card face up?
	 */
	public boolean isFaceUp()
	{
		return faceUp;
	}
	
	/**
	 * Allows the user to flip the
	 * Card
	 * 
	 * @param faceUp New orientation
	 */
	public void setFaceUp(boolean faceUp)
	{
		this.faceUp = faceUp;
	}
	
	/**
	 * Converts the ASCII value of card
	 * to a real card value.
	 * 
	 * @param value ASCII value of card
	 * @return Actual Integer card value
	 */
	public int convertValue(int value)
	{
		int[] asciiValue = { 50, 51, 52, 53, 54, 55, 56, 57, 65, 74, 75, 81 };
		int[] realValue = { 2, 3, 4, 5, 6, 7, 8, 9, 1, 10, 10, 10};
		for (int i = 0; i < 12; i++)
		{
			if (value == asciiValue[i])
			{
				return realValue[i];
			}
		}
		return 0;
	}
	
	/**
	 * Converts a char version of
	 * the suit to a String version
	 * 
	 * @param suit Char version of the suit
	 * @return String version of the suit
	 */
	public String convertSuit(char suit)
	{
		if (suit == 'C')
		{
			return "Clubs";
		}
		else if (suit == 'D')
		{
			return "Diamonds";
		}
		else if (suit == 'H')
		{
			return "Hearts";
		}
		else if (suit == 'S')
		{
			return "Spades";
		}
		else
		{
			return "Invalid Card Type";
		}
	}
	
	/**
	 * Converts the ASCII value of the card
	 * and char version of the suit to a
	 * readable String version of the name.
	 * 
	 * e.g. '5C' -> "5 of Clubs"
	 * 		'AD' -> "Ace of Diamonds"
	 * 
	 * @param value ASCII value of the card
	 * @param suit Char version of the suit
	 * @return String version of the name
	 */
	public String convertName(int value, char suit)
	{
		String name = "";
		if (value == 65)
		{
			name += "Ace ";
		}
		else if (value >= 50 && value <= 57)
		{
			value -= 48;
			name = name + value + " ";
		}
		else if (value == 74)
		{
			name += "Jack ";
		}
		else if (value == 75)
		{
			name += "King ";
		}
		else if (value == 81)
		{
			name += "Queen ";
		}
		else
		{
			name += "Invalid Value ";
		}
		name = name + "of " + convertSuit(suit);
		
		return name;
	}
}
