package pl.pw.edu.ee.figures;

public class Rook extends Figure {
    public Rook(){
        this.value = 5;
        this.type = "rook";
    }

    public Rook(Figure rook) {
        this.availableCastle = rook.availableCastle;
        this.availableMoves = rook.availableMoves;
        this.availableStrikes = rook.availableStrikes;
        this.color = rook.color;
        this.defaultSettings = rook.defaultSettings;
        this.exists = rook.exists;
        this.hasBeenMoved = rook.hasBeenMoved;
        this.legalMovesStrikes = rook.legalMovesStrikes;
        this.type = rook.type;
        this.value = rook.value;
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
    }
}
