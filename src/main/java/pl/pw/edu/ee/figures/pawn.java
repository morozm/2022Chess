package pl.pw.edu.ee.figures;

public class Pawn extends Figure {
    public Pawn() {
        this.value = 1;
        this.type = "pawn";
    }

    public Pawn(Figure pawn) {
        this.availableCastle = pawn.availableCastle;
        this.availableMoves = pawn.availableMoves;
        this.availableStrikes = pawn.availableStrikes;
        this.color = pawn.color;
        this.defaultSettings = pawn.defaultSettings;
        this.exists = pawn.exists;
        this.hasBeenMoved = pawn.hasBeenMoved;
        this.legalMovesStrikes = pawn.legalMovesStrikes;
        this.type = pawn.type;
        this.value = pawn.value;
    }

    @Override
    public void setAvailableMoves(Figure[][] board, boolean[][] attackedByWhiteBoard,
            boolean[][] attackedByBlackBoard, int currentX, int currentY) {

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
                attackedByWhiteBoard[currentX + 1][currentY - 1] = true;
                if (board[currentX + 1][currentY - 1].exists == true
                        && board[currentX + 1][currentY - 1].color == false) {
                    availableStrikes[currentX + 1][currentY - 1] = true;
                }
            }
            if (currentY - 1 >= 0 && currentX - 1 >= 0) {
                attackedByWhiteBoard[currentX - 1][currentY - 1] = true;
                if (board[currentX - 1][currentY - 1].exists == true
                        && board[currentX - 1][currentY - 1].color == false) {
                    availableStrikes[currentX - 1][currentY - 1] = true;
                }
            }
        }
    }
}
