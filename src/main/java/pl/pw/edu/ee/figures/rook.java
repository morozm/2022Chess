package pl.pw.edu.ee.figures;

public class rook extends figure {
    {
        this.value = 5;
    }

    @Override
    public void setAvailableMoves(figure[][] board, boolean[][] attackedByWhiteBoard,
            boolean[][] attackedByBlackBoard) {

        int i;
        i = 1;
        while (currentY + i < board.length) {
            if (board[currentY + i][currentX].exists == false) {
                availableMoves[currentY + i][currentX] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + i][currentX] = true;
                } else {
                    attackedByBlackBoard[currentY + i][currentX] = true;
                }
                i++;
            } else if (this.color != board[currentY + i][currentX].color) {
                availableStrikes[currentY + i][currentX] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + i][currentX] = true;
                } else {
                    attackedByBlackBoard[currentY + i][currentX] = true;
                }
                break;
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + i][currentX] = true;
                } else {
                    attackedByBlackBoard[currentY + i][currentX] = true;
                }
                break;
            }
        }
        i = 1;
        while (currentY - i >= 0) {
            if (board[currentY - i][currentX].exists == false) {
                availableMoves[currentY - i][currentX] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - i][currentX] = true;
                } else {
                    attackedByBlackBoard[currentY - i][currentX] = true;
                }
                i++;
            } else if (this.color != board[currentY - i][currentX].color) {
                availableStrikes[currentY - i][currentX] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - i][currentX] = true;
                } else {
                    attackedByBlackBoard[currentY - i][currentX] = true;
                }
                break;
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - i][currentX] = true;
                } else {
                    attackedByBlackBoard[currentY - i][currentX] = true;
                }
                break;
            }
        }
        i = 1;
        while (currentX + i < board[0].length) {
            if (board[currentY][currentX + i].exists == false) {
                availableMoves[currentY][currentX + i] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY][currentX + i] = true;
                } else {
                    attackedByBlackBoard[currentY][currentX + i] = true;
                }
                i++;
            } else if (this.color != board[currentY][currentX + i].color) {
                availableStrikes[currentY][currentX + i] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY][currentX + i] = true;
                } else {
                    attackedByBlackBoard[currentY][currentX + i] = true;
                }
                break;
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY][currentX + i] = true;
                } else {
                    attackedByBlackBoard[currentY][currentX + i] = true;
                }
                break;
            }
        }
        i = 1;
        while (currentX - i >= 0) {
            if (board[currentY][currentX - i].exists == false) {
                availableMoves[currentY][currentX - i] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY][currentX - i] = true;
                } else {
                    attackedByBlackBoard[currentY][currentX - i] = true;
                }
                i++;
            } else if (this.color != board[currentY][currentX - i].color) {
                availableStrikes[currentY][currentX - i] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY][currentX - i] = true;
                } else {
                    attackedByBlackBoard[currentY][currentX - i] = true;
                }
                break;
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY][currentX - i] = true;
                } else {
                    attackedByBlackBoard[currentY][currentX - i] = true;
                }
                break;
            }
        }
    }
}
