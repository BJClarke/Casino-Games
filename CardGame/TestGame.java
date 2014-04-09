import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestGame implements ActionListener
{
	JTextArea output;
	JScrollPane scrollPane;
	private static Deck deck;
	private static int topOfDeck = 0;
	
	public static void main(String [] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				runEnvironment();
			}
		});
	}
	
	public JMenuBar setupMenu()
	{
		//Creates MenuBar
		JMenuBar menubar = new JMenuBar();
		menubar.setPreferredSize(new Dimension(200, 20));
		
		//Creates and Adds Command Menu
		JMenu menu = new JMenu("Commands");
		menubar.add(menu);
		menu.setMnemonic(KeyEvent.VK_O);
		
		//Creates Command Options
		JMenuItem nextCommand = new JMenuItem("Next Card", KeyEvent.VK_N);
		nextCommand.addActionListener(this);
		JMenuItem shuffleCommand = new JMenuItem("Shuffle Deck", KeyEvent.VK_S);
		shuffleCommand.addActionListener(this);
		JMenuItem resetCommand = new JMenuItem("Reset Deck", KeyEvent.VK_R);
		resetCommand.addActionListener(this);
		JMenuItem displayCommand = new JMenuItem("Display Deck", KeyEvent.VK_D);
		displayCommand.addActionListener(this);
		JMenuItem sizeCommand = new JMenuItem("Deck Size", KeyEvent.VK_E);
		sizeCommand.addActionListener(this);
		JMenuItem clearCommand = new JMenuItem("Clear Output", KeyEvent.VK_C);
		clearCommand.addActionListener(this);
		
		//Adds Command Options to the Menu
		menu.add(nextCommand);
		menu.add(shuffleCommand);
		menu.add(resetCommand);
		menu.add(displayCommand);
		menu.add(sizeCommand);
		menu.add(clearCommand);
		
		return menubar;
	}
	
	public Container setupContentPane()
	{
		JPanel content = new JPanel(new BorderLayout());
		
		output = new JTextArea(5, 30);
		output.setEditable(false);
		scrollPane = new JScrollPane(output);
		
		content.add(scrollPane, BorderLayout.CENTER);
		
		return content;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		JMenuItem selection = (JMenuItem)(event.getSource());
		if (selection.getText().equals("Next Card"))
		{
			if (deck.getDeck().size() > 0)
			{
				output.setText(deck.getCardAt(topOfDeck).getName() + "\n");
				deck.getDeck().remove(topOfDeck);
			}
			else
			{
				output.setText("Deck is empty" + "\n");
			}
		}
		if (selection.getText().equals("Shuffle Deck"))
		{
			deck.shuffleDeck();
		}
		if (selection.getText().equals("Reset Deck"))
		{
			deck = new Deck();
		}
		if (selection.getText().equals("Display Deck"))
		{
			output.setText(null);
			for (int i = 0; i < deck.getDeck().size(); i++)
			{
				output.append(deck.getCardAt(i).getName() + "\n");
			}
		}
		if (selection.getText().equals("Deck Size"))
		{
			output.append("" + deck.getDeck().size() + " Cards Left\n");
		}
		if (selection.getText().equals("Clear Output"))
		{
			output.setText(null);
		}
		
	}
	
	public static void runEnvironment()
	{
		//Creates Frame
		deck = new Deck();
		
		JFrame frame = new JFrame("Test Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TestGame game = new TestGame();
		frame.setJMenuBar(game.setupMenu());
		frame.setContentPane(game.setupContentPane());
		
		
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
}

