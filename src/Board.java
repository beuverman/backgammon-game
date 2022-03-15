import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class Board extends JPanel
{
    private final Game game;
    private final Triangle[] points;
    private final Bar bar;
    private Triangle selectedTriangle;
    
    public static BoardGeometry getGeometry() {
        return BoardGeometry.getBoardGeometry();
    }

    public Game getGame() {
        return game;
    }
    
    public Board(Game game) {
        super(null, true);
        this.game = game;
        points = new Triangle[24];
        bar = new Bar(this);
        add(bar);

        setSize(getGeometry().getBoardWidth(), getGeometry().getBoardHeight());
        setMaximumSize(new Dimension(getGeometry().getBoardWidth(), getGeometry().getBoardHeight()));
        setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE));
        setBackground(Palette.getBoardBackgroundColour());

        this.setInitialBoard();
    }
    
    public Triangle getPoint(int num) {
        return points[num - 1];
    }

    public void setSelectedTriangle(Triangle t) {
        selectedTriangle = t;
    }

    public Triangle getSelectedTriangle() {
        return selectedTriangle;
    }

    private void setInitialBoard() {
        for (int i = 0; i < points.length; i++) {
            switch (i) {
                case 0:
                    points[i] = new Triangle(2, PlayerColor.BLACK, i+1, this);
                    break;
                case 5:
                case 12:
                    points[i] = new Triangle(5, PlayerColor.WHITE, i+1, this);
                    break;
                case 7:
                    points[i] = new Triangle(3, PlayerColor.WHITE, i+1, this);
                    break;
                case 11:
                case 18:
                    points[i] = new Triangle(5, PlayerColor.BLACK, i+1, this);
                    break;
                case 16:
                    points[i] = new Triangle(3, PlayerColor.BLACK, i+1, this);
                    break;
                case 23:
                    points[i] = new Triangle(2, PlayerColor.WHITE, i+1, this);
                    break;
                default:
                    points[i] = new Triangle(0, null, i+1, this);
            }
        }
    }
    
    public boolean checkHome(PlayerColor c){ //from liams score increment code
        boolean check = true;
        if(c == PlayerColor.BLACK){
            for(int x = 0; x < 5; x++){
                if(points[x].getCount() > 0){
                    check = false;
                }
            }

        }

        if(c == PlayerColor.WHITE){
            for(int x = 23; x > 17; x--){
                if(points[x].getCount() > 0){
                    check = false;
                }
            }
        }
        return check;
    }

    //empty board

    public int countPieces(PlayerColor c) {
        int count = bar.getCount(c);

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
