package pl.pw.edu.ee.figures;

public class bishop extends figure {
    {
        this.value = 3;
    }

    @Override
    public void setAvailableMoves(figure[][] board, boolean[][] attackedByWhiteBoard,
            boolean[][] attackedByBlackBoard) {

        int i;
        i = 1;
        while (currentY + i < board.length && currentX + i < board[0].length) {
            if (board[currentY + i][currentX + i].exists == false) {
                availableMoves[currentY + i][currentX + i] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + i][currentX + i] = true;
                } else {
                    attackedByBlackBoard[currentY + i][currentX + i] = true;
                }
                i++;
            } else if (this.color != board[currentY + i][currentX + i].color) {
                availableStrikes[currentY + i][currentX + i] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + i][currentX + i] = true;
                } else {
                    attackedByBlackBoard[currentY + i][currentX + i] = true;
                }
                break;
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + i][currentX + i] = true;
                } else {
                    attackedByBlackBoard[currentY + i][currentX + i] = true;
                }
                break;
            }
        }
        i = 1;
        while (currentY - i >= 0 && currentX + i < board[0].length) {
            if (board[currentY - i][currentX + i].exists == false) {
                availableMoves[currentY - i][currentX + i] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - i][currentX + i] = true;
                } else {
                    attackedByBlackBoard[currentY - i][currentX + i] = true;
                }
                i++;
            } else if (this.color != board[currentY - i][currentX + i].color) {
                availableStrikes[currentY - i][currentX + i] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - i][currentX + i] = true;
                } else {
                    attackedByBlackBoard[currentY - i][currentX + i] = true;
                }
                break;
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - i][currentX + i] = true;
                } else {
                    attackedByBlackBoard[currentY - i][currentX + i] = true;
                }
                break;
            }
        }
        i = 1;
        while (currentY + i < board.length && currentX - i >= 0) {
            if (board[currentY + i][currentX - i].exists == false) {
                availableMoves[currentY + i][currentX - i] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + i][currentX - i] = true;
                } else {
                    attackedByBlackBoard[currentY + i][currentX - i] = true;
                }
                i++;
            } else if (this.color != board[currentY + i][currentX - i].color) {
                availableStrikes[currentY + i][currentX - i] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + i][currentX - i] = true;
                } else {
                    attackedByBlackBoard[currentY + i][currentX - i] = true;
                }
                break;
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + i][currentX - i] = true;
                } else {
                    attackedByBlackBoard[currentY + i][currentX - i] = true;
                }
                break;
            }
        }
        i = 1;
        while (currentY - i >= 0 && currentX - i >= 0) {
            if (board[currentY - i][currentX - i].exists == false) {
                availableMoves[currentY - i][currentX - i] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - i][currentX - i] = true;
                } else {
                    attackedByBlackBoard[currentY - i][currentX - i] = true;
                }
                i++;
            } else if (this.color != board[currentY - i][currentX - i].color) {
                availableStrikes[currentY - i][currentX - i] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - i][currentX - i] = true;
                } else {
                    attackedByBlackBoard[currentY - i][currentX - i] = true;
                }
                break;
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - i][currentX - i] = true;
                } else {
                    attackedByBlackBoard[currentY - i][currentX - i] = true;
                }
                break;
            }
        }
    }
}
