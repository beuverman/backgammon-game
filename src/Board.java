public class Board {
    final private Triangle[] points = new Triangle[24];
    final Bar bar;

    public Board() {
        bar = new Bar();
        setInitialBoard();
    }

    private void setInitialBoard() {
        for (int i = 0; i < points.length; i++) {
            points[i] = switch (i) {
                case 0 -> new Triangle(2, Color.BLACK, this);
                case 5, 12 -> new Triangle(5, Color.WHITE, this);
                case 7 -> new Triangle(3, Color.WHITE, this);
                case 11, 18 -> new Triangle(5, Color.BLACK, this);
                case 16 -> new Triangle(3, Color.BLACK, this);
                case 23 -> new Triangle(2, Color.WHITE, this);
                default -> new Triangle(0, Color.EMPTY, this);
            };
        }
    }
}
