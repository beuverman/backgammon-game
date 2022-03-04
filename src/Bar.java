public class Bar {
    int blackCount;
    int whiteCount;

    public Bar() {
      blackCount = 0;
      whiteCount = 0;
    }

    public Bar(int blackCount, int whiteCount) {
        this.blackCount = blackCount;
        this.whiteCount = whiteCount;
    }

    public static int getCount(Color color){
        if(color == Color.BLACK){
            return blackCount;
        }else if (color == Color.WHITE){
            return whiteCount;
        }
        return 0;
    }
}
