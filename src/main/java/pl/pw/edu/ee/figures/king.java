package pl.pw.edu.ee.figures;

public class king extends figure {
    {
        this.value = 0;
    }

    @Override
    public void setAvailableMoves(figure[][] board, boolean[][] attackedByWhiteBoard,
            boolean[][] attackedByBlackBoard) {

        if (currentY + 1 < board.length) {
            if (board[currentY + 1][currentX].exists == false) {
                availableMoves[currentY + 1][currentX] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 1][currentX] = true;
                } else {
                    attackedByBlackBoard[currentY + 1][currentX] = true;
                }
            } else if (board[currentY + 1][currentX].exists == true
                    && this.color != board[currentY + 1][currentX].color) {
                availableStrikes[currentY + 1][currentX] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 1][currentX] = true;
                } else {
                    attackedByBlackBoard[currentY + 1][currentX] = true;
                }
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 1][currentX] = true;
                } else {
                    attackedByBlackBoard[currentY + 1][currentX] = true;
                }
            }
        }
        if (currentY + 1 < board.length && currentX + 1 < board[0].length) {
            if (board[currentY + 1][currentX + 1].exists == false) {
                availableMoves[currentY + 1][currentX + 1] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 1][currentX + 1] = true;
                } else {
                    attackedByBlackBoard[currentY + 1][currentX + 1] = true;
                }
            } else if (board[currentY + 1][currentX + 1].exists == true
                    && this.color != board[currentY + 1][currentX + 1].color) {
                availableStrikes[currentY + 1][currentX + 1] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 1][currentX + 1] = true;
                } else {
                    attackedByBlackBoard[currentY + 1][currentX + 1] = true;
                }
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 1][currentX + 1] = true;
                } else {
                    attackedByBlackBoard[currentY + 1][currentX + 1] = true;
                }
            }
        }
        if (currentY - 1 >= 0) {
            if (board[currentY - 1][currentX].exists == false) {
                availableMoves[currentY - 1][currentX] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 1][currentX] = true;
                } else {
                    attackedByBlackBoard[currentY - 1][currentX] = true;
                }
            } else if (board[currentY - 1][currentX].exists == true
                    && this.color != board[currentY - 1][currentX].color) {
                availableStrikes[currentY - 1][currentX] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 1][currentX] = true;
                } else {
                    attackedByBlackBoard[currentY - 1][currentX] = true;
                }
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 1][currentX] = true;
                } else {
                    attackedByBlackBoard[currentY - 1][currentX] = true;
                }
            }
        }
        if (currentY - 1 >= 0 && currentX - 1 >= 0) {
            if (board[currentY - 1][currentX - 1].exists == false) {
                availableMoves[currentY - 1][currentX - 1] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 1][currentX - 1] = true;
                } else {
                    attackedByBlackBoard[currentY - 1][currentX - 1] = true;
                }
            } else if (board[currentY - 1][currentX - 1].exists == true
                    && this.color != board[currentY - 1][currentX - 1].color) {
                availableStrikes[currentY - 1][currentX - 1] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 1][currentX - 1] = true;
                } else {
                    attackedByBlackBoard[currentY - 1][currentX - 1] = true;
                }
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 1][currentX - 1] = true;
                } else {
                    attackedByBlackBoard[currentY - 1][currentX - 1] = true;
                }
            }
        }
        if (currentX + 1 < board[0].length) {
            if (board[currentY][currentX + 1].exists == false) {
                availableMoves[currentY][currentX + 1] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY][currentX + 1] = true;
                } else {
                    attackedByBlackBoard[currentY][currentX + 1] = true;
                }
            } else if (board[currentY][currentX + 1].exists == true
                    && this.color != board[currentY][currentX + 1].color) {
                availableStrikes[currentY][currentX + 1] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY][currentX + 1] = true;
                } else {
                    attackedByBlackBoard[currentY][currentX + 1] = true;
                }
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY][currentX + 1] = true;
                } else {
                    attackedByBlackBoard[currentY][currentX + 1] = true;
                }
            }
        }
        if (currentY - 1 >= 0 && currentX + 1 < board[0].length) {
            if (board[currentY - 1][currentX + 1].exists == false) {
                availableMoves[currentY - 1][currentX + 1] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 1][currentX + 1] = true;
                } else {
                    attackedByBlackBoard[currentY - 1][currentX + 1] = true;
                }
            } else if (board[currentY - 1][currentX + 1].exists == true
                    && this.color != board[currentY - 1][currentX + 1].color) {
                availableStrikes[currentY - 1][currentX + 1] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 1][currentX + 1] = true;
                } else {
                    attackedByBlackBoard[currentY - 1][currentX + 1] = true;
                }
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY - 1][currentX + 1] = true;
                } else {
                    attackedByBlackBoard[currentY - 1][currentX + 1] = true;
                }
            }
        }
        if (currentX - 1 >= 0) {
            if (board[currentY][currentX - 1].exists == false) {
                availableMoves[currentY][currentX - 1] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY][currentX - 1] = true;
                } else {
                    attackedByBlackBoard[currentY][currentX - 1] = true;
                }
            } else if (board[currentY][currentX - 1].exists == true
                    && this.color != board[currentY][currentX - 1].color) {
                availableStrikes[currentY][currentX - 1] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY][currentX - 1] = true;
                } else {
                    attackedByBlackBoard[currentY][currentX - 1] = true;
                }
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY][currentX - 1] = true;
                } else {
                    attackedByBlackBoard[currentY][currentX - 1] = true;
                }
            }
        }
        if (currentY + 1 < board.length && currentX - 1 >= 0) {
            if (board[currentY + 1][currentX - 1].exists == false) {
                availableMoves[currentY + 1][currentX - 1] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 1][currentX - 1] = true;
                } else {
                    attackedByBlackBoard[currentY + 1][currentX - 1] = true;
                }
            } else if (board[currentY + 1][currentX - 1].exists == true
                    && this.color != board[currentY + 1][currentX - 1].color) {
                availableStrikes[currentY + 1][currentX - 1] = true;
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 1][currentX - 1] = true;
                } else {
                    attackedByBlackBoard[currentY + 1][currentX - 1] = true;
                }
            } else {
                if (this.color == true) {
                    attackedByWhiteBoard[currentY + 1][currentX - 1] = true;
                } else {
                    attackedByBlackBoard[currentY + 1][currentX - 1] = true;
                }
            }
        }

        int i = 0;
        if (this.color == true) {
            i = 0;
            while (currentX + i < board[0].length) {
                if (board[currentY][currentX + i].value == 5 && board[currentY][currentX + i].hasBeenMoved == false) {
                    for (int j = currentX + i + 1; j <= 6; j++) {
                        if (attackedByBlackBoard[currentY][currentX + j] == true
                                || board[currentY][currentX + j].exists == true) {
                            break;
                        }
                    }
                    availableCastle[7][6] = true;
                }
                if ((board[currentY][currentX + i].value != 5 && board[currentY][currentX + i].value != 0)
                        || attackedByBlackBoard[currentY][currentX + i] == true) {
                    break;
                }
                i++;
            }
            i = 0;
            while (currentX - i >= 0) {
                if (board[currentY][currentX - i].value == 5 && board[currentY][currentX - i].hasBeenMoved == false) {
                    for (int j = currentX - i - 1; j >= 1; j--) {
                        if (attackedByBlackBoard[currentY][currentX - j] == true
                                || board[currentY][currentX - j].exists == true) {
                            break;
                        }
                    }
                    availableCastle[7][2] = true;
                }
                if ((board[currentY][currentX - i].value != 5 && board[currentY][currentX - i].value != 0)
                        || attackedByBlackBoard[currentY][currentX - i] == true) {
                    break;
                }
                i++;
            }
        }
        if (this.color == false) {
            i = 0;
            while (currentX + i < board[0].length) {
                if (board[currentY][currentX + i].value == 5 && board[currentY][currentX + i].hasBeenMoved == false) {
                    for (int j = currentX + i + 1; j <= 6; j++) {
                        if (attackedByWhiteBoard[currentY][currentX + j] == true
                                || board[currentY][currentX + j].exists == true) {
                            break;
                        }
                    }
                    availableCastle[0][6] = true;
                }
                if ((board[currentY][currentX + i].value != 5 && board[currentY][currentX + i].value != 0)
                        || attackedByWhiteBoard[currentY][currentX + i] == true) {
                    break;
                }
                i++;
            }
            i = 0;
            while (currentX - i >= 0) {
                if (board[currentY][currentX - i].value == 5 && board[currentY][currentX - i].hasBeenMoved == false) {
                    for (int j = currentX - i - 1; j >= 1; j--) {
                        if (attackedByWhiteBoard[currentY][currentX - j] == true
                                || board[currentY][currentX - j].exists == true) {
                            break;
                        }
                    }
                    availableCastle[0][2] = true;
                }
                if ((board[currentY][currentX - i].value != 5 && board[currentY][currentX - i].value != 0)
                        || attackedByWhiteBoard[currentY][currentX - i] == true) {
                    break;
                }
                i++;
            }
        }
    }
}