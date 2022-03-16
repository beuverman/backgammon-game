import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game
{
   private Player p1;
   private Player p2;
   private Board board;
   private int[] rolls;
   private ArrayList<Turn> possibleTurns;

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

      do {
         rolls = new int[]{p1.firstRoll(), p2.firstRoll()};
      } while (rolls[0] == rolls[1]);

      if (rolls[0] < rolls[1])
         switchActivePlayer();

      info.updateInfo();
      System.out.println(Move.getPossibleTurns(board, getActivePlayer().getColor(), rolls[0], rolls[1]));
      Turn(p1, p2);
   }

   public void Turn(Player active, Player opponent) {
      do {
         if (rolls.length == 0)
            rolls = p1.RollTurn();

         possibleTurns = Move.getPossibleTurns(board, getActivePlayer().getColor(), rolls[0], rolls[1]);
         if (rolls[0] == rolls[1]) {
            rolls = new int[]{rolls[0], rolls[0], rolls[0], rolls[0]};
         }

         while (possibleTurns.size() != 0 || rolls.length != 0) {
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

   public void setRolls(int[] rolls) {
      this.rolls = rolls;
   }

   public ArrayList<Turn> getPossibleTurns() {
      return possibleTurns;
   }

   public void setPossibleTurns(ArrayList<Turn> turns) {
      possibleTurns = turns;
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
