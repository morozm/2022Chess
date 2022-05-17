package pl.pw.edu.ee.figures;

public class knight extends figure {
    {
        this.value = 3;
    }

    @Override
    public void setAvailableMoves(figure[][] board, boolean[][] attackedByWhiteBoard,
            boolean[][] attackedByBlackBoard) {

        if (currentY + 1 < board.length && currentX + 2 < board[0].length) {
            if (board[currentY + 1][currentX + 2].exists == false) {
                availableMoves[currentY + 1][currentX + 2] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 1][currentX + 2] = true;
                } else {
                    attackedByBlackBoard[currentY + 1][currentX + 2] = true;
                }
            } else if (board[currentY + 1][currentX + 2].exists == true
                    && this.color != board[currentY + 1][currentX + 2].color) {
                availableStrikes[currentY + 1][currentX + 2] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 1][currentX + 2] = true;
                } else {
                    attackedByBlackBoard[currentY + 1][currentX + 2] = true;
                }
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 1][currentX + 2] = true;
                } else {
                    attackedByBlackBoard[currentY + 1][currentX + 2] = true;
                }
            }
        }
        if (currentY + 2 < board.length && currentX + 1 < board[0].length) {
            if (board[currentY + 2][currentX + 1].exists == false) {
                availableMoves[currentY + 2][currentX + 1] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 2][currentX + 1] = true;
                } else {
                    attackedByBlackBoard[currentY + 2][currentX + 1] = true;
                }
            } else if (board[currentY + 2][currentX + 1].exists == true
                    && this.color != board[currentY + 2][currentX + 1].color) {
                availableStrikes[currentY + 2][currentX + 1] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 2][currentX + 1] = true;
                } else {
                    attackedByBlackBoard[currentY + 2][currentX + 1] = true;
                }
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 2][currentX + 1] = true;
                } else {
                    attackedByBlackBoard[currentY + 2][currentX + 1] = true;
                }
            }
        }
        if (currentY + 1 < board.length && currentX - 2 >= 0) {
            if (board[currentY + 1][currentX - 2].exists == false) {
                availableMoves[currentY + 1][currentX - 2] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 1][currentX - 2] = true;
                } else {
                    attackedByBlackBoard[currentY + 1][currentX - 2] = true;
                }
            } else if (board[currentY + 1][currentX - 2].exists == true
                    && this.color != board[currentY + 1][currentX - 2].color) {
                availableStrikes[currentY + 1][currentX - 2] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 1][currentX - 2] = true;
                } else {
                    attackedByBlackBoard[currentY + 1][currentX - 2] = true;
                }
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 1][currentX - 2] = true;
                } else {
                    attackedByBlackBoard[currentY + 1][currentX - 2] = true;
                }
            }
        }
        if (currentY + 2 < board.length && currentX - 1 >= 0) {
            if (board[currentY + 2][currentX - 1].exists == false) {
                availableMoves[currentY + 2][currentX - 1] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 2][currentX - 1] = true;
                } else {
                    attackedByBlackBoard[currentY + 2][currentX - 1] = true;
                }
            } else if (board[currentY + 2][currentX - 1].exists == true
                    && this.color != board[currentY + 2][currentX - 1].color) {
                availableStrikes[currentY + 2][currentX - 1] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 2][currentX - 1] = true;
                } else {
                    attackedByBlackBoard[currentY + 2][currentX - 1] = true;
                }
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 2][currentX - 1] = true;
                } else {
                    attackedByBlackBoard[currentY + 2][currentX - 1] = true;
                }
            }
        }
        if (currentY - 1 >= 0 && currentX - 2 >= 0) {
            if (board[currentY - 1][currentX - 2].exists == false) {
                availableMoves[currentY - 1][currentX - 2] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 1][currentX - 2] = true;
                } else {
                    attackedByBlackBoard[currentY - 1][currentX - 2] = true;
                }
            } else if (board[currentY - 1][currentX - 2].exists == true
                    && this.color != board[currentY - 1][currentX - 2].color) {
                availableStrikes[currentY - 1][currentX - 2] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 1][currentX - 2] = true;
                } else {
                    attackedByBlackBoard[currentY - 1][currentX - 2] = true;
                }
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 1][currentX - 2] = true;
                } else {
                    attackedByBlackBoard[currentY - 1][currentX - 2] = true;
                }
            }
        }
        if (currentY - 2 >= 0 && currentX - 1 >= 0) {
            if (board[currentY - 2][currentX - 1].exists == false) {
                availableMoves[currentY - 2][currentX - 1] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 2][currentX - 1] = true;
                } else {
                    attackedByBlackBoard[currentY - 2][currentX - 1] = true;
                }
            } else if (board[currentY - 2][currentX - 1].exists == true
                    && this.color != board[currentY - 2][currentX - 1].color) {
                availableStrikes[currentY - 2][currentX - 1] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 2][currentX - 1] = true;
                } else {
                    attackedByBlackBoard[currentY - 2][currentX - 1] = true;
                }
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 2][currentX - 1] = true;
                } else {
                    attackedByBlackBoard[currentY - 2][currentX - 1] = true;
                }
            }
        }
        if (currentY - 1 >= 0 && currentX + 2 < board[0].length) {
            if (board[currentY - 1][currentX + 2].exists == false) {
                availableMoves[currentY - 1][currentX + 2] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 1][currentX + 2] = true;
                } else {
                    attackedByBlackBoard[currentY - 1][currentX + 2] = true;
                }
            } else if (board[currentY - 1][currentX + 2].exists == true
                    && this.color != board[currentY - 1][currentX + 2].color) {
                availableStrikes[currentY - 1][currentX + 2] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 1][currentX + 2] = true;
                } else {
                    attackedByBlackBoard[currentY - 1][currentX + 2] = true;
                }
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 1][currentX + 2] = true;
                } else {
                    attackedByBlackBoard[currentY - 1][currentX + 2] = true;
                }
            }
        }
        if (currentY - 2 >= 0 && currentX + 1 < board[0].length) {
            if (board[currentY - 2][currentX + 1].exists == false) {
                availableMoves[currentY - 2][currentX + 1] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 2][currentX + 1] = true;
                } else {
                    attackedByBlackBoard[currentY - 2][currentX + 1] = true;
                }
            } else if (board[currentY - 2][currentX + 1].exists == true
                    && this.color != board[currentY - 2][currentX + 1].color) {
                availableStrikes[currentY - 2][currentX + 1] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 2][currentX + 1] = true;
                } else {
                    attackedByBlackBoard[currentY - 2][currentX + 1] = true;
                }
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 2][currentX + 1] = true;
                } else {
                    attackedByBlackBoard[currentY - 2][currentX + 1] = true;
                }
            }
        }
    }
}