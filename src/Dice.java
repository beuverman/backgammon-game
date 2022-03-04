public class Dice {
  //Import random. 
  //methods Roll() should return 2 random ints. 
    private int roll;
    public Dice() {
    }

    public int rollDice() {
        this.roll= (int)(Math.random() * 6 + 1);
           return roll;
    }
    
}
