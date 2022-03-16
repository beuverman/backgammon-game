public interface Position {
    void addPiece(PlayerColor color);
    void removePiece(PlayerColor color);
    int getPointNumber();
    void addHighlight();
    void removeHighlight();
}
