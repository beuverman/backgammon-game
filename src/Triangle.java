import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.MatteBorder;

public class Triangle extends JComponent implements Position {
    private final Board board;
    private final int pointNumber;
    private final Polygon polygon;
    private PlayerColor pieceColour;
    private int pieceCount;
    
    public Triangle(int pieceCount, PlayerColor pieceColour, int number, final Board board) {
        this.board = board;
        pointNumber = number;
        this.pieceColour = pieceColour;
        this.pieceCount = pieceCount;

        this.board.add(this);
        setBounds(Board.getGeometry().getPointRectangle(pointNumber));
        polygon = getTriangle();

        addMouseListener(addListener());
    }

    public int getCount(){
        return pieceCount;
    }

    public PlayerColor getColor(){
        return pieceColour;
    }

    public int getPointNumber() {
        return pointNumber;
}

    public void setColor(PlayerColor color) {
        pieceColour = color;
    }

    public void setCountAndColor(int count, PlayerColor color) {
        pieceCount = count;
        pieceColour = color;
    }

    public void addPiece(PlayerColor color){
        if (pieceCount == 0)
            setColor(color);

        if (pieceCount == 1 && color != pieceColour && pieceColour != null) {
            board.getBar().addPiece(color.getOtherColor());
            pieceColour = color;
        }
        else pieceCount = pieceCount + 1;
    }

    public void removePiece(PlayerColor color){
        if (pieceCount == 0) return;
        pieceCount = pieceCount - 1;
        if (pieceCount == 0)
            pieceColour = null;
    }

    public boolean isBlot(){
        return pieceCount == 1;
    }

    private MouseListener addListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Player p = board.getGame().getActivePlayer();
                Triangle selected = board.getSelectedTriangle();
                if (selected != null) selected = board.getPoint(selected.pointNumber);
                Triangle caller = (Triangle)e.getComponent();
                caller = board.getPoint(caller.pointNumber);

                if (selected == null && p.getColor() == caller.pieceColour) {
                    board.setSelectedTriangle(caller);
                    caller.setBorder(new MatteBorder(3, 3, 3, 3, Color.BLUE));
                    highlightMoves();
                }
                else if (selected == caller) {
                    board.setSelectedTriangle(null);
                    board.clearHighlights();
                }
                //TODO legal move function
                //TODO make move function
                else if (selected != null && caller != null) {
                    board.setSelectedTriangle(null);
                    board.clearHighlights();

                    board.makeMove(selected, caller);
                    board.repaint();
                }
                caller.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                if (board.getSelectedTriangle() == null) {
//                    Player p = board.getGame().getActivePlayer();
//                    Triangle caller = (Triangle)e.getComponent();
//
//                    board.clearHighlights();
//
//                    if (p.getColor() == caller.pieceColour) {
//                        highlightMoves();
//                    }
//                    repaint();
//                }
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }

    private void highlightMoves() {
        ArrayList<Turn> turns = board.getGame().getPossibleTurns();

        for (Turn turn : turns) {
            if (turn.getMoves()[0].getFrom().getPointNumber() == pointNumber)
                turn.getMoves()[0].getTo().addHighlight();
        }
    }

    @Override
    public void addHighlight() {
        setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GREEN));
        repaint();
    }

    @Override
    public void removeHighlight() {
        setBorder(null);
        repaint();
    }
    
    @Override
    protected void paintComponent(final Graphics g) {
        final Color fg = this.isDarkColoured() ? Palette.getDarkTriangleColour() : Palette.getLightTriangleColour();
        final Graphics2D g2 = (Graphics2D)g;
        g2.setColor(fg);
        g2.fillPolygon(this.polygon);
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
        return this.pointNumber <= 12;
    }
    
    public boolean pointsDown() {
        return !this.pointsUp();
    }
    
    public boolean isDarkColoured() {
        return this.pointNumber % 2 == 1;
    }
}
