import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Polygon;
import javax.swing.JComponent;

public class Triangle extends JComponent
{
    private final Board myParent;
    private final int myPointNumber;
    private final Polygon myTriangle;
    private PlayerColor pieceColour;
    private int pieceCount;
    
    public Triangle(int pieceCount, PlayerColor pieceColour, int number, final Board board) {
        myParent = board;
        myPointNumber = number;
        this.pieceColour = pieceColour;
        this.pieceCount = pieceCount;

        myParent.add(this);
        setBounds(Board.getGeometry().getPointRectangle(myPointNumber));
        myTriangle = getTriangle();
    }

    public int getCount(){
        return pieceCount;
    }

    public PlayerColor getColor(){
        return pieceColour;
    }

    public void addPiece(){
        pieceCount = pieceCount + 1;
    }

    public void removePiece(){
        pieceCount = pieceCount - 1;
    }

    public boolean isBlot(){
        return pieceCount == 1;
    }
    
    @Override
    protected void paintComponent(final Graphics g) {
        final Color fg = this.isDarkColoured() ? Palette.getDarkTriangleColour() : Palette.getLightTriangleColour();
        final Graphics2D g2 = (Graphics2D)g;
        g2.setColor(fg);
        g2.fillPolygon(this.myTriangle);
        this.paintPieces(g2);
    }
    
    protected void paintPieces(final Graphics2D g2) {
        if (this.pieceCount == 0) return;

        final Color diskColour = Palette.getPieceColor(pieceColour);
        final Color textColour = Palette.getPieceTextColor(pieceColour);
        final int diskCount = Math.min(5, pieceCount);
        final int multiplier = 1 + pieceCount - diskCount;
        for (int i = 1; i <= diskCount; i++) {
            this.paintPiece(g2, diskColour, textColour, i, (i > 1) ? 1 : multiplier);
        }
    }
    
    protected void paintPiece(final Graphics2D g2, final Color diskColour, final Color textColour, final int location, final int count) {
        final BoardGeometry geometry = Board.getGeometry();
        g2.setColor(Palette.getPieceOutlineColor());
        final Ellipse2D disk = geometry.diskAt(location, this.pointsDown());
        g2.draw(disk);
        g2.setColor(diskColour);
        g2.fill(disk);
        if (count > 1) {
            g2.setColor(textColour);
            g2.drawString(String.format("\u00d7%d", count), (int)(disk.getX() + 3.0), (int)(disk.getY() + 3.0 + disk.getHeight() / 2.0));
        }
    }
    
    private Polygon getTriangle() {
        final int x1 = 0;
        final int x2 = x1 + this.getWidth();
        final int xm = (x1 + x2) / 2;
        final int y1 = 0;
        final int y2 = y1 + this.getHeight();
        final Polygon answer = new Polygon();

        if (this.pointsDown()) {
            answer.addPoint(x1, y1);
            answer.addPoint(x2, y1);
            answer.addPoint(xm, y2);
        }
        else {
            answer.addPoint(x1, y2);
            answer.addPoint(x2, y2);
            answer.addPoint(xm, y1);
        }

        return answer;
    }
    
    public boolean pointsUp() {
        return this.myPointNumber <= 12;
    }
    
    public boolean pointsDown() {
        return !this.pointsUp();
    }
    
    public boolean isDarkColoured() {
        return this.myPointNumber % 2 == 1;
    }
}
