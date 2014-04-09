
import java.util.*;

public class Deck
{

	private List<Card> deck;
	
	/**
	 * Default Constructor creates a
	 * standard 52 card deck
	 */
	public Deck()
	{
		deck = new ArrayList<Card>();
		addCompleteSuit('D');
		addCompleteSuit('C');
		addCompleteSuit('H');
		addCompleteSuit('S');
	}
	
	/**
	 * Allows the user to use the
	 * current deck
	 * 
	 * @return The current deck list
	 */
	public List<Card> getDeck()
	{
		return deck;
	}
	
	/**
	 * Adds all the values of
	 * a given suit of cards to
	 * the deck
	 * 
	 * @param suit Suit of cards added
	 */
	public void addCompleteSuit(char suit)
	{
		String type = "";
		deck.add(new Card("A"+suit, false));
		for (int i = 2; i < 11; i++)
		{
			type = type + i + suit;
			deck.add(new Card(type, false));
			type = "";
		}
		deck.add(new Card("J"+suit, false));
		deck.add(new Card("Q"+suit, false));
		deck.add(new Card("K"+suit, false));
	}
	
	/**
	 * Shuffles the given deck
	 */
	public void shuffleDeck()
	{
		Card placeholder = new Card();
		int index;
		Random generator = new Random();
		for (int i = deck.size()-1; i > 0; i--)
		{
			index = generator.nextInt(i+1);
			placeholder = deck.get(index);
			deck.set(index, deck.get(i));
			deck.set(i, placeholder);
		}
	}
	
	/**
	 * Allows a card at a given
	 * location to be used
	 * 
	 * @param i The index of the deck
	 * @return The card at the index
	 */
	public Card getCardAt(int i)
	{
		return deck.get(i);
	}

}
