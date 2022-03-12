import java.awt.Graphics2D;
import java.awt.Graphics;

public class Bar extends BarGraphics
{
    private int whiteCount;
    private int blackCount;
    
    public void setBlackCount(final int blackCount) {
        this.blackCount = blackCount;
    }
    
    public void setWhiteCount(final int whiteCount) {
        this.whiteCount = whiteCount;
    }
    
    public Bar(final Board gui) {
        super(gui);
        this.setBlackCount(0);
        this.setWhiteCount(0);
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
