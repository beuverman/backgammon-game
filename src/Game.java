public class Game {
    private Player p1;
    private Player p2;
    private Board board;

    public Game() {
        p1 = new Player(Color.WHITE);
        p2 = new Player(Color.BLACK);
        board = new Board();
    }

    public void start() {
        int r1 = p1.firstRoll();
        int r2 = p2.firstRoll();
        
        if (r1 > r2)
            Turn(p1, p2);
        else
            Turn(p2, p1);
    }
    
    public void Turn(Player active, Player opponent) {
        int[] roll = active.RollTurn();

        Turn(opponent, active);
    }
}
