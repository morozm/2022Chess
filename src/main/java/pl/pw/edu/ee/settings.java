package pl.pw.edu.ee;

public class settings {
        int boardLength = 8; // a-h dont set for more than 140
        int boardWidth = 8; // 1-8
        String tilecolor1 = "Green";
        String tilecolor2 = "White";
        boolean whiteStartsGame = true;
        String[][] defaultPosition2 = {
                        { "bR", "bN", "bB", "bQ", "bK", "bB", "bN", "bR" },
                        { "bP", "bP", "bP", "bP", "bP", "bP", "bP", "bP" },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "wP", "wP", "wP", "wP", "wP", "wP", "wP", "wP" },
                        { "wR", "wN", "wB", "wQ", "wK", "wB", "wN", "wR" }
        };
        String[][] defaultPosition3 = {
                        { "wR", "  ", "bK", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "wN", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "bP", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "bP", "  ", "  ", "  ", "  ", "wK" },
                        { "  ", "wP", "  ", "wB", "  ", "wQ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " }
        };
        String[][] defaultPosition = {
                        { "bR", "  ", "  ", "  ", "bK", "  ", "  ", "bR" },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "bN" },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  " },
                        { "wR", "  ", "  ", "  ", "  ", "  ", "wK", "wR" }
        };
}
