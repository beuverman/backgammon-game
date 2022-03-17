import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TriangleListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        Position caller = (Position)e.getComponent();
        Board board = caller.getBoard();
        Player p = board.getGame().getActivePlayer();
        Position selected = board.getSelectedPosition();

        if (selected != null) selected = board.getPoint(selected.getPointNumber());
        caller = board.getPoint(caller.getPointNumber());

        if (selected == null && p.getColor() == caller.getPieceColor()) {
            board.setSelectedPosition(caller);
            caller.addHighlight(Color.BLUE);
            board.highlightMoves(Color.GREEN);
        }
        else if (selected == caller) {
            board.setSelectedPosition(null);
            board.clearHighlights();
        }
        else if (selected != null && caller != null) {
            board.setSelectedPosition(null);
            board.clearHighlights();

            board.makeMove(selected, caller);
            board.repaint();
        }
    }
}
