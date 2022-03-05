public class Triangle {
    private int count;
    private Color color;
    private Board board;

    public Triangle(int x,Color y,Board z){
        count = x;
        color = y;
        board = z;
    }

    public int getCount(){
        return count;
    }

    public Color getColor(){
        return color;
    }

    public void addPiece(){
        count = count + 1;
    }

    public void removePiece(){
        count = count - 1;
    }

    public boolean isBlot(){
        return count == 1;
    }
}
