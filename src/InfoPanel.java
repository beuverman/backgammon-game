import javax.swing.*;

public class InfoPanel extends JPanel {
    private Game game;
    private JLabel rolls;
    private JLabel activePlayer;

    public InfoPanel(Game game) {
        this.game = game;

        activePlayer = new JLabel();
        rolls = new JLabel();

        add(activePlayer);
        add(rolls);
    }

    public void updateInfo() {
        int[] r = game.getRolls();
        StringBuilder rollText = new StringBuilder("Moves: ");

        for (int roll : r){
            rollText.append(roll);
            rollText.append(", ");
        }
        rollText.setLength(rollText.length() - 2);

        rolls.setText(rollText.toString());
        activePlayer.setText(game.getActivePlayer().getName());

        repaint();
    }
}
