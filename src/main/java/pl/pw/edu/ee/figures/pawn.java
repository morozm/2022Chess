package pl.pw.edu.ee.figures;

public class pawn extends figure {
    {
        this.value = 1;
    }

    @Override
    public void setAvailableMoves(figure[][] board, boolean[][] attackedByWhiteBoard,
            boolean[][] attackedByBlackBoard) {

        if (board[currentX][currentY].color == false) {
            if (hasBeenMoved == false) {
                if (board[0].length - 1 >= currentY + 1) {
                    if (board[currentX][currentY + 1].exists == false) {
                        availableMoves[currentX][currentY + 1] = true;
                    }
                }
                if (board[0].length - 1 >= currentY + 2) {
                    if (board[currentX][currentY + 1].exists == false
                            && board[currentX][currentY + 2].exists == false) {
                        availableMoves[currentX][currentY + 2] = true;
                    }
                }
            } else if (hasBeenMoved == true) {
                if (board[0].length - 1 >= currentY + 1) {
                    if (board[currentX][currentY + 1].exists == false) {
                        availableMoves[currentX][currentY + 1] = true;
                    }
                }
            }
            if (currentY + 1 <= board[0].length - 1 && currentX + 1 <= board.length - 1) {
                // wywali się jak będzie na końcu boarda Y+1 poza zakresem
                attackedByBlackBoard[currentX + 1][currentY + 1] = true;
                if (board[currentX + 1][currentY + 1].exists == true
                        && board[currentX + 1][currentY + 1].color == true) {
                    availableStrikes[currentX + 1][currentY + 1] = true;
                }
            }
            if (currentY + 1 <= board[0].length - 1 && currentX - 1 >= 0) {
                attackedByBlackBoard[currentX - 1][currentY + 1] = true;
                if (board[currentX - 1][currentY + 1].exists == true
                        && board[currentX - 1][currentY + 1].color == true) {
                    availableStrikes[currentX - 1][currentY + 1] = true;
                }
            }
        } else if (board[currentX][currentY].color == true) {
            if (hasBeenMoved == false) {
                if (0 <= currentY - 1) {
                    if (board[currentX][currentY - 1].exists == false) {
                        availableMoves[currentX][currentY - 1] = true;
                    }
                }
                if (0 <= currentY - 2) {
                    if (board[currentX][currentY - 1].exists == false
                            && board[currentX][currentY - 2].exists == false) {
                        availableMoves[currentX][currentY - 2] = true;
                    }
                }
            } else if (hasBeenMoved == true) {
                if (0 <= currentY - 1) {
                    if (board[currentX][currentY - 1].exists == false) {
                        availableMoves[currentX][currentY - 1] = true;
                    }
                }
            }
            if (currentY - 1 >= 0 && currentX + 1 <= board.length - 1) {
                attackedByBlackBoard[currentX + 1][currentY - 1] = true;
                if (board[currentX + 1][currentY - 1].exists == true
                        && board[currentX + 1][currentY - 1].color == false) {
                    availableStrikes[currentX + 1][currentY - 1] = true;
                }
            }
            if (currentY - 1 >= 0 && currentX - 1 >= 0) {
                attackedByBlackBoard[currentX - 1][currentY - 1] = true;
                if (board[currentX - 1][currentY - 1].exists == true
                        && board[currentX - 1][currentY - 1].color == false) {
                    availableStrikes[currentX - 1][currentY - 1] = true;
                }
            }
        }
    }

    private void promote(int currentY, int currentX) {

    }
}
