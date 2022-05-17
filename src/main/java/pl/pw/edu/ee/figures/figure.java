package pl.pw.edu.ee.figures;

public abstract class figure {
    public boolean exists = false;
    public boolean color;
    public int value;
    public boolean[][] availableMoves;
    public boolean[][] availableStrikes;
    public boolean[][] availableCastle;
    public boolean hasBeenMoved = false;
    public int currentX;
    public int currentY;
    

    public void setAvailableMoves(figure[][] board, boolean[][] attackedByWhiteBoard, boolean[][] attackedByBlackBoard) {       // sets available moves and strikes
        ;
    }
}
