package pl.pw.edu.ee;

import pl.pw.edu.ee.figures.Figure;

public class Move {
    public Figure[][] board;
    public int fromX;
    public int fromY;
    public int toX;
    public int toY;
    public boolean isCastle;

    Move(Figure[][] board, int fromX, int fromY, int toX, int toY, boolean isCastle) {
        this.board = board;
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.isCastle = isCastle;
    }

    // public void moveFigure(Figure[][] board, int fromY, int fromX, int toY, int
    // toX){
    // Settings defaultSettings = new Settings();
    // board[toY][toX] = board[fromX][fromY];
    // board[toY][toX].hasBeenMoved = true;
    // board[fromX][fromY] = new Figure() {
    // };
    // board[fromX][fromY].availableMoves = new
    // boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
    // board[fromX][fromY].availableStrikes = new
    // boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
    // }
}
