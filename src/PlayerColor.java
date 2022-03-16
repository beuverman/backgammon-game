public enum PlayerColor
{
    BLACK,
    WHITE;

    public boolean equals(PlayerColor color) {
        return (this == color || color == null);
    }

    public PlayerColor getOtherColor() {
        if (this == PlayerColor.WHITE)
            return PlayerColor.BLACK;
        return PlayerColor.WHITE;
    }
}
