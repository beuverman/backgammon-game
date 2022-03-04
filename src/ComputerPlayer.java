import java.util.Random;

public class Computer extends Player{

    private String color = "computer";
    public Computer(String c) {
        super(c);
    }

    @Override
    public int SelectMove(int d1, int d2){
        int num = Move.getpossibleMoves(color, d1, d2);
        Random ra = new Random();
        int move = ra.nextInt(num);
        //ask the move they want from the possible move
        return move;
    }
}

