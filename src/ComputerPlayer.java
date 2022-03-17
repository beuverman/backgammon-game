import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player{
    private PlayerColor playerColor;
    private String name;
    private int score;
    private Game game;

    public ComputerPlayer(PlayerColor c, String name, Game game) {
        super(c, name, game);
        playerColor = c;
        this.name = name;
        score = 0;
        this.game = game;
    }
    @Override
    public void selectMove(ArrayList<Turn> turns) {
        Random rng = new Random();
        int num = rng.nextInt(turns.size());

        Turn turn = turns.get(num);
        for (Move move : turns.get(num).getMoves()) {
            game.getBoard().makeMove(move.getFrom(), move.getTo());
        }
    }
}

