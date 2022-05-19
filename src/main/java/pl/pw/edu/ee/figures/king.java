package pl.pw.edu.ee.figures;

public class king extends figure {
    {
        this.value = 0;
    }

    @Override
    public void setAvailableMoves(figure[][] board, boolean[][] attackedByWhiteBoard,
            boolean[][] attackedByBlackBoard) {

        if (currentX + 1 < board.length) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX + 1][currentY] = true;
            } else {
                attackedByBlackBoard[currentX + 1][currentY] = true;
            }
            if (board[currentX + 1][currentY].exists == false) {
                availableMoves[currentX + 1][currentY] = true;
            } else if (board[currentX + 1][currentY].exists == true
                    && this.color != board[currentX + 1][currentY].color) {
                availableStrikes[currentX + 1][currentY] = true;
            }
        }
        if (currentX + 1 < board.length && currentY + 1 < board[0].length) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX + 1][currentY + 1] = true;
            } else {
                attackedByBlackBoard[currentX + 1][currentY + 1] = true;
            }
            if (board[currentX + 1][currentY + 1].exists == false) {
                availableMoves[currentX + 1][currentY + 1] = true;
            } else if (board[currentX + 1][currentY + 1].exists == true
                    && this.color != board[currentX + 1][currentY + 1].color) {
                availableStrikes[currentX + 1][currentY + 1] = true;
            }
        }
        if (currentY + 1 < board[0].length) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX][currentY + 1] = true;
            } else {
                attackedByBlackBoard[currentX][currentY + 1] = true;
            }
            if (board[currentX][currentY + 1].exists == false) {
                availableMoves[currentX][currentY + 1] = true;
            } else if (board[currentX][currentY + 1].exists == true
                    && this.color != board[currentX][currentY + 1].color) {
                availableStrikes[currentX][currentY + 1] = true;
            }
        }
        if (currentX - 1 >= 0 && currentY + 1 < board[0].length) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX - 1][currentY + 1] = true;
            } else {
                attackedByBlackBoard[currentX - 1][currentY + 1] = true;
            }
            if (board[currentX - 1][currentY + 1].exists == false) {
                availableMoves[currentX - 1][currentY + 1] = true;
            } else if (board[currentX - 1][currentY + 1].exists == true
                    && this.color != board[currentX - 1][currentY + 1].color) {
                availableStrikes[currentX - 1][currentY + 1] = true;
            }
        }
        if (currentX - 1 >= 0) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX - 1][currentY] = true;
            } else {
                attackedByBlackBoard[currentX - 1][currentY] = true;
            }
            if (board[currentX - 1][currentY].exists == false) {
                availableMoves[currentX - 1][currentY] = true;
            } else if (board[currentX - 1][currentY].exists == true
                    && this.color != board[currentX - 1][currentY].color) {
                availableStrikes[currentX - 1][currentY] = true;
            }
        }
        if (currentX - 1 >= 0 && currentY - 1 >= 0) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX - 1][currentY - 1] = true;
            } else {
                attackedByBlackBoard[currentX - 1][currentY - 1] = true;
            }
            if (board[currentX - 1][currentY - 1].exists == false) {
                availableMoves[currentX - 1][currentY - 1] = true;
            } else if (board[currentX - 1][currentY - 1].exists == true
                    && this.color != board[currentX - 1][currentY - 1].color) {
                availableStrikes[currentX - 1][currentY - 1] = true;
            }
        }
        if (currentY - 1 >= 0) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX][currentY - 1] = true;
            } else {
                attackedByBlackBoard[currentX][currentY - 1] = true;
            }
            if (board[currentX][currentY - 1].exists == false) {
                availableMoves[currentX][currentY - 1] = true;
            } else if (board[currentX][currentY - 1].exists == true
                    && this.color != board[currentX][currentY - 1].color) {
                availableStrikes[currentX][currentY - 1] = true;
            }
        }
        if (currentX + 1 < board.length && currentY - 1 >= 0) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX + 1][currentY - 1] = true;
            } else {
                attackedByBlackBoard[currentX + 1][currentY - 1] = true;
            }
            if (board[currentX + 1][currentY - 1].exists == false) {
                availableMoves[currentX + 1][currentY - 1] = true;
            } else if (board[currentX + 1][currentY - 1].exists == true
                    && this.color != board[currentX + 1][currentY - 1].color) {
                availableStrikes[currentX + 1][currentY - 1] = true;
            }
        }

        /* Castling only for 8x8 standard and */
        int i = 0;
        if (this.color == true) {
            i = 0;
            while (currentX + i < board.length) {
                if (board[currentX + i][currentY].value == 5 && board[currentX + i][currentY].hasBeenMoved == false) {
                    for (int j = currentX + i + 1; j <= 6; j++) {
                        if (attackedByBlackBoard[currentX + j][currentY] == true
                                || board[currentX + j][currentY].exists == true) {
                            break;
                        }
                    }
                    availableCastle[7][7] = true;
                }
                if ((board[currentX + i][currentY].value != 5 && board[currentX + i][currentY].value != 0)
                        || attackedByBlackBoard[currentX + i][currentY] == true) {
                    break;
                }
                i++;
            }
            i = 0;
            while (currentX - i >= 0) {
                if (board[currentX - i][currentY].value == 5 && board[currentX - i][currentY].hasBeenMoved == false) {
                    for (int j = currentX - i - 1; j >= 2; j--) {
                        if (attackedByBlackBoard[currentX - j][currentY] == true
                                || board[currentX - j][currentY].exists == true) {
                            break;
                        }
                    }
                    availableCastle[0][7] = true;
                }
                if ((board[currentX - i][currentY].value != 5 && board[currentX - i][currentY].value != 0)
                        || attackedByBlackBoard[currentX - i][currentY] == true) {
                    break;
                }
                i++;
            }
        }
        if (this.color == false) {
            i = 0;
            while (currentX + i < board.length) {
                if (board[currentX + i][currentY].value == 5 && board[currentX + i][currentY].hasBeenMoved == false) {
                    for (int j = currentX + i + 1; j <= 6; j++) {
                        if (attackedByWhiteBoard[currentX + j][currentY] == true
                                || board[currentX + j][currentY].exists == true) {
                            break;
                        }
                    }
                    availableCastle[7][0] = true;
                }
                if ((board[currentX + i][currentY].value != 5 && board[currentX + i][currentY].value != 0)
                        || attackedByWhiteBoard[currentX + i][currentY] == true) {
                    break;
                }
                i++;
            }
            i = 0;
            while (currentX - i >= 0) {
                if (board[currentX - i][currentY].value == 5 && board[currentX - i][currentY].hasBeenMoved == false) {
                    for (int j = currentX - i - 1; j >= 2; j--) {
                        if (attackedByWhiteBoard[currentX - j][currentY] == true
                                || board[currentX - j][currentY].exists == true) {
                            break;
                        }
                    }
                    availableCastle[0][0] = true;
                }
                if ((board[currentX - i][currentY].value != 5 && board[currentX - i][currentY].value != 0)
                        || attackedByWhiteBoard[currentX - i][currentY] == true) {
                    break;
                }
                i++;
            }
        }
    }
}