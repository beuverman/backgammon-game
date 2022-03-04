public class Dice {

    private int roll;
    public Dice() {
    }

    public int rollDice() {
        this.roll= (int)(Math.random() * 6 + 1);
           return roll;
    }
    
}
