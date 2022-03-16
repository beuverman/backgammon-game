public class Player {
    private PlayerColor playerColor;
    private String name;
    private int score;

    public Player(PlayerColor c, String name){
        playerColor = c;
        this.name = name;
        score = 0;
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
    
    public void SelectMove(Turn [] turns){


    }

}

