import java.util.ArrayList;
import java.util.Arrays;

public class Move {
    private Position from;
    private Position to;
    private static int[] board;
    private static Board b;
    private static PlayerColor color;

    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    @Override
    public String toString() {
        return from.getPointNumber() + "-" + to.getPointNumber();
    }

    private static void parseBoard(Board b, PlayerColor color) {
        Move.b = b;
        int[] temp = new int[24];
        int[] board = new int[25];

        for (int i = 0; i < 24; i++) {
            Triangle point = b.getPoint(i + 1);
            temp[i] = point.getCount();
            if (color.equals(point.getPieceColor()))
                temp[i] *= -1;
        }
        if (color.equals(PlayerColor.WHITE))
            temp = reverse(temp);

        for (int i = 0; i < temp.length; i++) {
            board[i] = temp[i];
        }
        board[board.length - 1] = b.getBar().getCount(color);

        Move.board = board;
    }

    public static ArrayList<Turn> reducePossibleTurns(Board b, ArrayList<Turn> turns, Position from, Position to) {
        ArrayList<Turn> newTurns = new ArrayList<>();
        Move[] oldMoves;
        Move[] newMoves;

        for (Turn turn : turns) {
            if (turn.getMoves()[0].getFrom() == from && turn.getMoves()[0].getTo() == to) {
                oldMoves = turn.getMoves();
                newMoves = new Move[oldMoves.length - 1];
                System.arraycopy(oldMoves, 1, newMoves, 0, newMoves.length);
                newTurns.add(new Turn(newMoves));
            }
        }

        for (int i = 0; i < newTurns.size(); i++) {
            if (newTurns.get(i).getMoves().length == 0) {
                newTurns.remove(i);
                i--;
            }
        }

        parseBoard(b, b.getGame().getActivePlayer().getColor());
        System.out.print(Arrays.toString(board) + " : ");
        System.out.println(newTurns);
        return newTurns;
    }

    public static ArrayList<Turn> getPossibleTurns(Board b, PlayerColor c, int r1, int r2) {
        parseBoard(b, c);
        color = c;
        ArrayList<Turn> turns = new ArrayList<>();

        getTwoRollTurns(turns, r1, r2);
        getTwoRollTurns(turns, r2, r1);

        //Can only make one roll
        if (turns.size() == 0) {
            r1 = Math.max(r1, r2);

            for (int i = 0; i < board.length; i++) {
                if (isLegalMove(i, r1)) {
                    addTurn(turns, getTurnString(i + 1, i + 1 - r1));
                }
            }
        }

        //Bearing off highest point
        if (turns.size() == 0) {
            int highest = getHighestPoint();
            addTurn(turns, getTurnString(highest + 1, 0));
        }

        parseBoard(b, b.getGame().getActivePlayer().getColor());
        System.out.print(Arrays.toString(board) + " : ");
        System.out.println(turns);
        return turns;
    }

    private static void getTwoRollTurns(ArrayList<Turn> fullList, int r1, int r2) {
        ArrayList<Turn> turns = new ArrayList<>();
        boolean flag;

        for (int i = 0; i < board.length; i++) {
            flag = true;
            if (isLegalMove(i, r1)) {
                makeMove(i, i - r1);
                for (int j = 0; j < board.length; j++) {
                    if (isLegalMove(j, r2)) {
                        flag = false;
                        if (r1 == r2) {
                            makeMove(j, j - r2);
                            doubleExtensionLoop(turns, i, j, r1);
                            makeMove(j - r2, j);
                        }
                        else
                            addTurn(turns, getTurnString(i + 1, i + 1 - r1, j + 1, j + 1 - r2));
                    }
                }
                if (flag) {
                    int highest = getHighestPoint();
                    if (highest - r2 < 0)
                        addTurn(turns, getTurnString(i+1, i+1-r1, highest+1, 0));
                }
                makeMove(i - r1, i);
            }
        }

        for (Turn turn : turns)
            addTurn(fullList, turn);
    }

    private static void doubleExtensionLoop(ArrayList<Turn> fullList, int from1, int from2, int roll) {
        ArrayList<Turn> turns = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            if (isLegalMove(i, roll)) {
                makeMove(i, i - roll);
                for (int j = 0; j < board.length; j++) {
                    if (isLegalMove(j, roll)) {
                        addTurn(turns, getTurnString(new int[]{from1+1, from2+1, i+1, j+1}, roll));
                    }
                }
                makeMove(i - roll, i);
            }
        }

        if (turns.size() == 0) {
            for (int i = 0; i < board.length; i++) {
                if (isLegalMove(i, roll)) {
                    addTurn(turns, getTurnString(new int[]{from1+1, from2+1, i+1}, roll));
                }
            }
        }

        if (turns.size() == 0)
            addTurn(turns, getTurnString(new int[]{from1+1, from2+1}, roll));

        for (Turn turn : turns)
            addTurn(fullList, turn);
    }

    public static boolean isLegalMove(int point, int roll) {
        if (board[point] < 1) return false; //Selected point has your pieces
        else if (point - roll < -1) return false; //Move to point exists
        else if (board[24] > 0 && point != 24) return false; //Bar is empty

        if (point - roll == -1) { //Trying to bear off
            for (int i = 6; i < board.length; i++) { //Not all in inner board
                if (board[i] > 0) return false;
            }
        }
        else if (board[point - roll] < -1) return false; //Move to point is occupied

        return true;
    }

    private static Turn getTurnString(int from, int to) {
        Position f = findPosition(from);
        Position t = findPosition(to);
        return new Turn(new Move[]{new Move(f, t)});
    }

    private static Turn getTurnString(int from1, int to1, int from2, int to2) {
        Position f1 = findPosition(from1);
        Position t1 = findPosition(to1);
        Position f2 = findPosition(from2);
        Position t2 = findPosition(to2);

        return new Turn(new Move[]{new Move(f1, t1), new Move(f2, t2)});
    }

    private static Turn getTurnString(int[] from, int roll) {
        Move[] moves = new Move[from.length];
        Position f;
        Position t;

        for (int i = 0; i < from.length; i++) {
            f = findPosition(from[i]);
            t = findPosition(from[i] - roll);
            moves[i] = new Move(f, t);
        }

        return new Turn(moves);
    }

    private static Position findPosition(int pos) {
        if (pos == 25)
            return b.getBar();
        if (pos == 0)
            return b.getBearOff();

        if (b.getGame().getActivePlayer().getColor() == PlayerColor.BLACK)
            return b.getPoint(25 - pos);
        return b.getPoint(pos);
    }

    private static void addTurn(ArrayList<Turn> turns, Turn turn) {
        for (Turn t : turns) {
            if (t.equals(turn)) return;
        }

        turns.add(turn);
    }

    public static void makeMove(int from, int to) {
        if (from > 0)
            board[from]--;
        if (to > 0)
            board[to]++;
    }

    private static int getHighestPoint() {
        int highest = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] > 0)
                highest = i;
        }
        return highest;
    }

    private static int[] reverse(int[] arr) {
        int[] temp = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[arr.length - i - 1];
        }
        return temp;
    }
}