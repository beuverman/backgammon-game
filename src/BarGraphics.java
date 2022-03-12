import java.awt.geom.Ellipse2D;
import java.awt.Shape;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JPanel;

class BarGraphics extends JPanel
{
    private Board board;
    
    public BarGraphics(final Board gui) {
        super(null, true);
        board = gui;
        board.add(this);

        setBounds(Board.getGeometry().getBarBounds());
        setBackground(Palette.getBarColour());
    }
    
    @Override
    protected void paintComponent(final Graphics g) {
        final Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g2);
    }
    
    protected void paintDisk(final Graphics2D g2, final PlayerColor c, final int number, final int totalNumber) {
        final Color diskColour = Palette.getPieceColor(c);
        final Shape diskShape = this.diskEllipse(c, number, totalNumber);
        g2.setColor(Color.BLUE);
        g2.draw(diskShape);
        g2.setColor(diskColour);
        g2.fill(diskShape);
    }
    
    private Shape diskEllipse(final PlayerColor c, final int k, final int n) {
        final BoardGeometry geo = Board.getGeometry();
        final double diskWidth = geo.getDiskWidth();
        final double regionHeight = 0.45 * geo.getBoardHeight();
        final double overlap = Math.max(-2.0, diskWidth - regionHeight / n);
        double yLoc = (k - 1) * (diskWidth - overlap);
        if (c == PlayerColor.WHITE) {
            yLoc = geo.getBarHeight() - yLoc - diskWidth;
        }
        return new Ellipse2D.Double(0.0, yLoc, diskWidth, diskWidth);
    }
}
