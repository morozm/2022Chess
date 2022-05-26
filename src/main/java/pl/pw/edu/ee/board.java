package pl.pw.edu.ee;

public class Board {
    Settings defaultSettings = new Settings();

    public boolean[][] attackedByWhiteBoard = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
    public boolean[][] attackedByBlackBoard = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
}
