package pl.pw.edu.ee;

import pl.pw.edu.ee.figures.figure;

public class board {
    settings defaultSettings = new settings();

    boolean[][] attackedByWhiteBoard = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
	boolean[][] attackedByBlackBoard = new boolean[defaultSettings.boardLength][defaultSettings.boardWidth];
    figure[][] board = new figure[defaultSettings.boardLength][defaultSettings.boardWidth];
}
