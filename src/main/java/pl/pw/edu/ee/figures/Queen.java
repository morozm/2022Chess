package pl.pw.edu.ee.figures;

public class Queen extends Figure {
    public Queen(){
        this.value = 9;
        this.type = "queen";
        this.typeShort = "Q";
    }

    public Queen(Figure queen) {
        this.availableCastle = queen.availableCastle;
        this.availableMoves = queen.availableMoves;
        this.availableStrikes = queen.availableStrikes;
        this.color = queen.color;
        this.defaultSettings = queen.defaultSettings;
        this.exists = queen.exists;
        this.hasBeenMoved = queen.hasBeenMoved;
        this.legalMovesStrikes = queen.legalMovesStrikes;
        this.type = queen.type;
        this.typeShort = queen.typeShort;
        this.value = queen.value;
    }

    @Override
    public void setAvailableMoves(Figure[][] board, boolean[][] attackedByWhiteBoard,
            boolean[][] attackedByBlackBoard, int currentX, int currentY) {

        int i;
        i = 1;
        while (currentX + i < board.length) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX + i][currentY] = true;
            } else {
                attackedByBlackBoard[currentX + i][currentY] = true;
            }
            if (board[currentX + i][currentY].exists == false) {
                availableMoves[currentX + i][currentY] = true;
                i++;
            } else if (this.color != board[currentX + i][currentY].color) {
                availableStrikes[currentX + i][currentY] = true;
                break;
            } else {
                break;
            }
        }
        i = 1;
        while (currentX - i >= 0) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX - i][currentY] = true;
            } else {
                attackedByBlackBoard[currentX - i][currentY] = true;
            }
            if (board[currentX - i][currentY].exists == false) {
                availableMoves[currentX - i][currentY] = true;
                i++;
            } else if (this.color != board[currentX - i][currentY].color) {
                availableStrikes[currentX - i][currentY] = true;
                break;
            } else {
                break;
            }
        }
        i = 1;
        while (currentY + i < board[0].length) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX][currentY + i] = true;
            } else {
                attackedByBlackBoard[currentX][currentY + i] = true;
            }
            if (board[currentX][currentY + i].exists == false) {
                availableMoves[currentX][currentY + i] = true;
                i++;
            } else if (this.color != board[currentX][currentY + i].color) {
                availableStrikes[currentX][currentY + i] = true;
                break;
            } else {
                break;
            }
        }
        i = 1;
        while (currentY - i >= 0) {
            if (this.color == true) {
                attackedByWhiteBoard[currentX][currentY - i] = true;
            } else {
                attackedByBlackBoard[currentX][currentY - i] = true;
            }
            if (board[currentX][currentY - i].exists == false) {
                availableMoves[currentX][currentY - i] = true;
                i++;
            } else if (this.color != board[currentX][currentY - i].color) {
                availableStrikes[currentX][currentY - i] = true;
                break;
            } else {
                break;
            }
        }
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
