public interface Position {
    void addPiece(PlayerColor color);
    void removePiece(PlayerColor color);
    PlayerColor getPieceColor();
    int getPointNumber();
    Board getBoard();
    void addHighlight(java.awt.Color color);
    void removeHighlight();
}
