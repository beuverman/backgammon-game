import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Board extends JPanel
{
    private final Game game;
    private final Triangle[] points;
    private final Bar bar;
    private final Bar bearOff;
    private Position selectedPosition;

    public Board(Game game) {
        super(null, true);
        this.game = game;
        points = new Triangle[24];
        bar = new Bar(this, 1);
        bearOff = new Bar(this, 0);
        add(bar);
        add(bearOff);

        setSize(getGeometry().getBoardWidth(), getGeometry().getBoardHeight());
        setMaximumSize(new Dimension(getGeometry().getBoardWidth(), getGeometry().getBoardHeight()));
        setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
        setBackground(Palette.getBoardBackgroundColour());

        for (int i = 0; i < points.length; i++) {
            points[i] = new Triangle(0, null, i + 1, this);
        }
    }

    public static BoardGeometry getGeometry() {
        return BoardGeometry.getBoardGeometry();
    }

    public Game getGame() {
        return game;
    }

    public Bar getBar() {
        return bar;
    }

    public Bar getBearOff() {
        return bearOff;
    }

    public Triangle getPoint(int num) {
        return points[num - 1];
    }

    public void setSelectedPosition(Position p) {
        selectedPosition = p;
    }

    public Position getSelectedPosition() {
        return selectedPosition;
    }

    public void setInitialBoard() {
        for (int i = 0; i < points.length; i++) {
            switch (i) {
                case 0:
                    points[i].setCountAndColor(2, PlayerColor.BLACK);
                    break;
                case 5:
                case 12:
                    points[i].setCountAndColor(5, PlayerColor.WHITE);
                    break;
                case 7:
                    points[i].setCountAndColor(3, PlayerColor.WHITE);
                    break;
                case 11:
                case 18:
                    points[i].setCountAndColor(5, PlayerColor.BLACK);
                    break;
                case 16:
                    points[i].setCountAndColor(3, PlayerColor.BLACK);
                    break;
                case 23:
                    points[i].setCountAndColor(2, PlayerColor.WHITE);
                    break;
                default:
                    points[i].setCountAndColor(0, null);
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

    public int countPieces(PlayerColor c) {
        int count = bar.getCount(c);

        for (Triangle point : points) {
            if (point.getPieceColor() == c)
                count += point.getCount();
        }

        return count;
    }

    public void makeMove(Position from, Position to) {
        ArrayList<Turn> turns = game.getPossibleTurns();
        boolean flag = true;

        for (Turn turn : turns) {
            if (turn.getMoves()[0].getFrom() == from && turn.getMoves()[0].getTo() == to)
                flag = false;
        }
        if (flag) return;

        int[] rolls = getGame().getRolls();
        int[] temp = new int[rolls.length - 1];

        flag = false;
        for (int i = 0, j = 0; i < rolls.length; i++) {
            if (rolls[i] != Math.abs(from.getPointNumber() - to.getPointNumber()) || flag)
                temp[j++] = rolls[i];
            else flag = true;
        }
        game.setRolls(temp);

        from.removePiece(getGame().getActivePlayer().getColor());
        to.addPiece(getGame().getActivePlayer().getColor());
        game.setPossibleTurns(Move.reducePossibleTurns(this, turns, from, to));
        repaint();
    }

    public void highlightMoves(Color color) {
        ArrayList<Turn> turns = getGame().getPossibleTurns();

        for (Turn turn : turns) {
            if (turn.getMoves()[0].getFrom().getPointNumber() == selectedPosition.getPointNumber())
                turn.getMoves()[0].getTo().addHighlight(color);
        }
    }

    public void clearHighlights() {
        for (Triangle t : points)
            t.removeHighlight();
        bar.removeHighlight();
        bearOff.removeHighlight();
        repaint();
    }

    @Override
    protected void paintComponent(final Graphics g) {
        final Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g2);
    }
}