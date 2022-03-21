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
   private int[] score = new int[2];

   private JFrame frame;
   private InfoPanel info;

   public Game() {
      board = new Board(this);
      setupFrame();
   }

   public void setupFrame() {
      frame = new javax.swing.JFrame("Backgammon");
      frame.setSize(860,690);
      frame.setBackground(new java.awt.Color(12,64,8));
      frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

      info = new InfoPanel(this);

      frame.add(info, BorderLayout.SOUTH);
      frame.add(board, BorderLayout.CENTER);
      frame.setVisible(true);
   }

   private void getPlayers() {
      p1 = new Player(PlayerColor.WHITE, "Player 1", this);
      p2 = new Player(PlayerColor.BLACK, "Player 2", this);
      score[0] = p1.getScore();
      score[1] = p2.getScore();
   }

   public Player getActivePlayer() {
      return p1;
   }

   public Board getBoard() {
      return board;
   }

   public InfoPanel getInfoPanel() {return info;}

   public int[] getRolls() {
      return rolls;
   }

   public void setRolls(int[] rolls) {
      this.rolls = rolls;
      info.updateInfo();
   }

   public ArrayList<Turn> getPossibleTurns() {
      return possibleTurns;
   }

   public void setPossibleTurns(ArrayList<Turn> turns) {
      possibleTurns = turns;
   }

   public void start() {
      getPlayers();
      board.setInitialBoard();

      do {
         setRolls(new int[]{p1.firstRoll(), p2.firstRoll()});
      } while (rolls[0] == rolls[1]);

      if (rolls[0] < rolls[1])
         switchActivePlayer();

      info.updateInfo();
      Turn(p1, p2);
   }

   public void Turn(Player active, Player opponent) {
      do {
         if (rolls.length == 0)
            setRolls(p1.RollTurn());

         possibleTurns = Move.getPossibleTurns(board, getActivePlayer().getColor(), rolls[0], rolls[1]);
         if (rolls[0] == rolls[1]) {
            setRolls(new int[]{rolls[0], rolls[0], rolls[0], rolls[0]});
         }

         while (possibleTurns.size() != 0 || rolls.length != 0) {
            info.updateInfo();
            p1.selectMove(possibleTurns);
            //TURN
         }

         if (Won(active))
            break;
         switchActivePlayer();
      }while (true);

      end();
      findScore(active, opponent);
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

   private void switchActivePlayer() {
      Player temp = p1;
      p1 = p2;
      p2 = temp;
   }

   private boolean Won(Player p) {
      return board.countPieces(p.getColor()) == 0;
   }

   public int[] getScores(){
      return score;
   }
   
   public void findScore(Player p, Player op) {
      if (board.countPieces(op.getColor()) > 15 && !board.checkHome(op.getColor())) {
         p.setScore(3);
      } else {
         if (board.countPieces(op.getColor()) > 15 && board.checkHome(op.getColor())) {
            p.setScore(2);
         } else {
            p.setScore(1);
         }
      }
   }
   
   public void choosePlayer(){ // work in progress chooseplayer code
      JFrame c = new JFrame("Choose Player ");
      c.setSize(800,700);
      JPanel p = new JPanel();
      JButton choosePlayer = new JButton("Two Players");
      JButton chooseComputer = new JButton("Computer Player");
      p.add(choosePlayer, BorderLayout.CENTER);
      p.add(chooseComputer, BorderLayout.CENTER);
      c.add(p, BorderLayout.CENTER);
      choosePlayer.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            getPlayers();
            c.dispose();
//               start();
         }
      });
      chooseComputer.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
//            getComputerPlayers();
            c.dispose();
//               start();
         }
      });
      c.setVisible(true);

   }
}
