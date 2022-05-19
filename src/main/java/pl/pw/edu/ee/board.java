package pl.pw.edu.ee;

public class board {
    settings defaultSettings = new settings();

    public boolean[][] attackedByWhiteBoard = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
    public boolean[][] attackedByBlackBoard = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
}
