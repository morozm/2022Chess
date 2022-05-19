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
import javax.swing.Icon;
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
	int[] lastMove = new int[4];
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
		// frame.setLayout(null);
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
				buttons[j][i] = new JButton();
				button_panel.add(buttons[j][i]);
				buttons[j][i].setFont(new Font("Cheri Liney", Font.BOLD, 30));
				buttons[j][i].setBackground(defaultColor);
				buttons[j][i].setFocusable(false);
				buttons[j][i].addActionListener(this);
				board[j][i] = new figure() {
				};
			}
		}

		title_panel.add(textfield);
		frame.add(title_panel, BorderLayout.NORTH);
		frame.add(button_panel);

		// makeSound("bombsiteB");
		loadPosition();
		setAvailableMovesForBoard();
		System.out.println("test");
		// firstTurn();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				if (e.getSource() == buttons[j][i]) {
					if ((selectedButtonX == j) && (selectedButtonY == i) && (buttonIsSelected == true)) {
						// turn off selected button
						buttonIsSelected = false;
						turnOffAllButtons();
						turnOnLastMove();
					} else if ((board[selectedButtonX][selectedButtonY].availableMoves[j][i] == true
							|| board[selectedButtonX][selectedButtonY].availableStrikes[j][i] == true
							|| board[selectedButtonX][selectedButtonY].availableCastle[j][i] == true)
							&& (buttonIsSelected == true)
							&& (board[selectedButtonX][selectedButtonY].color == whiteTurn)) {
						if (board[selectedButtonX][selectedButtonY].availableCastle[j][i] == true) {
							moveCastle(selectedButtonX, selectedButtonY, j, i);
							saveLastMove(selectedButtonX, selectedButtonY, j, i);
							turnOnLastMove();
						} else {
							moveFigure(selectedButtonX, selectedButtonY, j, i);
							saveLastMove(selectedButtonX, selectedButtonY, j, i);
							turnOnLastMove();
							// mvoes a figure on board
						}
						turnOffAllButtons();
						buttonIsSelected = false;
						setAvailableMovesForBoard();
						turnNumber++;
						whiteTurn = !whiteTurn;
						turnOnLastMove();
					} else { // turn off all buttons and select one
						turnOffAllButtons();
						turnOnLastMove();
						buttonIsSelected = true;
						selectedButtonX = j;
						selectedButtonY = i;
						buttons[j][i].setBackground(new Color(230, 230, 230));
					}
					if (board[selectedButtonX][selectedButtonY].color != whiteTurn
							&& board[selectedButtonX][selectedButtonY].exists == true && buttonIsSelected == true) {
						textfield.setText("Not your piece");
					} else if (board[selectedButtonX][selectedButtonY].exists == false && buttonIsSelected == true) {
						textfield.setText("Nothing here");
					} else if (buttonIsSelected == false) {
						if (whiteTurn == true) {
							textfield.setText("White on move");
						} else {
							textfield.setText("Black on move");
						}
						// textfield.setText("Click something");
					} else if (board[selectedButtonX][selectedButtonY].color == whiteTurn
							&& board[selectedButtonX][selectedButtonY].exists == true && buttonIsSelected == true) {
						textfield.setText("OK but where?");
						for (int k = 0; k < defaultSettings.boardWidth; k++) {
							for (int l = 0; l < defaultSettings.boardLength; l++) {
								if (board[selectedButtonX][selectedButtonY].availableMoves[l][k] == true) {
									buttons[l][k].setBackground(new Color(200, 255, 200));
								}
								if (board[selectedButtonX][selectedButtonY].availableStrikes[l][k] == true) {
									buttons[l][k].setBackground(new Color(255, 200, 200));
								}
								if (board[selectedButtonX][selectedButtonY].availableCastle[l][k] == true) {
									buttons[l][k].setBackground(new Color(200, 200, 255));
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
						buttons[j][i].setIcon(piecesIcons[k]);
					}
				}
				if (defaultSettings.defaultPosition[i][j].charAt(1) == 'R')
					board[j][i] = new rook();
				if (defaultSettings.defaultPosition[i][j].charAt(1) == 'N')
					board[j][i] = new knight();
				if (defaultSettings.defaultPosition[i][j].charAt(1) == 'B')
					board[j][i] = new bishop();
				if (defaultSettings.defaultPosition[i][j].charAt(1) == 'Q')
					board[j][i] = new queen();
				if (defaultSettings.defaultPosition[i][j].charAt(1) == 'K')
					board[j][i] = new king();
				if (defaultSettings.defaultPosition[i][j].charAt(1) == 'P')
					board[j][i] = new pawn();
				if (defaultSettings.defaultPosition[i][j].charAt(0) == 'w') {
					board[j][i].color = true;
					board[j][i].exists = true;
				}
				if (defaultSettings.defaultPosition[i][j].charAt(0) == 'b') {
					board[j][i].color = false;
					board[j][i].exists = true;
				}
				board[j][i].currentX = j;
				board[j][i].currentY = i;
			}
		}
	}

	void setAvailableMovesForBoard() {
		// clear attackedBoard and then set for each figure
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				defaultBoard.attackedByWhiteBoard = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
				defaultBoard.attackedByBlackBoard = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
			}
		}
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				board[j][i].availableMoves = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
				board[j][i].availableStrikes = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
				board[j][i].availableCastle = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
				this.board[j][i].setAvailableMoves(board, this.defaultBoard.attackedByWhiteBoard,
						this.defaultBoard.attackedByBlackBoard);
			}
		}
		System.out.println("test");
	}

	void moveGUI(int selectedButtonX, int selectedButtonY, int j, int i) {
		Icon tmpIcon = buttons[selectedButtonX][selectedButtonY].getIcon();
		buttons[selectedButtonX][selectedButtonY].setIcon(null);
		buttons[j][i].setIcon(tmpIcon);
	}

	public void moveFigure(int fromX, int fromY, int toX, int toY) {
		figure tmpFigure = board[fromX][fromY];
		board[fromX][fromY] = new figure() {
		};
		board[fromX][fromY].availableMoves = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
		board[fromX][fromY].availableStrikes = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
		board[toX][toY] = tmpFigure;
		board[toX][toY].hasBeenMoved = true;
		board[toX][toY].currentX = toX;
		board[toX][toY].currentY = toY;
		moveGUI(fromX, fromY, toX, toY);
	}

	public void moveCastle(int kingX, int kingY, int rookX, int rookY) {
		if (rookX > kingX) { // short castle
			moveFigure(rookX, rookY, 5, rookY);
			moveFigure(kingX, kingY, 6, kingY);
		} else { // long castle
			moveFigure(rookX, rookY, 3, rookY);
			moveFigure(kingX, kingY, 2, kingY);
		}
	}

	public void saveLastMove(int fromX, int fromY, int toX, int toY) {
		lastMove[0] = fromX;
		lastMove[1] = fromY;
		lastMove[2] = toX;
		lastMove[3] = toY;
	}

	public void turnOnLastMove() {
		if (turnNumber != 1 || whiteTurn != true) {
			buttons[lastMove[0]][lastMove[1]].setBackground(new Color(255, 255, 200));
			buttons[lastMove[2]][lastMove[3]].setBackground(new Color(255, 255, 200));
		}
	}

	public void turnOffAllButtons() {
		for (int k = 0; k < defaultSettings.boardWidth; k++) {
			for (int l = 0; l < defaultSettings.boardLength; l++) {
				buttons[l][k].setBackground(defaultColor);
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