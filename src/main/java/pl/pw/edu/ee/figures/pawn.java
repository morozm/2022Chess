package pl.pw.edu.ee.figures;

public class pawn extends figure {
    {
        this.value = 1;
    }

    @Override
    public void setAvailableMoves(figure[][] board, boolean[][] attackedByWhiteBoard,
            boolean[][] attackedByBlackBoard) {

        if (board[currentY][currentX].color == false) {
            if (hasBeenMoved == false) {
                if (board.length - 1 >= currentY + 1) {
                    if (board[currentY + 1][currentX].exists == false) {
                        availableMoves[currentY + 1][currentX] = true;
                    }
                }

                if (board.length - 1 >= currentY + 2) {
                    if (board[currentY + 1][currentX].exists == false
                            && board[currentY + 2][currentX].exists == false) {
                        availableMoves[currentY + 2][currentX] = true;
                    }
                }
            } else if (hasBeenMoved == true) {
                if (board.length - 1 >= currentY + 1) {
                    if (board[currentY + 1][currentX].exists == false) {
                        availableMoves[currentY + 1][currentX] = true;
                    }
                }
            }
            if (currentX + 1 < board[0].length) { // wywali się jak będzie na końcu boarda Y+1 poza zakresem
                if (board[currentY + 1][currentX + 1].exists == true) {
                    attackedByBlackBoard[currentY + 1][currentX + 1] = true;
                    if (board[currentY + 1][currentX + 1].color == true) {
                        availableStrikes[currentY + 1][currentX + 1] = true;
                    }
                }
            }
            if (currentX - 1 >= 0) {
                if (board[currentY + 1][currentX - 1].exists == true) {
                    attackedByBlackBoard[currentY + 1][currentX - 1] = true;
                    if (board[currentY + 1][currentX - 1].color == true) {
                        availableStrikes[currentY + 1][currentX - 1] = true;
                    }
                }
            }
        } else if (board[currentY][currentX].color == true) {
            if (hasBeenMoved == false) {
                if (0 <= currentY - 1) {
                    if (board[currentY - 1][currentX].exists == false) {
                        availableMoves[currentY - 1][currentX] = true;
                    }
                }

                if (0 <= currentY - 2) {
                    if (board[currentY - 1][currentX].exists == false
                            && board[currentY - 2][currentX].exists == false) {
                        availableMoves[currentY - 2][currentX] = true;
                    }
                }
            } else if (hasBeenMoved == true) {
                if (0 <= currentY - 1) {
                    if (board[currentY - 1][currentX].exists == false) {
                        availableMoves[currentY - 1][currentX] = true;
                    }
                }
            }
            if (currentX + 1 < board[0].length) { // wywali się jak będzie na końcu boarda Y-1 poza zakresem
                if (board[currentY - 1][currentX + 1].exists == true) {
                    attackedByWhiteBoard[currentY - 1][currentX + 1] = true;
                    if (board[currentY - 1][currentX + 1].color == false) {
                        availableStrikes[currentY - 1][currentX + 1] = true;
                    }
                }
            }
            if (currentX - 1 >= 0) {
                if (board[currentY - 1][currentX - 1].exists == true) {
                    attackedByWhiteBoard[currentY - 1][currentX - 1] = true;
                    if (board[currentY - 1][currentX - 1].color == false) {
                        availableStrikes[currentY - 1][currentX - 1] = true;
                    }
                }
            }
        }
    }

    private void promote(int currentY, int currentX) {

    }
}
