package pl.pw.edu.ee.figures;

public class King extends Figure {
    public King() {
        this.value = 0;
        this.type = "king";
    }

    public King(Figure king) {
        this.availableCastle = king.availableCastle;
        this.availableMoves = king.availableMoves;
        this.availableStrikes = king.availableStrikes;
        this.color = king.color;
        this.defaultSettings = king.defaultSettings;
        this.exists = king.exists;
        this.hasBeenMoved = king.hasBeenMoved;
        this.legalMovesStrikes = king.legalMovesStrikes;
        this.type = king.type;
        this.value = king.value;
    }

    @Override
    public void setAvailableMoves(Figure[][] board, boolean[][] attackedByWhiteBoard,
            boolean[][] attackedByBlackBoard, int currentX, int currentY) {

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
    }

    @Override
    public void setAvailableCastles(Figure[][] board, boolean[][] attackedByWhiteBoard,
            boolean[][] attackedByBlackBoard, int currentX, int currentY) {
        /* Castling only for 8x8 standard and 960 */
        int i = 1;
        if (this.color == true && board[currentX][currentY].hasBeenMoved == false) {
            i = 1;
            while (currentX + i < board.length) {
                if (board[currentX + i][currentY].value == 5 && board[currentX + i][currentY].hasBeenMoved == false) {
                    for (int j = currentX + i + 1; j <= 6; j++) {
                        if (attackedByBlackBoard[currentX + j][currentY] == true
                                || board[currentX + j][currentY].exists == true) {
                            break;
                        }
                    }
                    availableCastle[currentX + i][currentY] = true;
                    break;
                }
                if (board[currentX + i][currentY].exists == true
                        || attackedByBlackBoard[currentX + i][currentY] == true) {
                    break;
                }
                i++;
            }
            i = 1;
            while (currentX - i >= 0) {
                if (board[currentX - i][currentY].value == 5 && board[currentX - i][currentY].hasBeenMoved == false) {
                    for (int j = currentX - i - 1; j >= 2; j--) {
                        if (attackedByBlackBoard[currentX - j][currentY] == true
                                || board[currentX - j][currentY].exists == true) {
                            break;
                        }
                    }
                    availableCastle[currentX - i][currentY] = true;
                    break;
                }
                if (board[currentX - i][currentY].exists == true
                        || attackedByBlackBoard[currentX - i][currentY] == true) {
                    break;
                }
                i++;
            }
        }
        if (this.color == false && board[currentX][currentY].hasBeenMoved == false) {
            i = 1;
            while (currentX + i < board.length) {
                if (board[currentX + i][currentY].value == 5 && board[currentX + i][currentY].hasBeenMoved == false) {
                    for (int j = currentX + i + 1; j <= 6; j++) {
                        if (attackedByWhiteBoard[currentX + j][currentY] == true
                                || board[currentX + j][currentY].exists == true) {
                            break;
                        }
                    }
                    availableCastle[currentX + i][currentY] = true;
                    break;
                }
                if (board[currentX + i][currentY].exists == true
                        || attackedByWhiteBoard[currentX + i][currentY] == true) {
                    break;
                }
                i++;
            }
            i = 1;
            while (currentX - i >= 0) {
                if (board[currentX - i][currentY].value == 5 && board[currentX - i][currentY].hasBeenMoved == false) {
                    for (int j = currentX - i - 1; j >= 2; j--) {
                        if (attackedByWhiteBoard[currentX - j][currentY] == true
                                || board[currentX - j][currentY].exists == true) {
                            break;
                        }
                    }
                    availableCastle[currentX - i][currentY] = true;
                    break;
                }
                if (board[currentX - i][currentY].exists == true
                        || attackedByWhiteBoard[currentX - i][currentY] == true) {
                    break;
                }
                i++;
            }
        }
    }
}