import javax.swing.*;
import java.awt.*;

public class Bar extends BarGraphics implements Position
{
    private int whiteCount;
    private int blackCount;
    private int type;
    private Board board;

    public Bar(final Board gui, int num) {
        super(gui, num);
        this.setBlackCount(0);
        this.setWhiteCount(0);
        type = num;
        board = gui;

        addMouseListener(new TriangleListener());
    }

    public void setBlackCount(final int blackCount) {
        this.blackCount = blackCount;
    }
    
    public void setWhiteCount(final int whiteCount) {
        this.whiteCount = whiteCount;
    }

    public int getCount(PlayerColor playerColor){
        if(playerColor == PlayerColor.BLACK){
            return blackCount;
        }else if (playerColor == PlayerColor.WHITE){
            return whiteCount;
        }
        return 0;
    }

    public void setCount(PlayerColor color, int count) {
        if (color == PlayerColor.WHITE)
            whiteCount = count;
        else if (color == PlayerColor.BLACK)
            blackCount = count;
    }

    @Override
    public void addPiece(PlayerColor color) {
        setCount(color, getCount(color) + 1);
    }

    @Override
    public void removePiece(PlayerColor color) {
        setCount(color, getCount(color) - 1);
    }

    @Override
    public int getPointNumber() {
        PlayerColor color = board.getGame().getActivePlayer().getColor();

        if (color == PlayerColor.WHITE)
            return 25 * type;
        else return 25 * Math.abs(type - 1);
    }

    @Override
    public PlayerColor getPieceColor() {
        return board.getGame().getActivePlayer().getColor();
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void addHighlight(Color color) {
        setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, color));
        repaint();
    }

    @Override
    public void removeHighlight() {
        setBorder(null);
        repaint();
    }

    @Override
    protected void paintComponent(final Graphics g) {
        final Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g2);
        this.paintDisks(g2, PlayerColor.BLACK, this.blackCount);
        this.paintDisks(g2, PlayerColor.WHITE, this.whiteCount);
    }
    
    private void paintDisks(final Graphics2D g2, final PlayerColor player, final int totalCount) {
        for (int k = 1; k <= totalCount; ++k) {
            this.paintDisk(g2, player, k, totalCount);
        }
    }
}
