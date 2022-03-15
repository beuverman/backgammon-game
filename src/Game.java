import javax.swing.*;
import java.awt.*;

public class Game
{
   private Player p1;
   private Player p2;
   private Board board;
   private int[] rolls;

   private JFrame frame;
   private InfoPanel info;

   public Game() {
      board = new Board(this);
      setupFrame();
   }

   public void setupFrame() {
      frame = new javax.swing.JFrame("Backgammon");
      frame.setSize(800,700);
      frame.setBackground(new java.awt.Color(12,64,8));
      frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

      info = new InfoPanel(this);

      frame.add(info, BorderLayout.SOUTH);
      frame.add(board, BorderLayout.CENTER);
      frame.setVisible(true);
   }

   private void getPlayers() {
      p1 = new Player(PlayerColor.WHITE, "Player 1");
      p2 = new Player(PlayerColor.BLACK, "Player 2");
   }

   private void switchActivePlayer() {
      Player temp = p1;
      p1 = p2;
      p2 = temp;
   }

   public void start() {
      getPlayers();
      board.setInitialBoard();

      rolls = new int[]{p1.firstRoll(), p2.firstRoll()};
      if (rolls[0] == rolls[1]) {
         rolls = new int[]{rolls[0], rolls[0], rolls[0], rolls[0]};
      }

      if (rolls[0] > rolls[1])
         switchActivePlayer();

         Turn(p1, p2);
   }

   public void Turn(Player active, Player opponent) {
      do {
         if (rolls.length != 0)
            rolls = p1.RollTurn();

         while (rolls.length != 0) {
            info.updateInfo();
            //TURN
         }

         if (Won(active))
            break;
         switchActivePlayer();
      }while (true);
      end();

   }

   private boolean Won(Player p) {
      return board.countPieces(p.getColor()) == 0;
   }

   public Player getActivePlayer() {
      return p1;
   }

   public int[] getRolls() {
      return rolls;
   }
   
      public void end(){
      JButton replay = new JButton("Replay?");
      replay.setFont(new Font("Arial", Font.PLAIN,40));
      board.setVisible(false);
      frame.add(replay,BorderLayout.CENTER);
      frame.repaint();
      replay.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            setupFrame();
            start();


         }
      });
   }
}
