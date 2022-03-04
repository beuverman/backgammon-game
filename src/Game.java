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

    }
}
