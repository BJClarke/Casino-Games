package com.casino;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.casino.blackjack.Blackjack;
import com.casino.craps.Craps;
import com.casino.slots.Slots;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Game extends JFrame implements ActionListener {

	private static final String PATH = System.getenv("APPDATA")
			+ "/Casino/saves/";
	private JPanel dataManagement, games;
	private InfoPanel info;
	private JButton newGame, loadGame, saveGame, craps, blackjack, slots,
			roulette;
	private ImageIcon crapsImage, crapsImageH, blackjackImage, blackjackImageH,
			slotsImage, slotsImageH, rouletteImage, rouletteImageH, newImage,
			newImageH, loadImage, loadImageH, saveImage, saveImageH;
	private String name;
	private int money;

	public static void main(String[] args) {
		(new File(PATH)).mkdirs();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Game();
			}
		});
	}

	public Game() {
		super();
		setTitle("Casino Royale");
		setupContent();
		craps.setEnabled(false);
		blackjack.setEnabled(false);
		slots.setEnabled(false);
		roulette.setEnabled(false);
		saveGame.setEnabled(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}

	public Game(int money, String name) {
		super();
		setTitle("Casino Royale");
		this.money = money;
		this.name = name;
		setupContent();
		roulette.setEnabled(false);
		info.updateMoney(money);
		info.updateName(name);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}

	public void setupContent() {
		getContentPane().setLayout(new GridBagLayout());

		info = new InfoPanel();
		info.setBorder(new EmptyBorder(0, 0, 65, 0));

		dataManagement = new JPanel();
		dataManagement.setBorder(new EmptyBorder(75, 0, 0, 0));
		dataManagement.setOpaque(false);

		games = new JPanel(new GridBagLayout());
		games.setOpaque(false);

		newImage = new ImageIcon(getClass().getResource("resources/New.png"));
		newImageH = new ImageIcon(getClass().getResource(
				"resources/NewHighlighted.png"));
		loadImage = new ImageIcon(getClass().getResource("resources/Load.png"));
		loadImageH = new ImageIcon(getClass().getResource(
				"resources/LoadHighlighted.png"));
		saveImage = new ImageIcon(getClass().getResource("resources/Save.png"));
		saveImageH = new ImageIcon(getClass().getResource(
				"resources/SaveHighlighted.png"));
		crapsImage = new ImageIcon(getClass()
				.getResource("resources/Craps.png"));
		crapsImageH = new ImageIcon(getClass().getResource(
				"resources/CrapsHighlighted.png"));
		blackjackImage = new ImageIcon(getClass().getResource(
				"resources/Blackjack.png"));
		blackjackImageH = new ImageIcon(getClass().getResource(
				"resources/BlackjackHighlighted.png"));
		slotsImage = new ImageIcon(getClass()
				.getResource("resources/Slots.png"));
		slotsImageH = new ImageIcon(getClass().getResource(
				"resources/SlotsHighlighted.png"));
		rouletteImage = new ImageIcon(getClass().getResource(
				"resources/Roulette.png"));
		rouletteImageH = new ImageIcon(getClass().getResource(
				"resources/RouletteHighlighted.png"));
		JLabel background = new JLabel(new ImageIcon(getClass().getResource(
				"resources/background.png")));

		newGame = new JButton(newImage);
		newGame.setOpaque(false);
		newGame.setContentAreaFilled(false);
		newGame.setBorderPainted(false);
		newGame.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				newGame.setIcon(newImageH);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				newGame.setIcon(newImage);
			}
		});
		newGame.addActionListener(this);

		loadGame = new JButton(loadImage);
		loadGame.setOpaque(false);
		loadGame.setContentAreaFilled(false);
		loadGame.setBorderPainted(false);
		loadGame.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				loadGame.setIcon(loadImageH);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				loadGame.setIcon(loadImage);
			}
		});
		loadGame.addActionListener(this);

		saveGame = new JButton(saveImage);
		saveGame.setOpaque(false);
		saveGame.setContentAreaFilled(false);
		saveGame.setBorderPainted(false);
		saveGame.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				saveGame.setIcon(saveImageH);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				saveGame.setIcon(saveImage);
			}
		});
		saveGame.addActionListener(this);

		craps = new JButton(crapsImage);
		craps.setOpaque(false);
		craps.setContentAreaFilled(false);
		craps.setBorderPainted(false);
		craps.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				craps.setIcon(crapsImageH);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				craps.setIcon(crapsImage);
			}
		});
		craps.addActionListener(this);

		blackjack = new JButton(blackjackImage);
		blackjack.setOpaque(false);
		blackjack.setContentAreaFilled(false);
		blackjack.setBorderPainted(false);
		blackjack.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				blackjack.setIcon(blackjackImageH);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				blackjack.setIcon(blackjackImage);
			}
		});
		blackjack.addActionListener(this);

		slots = new JButton(slotsImage);
		slots.setOpaque(false);
		slots.setContentAreaFilled(false);
		slots.setBorderPainted(false);
		slots.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				slots.setIcon(slotsImageH);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				slots.setIcon(slotsImage);
			}
		});
		slots.addActionListener(this);

		roulette = new JButton(rouletteImage);
		roulette.setOpaque(false);
		roulette.setContentAreaFilled(false);
		roulette.setBorderPainted(false);
		roulette.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				roulette.setIcon(rouletteImageH);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				roulette.setIcon(rouletteImage);
			}
		});
		roulette.addActionListener(this);

		GridBagConstraints gbc_background = new GridBagConstraints();
		gbc_background.gridx = 0;
		gbc_background.gridy = 0;
		gbc_background.gridheight = 3;

		GridBagConstraints gbc_info = new GridBagConstraints();
		gbc_info.gridx = 0;
		gbc_info.gridy = 2;

		GridBagConstraints gbc_games = new GridBagConstraints();
		gbc_games.gridx = 0;
		gbc_games.gridy = 1;

		GridBagConstraints gbc_data = new GridBagConstraints();
		gbc_data.gridx = 0;
		gbc_data.gridy = 0;

		GridBagConstraints gbc_craps = new GridBagConstraints();
		gbc_craps.gridx = 0;
		gbc_craps.gridy = 0;

		GridBagConstraints gbc_blackjack = new GridBagConstraints();
		gbc_blackjack.gridx = 1;
		gbc_blackjack.gridy = 0;

		GridBagConstraints gbc_slots = new GridBagConstraints();
		gbc_slots.gridx = 0;
		gbc_slots.gridy = 1;

		GridBagConstraints gbc_roulette = new GridBagConstraints();
		gbc_roulette.gridx = 1;
		gbc_roulette.gridy = 1;

		dataManagement.add(newGame);
		dataManagement.add(loadGame);
		dataManagement.add(saveGame);

		games.add(craps, gbc_craps);
		games.add(blackjack, gbc_blackjack);
		games.add(roulette, gbc_roulette);
		games.add(slots, gbc_slots);

		getContentPane().add(info, gbc_info);
		getContentPane().add(games, gbc_games);
		getContentPane().add(dataManagement, gbc_data);
		getContentPane().add(background, gbc_background);
	}

	public void newGame() {
		money = 1000;
		name = JOptionPane.showInputDialog(this, "Please enter your name:");
		if (name == null) {
			return;
		}
		File savegame = new File(PATH + name + ".csg");
		if (savegame.exists()) {
			Object[] options = { "Yes", "No" };
			int option = JOptionPane.showOptionDialog(this,
					"Save game already exists. Overwrite?", "Save game exists",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, options, options[1]);
			if (option == 1) {
				return;
			}
		}
		try {
			savegame.createNewFile();
			saveGame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		info.updateMoney(money);
		info.updateName(name);
		craps.setEnabled(true);
		blackjack.setEnabled(true);
		slots.setEnabled(true);
		saveGame.setEnabled(true);
	}

	public void loadGame() {
		String filename = JOptionPane.showInputDialog(this,
				"Please enter your name:");
		if (filename == null) {
			return;
		}
		File savegame = new File(PATH + filename + ".csg");
		try {
			Scanner input = new Scanner(savegame);
			name = input.nextLine();
			money = input.nextInt();
			input.close();
		} catch (FileNotFoundException e) {
			Object[] options = { "Yes", "No" };
			int option = JOptionPane.showOptionDialog(this,
					"Save game does not exist. Create a new one?",
					"File does not exist", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (option == 0) {
				try {
					savegame.createNewFile();
					name = filename;
					money = 1000;
					saveGame();
				} catch (IOException ioex) {
					System.err.println("Could not create file");
					ioex.printStackTrace();
					return;
				}
			} else {
				return;
			}
		}
		info.updateMoney(money);
		info.updateName(name);
		craps.setEnabled(true);
		blackjack.setEnabled(true);
		slots.setEnabled(true);
		saveGame.setEnabled(true);
	}

	public void saveGame() {
		File savegame = new File(PATH + name + ".csg");
		try {
			PrintWriter output = new PrintWriter(savegame);
			output.println(name);
			output.println(money);
			output.close();
			JOptionPane.showMessageDialog(this, "Game Saved");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error saving game:");
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JButton selection = (JButton) (event.getSource());
		if (selection.equals(craps)) {
			new Craps(money, name);
			this.dispose();
		}
		if (selection.equals(blackjack)) {
			new Blackjack(money, name);
			this.dispose();
		}
		if (selection.equals(slots)) {
			new Slots(money, name);
			this.dispose();
		}
		if (selection.equals(newGame)) {
			newGame();
		}
		if (selection.equals(loadGame)) {
			loadGame();
		}
		if (selection.equals(saveGame)) {
			saveGame();
		}
	}

}
