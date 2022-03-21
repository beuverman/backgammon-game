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

    //**************************************************
    //GET ALL POSSIBLE TURNS
    //**************************************************

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

        parseBoard(b, color);
        System.out.print(Arrays.toString(board) + " : ");
        System.out.println(newTurns);
        return newTurns;
    }

    public static ArrayList<Turn> getPossibleTurns(Board b, PlayerColor c, int r1, int r2) {
        parseBoard(b, c);
        color = c;
        ArrayList<Turn> turns = new ArrayList<>();

        if (r1 == r2) {
            doubleRolls(turns, r1);
        }
        else {
            twoRolls(turns, r1, r2);
            twoRolls(turns, r2, r1);
        }

        System.out.println(Arrays.toString(board) + " : " + turns);
        return turns;
    }

    private static void twoRolls(ArrayList<Turn> fullList, int r1, int r2) {
        ArrayList<Turn> turns = new ArrayList<>();
        ArrayList<Move> moves1;
        ArrayList<Move> moves2;
        int[] tempBoard = board.clone();

        moves1 = getNextMove(tempBoard, r2);
        for (Move move1 : moves1) {
            tempBoard = makeMove(tempBoard, move1);
            moves2 = getNextMove(tempBoard, r1);
            for (Move move2 : moves2) {
                addTurn(turns, createTurn(move1.getFrom(), move1.getTo(), move2.getFrom(), move2.getTo()));
            }
        }

        for (Turn turn : turns)
            addTurn(fullList, turn);
    }

    private static void doubleRolls(ArrayList<Turn> fullList, int r) {
        ArrayList<Turn> turns = new ArrayList<>();
        ArrayList<Move> moves1;
        ArrayList<Move> moves2;
        ArrayList<Move> moves3;
        ArrayList<Move> moves4;
        int[] tempBoard = board.clone();

        moves1 = getNextMove(tempBoard, r);
        for (Move move1 : moves1) {
            tempBoard = makeMove(tempBoard, move1);
            moves2 = getNextMove(tempBoard, r);
            for (Move move2 : moves2) {
                tempBoard = makeMove(tempBoard, move2);
                moves3 = getNextMove(tempBoard, r);
                for (Move move3 : moves3) {
                    tempBoard = makeMove(tempBoard, move3);
                    moves4 = getNextMove(tempBoard, r);
                    for (Move move4 : moves4) {
                        addTurn(turns, new Turn(new Move[]{move1, move2, move3, move4}));
                    }
                }
            }
        }

        for (Turn turn : turns)
            addTurn(fullList, turn);
    }

    private static ArrayList<Move> getNextMove(int[] board, int roll) {
        ArrayList<Move> moves = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            if (isLegalMove(board, i, roll)) {
                moves.add(new Move(intToPosition(i), intToPosition(i - roll)));
            }
        }

        return moves;
    }

    private static boolean isLegalMove(int[] board, int point, int roll) {
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

    private static Turn createTurn(Position from1, Position to1, Position from2, Position to2) {
        return new Turn(new Move[]{new Move(from1, to1), new Move(from2, to2)});
    }

    private static Position intToPosition(int pos) {
        if (pos == 24)
            return b.getBar();
        if (pos == -1)
            return b.getBearOff();

        if (b.getGame().getActivePlayer().getColor() == PlayerColor.BLACK)
            return b.getPoint(24 - pos);
        return b.getPoint(pos + 1);
    }

    private static int PositionToInt(Position pos) {
        if (pos.equals(b.getBar()))
            return 24;
        if (pos.equals(b.getBearOff()))
            return -1;

        if (color == PlayerColor.BLACK)
            return 24 - pos.getPointNumber();
        return pos.getPointNumber() - 1;
    }

    private static int[] makeMove(int[] board, int from, int to) {
        int[] newBoard = board.clone();

        if (from >= 0) {
            newBoard[from]--;
        }
        if (to >= 0) {
            if (newBoard[to] == -1)
                newBoard[to]++;
            newBoard[to]++;
        }

        return newBoard;
    }

    private static int[] makeMove(int[] board, Move move) {
        return makeMove(board, PositionToInt(move.getFrom()), PositionToInt(move.getTo()));
    }

    private static void addTurn(ArrayList<Turn> turns, Turn turn) {
        for (Turn t : turns) {
            if (t.equals(turn)) return;
        }

        turns.add(turn);
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