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

        if (selected == null && p.getColor() == caller.getPieceColor()) {
            if (caller == board.getBar() && board.getBar().getCount(p.getColor()) == 0) return;
            else if (caller == board.getBearOff()) return;
            board.setSelectedPosition(caller);
            caller.addHighlight(Color.BLUE);
            board.highlightMoves(Color.GREEN);
        }
        else if (selected == caller) {
            board.setSelectedPosition(null);
            board.clearHighlights();
        }
        else if (selected != null) {
            board.setSelectedPosition(null);
            board.clearHighlights();

            //Move won't be made unless it exists in the turnlist
            board.makeMove(selected, caller);
            board.repaint();
        }
    }
}
