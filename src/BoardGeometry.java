import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BoardGeometry
{
    private static final int triangleWidth = 60;
    private static final double diskWidth = 57;
    private static final int barWidth = 60;
    private static final int triangleHeight = 300;
    private static BoardGeometry theGeometry;
    private int myTriangleWidth;
    private int myTriangleHeight;
    private final int myBarWidth;
    private final double myDiskWidth;
    
    public double getDiskWidth() {
        return this.myDiskWidth;
    }
    
    public int getTriangleWidth() {
        return this.myTriangleWidth;
    }
    
    public int getTriangleHeight() {
        return this.myTriangleHeight;
    }
    
    public int getBarHeight() {
        return 2 * this.getTriangleHeight() + 20;
    }
    
    public int getBarWidth() {
        return this.myBarWidth;
    }
    
    public int getBoardWidth() {
        int answer = 2 * this.getBarWidth();
        answer += 12 * this.getTriangleWidth();
        return answer;
    }
    
    public int getBoardHeight() {
        return this.getBarHeight();
    }
    
    public Rectangle getBoardRectangle() {
        return new Rectangle(this.getBoardWidth(), this.getBoardHeight());
    }
    
    private BoardGeometry() {
        this.myTriangleWidth = triangleWidth;
        this.myTriangleHeight = triangleHeight;
        this.myBarWidth = barWidth;
        this.myDiskWidth = diskWidth;
    }
    
    public static BoardGeometry getBoardGeometry() {
        if (BoardGeometry.theGeometry == null) {
            BoardGeometry.theGeometry = new BoardGeometry();
        }
        return BoardGeometry.theGeometry;
    }
    
    public Rectangle getBarBounds(int num) {
        return new Rectangle(num * (6 * this.getTriangleWidth() + getBarWidth()), 0, this.getBarWidth(), this.getBarHeight());
    }
    
    private Point getPointPoint(final int pointNumber) {
        final boolean isUpper = pointNumber > 12;
        final int ltrIndex = isUpper ? (24 - pointNumber) : (pointNumber - 1);
        final boolean rightOfBar = ltrIndex >= 6;
        final int x = ltrIndex * this.getTriangleWidth() + (rightOfBar ? getBarWidth() : 0) + getBarWidth();
        final int y = isUpper ? 0 : (this.getBoardHeight() - this.getTriangleHeight());
        return new Point(x, y);
    }
    
    private Dimension getTriangleDimension() {
        return new Dimension(this.getTriangleWidth(), this.getTriangleHeight());
    }
    
    public Rectangle getPointRectangle(final int pointNumber) {
        return new Rectangle(this.getPointPoint(pointNumber), this.getTriangleDimension());
    }
    
    public Ellipse2D diskAt(final int k, final boolean isDownTriangle) {
        final double w = this.getDiskWidth();
        final double x = 0.0;
        double y = (k - 1) * w;
        if (!isDownTriangle) {
            y = this.getTriangleHeight() - y - w;
        }
        return new Ellipse2D.Double(x, y, w, w);
    }
}
