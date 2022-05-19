package pl.pw.edu.ee.figures;

public class queen extends figure {
    {
        this.value = 9;
    }

    @Override
    public void setAvailableMoves(figure[][] board, boolean[][] attackedByWhiteBoard,
            boolean[][] attackedByBlackBoard) {

        int i;
        i = 1;
        while (currentX + i < board.length) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX + i][currentY] = true;
            } else {
                attackedByBlackBoard[currentX + i][currentY] = true;
            }
            if (board[currentX + i][currentY].exists == false) {
                availableMoves[currentX + i][currentY] = true;
                i++;
            } else if (this.color != board[currentX + i][currentY].color) {
                availableStrikes[currentX + i][currentY] = true;
                break;
            } else {
                break;
            }
        }
        i = 1;
        while (currentX - i >= 0) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX - i][currentY] = true;
            } else {
                attackedByBlackBoard[currentX - i][currentY] = true;
            }
            if (board[currentX - i][currentY].exists == false) {
                availableMoves[currentX - i][currentY] = true;
                i++;
            } else if (this.color != board[currentX - i][currentY].color) {
                availableStrikes[currentX - i][currentY] = true;
                break;
            } else {
                break;
            }
        }
        i = 1;
        while (currentY + i < board[0].length) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX][currentY + i] = true;
            } else {
                attackedByBlackBoard[currentX][currentY + i] = true;
            }
            if (board[currentX][currentY + i].exists == false) {
                availableMoves[currentX][currentY + i] = true;
                i++;
            } else if (this.color != board[currentX][currentY + i].color) {
                availableStrikes[currentX][currentY + i] = true;
                break;
            } else {
                break;
            }
        }
        i = 1;
        while (currentY - i >= 0) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX][currentY - i] = true;
            } else {
                attackedByBlackBoard[currentX][currentY - i] = true;
            }
            if (board[currentX][currentY - i].exists == false) {
                availableMoves[currentX][currentY - i] = true;
                i++;
            } else if (this.color != board[currentX][currentY - i].color) {
                availableStrikes[currentX][currentY - i] = true;
                break;
            } else {
                break;
            }
        }
        i = 1;
        while (currentX + i < board.length && currentY + i < board[0].length) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX + i][currentY + i] = true;
            } else {
                attackedByBlackBoard[currentX + i][currentY + i] = true;
            }
            if (board[currentX + i][currentY + i].exists == false) {
                availableMoves[currentX + i][currentY + i] = true;
                i++;
            } else if (this.color != board[currentX + i][currentY + i].color) {
                availableStrikes[currentX + i][currentY + i] = true;
                break;
            } else {
                break;
            }
        }
        i = 1;
        while (currentX - i >= 0 && currentY + i < board[0].length) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX - i][currentY + i] = true;
            } else {
                attackedByBlackBoard[currentX - i][currentY + i] = true;
            }
            if (board[currentX - i][currentY + i].exists == false) {
                availableMoves[currentX - i][currentY + i] = true;
                i++;
            } else if (this.color != board[currentX - i][currentY + i].color) {
                availableStrikes[currentX - i][currentY + i] = true;
                break;
            } else {
                break;
            }
        }
        i = 1;
        while (currentX + i < board.length && currentY - i >= 0) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX + i][currentY - i] = true;
            } else {
                attackedByBlackBoard[currentX + i][currentY - i] = true;
            }
            if (board[currentX + i][currentY - i].exists == false) {
                availableMoves[currentX + i][currentY - i] = true;
                i++;
            } else if (this.color != board[currentX + i][currentY - i].color) {
                availableStrikes[currentX + i][currentY - i] = true;
                break;
            } else {
                break;
            }
        }
        i = 1;
        while (currentX - i >= 0 && currentY - i >= 0) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX - i][currentY - i] = true;
            } else {
                attackedByBlackBoard[currentX - i][currentY - i] = true;
            }
            if (board[currentX - i][currentY - i].exists == false) {
                availableMoves[currentX - i][currentY - i] = true;
                i++;
            } else if (this.color != board[currentX - i][currentY - i].color) {
                availableStrikes[currentX - i][currentY - i] = true;
                break;
            } else {
                break;
            }
        }
    }
}