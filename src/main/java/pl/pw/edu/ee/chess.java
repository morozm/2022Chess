package pl.pw.edu.ee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;

import pl.pw.edu.ee.figures.bishop;
import pl.pw.edu.ee.figures.figure;
import pl.pw.edu.ee.figures.king;
import pl.pw.edu.ee.figures.knight;
import pl.pw.edu.ee.figures.pawn;
import pl.pw.edu.ee.figures.queen;
import pl.pw.edu.ee.figures.rook;

public class chess implements ActionListener {

	settings defaultSettings = new settings();
	board defaultBoard = new board();
	// move defaultMove = new move();

	Random random = new Random();
	boolean whiteTurn = true;
	int turnNumber = 1;
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JLabel textfield = new JLabel();
	Border border = BorderFactory.createLineBorder(new Color(222, 155, 53), 3);
	JButton[][] buttons = new JButton[defaultSettings.boardLength][defaultSettings.boardWidth];
	figure[][] board = new figure[defaultSettings.boardLength][defaultSettings.boardWidth];
	boolean buttonIsSelected = false;
	int selectedButtonY;
	int selectedButtonX;
	Color defaultColor = UIManager.getColor("Panel.background");

	String[] pieces = { "bP", "bR", "bN", "bB", "bK", "bQ", "wP", "wR", "wN", "wB", "wK", "wQ" };
	ImageIcon[] piecesIcons = {
			new ImageIcon("src\\pieces\\bP.png"),
			new ImageIcon("src\\pieces\\bR.png"),
			new ImageIcon("src\\pieces\\bN.png"),
			new ImageIcon("src\\pieces\\bB.png"),
			new ImageIcon("src\\pieces\\bK.png"),
			new ImageIcon("src\\pieces\\bQ.png"),
			new ImageIcon("src\\pieces\\wP.png"),
			new ImageIcon("src\\pieces\\wR.png"),
			new ImageIcon("src\\pieces\\wN.png"),
			new ImageIcon("src\\pieces\\wB.png"),
			new ImageIcon("src\\pieces\\wK.png"),
			new ImageIcon("src\\pieces\\wQ.png") };

	chess() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.setTitle("Best Chess Ever Made");
		frame.setResizable(false); // cant resize window
		frame.setIconImage(piecesIcons[10].getImage());
		frame.getContentPane().setBackground(new Color(50, 50, 50)); // ???
		frame.setLayout(new BorderLayout());
		//frame.setLayout(null);
		frame.setVisible(true);

