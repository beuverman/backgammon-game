public class Turn {
    private Move[] moves;

    public Turn(Move[] moves) {
        this.moves = moves;
    }
    
    public Move[] getMoves() {
        return moves;
    }

    public boolean equals(Turn t) {
        if (moves.length != t.moves.length) return false;

        for (int i = 0; i < moves.length; i++) {
            if (moves[i] != t.moves[i]) return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        for (Move move : moves) {
            sb.append(move);
            sb.append(",");
        }
        sb.setCharAt(sb.length() - 1, ']');

        return sb.toString();
    }
}
