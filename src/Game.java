import javax.swing.*;
import java.awt.*;

public class Game
{
   private Player p1;
   private Player p2;
   private Board board;
   private JFrame frame;

   public Game() {
      board = new Board();
      setupFrame();
   }

   public void setupFrame() {
      frame = new javax.swing.JFrame("Backgammon");
      frame.setSize(800,700);
      frame.setLayout(null);
      frame.setBackground(new java.awt.Color(12,64,8));
      frame.add(board, BorderLayout.CENTER);
      frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);

   }

   private void getPlayers() {
      p1 = new Player(PlayerColor.WHITE);
      p2 = new Player(PlayerColor.BLACK);
   }

   public void start() {
      getPlayers();

      int r1 = p1.firstRoll();
      int r2 = p2.firstRoll();

      if (r1 > r2)
         Turn(p1, p2);
      else
         Turn(p2, p1);
   }

   public void Turn(Player active, Player opponent) {
      int[] roll = active.RollTurn();

      if (!Won(active))
         Turn(opponent, active);
   }

   private boolean Won(Player p) {
      return board.countPieces(p.getColor()) == 0;
   }
}
