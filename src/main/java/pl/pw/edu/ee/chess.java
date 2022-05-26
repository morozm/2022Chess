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

import pl.pw.edu.ee.figures.Bishop;
import pl.pw.edu.ee.figures.Figure;
import pl.pw.edu.ee.figures.King;
import pl.pw.edu.ee.figures.Knight;
import pl.pw.edu.ee.figures.Pawn;
import pl.pw.edu.ee.figures.Queen;
import pl.pw.edu.ee.figures.Rook;

public class Chess implements ActionListener {

	Settings defaultSettings = new Settings();
	Board defaultBoard = new Board();
	Board testDefaultdBoard = new Board();
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
	Figure[][] mainBoard = new Figure[defaultSettings.boardLength][defaultSettings.boardWidth];
	Figure[][] testBoard = new Figure[defaultSettings.boardLength][defaultSettings.boardWidth];
	String[][][] savedGame = new String[1000][defaultSettings.boardLength][defaultSettings.boardWidth];
	// change for longer games
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

	Chess() {
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
				mainBoard[j][i] = new Figure() {
				};
				testBoard[j][i] = new Figure() {
				};
			}
		}

		title_panel.add(textfield);
		frame.add(title_panel, BorderLayout.NORTH);
		frame.add(button_panel);

		// makeSound("bombsiteB");
		loadPosition();
		setAvailableMovesForBoard(mainBoard, defaultBoard);
		setLegalMovesForBoard();
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
					} else if ((((mainBoard[selectedButtonX][selectedButtonY].availableMoves[j][i] == true
							|| mainBoard[selectedButtonX][selectedButtonY].availableStrikes[j][i] == true)
							&& mainBoard[selectedButtonX][selectedButtonY].legalMovesStrikes[j][i] == true)
							|| mainBoard[selectedButtonX][selectedButtonY].availableCastle[j][i] == true)
							&& (buttonIsSelected == true)
							&& (mainBoard[selectedButtonX][selectedButtonY].color == whiteTurn)) {
						if (mainBoard[selectedButtonX][selectedButtonY].availableCastle[j][i] == true) {
							moveCastle(selectedButtonX, selectedButtonY, j, i, mainBoard);
							moveGUICastle(selectedButtonX, selectedButtonY, j, i);
							saveLastMove(selectedButtonX, selectedButtonY, j, i);
							turnOnLastMove();
						} else {
							moveFigure(selectedButtonX, selectedButtonY, j, i, mainBoard);
							moveGUI(selectedButtonX, selectedButtonY, j, i);
							checkPromotion(j, i, mainBoard);
							saveLastMove(selectedButtonX, selectedButtonY, j, i);
							turnOnLastMove();
							// mvoes a figure on board
						}
						turnOffAllButtons();
						buttonIsSelected = false;
						setAvailableMovesForBoard(mainBoard, defaultBoard);
						checkCheck(mainBoard, defaultBoard, true);
						whiteTurn = !whiteTurn;
						turnNumber++;
						setLegalMovesForBoard();
						turnOnLastMove();
						System.out.println("test");
					} else { // turn off all buttons and select one
						turnOffAllButtons();
						turnOnLastMove();
						buttonIsSelected = true;
						selectedButtonX = j;
						selectedButtonY = i;
						buttons[j][i].setBackground(new Color(230, 230, 230));
					}
					if (mainBoard[selectedButtonX][selectedButtonY].color != whiteTurn
							&& mainBoard[selectedButtonX][selectedButtonY].exists == true && buttonIsSelected == true) {
						textfield.setText("Not your piece");
					} else if (mainBoard[selectedButtonX][selectedButtonY].exists == false
							&& buttonIsSelected == true) {
						textfield.setText("Nothing here");
					} else if (buttonIsSelected == false) {
						if (whiteTurn == true) {
							textfield.setText("White on move");
						} else {
							textfield.setText("Black on move");
						}
						// textfield.setText("Click something");
					} else if (mainBoard[selectedButtonX][selectedButtonY].color == whiteTurn
							&& mainBoard[selectedButtonX][selectedButtonY].exists == true && buttonIsSelected == true) {
						textfield.setText("OK but where?");
						for (int k = 0; k < defaultSettings.boardWidth; k++) {
							for (int l = 0; l < defaultSettings.boardLength; l++) {
								if (mainBoard[selectedButtonX][selectedButtonY].availableMoves[l][k] == true
										&& mainBoard[selectedButtonX][selectedButtonY].legalMovesStrikes[l][k] == true) {
									buttons[l][k].setBackground(new Color(200, 255, 200));
								}
								if (mainBoard[selectedButtonX][selectedButtonY].availableStrikes[l][k] == true
										&& mainBoard[selectedButtonX][selectedButtonY].legalMovesStrikes[l][k] == true) {
									buttons[l][k].setBackground(new Color(255, 200, 200));
								}
								if (mainBoard[selectedButtonX][selectedButtonY].availableCastle[l][k] == true) {
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
					mainBoard[j][i] = new Rook();
				if (defaultSettings.defaultPosition[i][j].charAt(1) == 'N')
					mainBoard[j][i] = new Knight();
				if (defaultSettings.defaultPosition[i][j].charAt(1) == 'B')
					mainBoard[j][i] = new Bishop();
				if (defaultSettings.defaultPosition[i][j].charAt(1) == 'Q')
					mainBoard[j][i] = new Queen();
				if (defaultSettings.defaultPosition[i][j].charAt(1) == 'K')
					mainBoard[j][i] = new King();
				if (defaultSettings.defaultPosition[i][j].charAt(1) == 'P')
					mainBoard[j][i] = new Pawn();
				if (defaultSettings.defaultPosition[i][j].charAt(0) == 'w') {
					mainBoard[j][i].color = true;
					mainBoard[j][i].exists = true;
				}
				if (defaultSettings.defaultPosition[i][j].charAt(0) == 'b') {
					mainBoard[j][i].color = false;
					mainBoard[j][i].exists = true;
				}
				// mainBoard[j][i].currentX = j;
				// mainBoard[j][i].currentY = i;
				savedGame[turnNumber] = defaultSettings.defaultPosition;
			}
		}
	}

	void setAvailableMovesForBoard(Figure[][] board, Board attackedBoard) {
		// clear attackedBoard and then set for each figure
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				attackedBoard.attackedByWhiteBoard = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
				attackedBoard.attackedByBlackBoard = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
			}
		}
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				board[j][i].availableMoves = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
				board[j][i].availableStrikes = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
				board[j][i].setAvailableMoves(board, attackedBoard.attackedByWhiteBoard,
						attackedBoard.attackedByBlackBoard, j, i);
			}
		}
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				board[j][i].availableCastle = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
				board[j][i].setAvailableCastles(board, attackedBoard.attackedByWhiteBoard,
						attackedBoard.attackedByBlackBoard, j, i);
			}
		}
		// System.out.println("test");
	}

	void setLegalMovesForBoard() {
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				for (int k = 0; k < defaultSettings.boardWidth; k++) {
					for (int l = 0; l < defaultSettings.boardLength; l++) {
						// figure[][] testBoard = new
						// figure[defaultSettings.boardLength][defaultSettings.boardWidth];

						if (j == 3 && i == 6 && l == 2 && k == 5) {
							System.out.println("test");
						}
						mainBoard[j][i].legalMovesStrikes[l][k] = false;
						if ((mainBoard[j][i].availableMoves[l][k] == true
								|| mainBoard[j][i].availableStrikes[l][k] == true)
								&& (mainBoard[j][i].color == whiteTurn)) {
							for (int m = 0; m < mainBoard.length; m++) {
								for (int n = 0; n < mainBoard.length; n++) {
									cloneFigure(m, n, testBoard, mainBoard);
								}
							}
							moveFigure(j, i, l, k, testBoard);
							setAvailableMovesForBoard(testBoard, testDefaultdBoard);
							if (checkCheck(testBoard, testDefaultdBoard, false) == false) {
								mainBoard[j][i].legalMovesStrikes[l][k] = true;
							}
						}
					}
				}
			}
		}
	}

	void cloneFigure(int m, int n, Figure[][] boardTestFunction, Figure[][] boardMainFunction) {
		if (boardMainFunction[n][m] instanceof Bishop) {
			boardTestFunction[n][m] = new Bishop(boardMainFunction[n][m]);
		} else if (boardMainFunction[n][m] instanceof King) {
			boardTestFunction[n][m] = new King(boardMainFunction[n][m]);
		} else if (boardMainFunction[n][m] instanceof Knight) {
			boardTestFunction[n][m] = new Knight(boardMainFunction[n][m]);
		} else if (boardMainFunction[n][m] instanceof Pawn) {
			boardTestFunction[n][m] = new Pawn(boardMainFunction[n][m]);
		} else if (boardMainFunction[n][m] instanceof Queen) {
			boardTestFunction[n][m] = new Queen(boardMainFunction[n][m]);
		} else if (boardMainFunction[n][m] instanceof Rook) {
			boardTestFunction[n][m] = new Rook(boardMainFunction[n][m]);
		} else {
			boardTestFunction[n][m] = new Figure() {
			};
		}
	}

	void moveGUI(int selectedButtonX, int selectedButtonY, int j, int i) {
		Icon tmpIcon = buttons[selectedButtonX][selectedButtonY].getIcon();
		buttons[selectedButtonX][selectedButtonY].setIcon(null);
		buttons[j][i].setIcon(tmpIcon);
	}

	void moveGUICastle(int kingX, int kingY, int rookX, int rookY) {
		if (rookX > kingX) { // short castle
			moveGUI(rookX, rookY, 5, rookY);
			moveGUI(kingX, kingY, 6, kingY);
		} else { // long castle
			moveGUI(rookX, rookY, 3, rookY);
			moveGUI(kingX, kingY, 2, kingY);
		}
	}

	public void moveFigure(int fromX, int fromY, int toX, int toY, Figure[][] board) {
		Figure tmpFigure = board[fromX][fromY];
		board[fromX][fromY] = new Figure() {
		};
		// board[fromX][fromY].availableMoves = new
		// boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
		// board[fromX][fromY].availableStrikes = new
		// boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
		board[toX][toY] = tmpFigure;
		board[toX][toY].hasBeenMoved = true;
		// board[toX][toY].currentX = toX;
		// board[toX][toY].currentY = toY;
	}

	public void moveCastle(int kingX, int kingY, int rookX, int rookY, Figure[][] board) {
		if (rookX > kingX) { // short castle
			moveFigure(rookX, rookY, 5, rookY, board);
			moveFigure(kingX, kingY, 6, kingY, board);
		} else { // long castle
			moveFigure(rookX, rookY, 3, rookY, board);
			moveFigure(kingX, kingY, 2, kingY, board);
		}
	}

	public void checkPromotion(int currentX, int currentY, Figure[][] board) {
		if ((currentY == board[0].length - 1 && board[currentX][currentY].color == false)
				|| (currentY == 0 && board[currentX][currentY].color == true)) {
			promote(board, currentX, currentY);
			changeGUI(currentX, currentY);
			// board[currentX][currentY].setAvailableMoves(board, attackedByWhiteBoard, attackedByBlackBoard, currentX,
			// 		currentY);
		}
	}

	public void promote(Figure[][] board, int currentX, int currentY) {
		board[currentX][currentY] = new Queen(board[currentX][currentY]);
	}

	public void changeGUI(int currentX, int currentY) {
		// for (int k = 0; k < pieces.length; k++) {
		// if (pieces[k] == defaultSettings.defaultPosition[i][j]) {

		// }
		// }
		if (mainBoard[currentX][currentY].color == true)
			buttons[currentX][currentY].setIcon(piecesIcons[11]);
		else
			buttons[currentX][currentY].setIcon(piecesIcons[5]);
	}

	public void saveLastMove(int fromX, int fromY, int toX, int toY) {
		lastMove[0] = fromX;
		lastMove[1] = fromY;
		lastMove[2] = toX;
		lastMove[3] = toY;
		savedGame[turnNumber++] = defaultSettings.defaultPosition;
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

	public boolean checkCheck(Figure[][] board, Board attackedBoard, boolean enemy) {
		if ((enemy == true && whiteTurn == true) || (enemy == false && whiteTurn == false)) {
			for (int i = 0; i < defaultSettings.boardWidth; i++) {
				for (int j = 0; j < defaultSettings.boardLength; j++) {
					if (board[j][i].type.equals("king") && board[j][i].color == false) {
						if (attackedBoard.attackedByWhiteBoard[j][i] == true) {
							System.out.println("CHECK (by white)");
							return true;
						}
					}
				}
			}
		}
		if ((enemy == true && whiteTurn == false) || (enemy == false && whiteTurn == true)) {
			for (int i = 0; i < defaultSettings.boardWidth; i++) {
				for (int j = 0; j < defaultSettings.boardLength; j++) {
					if (board[j][i].type.equals("king") && board[j][i].color == true) {
						if (attackedBoard.attackedByBlackBoard[j][i] == true) {
							System.out.println("CHECK (by black)");
							return true;
						}
					}
				}
			}
		}
		return false;
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