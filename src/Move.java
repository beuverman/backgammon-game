public class Move {
    private Triangle from;
    private Triangle to;

    public Move(Triangle from, Triangle to) {
        this.from = from;
        this.to = to;
    }

    public boolean isLegal(Board board, int roll) {
        Player p = board.getGame().getActivePlayer();
        int point1 = from.getPointNumber();
        int point2 = to.getPointNumber();

        if (from.getColor() != p.getColor()) return false;
        else if (point2 - point1 < 0) return false;
        else if (board.getBar().getCount(p.getColor()) != 0) return false;

        if (point2 - point1 == 0) {
            for (int i = 7; i <= 24; i++) {
                if (board.getPoint(i).getColor() != p.getColor() || board.getPoint(i).getCount() == 0) {
                    return false;
                }
            }
        }
        else if (to.getColor() != p.getColor() && to.getCount() > 1) return false;

        return true;
    }
