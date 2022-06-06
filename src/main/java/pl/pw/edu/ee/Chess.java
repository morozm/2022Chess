package pl.pw.edu.ee;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.Timer;

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
	GUI gui = new GUI();
	// Move defaultMove = new Move();

	Figure[][] mainBoard = new Figure[defaultSettings.boardLength][defaultSettings.boardWidth];
	String[][][] savedGame = new String[1000][defaultSettings.boardLength][defaultSettings.boardWidth];
	String[] pieces = { "bP", "bR", "bN", "bB", "bQ", "bK", "wP", "wR", "wN", "wB", "wQ", "wK" };
	int[] lastMove = new int[4];

	int turnNumber = 1;
	int chosenFigure;
	int selectedButtonY;
	int selectedButtonX;

	boolean buttonIsSelected = false;
	boolean gameStopped = false;
	boolean justPromoted = false;
	boolean whiteTurn = true;

	Random random = new Random();

	ArrayList<Move> allMoves = new ArrayList<Move>();

	Timer timer;

	Chess() {
		timer = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println("test");
				if (((TimerLabel) gui.sideLabel[0]).isRunOutOfTime() == true && gameStopped == false) {
					gameStopped = true;
					System.out.println("BLACK WIN (white run out of time)");
					System.out.println("Wie pan co to znaczy przegrać na czas?");
				}
				if (((TimerLabel) gui.sideLabel[2]).isRunOutOfTime() == true && gameStopped == false) {
					gameStopped = true;
					System.out.println("WHITE WIN (black run out of time)");
					System.out.println("Wie pan co to znaczy przegrać na czas?");
				}
			}
		});
		for (int i = 0; i < 4; i++) {
			gui.sideButtons[i].addActionListener(this);
		}
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				gui.buttons[j][i].addActionListener((ActionListener) this);
			}
		}
		timer.start();

		// makeSound("bombsiteB");
		loadPosition();
		setAvailableMovesForBoard(mainBoard, defaultBoard);
		setLegalMovesForBoard(whiteTurn);
		setAllMovesArrayList();
		setTurnText(turnNumber);
		System.out.println("test");
		// firstTurn();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				if (e.getSource() == gui.buttons[j][i]) {
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
						justPromoted = false;
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
						if (justPromoted == false) {
							setAvailableMovesForBoard(mainBoard, defaultBoard);
							setLegalMovesForBoard(!whiteTurn);
							setAllMovesArrayList();
							checkCheck(mainBoard, defaultBoard, !whiteTurn);
							checkStaleMate(mainBoard, defaultBoard, !whiteTurn);
							pauseTimer(whiteTurn);
							if (gameStopped == false) {
								startTimer(!whiteTurn);
								whiteTurn = !whiteTurn;
								turnNumber++;
							}
							setTurnText(turnNumber);
							turnOnLastMove();
						}
						System.out.println("test");
					} else { // turn off all buttons and select one
						turnOffAllButtons();
						turnOnLastMove();
						buttonIsSelected = true;
						selectedButtonX = j;
						selectedButtonY = i;
						gui.buttons[j][i].setBackground(defaultSettings.selectColor);
						gui.buttons[j][i].setBorder(gui.border2);
					}
					if (mainBoard[selectedButtonX][selectedButtonY].color != whiteTurn
							&& mainBoard[selectedButtonX][selectedButtonY].exists == true && buttonIsSelected == true) {
						gui.textLabel.setText("Not your piece");
					} else if (mainBoard[selectedButtonX][selectedButtonY].exists == false
							&& buttonIsSelected == true) {
						gui.textLabel.setText("Nothing here");
					} else if (buttonIsSelected == false) {
						if (whiteTurn == true) {
							gui.textLabel.setText("White on move");
						} else {
							gui.textLabel.setText("Black on move");
						}
						// textfield.setText("Click something");
					} else if (mainBoard[selectedButtonX][selectedButtonY].color == whiteTurn
							&& mainBoard[selectedButtonX][selectedButtonY].exists == true && buttonIsSelected == true) {
						gui.textLabel.setText("OK but where?");
						for (int k = 0; k < defaultSettings.boardWidth; k++) {
							for (int l = 0; l < defaultSettings.boardLength; l++) {
								if (mainBoard[selectedButtonX][selectedButtonY].availableMoves[l][k] == true
										&& mainBoard[selectedButtonX][selectedButtonY].legalMovesStrikes[l][k] == true) {
									gui.buttons[l][k].setBackground(defaultSettings.moveColor);
								}
								if (mainBoard[selectedButtonX][selectedButtonY].availableStrikes[l][k] == true
										&& mainBoard[selectedButtonX][selectedButtonY].legalMovesStrikes[l][k] == true) {
									gui.buttons[l][k].setBackground(defaultSettings.attackColor);
								}
								if (mainBoard[selectedButtonX][selectedButtonY].availableCastle[l][k] == true) {
									gui.buttons[l][k].setBackground(defaultSettings.castleColor);
								}
							}
						}
					}
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			if (e.getSource() == gui.sideButtons[i]) {
				promote2(mainBoard, selectedButtonX, selectedButtonY, i);
			}
		}
	}

	void loadPosition() {
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				mainBoard[j][i] = new Figure() {
				};
				if (i >= defaultSettings.defaultPosition.length || j >= defaultSettings.defaultPosition[0].length) {
					continue;
				}
				if (i >= defaultSettings.defaultPositionHasBeenMoved.length
						|| j >= defaultSettings.defaultPositionHasBeenMoved[0].length) {
					continue;
				}
				if (i >= defaultSettings.defaultPositionisJustDoubleMovedPawn.length
						|| j >= defaultSettings.defaultPositionisJustDoubleMovedPawn[0].length) {
					continue;
				}
				if (i >= defaultSettings.boardWidth || j >= defaultSettings.boardLength) {
					continue;
				}
				for (int k = 0; k < pieces.length; k++) {
					if (pieces[k] == defaultSettings.defaultPosition[i][j]) {
						gui.buttons[j][i].setIcon(gui.piecesIcons[k]);
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
				mainBoard[j][i].hasBeenMoved = defaultSettings.defaultPositionHasBeenMoved[i][j];
				mainBoard[j][i].isJustDoubleMovedPawn = defaultSettings.defaultPositionisJustDoubleMovedPawn[i][j];
			}
		}
		savedGame[turnNumber] = defaultSettings.defaultPosition;
		// for (int m = 0; m < mainBoard[0].length; m++) {
		// for (int n = 0; n < mainBoard.length; n++) {
		// cloneFigure(m, n, testBoard, mainBoard);
		// }
		// }
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
	}

	void setLegalMovesForBoard(boolean isWhiteTurn) {
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				for (int k = 0; k < defaultSettings.boardWidth; k++) {
					for (int l = 0; l < defaultSettings.boardLength; l++) {
						mainBoard[j][i].legalMovesStrikes[l][k] = false;
						if ((mainBoard[j][i].availableMoves[l][k] == true
								|| mainBoard[j][i].availableStrikes[l][k] == true)
								&& (mainBoard[j][i].color == isWhiteTurn)) {
							boolean hasBeenMoved = mainBoard[j][i].hasBeenMoved;
							boolean isJustDoubleMovedPawn = mainBoard[j][i].isJustDoubleMovedPawn;
							Figure tmpFigure = mainBoard[l][k];
							Figure tmpEnPassantFigure = new Figure() {
							};
							if (mainBoard[j][i].type == "pawn" && mainBoard[l][k].exists == false && j != l) {
								if (mainBoard[j][i].color == true) {
									tmpEnPassantFigure = mainBoard[l][k + 1];
								} else {
									tmpEnPassantFigure = mainBoard[l][k - 1];
								}
								moveFigure(j, i, l, k, mainBoard);
								if (checkCheck(mainBoard, isWhiteTurn) == false) {
									mainBoard[l][k].legalMovesStrikes[l][k] = true;
								}
								undoMoveFigure(l, k, j, i, mainBoard, hasBeenMoved, isJustDoubleMovedPawn, tmpFigure,
										tmpEnPassantFigure);
							} // en passant
							else {
								moveFigure(j, i, l, k, mainBoard);
								if (checkCheck(mainBoard, isWhiteTurn) == false) {
									mainBoard[l][k].legalMovesStrikes[l][k] = true;
								}
								undoMoveFigure(l, k, j, i, mainBoard, hasBeenMoved, isJustDoubleMovedPawn, tmpFigure);
							}
						}
					}
				}
			}
		}
	}

	void setAllMovesArrayList() {
		allMoves.clear();
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				for (int k = 0; k < defaultSettings.boardWidth; k++) {
					for (int l = 0; l < defaultSettings.boardLength; l++) {
						if (mainBoard[j][i].legalMovesStrikes[l][k] == true)
							allMoves.add(new Move(mainBoard, j, i, l, k, false));
						else if (mainBoard[j][i].availableCastle[l][k] == true) {
							allMoves.add(new Move(mainBoard, j, i, l, k, true));
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
		if (mainBoard[j][i].color == true && mainBoard[j][i].type == "pawn"
				&& mainBoard[j][i + 1].exists == false && selectedButtonX != j) {
			gui.buttons[j][i + 1].setIcon(null);
		}
		if (mainBoard[j][i].color == false && mainBoard[j][i].type == "pawn"
				&& mainBoard[j][i - 1].exists == false && selectedButtonX != j) {
			gui.buttons[j][i - 1].setIcon(null);
		}
		Icon tmpIcon = gui.buttons[selectedButtonX][selectedButtonY].getIcon();
		gui.buttons[selectedButtonX][selectedButtonY].setIcon(null);
		gui.buttons[j][i].setIcon(tmpIcon);
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
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				board[j][i].isJustDoubleMovedPawn = false;
			}
		}
		if (board[fromX][fromY].type == "pawn" && board[toX][toY].exists == false && fromX != toX) { // en passant
			if (board[fromX][fromY].color == true) {
				board[toX][toY + 1] = new Figure() {
				};
			}
			if (board[fromX][fromY].color == false) {
				board[toX][toY - 1] = new Figure() {
				};
			}
		}
		Figure tmpFigure = board[fromX][fromY];
		board[fromX][fromY] = new Figure() {
		};
		board[toX][toY] = tmpFigure;
		if (board[toX][toY].type == "pawn" && board[toX][toY].hasBeenMoved == false && Math.abs(fromY - toY) == 2) {
			board[toX][toY].isJustDoubleMovedPawn = true;
		} else {
			board[toX][toY].isJustDoubleMovedPawn = false;
		}
		board[toX][toY].hasBeenMoved = true;
	}

	public void undoMoveFigure(int fromX, int fromY, int toX, int toY, Figure[][] board, boolean hasBeenMoved,
			boolean isJustDoubleMovedPawn, Figure strikeFigure) {
		Figure tmpFigure = board[fromX][fromY];
		board[fromX][fromY] = strikeFigure;
		board[toX][toY] = tmpFigure;
		board[toX][toY].hasBeenMoved = hasBeenMoved;
		board[toX][toY].isJustDoubleMovedPawn = isJustDoubleMovedPawn;
	}

	public void undoMoveFigure(int fromX, int fromY, int toX, int toY, Figure[][] board, boolean hasBeenMoved,
			boolean isJustDoubleMovedPawn, Figure strikeFigure, Figure enPassantFigure) {
		Figure tmpFigure = board[fromX][fromY];
		board[fromX][fromY] = strikeFigure;
		board[toX][toY] = tmpFigure;
		board[toX][toY].hasBeenMoved = hasBeenMoved;
		board[toX][toY].isJustDoubleMovedPawn = isJustDoubleMovedPawn;
		if (board[toX][toY].color == true) {
			board[fromX][fromY + 1] = enPassantFigure;
		}
		if (board[toX][toY].color == false) {
			board[fromX][fromY - 1] = enPassantFigure;
		}
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
		if ((currentY == board[0].length - 1 && board[currentX][currentY].color == false
				&& board[currentX][currentY].type == "pawn")
				|| (currentY == 0 && board[currentX][currentY].color == true
						&& board[currentX][currentY].type == "pawn")) {
			promote1(board, currentX, currentY);
			justPromoted = true;
		}
	}

	public void promote1(Figure[][] board, int currentX, int currentY) {
		gui.sidePiecesPanel.setVisible(true);
		if (board[currentX][currentY].color == true) {
			gui.sideButtons[0].setIcon(gui.piecesIcons[7]);
			gui.sideButtons[1].setIcon(gui.piecesIcons[8]);
			gui.sideButtons[2].setIcon(gui.piecesIcons[9]);
			gui.sideButtons[3].setIcon(gui.piecesIcons[10]);
		} else {
			gui.sideButtons[0].setIcon(gui.piecesIcons[1]);
			gui.sideButtons[1].setIcon(gui.piecesIcons[2]);
			gui.sideButtons[2].setIcon(gui.piecesIcons[3]);
			gui.sideButtons[3].setIcon(gui.piecesIcons[4]);
		}
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				gui.buttons[j][i].setEnabled(false);
			}
		}
		gui.textLabel.setText("Choose a figure");
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
				gui.buttons[j][i].setEnabled(true);
			}
		}
		gui.sidePiecesPanel.setVisible(false);
		setAvailableMovesForBoard(mainBoard, defaultBoard);
		setLegalMovesForBoard(!whiteTurn);
		setAllMovesArrayList();
		checkCheck(mainBoard, defaultBoard, !whiteTurn);
		checkStaleMate(mainBoard, defaultBoard, !whiteTurn);
		pauseTimer(whiteTurn);
		if (gameStopped == false) {
			startTimer(!whiteTurn);
			whiteTurn = !whiteTurn;
			turnNumber++;
		}
		setTurnText(turnNumber);
		turnOnLastMove();
	}

	public void changeGUI(int whichButton) {
		if (mainBoard[lastMove[2]][lastMove[3]].color == true)
			gui.buttons[lastMove[2]][lastMove[3]].setIcon(gui.piecesIcons[7 + whichButton]);
		else
			gui.buttons[lastMove[2]][lastMove[3]].setIcon(gui.piecesIcons[1 + whichButton]);
	}

	public void saveLastMove(int fromX, int fromY, int toX, int toY) {
		lastMove[0] = fromX;
		lastMove[1] = fromY;
		lastMove[2] = toX;
		lastMove[3] = toY;
		savedGame[turnNumber + 1] = defaultSettings.defaultPosition;
	}

	public void turnOnLastMove() {
		if (turnNumber != 1 || whiteTurn != true) {
			if (turnNumber != 1 || whiteTurn != true) {
				gui.buttons[lastMove[0]][lastMove[1]].setBackground(defaultSettings.lastMoveColor);
				gui.buttons[lastMove[2]][lastMove[3]].setBackground(defaultSettings.lastMoveColor);
			}
		}
	}

	public void turnOffAllButtons() {
		for (int i = 0; i < defaultSettings.boardWidth; i++) {
			for (int j = 0; j < defaultSettings.boardLength; j++) {
				gui.buttons[j][i].setBorder(gui.emptyBorder2);
				if ((i + j) % 2 == 0) {
					gui.buttons[j][i].setBackground(defaultSettings.tileColor1);
					gui.buttons[j][i].setForeground(defaultSettings.tileColor1);
				} else
					gui.buttons[j][i].setBackground(defaultSettings.tileColor2);
				gui.buttons[j][i].setForeground(defaultSettings.tileColor2);
			}
		}
	}

	public boolean checkCheck(Figure[][] board, Board attackedBoard, boolean isWhite) {
		if (isWhite == false) {
			for (int i = 0; i < defaultSettings.boardWidth; i++) {
				for (int j = 0; j < defaultSettings.boardLength; j++) {
					if (board[j][i].type.equals("king") && board[j][i].color == false) {
						if (attackedBoard.attackedByWhiteBoard[j][i] == true) {
							System.out.println("CHECK (by white)");
							checkMate(isWhite);
							return true;
						}
					}
				}
			}
		}
		if (isWhite == true) {
			for (int i = 0; i < defaultSettings.boardWidth; i++) {
				for (int j = 0; j < defaultSettings.boardLength; j++) {
					if (board[j][i].type.equals("king") && board[j][i].color == true) {
						if (attackedBoard.attackedByBlackBoard[j][i] == true) {
							System.out.println("CHECK (by black)");
							checkMate(isWhite);
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean checkCheck(Figure[][] board, boolean isWhite) {
		if (isWhite == false) {
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
		if (isWhite == true) {
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

	void checkMate(boolean enemy) {
		if (allMoves.size() == 0) {
			if (mainBoard[lastMove[2]][lastMove[3]].color == true)
				System.out.println("MATE (by white)");
			if (mainBoard[lastMove[2]][lastMove[3]].color == false)
				System.out.println("MATE (by black)");
			System.out.println("Wie pan co to znaczy dostać szewca?");
			gameStopped = true;
		}
	}

	void checkStaleMate(Figure[][] board, Board attackedBoard, boolean enemy) {
		if (checkCheck(board, enemy) == false) {
			if (allMoves.size() == 0) {
				if (mainBoard[lastMove[2]][lastMove[3]].color == true)
					System.out.println("STALEMATE (by white)");
				if (mainBoard[lastMove[2]][lastMove[3]].color == false)
					System.out.println("STALEMATE (by black)");
				System.out.println("Wie pan co to znaczy zrobić pata?");
				gameStopped = true;
			}
		}
	}

	public Color colorChange(Color color, int r, int g, int b) { // not used currently
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

	public void pauseTimer(boolean isWhite) {
		if (isWhite == true)
			((TimerLabel) gui.sideLabel[0]).stopTimer();
		else
			((TimerLabel) gui.sideLabel[2]).stopTimer();
	}

	public void startTimer(boolean isWhite) {
		if (isWhite == true)
			((TimerLabel) gui.sideLabel[0]).startTimer();
		else
			((TimerLabel) gui.sideLabel[2]).startTimer();
	}

	public void setTurnText(int turnNumber) {
		gui.sideLabel[1].setText(Integer.toString(turnNumber));
	}

	public void firstTurn() { // not used currently
		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (random.nextInt(2) == 0) {
			gui.textLabel.setText("White starts");
		} else {
			gui.textLabel.setText("Black starts (Just Kidding)");
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
