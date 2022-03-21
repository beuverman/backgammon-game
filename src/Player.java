import java.util.ArrayList;

public class Player {
    private PlayerColor playerColor;
    private String name;
    private int score;
    private Game game;

    public Player(PlayerColor c, String name, Game game){
        playerColor = c;
        this.name = name;
        score = 0;
        this.game = game;
    }

    public PlayerColor getColor() {
        return playerColor;
    }

    public String getName() {
        return name;
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
    
    public int getScore(){
        return score;
    }
    
    public void setScore(int x){
        score = score + x;
    }
    
    public void selectMove(ArrayList<Turn> turns){
    }

}

