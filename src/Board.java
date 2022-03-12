import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class Board extends JPanel
{
    private final Triangle[] points;
    private final Bar myBar;
    
    public static BoardGeometry getGeometry() {
        return BoardGeometry.getBoardGeometry();
    }
    
    public Board() {
        super(null, true);
        points = new Triangle[24];
        myBar = new Bar(this);
        add(myBar);

        setSize(getGeometry().getBoardWidth(), getGeometry().getBoardHeight());
        setMaximumSize(new Dimension(getGeometry().getBoardWidth(), getGeometry().getBoardHeight()));
        setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE));
        setBackground(Palette.getBoardBackgroundColour());

        this.setInitialBoard();
    }
    
    public Triangle getPoint(int num) {
        return points[num - 1];
    }

    private void setInitialBoard() {
        for (int i = 0; i < points.length; i++) {
            points[i] = switch (i) {
                case 0 -> new Triangle(2, PlayerColor.BLACK, i+1, this);
                case 5, 12 -> new Triangle(5, PlayerColor.WHITE, i+1, this);
                case 7 -> new Triangle(3, PlayerColor.WHITE, i+1, this);
                case 11, 18 -> new Triangle(5, PlayerColor.BLACK, i+1, this);
                case 16 -> new Triangle(3, PlayerColor.BLACK, i+1, this);
                case 23 -> new Triangle(2, PlayerColor.WHITE, i+1, this);
                default -> new Triangle(0, null, i+1, this);
            };
        }
    }

    //empty board

    public int countPieces(PlayerColor c) {
        int count = myBar.getCount(c);

        for (Triangle point : points) {
            if (point.getColor() == c)
                count += point.getCount();
        }

        return count;
    }
    @Override
    protected void paintComponent(final Graphics g) {
        final Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g2);
    }
}
