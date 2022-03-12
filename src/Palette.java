import java.awt.Color;

public class Palette
{
    private static final Color DEFAULT_BLACK_PIECE_COLOUR = new Color(0, 0, 0);
    private static final Color DEFAULT_WHITE_PIECE_COLOUR = new Color(255, 255, 255);
    private static final Color DEFAULT_PIECE_OUTLINE = new Color(0, 0, 0);
    private static final Color DEFAULT_DARK_TRIANGLE_COLOUR = new Color(128, 115, 102);
    private static final Color DEFAULT_LIGHT_TRIANGLE_COLOUR = new Color(204, 184, 163);
    private static final Color DEFAULT_BAR_COLOUR = new Color(230, 218, 207);
    private static final Color DEFAULT_BOARD_BACKGROUND_COLOUR = new Color(230, 230, 242);

    public static Color getPieceColor(final PlayerColor c) {
        if (c == PlayerColor.BLACK)
            return DEFAULT_BLACK_PIECE_COLOUR;
        else
            return DEFAULT_WHITE_PIECE_COLOUR;
    }
    
    public static Color getPieceTextColor(final PlayerColor c) {
        if (c == PlayerColor.BLACK)
            return Color.WHITE;
        else
            return Color.BLACK;
    }

    public static Color getPieceOutlineColor() {
        return DEFAULT_PIECE_OUTLINE;
    }
    
    public static Color getDarkTriangleColour() {
        return DEFAULT_DARK_TRIANGLE_COLOUR;
    }
    
    public static Color getLightTriangleColour() {
        return DEFAULT_LIGHT_TRIANGLE_COLOUR;
    }
    
    public static Color getBoardBackgroundColour() {
        return DEFAULT_BOARD_BACKGROUND_COLOUR;
    }

    public static Color getBarColour() {
        return DEFAULT_BAR_COLOUR;
    }
}
