package pl.pw.edu.ee.figures;

public class Bishop extends Figure {
    public Bishop() {
        this.value = 3;
        this.type = "bishop";
        this.typeShort = "B";
    }

    public Bishop(Figure bishop) {
        this.availableCastle = bishop.availableCastle;
        this.availableMoves = bishop.availableMoves;
        this.availableStrikes = bishop.availableStrikes;
        this.color = bishop.color;
        this.defaultSettings = bishop.defaultSettings;
        this.exists = bishop.exists;
        this.hasBeenMoved = bishop.hasBeenMoved;
        this.legalMovesStrikes = bishop.legalMovesStrikes;
        this.type = bishop.type;
        this.typeShort = bishop.typeShort;
        this.value = bishop.value;
    }

    @Override
    public void setAvailableMoves(Figure[][] board, boolean[][] attackedByWhiteBoard,
            boolean[][] attackedByBlackBoard, int currentX, int currentY) {

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
