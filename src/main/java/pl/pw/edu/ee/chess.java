package pl.pw.edu.ee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JLayeredPane;
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
	Move defaultMove = new Move();

	Random random = new Random();
	boolean whiteTurn = true;
	int turnNumber = 1;
	JFrame frame = new JFrame();
	JPanel titlePanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JLayeredPane[][] buttonsLayeredPane = new JLayeredPane[defaultSettings.boardLength][defaultSettings.boardWidth];
	JPanel sidePanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	JPanel sidePiecesPanel = new JPanel();
	JLabel textLabel = new JLabel();
	JLabel[] leftLabels = new JLabel[defaultSettings.boardWidth];
	JLabel[] bottomLabels = new JLabel[defaultSettings.boardLength];
	Border border1 = BorderFactory.createLineBorder(defaultSettings.mainColor1, 4);
	Border border2 = BorderFactory.createLineBorder(defaultSettings.mainColor2, 4);
	Border emptyBorder = BorderFactory.createEmptyBorder();
	Border defaultBorder = (new JButton().getBorder());
	JButton[][] buttons = new JButton[defaultSettings.boardLength][defaultSettings.boardWidth];
	JButton[] sideButtons = new JButton[4];
	Figure[][] mainBoard = new Figure[defaultSettings.boardLength][defaultSettings.boardWidth];
	Figure[][] testBoard = new Figure[defaultSettings.boardLength][defaultSettings.boardWidth];
	String[][][] savedGame = new String[1000][defaultSettings.boardLength][defaultSettings.boardWidth];
	// change for longer games
	boolean buttonIsSelected = false;
	int selectedButtonY;
	int selectedButtonX;
	int chosenFigure;
	int[] lastMove = new int[4];
	Color defaultColor = UIManager.getColor("Panel.background");

	String[] pieces = { "bP", "bR", "bN", "bB", "bQ", "bK", "wP", "wR", "wN", "wB", "wQ", "wK" };
	ImageIcon[] piecesIcons = {
			new ImageIcon("src\\pieces\\bP.png"),
			new ImageIcon("src\\pieces\\bR.png"),
			new ImageIcon("src\\pieces\\bN.png"),
			new ImageIcon("src\\pieces\\bB.png"),
			new ImageIcon("src\\pieces\\bQ.png"),
			new ImageIcon("src\\pieces\\bK.png"),
			new ImageIcon("src\\pieces\\wP.png"),
			new ImageIcon("src\\pieces\\wR.png"),
			new ImageIcon("src\\pieces\\wN.png"),
			new ImageIcon("src\\pieces\\wB.png"),
			new ImageIcon("src\\pieces\\wQ.png"),
			new ImageIcon("src\\pieces\\wK.png") };

	Chess() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 930);
		frame.setTitle("Best Chess Ever Made");
		frame.setResizable(false); // cant resize window
		frame.setIconImage(piecesIcons[11].getImage());
		frame.getContentPane().setBackground(defaultSettings.mainColor2);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);

		textLabel.setBackground(defaultSettings.mainColor2);
		textLabel.setForeground(defaultSettings.mainColor1);
		textLabel.setFont(defaultSettings.font1);
		textLabel.setHorizontalAlignment(JLabel.CENTER);
		textLabel.setText("Wie Pan Co To Znaczy Trappin?");
		textLabel.setOpaque(true);
		textLabel.setBorder(border1);

		titlePanel.setLayout(new BorderLayout());
		titlePanel.setPreferredSize(new Dimension(1200, 83));

		sidePanel.setLayout(new BorderLayout());
		sidePanel.setPreferredSize(new Dimension(400, 800));
		sidePanel.setBorder(border1);
		sidePanel.setBackground(defaultSettings.mainColor2);

		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.setPreferredSize(new Dimension(800, 800));
		bottomPanel.setBorder(border1);
		bottomPanel.setBackground(defaultSettings.mainColor2);

		sidePiecesPanel.setLayout(new GridLayout(1, 4));
		sidePiecesPanel.setPreferredSize(new Dimension(400, 100));
		sidePiecesPanel.setBackground(defaultSettings.mainColor2);
		sidePiecesPanel.setVisible(false);

		for (int i = 0; i < 4; i++) {
			sideButtons[i] = new JButton();
			sidePiecesPanel.add(sideButtons[i]);
			sideButtons[i].setFocusable(false);
			sideButtons[i].addActionListener(this);
			// sideButtons[i].setBorder(emptyBorder);
			if (i % 2 == 1) {
				sideButtons[i].setBackground(defaultSettings.tileColor1);
			} else
				sideButtons[i].setBackground(defaultSettings.tileColor2);
		}

		buttonPanel.setLayout(new GridLayout(defaultSettings.boardWidth, defaultSettings.boardLength));
		buttonPanel.setBackground(defaultSettings.mainColor2);
		buttonPanel.setPreferredSize(new Dimension(800, 800));

		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				buttons[j][i] = new JButton();
				buttonPanel.add(buttons[j][i]);
				buttons[j][i].setFocusable(false);
				buttons[j][i].addActionListener(this);
				// buttons[j][i].setBorder(emptyBorder);
				mainBoard[j][i] = new Figure() {
				};
				testBoard[j][i] = new Figure() {
				};
				if ((i + j) % 2 == 0) {
					buttons[j][i].setBackground(defaultSettings.tileColor1);
					buttons[j][i].setForeground(defaultSettings.tileColor1);
				} else
					buttons[j][i].setBackground(defaultSettings.tileColor2);
					buttons[j][i].setForeground(defaultSettings.tileColor2);
				if (j == 0) {
					leftLabels[i] = new JLabel();
					leftLabels[i].setText(Integer.toString(defaultSettings.boardWidth - i));
					leftLabels[i].setFont(defaultSettings.font2);
					if ((i + j) % 2 == 0)
						leftLabels[i].setForeground(defaultSettings.tileColor2);
					else
						leftLabels[i].setForeground(defaultSettings.tileColor1);
					buttons[j][i].setLayout(new BorderLayout());
					buttons[j][i].add(leftLabels[i], BorderLayout.NORTH);
				}
				if (i == defaultSettings.boardWidth - 1) {
					bottomLabels[i] = new JLabel();
					char c = (char) (j + 1 + 64 + 32);
					String s = String.valueOf(c);
					bottomLabels[i].setText(s);
					bottomLabels[i].setFont(defaultSettings.font2);
					bottomLabels[i].setHorizontalAlignment(JLabel.RIGHT);
					if ((i + j) % 2 == 0)
						bottomLabels[i].setForeground(defaultSettings.tileColor2);
					else
						bottomLabels[i].setForeground(defaultSettings.tileColor1);
					if (j != 0)
						buttons[j][i].setLayout(new BorderLayout());
					buttons[j][i].add(bottomLabels[i], BorderLayout.SOUTH);
				}
			}
		}

		titlePanel.add(textLabel);
		sidePanel.add(sidePiecesPanel, BorderLayout.SOUTH);
		bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
		frame.add(titlePanel, BorderLayout.NORTH);
		frame.add(sidePanel, BorderLayout.EAST);
		frame.add(bottomPanel);

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
							moveCastle(selectedButtonX, selectedButtonY, j, i, testBoard);
							moveGUICastle(selectedButtonX, selectedButtonY, j, i);
							saveLastMove(selectedButtonX, selectedButtonY, j, i);
							turnOnLastMove();
						} else {
							moveFigure(selectedButtonX, selectedButtonY, j, i, mainBoard);
							moveFigure(selectedButtonX, selectedButtonY, j, i, testBoard);
							moveGUI(selectedButtonX, selectedButtonY, j, i);
							checkPromotion(j, i, mainBoard, testBoard);
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
						buttons[j][i].setBackground(defaultSettings.selectColor);
						buttons[j][i].setBorder(border2);
					}
					if (mainBoard[selectedButtonX][selectedButtonY].color != whiteTurn
							&& mainBoard[selectedButtonX][selectedButtonY].exists == true && buttonIsSelected == true) {
						textLabel.setText("Not your piece");
					} else if (mainBoard[selectedButtonX][selectedButtonY].exists == false
							&& buttonIsSelected == true) {
						textLabel.setText("Nothing here");
					} else if (buttonIsSelected == false) {
						if (whiteTurn == true) {
							textLabel.setText("White on move");
						} else {
							textLabel.setText("Black on move");
						}
						// textfield.setText("Click something");
					} else if (mainBoard[selectedButtonX][selectedButtonY].color == whiteTurn
							&& mainBoard[selectedButtonX][selectedButtonY].exists == true && buttonIsSelected == true) {
						textLabel.setText("OK but where?");
						for (int k = 0; k < defaultSettings.boardWidth; k++) {
							for (int l = 0; l < defaultSettings.boardLength; l++) {
								if (mainBoard[selectedButtonX][selectedButtonY].availableMoves[l][k] == true
										&& mainBoard[selectedButtonX][selectedButtonY].legalMovesStrikes[l][k] == true) {
									buttons[l][k].setBackground(defaultSettings.moveColor);
								}
								if (mainBoard[selectedButtonX][selectedButtonY].availableStrikes[l][k] == true
										&& mainBoard[selectedButtonX][selectedButtonY].legalMovesStrikes[l][k] == true) {
									buttons[l][k].setBackground(defaultSettings.attackColor);
								}
								if (mainBoard[selectedButtonX][selectedButtonY].availableCastle[l][k] == true) {
									buttons[l][k].setBackground(defaultSettings.castleColor);
								}
							}
						}
					}
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			if (e.getSource() == sideButtons[i]) {
				promote2(mainBoard, selectedButtonX, selectedButtonY, i);
				promote2(testBoard, selectedButtonX, selectedButtonY, i);
			}
		}
	}

	void loadPosition() {
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				if (i >= defaultSettings.defaultPosition.length || j >= defaultSettings.defaultPosition[0].length
						|| i >= defaultSettings.boardWidth || j >= defaultSettings.boardLength) {
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
			}
		}
		savedGame[turnNumber] = defaultSettings.defaultPosition;
		for (int m = 0; m < mainBoard[0].length; m++) {
			for (int n = 0; n < mainBoard.length; n++) {
				cloneFigure(m, n, testBoard, mainBoard);
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
						mainBoard[j][i].legalMovesStrikes[l][k] = false;
						if ((mainBoard[j][i].availableMoves[l][k] == true
								|| mainBoard[j][i].availableStrikes[l][k] == true)
								&& (mainBoard[j][i].color == whiteTurn)) {
							boolean hasBeenMoved = testBoard[j][i].hasBeenMoved;
							Figure tmpFigure = testBoard[l][k];
							moveFigure(j, i, l, k, testBoard);
							if (checkCheck(testBoard, false) == false) {
								mainBoard[j][i].legalMovesStrikes[l][k] = true;
							}
							undoMoveFigure(l, k, j, i, testBoard, hasBeenMoved, tmpFigure);
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
		board[toX][toY] = tmpFigure;
		board[toX][toY].hasBeenMoved = true;
	}

	public void undoMoveFigure(int fromX, int fromY, int toX, int toY, Figure[][] board, boolean hasBeenMoved,
			Figure strikeFigure) {
		Figure tmpFigure = board[fromX][fromY];
		board[fromX][fromY] = strikeFigure;
		board[toX][toY] = tmpFigure;
		board[toX][toY].hasBeenMoved = hasBeenMoved;
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

	public void checkPromotion(int currentX, int currentY, Figure[][] board1, Figure[][] board2) {
		if ((currentY == board1[0].length - 1 && board1[currentX][currentY].color == false
				&& board1[currentX][currentY].type == "pawn")
				|| (currentY == 0 && board1[currentX][currentY].color == true
						&& board1[currentX][currentY].type == "pawn")) {
			promote1(board1, currentX, currentY);
			promote1(board2, currentX, currentY);
		}
	}

	public void promote1(Figure[][] board, int currentX, int currentY) {
		sidePiecesPanel.setVisible(true);
		if (board[currentX][currentY].color == true) {
			sideButtons[0].setIcon(piecesIcons[7]);
			sideButtons[1].setIcon(piecesIcons[8]);
			sideButtons[2].setIcon(piecesIcons[9]);
			sideButtons[3].setIcon(piecesIcons[10]);
		} else {
			sideButtons[0].setIcon(piecesIcons[1]);
			sideButtons[1].setIcon(piecesIcons[2]);
			sideButtons[2].setIcon(piecesIcons[3]);
			sideButtons[3].setIcon(piecesIcons[4]);
		}
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				buttons[j][i].setEnabled(false);
			}
		}
		textLabel.setText("Choose a figure");
	}

	public void promote2(Figure[][] board, int currentX, int currentY, int whichButton) {
		if (whichButton == 0) {
			board[lastMove[2]][lastMove[3]] = new Rook(board[lastMove[2]][lastMove[3]]);
			board[lastMove[2]][lastMove[3]].type = "rook";
			board[lastMove[2]][lastMove[3]].value = 5;
			changeGUI(whichButton);
		}
		if (whichButton == 1) {
			board[lastMove[2]][lastMove[3]] = new Knight(board[lastMove[2]][lastMove[3]]);
			board[lastMove[2]][lastMove[3]].type = "knight";
			board[lastMove[2]][lastMove[3]].value = 3;
			changeGUI(whichButton);
		}
		if (whichButton == 2) {
			board[lastMove[2]][lastMove[3]] = new Bishop(board[lastMove[2]][lastMove[3]]);
			board[lastMove[2]][lastMove[3]].type = "bishop";
			board[lastMove[2]][lastMove[3]].value = 3;
			changeGUI(whichButton);
		}
		if (whichButton == 3) {
			board[lastMove[2]][lastMove[3]] = new Queen(board[lastMove[2]][lastMove[3]]);
			board[lastMove[2]][lastMove[3]].type = "queen";
			board[lastMove[2]][lastMove[3]].value = 9;
			changeGUI(whichButton);
		}
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				buttons[j][i].setEnabled(true);
			}
		}
		sidePiecesPanel.setVisible(false);
		turnOffAllButtons();
		buttonIsSelected = false;
		setAvailableMovesForBoard(mainBoard, defaultBoard);
		checkCheck(mainBoard, defaultBoard, true);
		whiteTurn = !whiteTurn;
		turnNumber++;
		setLegalMovesForBoard();
		turnOnLastMove(); // not sure bout this block
	}

	public void changeGUI(int whichButton) {
		if (mainBoard[lastMove[2]][lastMove[3]].color == true)
			buttons[lastMove[2]][lastMove[3]].setIcon(piecesIcons[7 + whichButton]);
		else
			buttons[lastMove[2]][lastMove[3]].setIcon(piecesIcons[1 + whichButton]);
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
			if (turnNumber != 1 || whiteTurn != true) {
				buttons[lastMove[0]][lastMove[1]].setBackground(defaultSettings.lastMoveColor);
				buttons[lastMove[2]][lastMove[3]].setBackground(defaultSettings.lastMoveColor);
			}
		}
	}

	public void turnOffAllButtons() {
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				// buttons[j][i].setBorder(emptyBorder);
				buttons[j][i].setBorder(defaultBorder);
				if ((i + j) % 2 == 0) {
					buttons[j][i].setBackground(defaultSettings.tileColor1);
					buttons[j][i].setForeground(defaultSettings.tileColor1);
				} else
					buttons[j][i].setBackground(defaultSettings.tileColor2);
					buttons[j][i].setForeground(defaultSettings.tileColor2);
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

	public boolean checkCheck(Figure[][] board, boolean enemy) {
		if ((enemy == true && whiteTurn == true) || (enemy == false && whiteTurn == false)) {
			for (int i = 0; i < defaultSettings.boardWidth; i++) {
				for (int j = 0; j < defaultSettings.boardLength; j++) {
					if (board[j][i].type.equals("king") && board[j][i].color == false) {
						int k;
						k = 1;
						while (j + k < board.length && i + k < board[0].length) {
							if (board[j + k][i + k].color == true
									&& (board[j + k][i + k].type == "bishop" || board[j + k][i + k].type == "queen")) {
								return true;
							} else if (board[j + k][i + k].exists == false) {
								k++;
							} else {
								break;
							}
						}
						k = 1;
						while (j + k < board.length && i - k >= 0) {
							if (board[j + k][i - k].color == true
									&& (board[j + k][i - k].type == "bishop" || board[j + k][i - k].type == "queen")) {
								return true;
							} else if (board[j + k][i - k].exists == false) {
								k++;
							} else {
								break;
							}
						}
						k = 1;
						while (j - k >= 0 && i - k >= 0) {
							if (board[j - k][i - k].color == true
									&& (board[j - k][i - k].type == "bishop" || board[j - k][i - k].type == "queen")) {
								return true;
							} else if (board[j - k][i - k].exists == false) {
								k++;
							} else {
								break;
							}
						}
						k = 1;
						while (j - k >= 0 && i + k < board[0].length) {
							if (board[j - k][i + k].color == true
									&& (board[j - k][i + k].type == "bishop" || board[j - k][i + k].type == "queen")) {
								return true;
							} else if (board[j - k][i + k].exists == false) {
								k++;
							} else {
								break;
							}
						}
						k = 1;
						while (j + k < board.length) {
							if (board[j + k][i].color == true
									&& (board[j + k][i].type == "rook" || board[j + k][i].type == "queen")) {
								return true;
							} else if (board[j + k][i].exists == false) {
								k++;
							} else {
								break;
							}
						}
						k = 1;
						while (i - k >= 0) {
							if (board[j][i - k].color == true
									&& (board[j][i - k].type == "rook" || board[j][i - k].type == "queen")) {
								return true;
							} else if (board[j][i - k].exists == false) {
								k++;
							} else {
								break;
							}
						}
						k = 1;
						while (j - k >= 0) {
							if (board[j - k][i].color == true
									&& (board[j - k][i].type == "rook" || board[j - k][i].type == "queen")) {
								return true;
							} else if (board[j - k][i].exists == false) {
								k++;
							} else {
								break;
							}
						}
						k = 1;
						while (i + k < board[0].length) {
							if (board[j][i + k].color == true
									&& (board[j][i + k].type == "rook" || board[j][i + k].type == "queen")) {
								return true;
							} else if (board[j][i + k].exists == false) {
								k++;
							} else {
								break;
							}
						}
						if (j + 1 < board.length && i + 2 < board[0].length) {
							if (board[j + 1][i + 2].type == "knight" && board[j + 1][i + 2].color == true)
								return true;
						}
						if (j + 1 < board.length && i - 2 >= 0) {
							if (board[j + 1][i - 2].type == "knight" && board[j + 1][i - 2].color == true)
								return true;
						}
						if (j + 2 < board.length && i + 1 < board[0].length) {
							if (board[j + 2][i + 1].type == "knight" && board[j + 2][i + 1].color == true)
								return true;
						}
						if (j + 2 < board.length && i - 1 >= 0) {
							if (board[j + 2][i - 1].type == "knight" && board[j + 2][i - 1].color == true)
								return true;
						}
						if (j - 1 >= 0 && i + 2 < board[0].length) {
							if (board[j - 1][i + 2].type == "knight" && board[j - 1][i + 2].color == true)
								return true;
						}
						if (j - 1 >= 0 && i - 2 >= 0) {
							if (board[j - 1][i - 2].type == "knight" && board[j - 1][i - 2].color == true)
								return true;
						}
						if (j - 2 >= 0 && i + 1 < board[0].length) {
							if (board[j - 2][i + 1].type == "knight" && board[j - 2][i + 1].color == true)
								return true;
						}
						if (j - 2 >= 0 && i - 1 >= 0) {
							if (board[j - 2][i - 1].type == "knight" && board[j - 2][i - 1].color == true)
								return true;
						}
						if (j + 1 < board.length && i + 1 < board[0].length) {
							if ((board[j + 1][i + 1].type == "king" || board[j + 1][i + 1].type == "pawn")
									&& board[j + 1][i + 1].color == true)
								return true;
						}
						if (j + 1 < board.length && i - 1 >= 0) {
							if (board[j + 1][i - 1].type == "king" && board[j + 1][i - 1].color == true)
								return true;
						}
						if (j - 1 >= 0 && i + 1 < board[0].length) {
							if ((board[j - 1][i + 1].type == "king" || board[j - 1][i + 1].type == "pawn")
									&& board[j - 1][i + 1].color == true)
								return true;
						}
						if (j - 1 >= 0 && i - 1 >= 0) {
							if (board[j - 1][i - 1].type == "king" && board[j - 1][i - 1].color == true)
								return true;
						}
						if (j + 1 < board.length) {
							if (board[j + 1][i].type == "king" && board[j + 1][i].color == true)
								return true;
						}
						if (i - 1 >= 0) {
							if (board[j][i - 1].type == "king" && board[j][i - 1].color == true)
								return true;
						}
						if (i + 1 < board[0].length) {
							if (board[j][i + 1].type == "king" && board[j][i + 1].color == true)
								return true;
						}
						if (j - 1 >= 0) {
							if (board[j - 1][i].type == "king" && board[j - 1][i].color == true)
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
						int k;
						k = 1;
						while (j + k < board.length && i + k < board[0].length) {
							if (board[j + k][i + k].color == false
									&& (board[j + k][i + k].type == "bishop" || board[j + k][i + k].type == "queen")) {
								return true;
							} else if (board[j + k][i + k].exists == false) {
								k++;
							} else {
								break;
							}
						}
						k = 1;
						while (j + k < board.length && i - k >= 0) {
							if (board[j + k][i - k].color == false
									&& (board[j + k][i - k].type == "bishop" || board[j + k][i - k].type == "queen")) {
								return true;
							} else if (board[j + k][i - k].exists == false) {
								k++;
							} else {
								break;
							}
						}
						k = 1;
						while (j - k >= 0 && i - k >= 0) {
							if (board[j - k][i - k].color == false
									&& (board[j - k][i - k].type == "bishop" || board[j - k][i - k].type == "queen")) {
								return true;
							} else if (board[j - k][i - k].exists == false) {
								k++;
							} else {
								break;
							}
						}
						k = 1;
						while (j - k >= 0 && i + k < board[0].length) {
							if (board[j - k][i + k].color == false
									&& (board[j - k][i + k].type == "bishop" || board[j - k][i + k].type == "queen")) {
								return true;
							} else if (board[j - k][i + k].exists == false) {
								k++;
							} else {
								break;
							}
						}
						k = 1;
						while (j + k < board.length) {
							if (board[j + k][i].color == false
									&& (board[j + k][i].type == "rook" || board[j + k][i].type == "queen")) {
								return true;
							} else if (board[j + k][i].exists == false) {
								k++;
							} else {
								break;
							}
						}
						k = 1;
						while (i - k >= 0) {
							if (board[j][i - k].color == false
									&& (board[j][i - k].type == "rook" || board[j][i - k].type == "queen")) {
								return true;
							} else if (board[j][i - k].exists == false) {
								k++;
							} else {
								break;
							}
						}
						k = 1;
						while (j - k >= 0) {
							if (board[j - k][i].color == false
									&& (board[j - k][i].type == "rook" || board[j - k][i].type == "queen")) {
								return true;
							} else if (board[j - k][i].exists == false) {
								k++;
							} else {
								break;
							}
						}
						k = 1;
						while (i + k < board[0].length) {
							if (board[j][i + k].color == false
									&& (board[j][i + k].type == "rook" || board[j][i + k].type == "queen")) {
								return true;
							} else if (board[j][i + k].exists == false) {
								k++;
							} else {
								break;
							}
						}
						if (j + 1 < board.length && i + 2 < board[0].length) {
							if (board[j + 1][i + 2].type == "knight" && board[j + 1][i + 2].color == false)
								return true;
						}
						if (j + 1 < board.length && i - 2 >= 0) {
							if (board[j + 1][i - 2].type == "knight" && board[j + 1][i - 2].color == false)
								return true;
						}
						if (j + 2 < board.length && i + 1 < board[0].length) {
							if (board[j + 2][i + 1].type == "knight" && board[j + 2][i + 1].color == false)
								return true;
						}
						if (j + 2 < board.length && i - 1 >= 0) {
							if (board[j + 2][i - 1].type == "knight" && board[j + 2][i - 1].color == false)
								return true;
						}
						if (j - 1 >= 0 && i + 2 < board[0].length) {
							if (board[j - 1][i + 2].type == "knight" && board[j - 1][i + 2].color == false)
								return true;
						}
						if (j - 1 >= 0 && i - 2 >= 0) {
							if (board[j - 1][i - 2].type == "knight" && board[j - 1][i - 2].color == false)
								return true;
						}
						if (j - 2 >= 0 && i + 1 < board[0].length) {
							if (board[j - 2][i + 1].type == "knight" && board[j - 2][i + 1].color == false)
								return true;
						}
						if (j - 2 >= 0 && i - 1 >= 0) {
							if (board[j - 2][i - 1].type == "knight" && board[j - 2][i - 1].color == false)
								return true;
						}
						if (j + 1 < board.length && i + 1 < board[0].length) {
							if ((board[j + 1][i + 1].type == "king" || board[j + 1][i + 1].type == "pawn")
									&& board[j + 1][i + 1].color == false)
								return true;
						}
						if (j + 1 < board.length && i - 1 >= 0) {
							if (board[j + 1][i - 1].type == "king" && board[j + 1][i - 1].color == false)
								return true;
						}
						if (j - 1 >= 0 && i + 1 < board[0].length) {
							if ((board[j - 1][i + 1].type == "king" || board[j - 1][i + 1].type == "pawn")
									&& board[j - 1][i + 1].color == false)
								return true;
						}
						if (j - 1 >= 0 && i - 1 >= 0) {
							if (board[j - 1][i - 1].type == "king" && board[j - 1][i - 1].color == false)
								return true;
						}
						if (j + 1 < board.length) {
							if (board[j + 1][i].type == "king" && board[j + 1][i].color == false)
								return true;
						}
						if (i - 1 >= 0) {
							if (board[j][i - 1].type == "king" && board[j][i - 1].color == false)
								return true;
						}
						if (i + 1 < board[0].length) {
							if (board[j][i + 1].type == "king" && board[j][i + 1].color == false)
								return true;
						}
						if (j - 1 >= 0) {
							if (board[j - 1][i].type == "king" && board[j - 1][i].color == false)
								return true;
						}
					}
				}
			}
		}
		return false;
	}

	public Color colorChange(Color color, int r, int g, int b) {
		int newRed = color.getRed() + r;
		int newGreen = color.getGreen() + g;
		int newBlue = color.getBlue() + b;
		if (newRed > 255)
			newRed = 255;
		if (newGreen > 255)
			newGreen = 255;
		if (newBlue > 255)
			newBlue = 255;
		if (newRed < 0)
			newRed = 0;
		if (newGreen < 0)
			newGreen = 0;
		if (newBlue < 0)
			newBlue = 0;
		Color returnedColor = new Color(newRed, newGreen, newBlue);
		return returnedColor;
	}

	public void firstTurn() {
		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (random.nextInt(2) == 0) {
			textLabel.setText("White starts");
		} else {
			textLabel.setText("Black starts (Just Kidding)");
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