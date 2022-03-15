public class Move {
	final private Triangle[] points = new Triangle[24];
	int selected;
	Color color;
	int x;
	int board;
	
	
	public Move() {
		selected = 0;
  }

	public Triangle[] getPossibleMoves(int[] dice, int selected, int board, Color color, int x) {
		switch(color){
			case WHITE:
				if (Bar.whiteCount > 0) {
					if(Triangle(1, Color.BLACK, (24 - dice[0] + 1)))
						return Triangle(1, Color.BLACK, (24 - dice[0] + 1));
					if(Triangle(x, Color.WHITE, (24 - dice[0] + 1)))
						return Triangle(x, Color.WHITE, (24 - dice[0] + 1));
					if(Triangle(0, Color.EMPTY, (24 - dice[0] + 1)))
						return Triangle(0, Color.EMPTY, (24 - dice[0] + 1));
						
					if(Triangle(1, Color.BLACK, (24 - dice[1] + 1)))
						return Triangle(1, Color.BLACK, (24 - dice[1] + 1));
					if(Triangle(x, Color.WHITE, (24 - dice[1] + 1)))
						return Triangle(x, Color.WHITE, (24 - dice[1] + 1));
					if(Triangle(0, Color.EMPTY, (24 - dice[1] + 1)))
						return Triangle(0, Color.EMPTY, (24 - dice[1] + 1));
				}
				else if (Bar.whiteCount = 0) {
					if(Triangle(1, Color.BLACK, (selected - dice[0])))
						return Triangle(1, Color.BLACK, (selected - dice[0]));
					if(Triangle(x, Color.WHITE, (selected - dice[0])))
						return Triangle(x, Color.WHITE, (selected - dice[0]));
					if(Triangle(0, Color.EMPTY, (selected - dice[0])))
						return Triangle(0, Color.EMPTY, (selected - dice[0]));
					
					if(Triangle(1, Color.BLACK, (selected - dice[1])))
						return Triangle(1, Color.BLACK, (selected - dice[1]));
					if(Triangle(x, Color.WHITE, (selected - dice[1])))
						return Triangle(x, Color.WHITE, (selected - dice[1]));
					if(Triangle(0, Color.EMPTY, (selected - dice[1])))
						return Triangle(0, Color.EMPTY, (selected - dice[1]));
				}
				
			case BLACK:
				if (Bar.blackCount > 0) {
					if(Triangle(1, Color.WHITE, dice[0]))
						return Triangle(1, Color.WHITE, dice[0]);
					if(Triangle(x, Color.BLACK, dice[0]))
						return Triangle(x, Color.BLACK, dice[0]);
					if(Triangle(0, Color.EMPTY, dice[0]))
						return Triangle(0, Color.EMPTY, dice[0]);
						
					if(Triangle(1, Color.WHITE, dice[1]))
						return Triangle(1, Color.WHITE, dice[1]);
					if(Triangle(x, Color.BLACK, dice[1]))
						return Triangle(x, Color.BLACK, dice[1]);
					if(Triangle(0, Color.EMPTY, dice[1]))
						return Triangle(0, Color.EMPTY, dice[1]);
				}
				else if (Bar.blackCount = 0) {
					if(Triangle(1, Color.WHITE, (selected + dice[0])))
						return Triangle(1, Color.WHITE, (selected + dice[0]));
					if(Triangle(x, Color.BLACK, (selected + dice[0])))
						return Triangle(x, Color.BLACK, (selected + dice[0]));
					if(Triangle(0, Color.EMPTY, (selected + dice[0])))
						return Triangle(0, Color.EMPTY, (selected + dice[0]));
					
					if(Triangle(1, Color.WHITE, (selected + dice[1])))
						return Triangle(1, Color.WHITE, (selected + dice[1]));
					if(Triangle(x, Color.BLACK, (selected + dice[1])))
						return Triangle(x, Color.BLACK, (selected + dice[1]));
					if(Triangle(0, Color.EMPTY, (selected + dice[1])))
						return Triangle(0, Color.EMPTY, (selected + dice[1]));
				}
			}
	}
	


	public void makeMove(int[] dice, int selected, int board, Color color, int x) {
		
		
	}

}
