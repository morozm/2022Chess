package pl.pw.edu.ee;

import java.awt.Color;
import java.awt.Font;
//import java.lang.Math;

public class Settings {
        public int boardLength = 8; // a-h
        public int boardWidth = 8; // 1-8
        boolean whiteStartsGame = true;
        Color mainColor1 = new Color(222, 155, 53); // orange
        Color mainColor2 = new Color(12, 15, 18); // black
        Color mainColor3 = new Color(188, 182, 174); // grey
        Color tileColor1 = new Color(204, 186, 124);
        Color tileColor2 = new Color(65, 58, 39);
        Color attackColor = new Color(255, 100, 100); // red
        Color moveColor = new Color(100, 255, 100); // green
        Color selectColor = new Color(222, 155, 53);
        Color castleColor = new Color(100, 100, 255); // blue
        Color lastMoveColor = new Color(255, 255, 100); // yellow

        Font font1 = new Font("Counter-Strike Regular", Font.PLAIN, 50);
        Font font2 = new Font("Guardians", Font.BOLD, (int) (400 / (Math.pow(Math.max(boardLength, boardWidth), 1.5))));
        Font font3 = new Font("Guardians", Font.BOLD, 18);
        Font font4 = new Font("Guardians", Font.BOLD, 12);
        Font font5 = new Font("Guardians", Font.BOLD, 10);
        Font font6 = new Font("blackpast", Font.PLAIN, 22);
        Font font7 = new Font("Counter-Strike Regular", Font.PLAIN, 30);

        String[][] defaultPosition = {
                        { "bR", "bN", "bB", "bQ", "bK", "bB", "bN", "bR" },
                        { "bP", "bP", "bP", "bP", "bP", "bP", "bP", "bP" },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "wP", "wP", "wP", "wP", "wP", "wP", "wP", "wP" },
                        { "wR", "wN", "wB", "wQ", "wK", "wB", "wN", "wR" }
        };
        String[][] defaultPosition2 = {
                        { "wR", "  ", "bK", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "wN", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "bP", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "bP", "  ", "  ", "  ", "  ", "wK" },
                        { "  ", "wP", "  ", "wB", "  ", "wQ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " }
        };
        String[][] defaultPositio3 = {
                        { "bR", "  ", "  ", "  ", "bK", "  ", "  ", "bR" },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "bN" },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "wR", "  ", "  ", "wK", "  ", "wR", "wR" }
        };
        String[][] defaultPosition4 = {
                        { "bR", "  ", "  ", "  ", "bK", "  ", "bR", "bR" },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "bN" },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "wR", "wR", "wR", "  ", "wR", "wR", "wR" }
        };
        String[][] defaultPosition5 = {
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "wP", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "bP", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " }
        };
        String[][] defaultPosition6 = {
                        { "  ", "bP", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "wQ", "  ", "  ", "  ", "  ", "bK", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " }
        };
        String[][] defaultPosition7 = {
                        { "wQ", "  ", "bB", "  ", "  ", "bR", "bK", "  " },
                        { "bP", "  ", "bP", "bP", "  ", "  ", "bP", "bP" },
                        { "  ", "bP", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "bB", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "wP", "  ", "wN", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "wB", "  ", "  ", "wP", "  " },
                        { "wP", "wP", "  ", "wK", "  ", "  ", "bQ", "  " },
                        { "wR", "  ", "wB", "  ", "  ", "  ", "  ", "  " }
        };
        String[][] defaultPosition8 = {
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "bK", "  ", "  ", "  ", "  ", "  ", "wP", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "wQ" },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "wQ", "  ", "  ", "  ", "  ", "  ", "wK" }
        };
        String[][] defaultPosition9 = {
                        { "  ", "  ", "bR", "  ", "  ", "  ", "  ", "bK" },
                        { "  ", "  ", "bR", "bN", "  ", "bP", "bP", "bP" },
                        { "bP", "  ", "bQ", "  ", "  ", "bN", "  ", "  " },
                        { "wP", "bP", "wP", "bP", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "wP", "bP", "  ", "  ", "  " },
                        { "wR", "  ", "  ", "  ", "wP", "  ", "  ", "  " },
                        { "  ", "wQ", "  ", "wN", "wB", "wP", "wP", "wP" },
                        { "  ", "  ", "wR", "  ", "  ", "  ", "wK", "  " }
        };
        String[][] defaultPosition10 = {
                        { "bP", "bP", "bP", "bP", "bP", "bP", "bP", "bP" },
                        { "bP", "bP", "bP", "bP", "bP", "bP", "bP", "bP" },
                        { "bP", "bP", "bP", "bP", "bP", "bP", "bP", "bP" },
                        { "bP", "bP", "bP", "bP", "bP", "bP", "bP", "bP" },
                        { "wP", "wP", "wP", "wP", "wP", "wP", "wP", "wP" },
                        { "wP", "wP", "wP", "wP", "wP", "wP", "wP", "wP" },
                        { "wP", "wP", "wP", "wP", "wP", "wP", "wP", "wP" },
                        { "wP", "wP", "wP", "wP", "wP", "wP", "wP", "wP" },
                        { "wP", "wP", "wP", "wP", "wP", "wP", "wP", "wP" },
                        { "wP", "wP", "wP", "wP", "wP", "wP", "wP", "wP" },
                        { "wP", "wP", "wP", "wP", "wP", "wP", "wP", "wP" },
                        { "wP", "wP", "wP", "wP", "wP", "wP", "wP", "wP" }
        };
        String[][] defaultPosition11 = {
                        { "bK", "  ", "  " },
                        { "  ", "  ", "  " },
                        { "  ", "wQ", "  " }
        };
        String[][] defaultPosition12 = {
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "bP", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "wP", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " }
        };
        boolean[][] defaultPositionHasBeenMoved = {
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, }
        };
        boolean[][] defaultPositionisJustDoubleMovedPawn = {
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, }
        };
        boolean[][] defaultPositionHasBeenMoved9 = {
                        { false, false, false, false, false, false, false, true, },
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, true, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, false, true, false, false, false, false, false, }
        };
        boolean[][] defaultPositionisJustDoubleMovedPawn9 = {
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, true, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, },
                        { false, false, false, false, false, false, false, false, }
        };
}