		textfield.setBackground(new Color(12, 15, 18));
		textfield.setForeground(new Color(222, 155, 53));
		textfield.setFont(new Font("Counter-Strike Regular", Font.PLAIN, 50));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Wie Pan Co To Znaczy Trappin?");
		textfield.setOpaque(true);
		textfield.setBorder(border);

		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0, 0, 900, 100);

		button_panel.setLayout(new GridLayout(defaultSettings.boardWidth, defaultSettings.boardLength));
		button_panel.setBackground(new Color(150, 150, 150));

		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				buttons[i][j] = new JButton();
				button_panel.add(buttons[i][j]);
				buttons[i][j].setFont(new Font("Cheri Liney", Font.BOLD, 30));
				buttons[i][j].setBackground(defaultColor);
				buttons[i][j].setFocusable(false);
				buttons[i][j].addActionListener(this);
				board[i][j] = new figure() {
				};
			}
		}

		title_panel.add(textfield);
		frame.add(title_panel, BorderLayout.NORTH);
		frame.add(button_panel);

		makeSound("bombsiteB");
		loadPosition();
		setAvailableMovesForBoard();
		// firstTurn();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				if (e.getSource() == buttons[i][j]) {
					if ((selectedButtonX == j) && (selectedButtonY == i) && (buttonIsSelected == true)) { // turn off
																											// selected
																											// button
						buttonIsSelected = false;
						for (int k = 0; k < defaultSettings.boardWidth; k++) {
							for (int l = 0; l < defaultSettings.boardLength; l++) {
								buttons[k][l].setBackground(defaultColor);
							}
						}
					} else if ((board[selectedButtonY][selectedButtonX].availableMoves[i][j] == true
							|| board[selectedButtonY][selectedButtonX].availableStrikes[i][j] == true
							|| board[selectedButtonY][selectedButtonX].availableCastle[i][j] == true)
							&& (buttonIsSelected == true)
							&& (board[selectedButtonY][selectedButtonX].color == whiteTurn)) {
						if (board[selectedButtonY][selectedButtonX].availableCastle[i][j] == true) {
							moveCastle(selectedButtonY, selectedButtonX, i, j);
						} else {
							moveFigure(selectedButtonY, selectedButtonX, i, j); // mvoes a figure on
							// board
						}

						moveGUI(selectedButtonY, selectedButtonX, i, j);
						for (int k = 0; k < defaultSettings.boardWidth; k++) { // turn off all buttons
							for (int l = 0; l < defaultSettings.boardLength; l++) {
								buttons[k][l].setBackground(defaultColor);
							}
						}
						buttonIsSelected = false;
						setAvailableMovesForBoard();
						turnNumber++;
						whiteTurn = !whiteTurn;
					} else { // turn off all buttons and select one
						for (int k = 0; k < defaultSettings.boardWidth; k++) {
							for (int l = 0; l < defaultSettings.boardLength; l++) {
								buttons[k][l].setBackground(defaultColor);
							}
						}
						buttonIsSelected = true;
						selectedButtonY = i;
						selectedButtonX = j;
						buttons[i][j].setBackground(new Color(230, 230, 230));
					}
					if (board[selectedButtonY][selectedButtonX].color != whiteTurn
							&& board[selectedButtonY][selectedButtonX].exists == true && buttonIsSelected == true) {
						textfield.setText("Not your piece");
					} else if (board[selectedButtonY][selectedButtonX].exists == false && buttonIsSelected == true) {
						textfield.setText("Nothing here");
					} else if (buttonIsSelected == false) {
						if (whiteTurn == true){
							textfield.setText("White on move");
						}
						else{
							textfield.setText("Black on move");
						}
						// textfield.setText("Click something");
					} else if (board[selectedButtonY][selectedButtonX].color == whiteTurn
							&& board[selectedButtonY][selectedButtonX].exists == true && buttonIsSelected == true) {
						textfield.setText("OK but where?");
						for (int k = 0; k < defaultSettings.boardWidth; k++) {
							for (int l = 0; l < defaultSettings.boardLength; l++) {
								if (board[selectedButtonY][selectedButtonX].availableMoves[k][l] == true) {
									buttons[k][l].setBackground(new Color(200, 255, 200));
								}
								if (board[selectedButtonY][selectedButtonX].availableStrikes[k][l] == true) {
									buttons[k][l].setBackground(new Color(255, 200, 200));
								}
								if (board[selectedButtonY][selectedButtonX].availableCastle[k][l] == true) {
									buttons[k][l].setBackground(new Color(200, 200, 255));
								}
							}
						}
					}
				}
			}
		}
	}

	void loadPosition() {
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				if (i >= defaultSettings.defaultPosition.length || j >= defaultSettings.defaultPosition[0].length) {
					continue;
				}
				for (int k = 0; k < pieces.length; k++) {
					if (pieces[k] == defaultSettings.defaultPosition[i][j]) {
						buttons[i][j].setIcon(piecesIcons[k]);
					}
				}
				if (defaultSettings.defaultPosition[i][j].charAt(1) == 'R')
					board[i][j] = new rook();
				if (defaultSettings.defaultPosition[i][j].charAt(1) == 'N')
					board[i][j] = new knight();
				if (defaultSettings.defaultPosition[i][j].charAt(1) == 'B')
					board[i][j] = new bishop();
				if (defaultSettings.defaultPosition[i][j].charAt(1) == 'Q')
					board[i][j] = new queen();
				if (defaultSettings.defaultPosition[i][j].charAt(1) == 'K')
					board[i][j] = new king();
				if (defaultSettings.defaultPosition[i][j].charAt(1) == 'P')
					board[i][j] = new pawn();
				if (defaultSettings.defaultPosition[i][j].charAt(0) == 'w') {
					board[i][j].color = true;
					board[i][j].exists = true;
				}
				if (defaultSettings.defaultPosition[i][j].charAt(0) == 'b') {
					board[i][j].color = false;
					board[i][j].exists = true;
				}
				board[i][j].currentX = j;
				board[i][j].currentY = i;
			}
		}
	}

	void setAvailableMovesForBoard() {
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				board[i][j].availableMoves = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
				board[i][j].availableStrikes = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
				board[i][j].availableCastle = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
				board[i][j].setAvailableMoves(board, defaultBoard.attackedByWhiteBoard,
						defaultBoard.attackedByBlackBoard);
			}
		}
		// System.out.println("dupa");
	}

	void moveGUI(int selectedButtonY, int selectedButtonX, int i, int j) {
		buttons[i][j].setIcon(buttons[selectedButtonY][selectedButtonX].getIcon());
		buttons[selectedButtonY][selectedButtonX].setIcon(null);
	}

	public void moveFigure(int fromY, int fromX, int toY, int toX) {
		board[toY][toX] = board[fromY][fromX];
		board[toY][toX].hasBeenMoved = true;
		board[toY][toX].currentY = toY;
		board[toY][toX].currentX = toX;
		board[fromY][fromX] = new figure() {
		};
		board[fromY][fromX].availableMoves = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
		board[fromY][fromX].availableStrikes = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
	}

	public void moveCastle(int fromY, int fromX, int toY, int toX) {
		moveFigure(fromY, fromX, toY, toX);
		int i = 1;
		if (toX > fromX) { // short castle
			i = 1;
			while (fromX + i < board[0].length) {
				if (board[fromY][fromX + i].value == 5) {
					moveFigure(fromY, fromX + i, toY, 5);
					moveGUI(fromY, fromX + i, toY, 5);
				}
				i++;
			}
		} else { // long castle
			i = 1;
			while (fromX - i >= 0) {
				if (board[fromY][fromX - i].value == 5) {
					moveFigure(fromY, fromX + i, toY, 3);
					moveGUI(fromY, fromX + i, toY, 3);
				}
				i++;
			}
		}
	}

	public void firstTurn() {
		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (random.nextInt(2) == 0) {
			textfield.setText("White starts");
		} else {
			textfield.setText("Black starts (Just Kidding)");
		}
	}

	public void makeSound(String sound) {
		File song = new File("src\\sounds\\" + sound + ".wav");
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(song));
			clip.start();
			clip.loop(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}