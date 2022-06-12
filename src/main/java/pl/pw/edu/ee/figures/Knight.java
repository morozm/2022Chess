package pl.pw.edu.ee.figures;

public class Knight extends Figure {
    public Knight(){
        this.value = 3;
        this.type = "knight";
        this.typeShort = "N";
    }

    public Knight(Figure knight) {
        this.availableCastle = knight.availableCastle;
        this.availableMoves = knight.availableMoves;
        this.availableStrikes = knight.availableStrikes;
        this.color = knight.color;
        this.defaultSettings = knight.defaultSettings;
        this.exists = knight.exists;
        this.hasBeenMoved = knight.hasBeenMoved;
        this.legalMovesStrikes = knight.legalMovesStrikes;
        this.type = knight.type;
        this.typeShort = knight.typeShort;
        this.value = knight.value;
    }

    @Override
    public void setAvailableMoves(Figure[][] board, boolean[][] attackedByWhiteBoard,
            boolean[][] attackedByBlackBoard, int currentX, int currentY) {

        if (currentX + 1 < board.length && currentY + 2 < board[0].length) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX + 1][currentY + 2] = true;
            } else {
                attackedByBlackBoard[currentX + 1][currentY + 2] = true;
            }
            if (board[currentX + 1][currentY + 2].exists == false) {
                availableMoves[currentX + 1][currentY + 2] = true;
            } else if (board[currentX + 1][currentY + 2].exists == true
                    && this.color != board[currentX + 1][currentY + 2].color) {
                availableStrikes[currentX + 1][currentY + 2] = true;
            }
        }
        if (currentX + 1 < board.length && currentY - 2 >= 0) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX + 1][currentY - 2] = true;
            } else {
                attackedByBlackBoard[currentX + 1][currentY - 2] = true;
            }
            if (board[currentX + 1][currentY - 2].exists == false) {
                availableMoves[currentX + 1][currentY - 2] = true;
            } else if (board[currentX + 1][currentY - 2].exists == true
                    && this.color != board[currentX + 1][currentY - 2].color) {
                availableStrikes[currentX + 1][currentY - 2] = true;
            }
        }
        if (currentX + 2 < board.length && currentY + 1 < board[0].length) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX + 2][currentY + 1] = true;
            } else {
                attackedByBlackBoard[currentX + 2][currentY + 1] = true;
            }
            if (board[currentX + 2][currentY + 1].exists == false) {
                availableMoves[currentX + 2][currentY + 1] = true;
            } else if (board[currentX + 2][currentY + 1].exists == true
                    && this.color != board[currentX + 2][currentY + 1].color) {
                availableStrikes[currentX + 2][currentY + 1] = true;
            }
        }
        if (currentX + 2 < board.length && currentY - 1 >= 0) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX + 2][currentY - 1] = true;
            } else {
                attackedByBlackBoard[currentX + 2][currentY - 1] = true;
            }
            if (board[currentX + 2][currentY - 1].exists == false) {
                availableMoves[currentX + 2][currentY - 1] = true;
            } else if (board[currentX + 2][currentY - 1].exists == true
                    && this.color != board[currentX + 2][currentY - 1].color) {
                availableStrikes[currentX + 2][currentY - 1] = true;
            }
        }
        if (currentX - 1 >= 0 && currentY + 2 < board[0].length) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX - 1][currentY + 2] = true;
            } else {
                attackedByBlackBoard[currentX - 1][currentY + 2] = true;
            }
            if (board[currentX - 1][currentY + 2].exists == false) {
                availableMoves[currentX - 1][currentY + 2] = true;
            } else if (board[currentX - 1][currentY + 2].exists == true
                    && this.color != board[currentX - 1][currentY + 2].color) {
                availableStrikes[currentX - 1][currentY + 2] = true;
            }
        }
        if (currentX - 1 >= 0 && currentY - 2 >= 0) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX - 1][currentY - 2] = true;
            } else {
                attackedByBlackBoard[currentX - 1][currentY - 2] = true;
            }
            if (board[currentX - 1][currentY - 2].exists == false) {
                availableMoves[currentX - 1][currentY - 2] = true;
            } else if (board[currentX - 1][currentY - 2].exists == true
                    && this.color != board[currentX - 1][currentY - 2].color) {
                availableStrikes[currentX - 1][currentY - 2] = true;
            }
        }
        if (currentX - 2 >= 0 && currentY + 1 < board[0].length) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX - 2][currentY + 1] = true;
            } else {
                attackedByBlackBoard[currentX - 2][currentY + 1] = true;
            }
            if (board[currentX - 2][currentY + 1].exists == false) {
                availableMoves[currentX - 2][currentY + 1] = true;
            } else if (board[currentX - 2][currentY + 1].exists == true
                    && this.color != board[currentX - 2][currentY + 1].color) {
                availableStrikes[currentX - 2][currentY + 1] = true;
            }
        }
        if (currentX - 2 >= 0 && currentY - 1 >= 0) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX - 2][currentY - 1] = true;
            } else {
                attackedByBlackBoard[currentX - 2][currentY - 1] = true;
            }
            if (board[currentX - 2][currentY - 1].exists == false) {
                availableMoves[currentX - 2][currentY - 1] = true;
            } else if (board[currentX - 2][currentY - 1].exists == true
                    && this.color != board[currentX - 2][currentY - 1].color) {
                availableStrikes[currentX - 2][currentY - 1] = true;
            }
        }
    }
}
