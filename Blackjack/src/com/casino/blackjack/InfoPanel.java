package com.casino.blackjack;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class InfoPanel extends JPanel
{
	private Blackjack game;
	private Player player;
	private JLabel playerName, playerTotal, playerMoney, currentBet;
	private JLabel deckSize;
	private GridBagConstraints gbc_playerTotal, gbc_size,
		gbc_playerName, gbc_playerMoney, gbc_currentBet;
	
	public InfoPanel(Player player, Blackjack game)
	{
		super();
		this.player = player;
		this.game = game;
		setOpaque(false);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new GridBagLayout());
		setup();
	}
	
	public void setup()
	{
		removeAll();
		
		GridBagConstraints gbc_name = new GridBagConstraints();
		JLabel name = new JLabel("Player: ");
		gbc_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_name.gridx = 0;
		gbc_name.gridy = 0;
		
		GridBagConstraints gbc_money = new GridBagConstraints();
		JLabel money = new JLabel("Money: $");
		gbc_money.fill = GridBagConstraints.HORIZONTAL;
		gbc_money.gridx = 2;
		gbc_money.gridy = 0;
		
		GridBagConstraints gbc_total = new GridBagConstraints();
		JLabel total = new JLabel("Total: ");
		gbc_total.fill = GridBagConstraints.HORIZONTAL;
		gbc_total.gridx = 4;
		gbc_total.gridy = 0;
		
		GridBagConstraints gbc_bet = new GridBagConstraints();
		JLabel bet = new JLabel("Current Bet: $");
		gbc_bet.fill = GridBagConstraints.HORIZONTAL;
		gbc_bet.gridx = 6;
		gbc_bet.gridy = 0;
		
		GridBagConstraints gbc_deck = new GridBagConstraints();
		JLabel cardsLeft = new JLabel("Deck: ");
		gbc_deck.fill = GridBagConstraints.HORIZONTAL;
		gbc_deck.gridx = 8;
		gbc_deck.gridy = 0;
		
		gbc_playerName = new GridBagConstraints();
		playerName= new JLabel(player.getName() + " ");
		gbc_playerName.fill = GridBagConstraints.HORIZONTAL;
		gbc_playerName.gridx = 1;
		gbc_playerName.gridy = 0;
		
		gbc_playerMoney = new GridBagConstraints();
		playerMoney = new JLabel(player.getMoney() + " ");
		gbc_playerMoney.fill = GridBagConstraints.HORIZONTAL;
		gbc_playerMoney.gridx = 3;
		gbc_playerMoney.gridy = 0;
		
		gbc_playerTotal = new GridBagConstraints();
		playerTotal = new JLabel("0 ");
		gbc_playerTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_playerTotal.gridx = 5;
		gbc_playerTotal.gridy = 0;
		
		gbc_currentBet = new GridBagConstraints();
		currentBet = new JLabel("0 ");
		gbc_currentBet.fill = GridBagConstraints.HORIZONTAL;
		gbc_currentBet.gridx = 7;
		gbc_currentBet.gridy = 0;
		
		gbc_size = new GridBagConstraints();
		deckSize = new JLabel("52 ");
		gbc_size.fill = GridBagConstraints.HORIZONTAL;
		gbc_size.gridx = 9;
		gbc_size.gridy = 0;
		
		add(name, gbc_name);
		add(money, gbc_money);
		add(total, gbc_total);
		add(bet, gbc_bet);
		add(cardsLeft, gbc_deck);
		add(playerName, gbc_playerName);
		add(playerMoney, gbc_playerMoney);
		add(playerTotal, gbc_playerTotal);
		add(currentBet, gbc_currentBet);
		add(deckSize, gbc_size);
		
		revalidate();
		repaint();
	}
	
	public void updateTotal()
	{
		remove(playerTotal);
		playerTotal = new JLabel(""+player.getTotal()+ " ");
		add(playerTotal, gbc_playerTotal);
	}
	
	public void updateSize(Deck deck)
	{
		remove(deckSize);
		deckSize = new JLabel(deck.getDeck().size()+ " ");
		add(deckSize, gbc_size);
	}
	
	public void updateMoney()
	{
		remove(playerMoney);
		playerMoney = new JLabel(player.getMoney()+ " ");
		add(playerMoney, gbc_playerMoney);
	}
	
	public void updateBet()
	{
		remove(currentBet);
		currentBet = new JLabel(game.getBet()+" ");
		add(currentBet, gbc_currentBet);
	}
	
	public void update(Deck deck)
	{
		updateTotal();
		updateSize(deck);
		updateMoney();
		updateBet();
		revalidate();
		repaint();
	}
	
	public int getTotal()
	{
		return Integer.parseInt(playerTotal.getText());
	}
	
}
