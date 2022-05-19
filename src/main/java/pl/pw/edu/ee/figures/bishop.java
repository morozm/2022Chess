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
