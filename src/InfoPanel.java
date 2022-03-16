import javax.swing.*;

public class InfoPanel extends JPanel {
    private Game game;
    private JLabel rolls;
    private JLabel activePlayer;
    private JLabel scores;

    public InfoPanel(Game game) {
        this.game = game;

        activePlayer = new JLabel();
        rolls = new JLabel();
        scores = new JLabel();

        add(activePlayer);
        add(rolls);
        add(scores);
    }

    public void updateInfo() {
        int[] r = game.getRolls();
        StringBuilder rollText = new StringBuilder("Moves: ");
        int[] s = game.getScores();
        StringBuilder scoreText = new StringBuilder("Score: ");
        
        for(int score : s){
            scoreText.append(score);
            scoreText.append(", ");
        }

        for (int roll : r){
            rollText.append(roll);
            rollText.append(", ");
        }
        rollText.setLength(rollText.length() - 2);
        scoreText.setLength(scoreText.length() - 2);

        scores.setText(scoreText.toString());
        rolls.setText(rollText.toString());
        activePlayer.setText(game.getActivePlayer().getName());

        repaint();
    }
}
