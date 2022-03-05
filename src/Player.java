import java.util.Scanner;

public class Player {
    private Color color;

    public Player(Color c){
        color = c;
    }

    public Color getColor() {
        return color;
    }

    public int firstRoll(){
        return Dice.roll();
    }

    public int[] RollTurn(){
        int dice[]=new int[2];
        dice[0] = Dice.roll();
        dice[1] = Dice.roll();

        //SelectMove(dice1,dice2);
        return dice;
    }

//    public int SelectMove(int d1, int d2){
//        Move.getpossibleMoves(color, d1, d2);
//        System.out.println("Select move from the list");
//        Scanner kbd = new Scanner(System.in);
//        int move = kbd.nextInt();
//        //ask the move they want from the possible move
//        return move;
//    }

}

