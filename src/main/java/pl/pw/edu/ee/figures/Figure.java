package pl.pw.edu.ee.figures;

import pl.pw.edu.ee.Settings;

public abstract class Figure {
    Settings defaultSettings = new Settings();
    public boolean exists = false;
    public boolean color;
    public int value;
    public boolean[][] availableMoves = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
    public boolean[][] availableStrikes = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
    public boolean[][] legalMovesStrikes = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
    public boolean[][] availableCastle = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
    public boolean hasBeenMoved = false;
    public boolean isJustDoubleMovedPawn = false;
    public String type = "";

    public void setAvailableMoves(Figure[][] board, boolean[][] attackedByWhiteBoard,
            boolean[][] attackedByBlackBoard, int currentX, int currentY) { // sets available moves and strikes
        ;
    }

    public void setAvailableCastles(Figure[][] board, boolean[][] attackedByWhiteBoard,
            boolean[][] attackedByBlackBoard, int currentX, int currentY) { // sets available castles only for king
        ;
    }
}
