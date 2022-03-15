public class Move {
	int pieceCount;
	PlayerColor pieceColour;
	int number;
	int board;
	int dice[];
	int blackCount;
	int whiteCount;
	
	
	public Move() {
		pieceCount = 0;
  }

	public Object getPossibleMoves() {
		switch(pieceColour){
			case WHITE:
				if (whiteCount > 0) {
					if((boolean) Triangle(1, PlayerColor.BLACK, (24 - dice[0] + 1)))
						return Triangle(1, PlayerColor.BLACK, (24 - dice[0] + 1));
					if((boolean) Triangle(pieceCount, PlayerColor.WHITE, (24 - dice[0] + 1)))
						return Triangle(pieceCount, PlayerColor.WHITE, (24 - dice[0] + 1));
					if((boolean) Triangle(0, null, (24 - dice[0] + 1)))
						return Triangle(0, null, (24 - dice[0] + 1));
						
					if((boolean) Triangle(1, PlayerColor.BLACK, (24 - dice[1] + 1)))
						return Triangle(1, PlayerColor.BLACK, (24 - dice[1] + 1));
					if((boolean) Triangle(pieceCount, PlayerColor.WHITE, (24 - dice[1] + 1)))
						return Triangle(pieceCount, PlayerColor.WHITE, (24 - dice[1] + 1));
					if((boolean) Triangle(0, null, (24 - dice[1] + 1)))
						return Triangle(0, null, (24 - dice[1] + 1));
				}
				else  {
					if((boolean) Triangle(1, PlayerColor.BLACK, (number - dice[0])))
						return Triangle(1, PlayerColor.BLACK, (number - dice[0]));
					if((boolean) Triangle(pieceCount, PlayerColor.WHITE, (number - dice[0])))
						return Triangle(pieceCount, PlayerColor.WHITE, (number - dice[0]));
					if((boolean) Triangle(0, null, (number - dice[0])))
						return Triangle(0, null, (number - dice[0]));
					
					if((boolean) Triangle(1, PlayerColor.BLACK, (number - dice[1])))
						return Triangle(1, PlayerColor.BLACK, (number - dice[1]));
					if((boolean) Triangle(pieceCount, PlayerColor.WHITE, (number - dice[1])))
						return Triangle(pieceCount, PlayerColor.WHITE, (number - dice[1]));
					if((boolean) Triangle(0, null, (number - dice[1])))
						return Triangle(0, null, (number - dice[1]));
				}
				
			case BLACK:
				if (blackCount > 0) {
					if((boolean) Triangle(1, PlayerColor.WHITE, dice[0]))
						return Triangle(1, PlayerColor.WHITE, dice[0]);
					if((boolean) Triangle(pieceCount, PlayerColor.BLACK, dice[0]))
						return Triangle(pieceCount, PlayerColor.BLACK, dice[0]);
					if((boolean) Triangle(0, null, dice[0]))
						return Triangle(0, null, dice[0]);
						
					if((boolean) Triangle(1, PlayerColor.WHITE, dice[1]))
						return Triangle(1, PlayerColor.WHITE, dice[1]);
					if((boolean) Triangle(pieceCount, PlayerColor.BLACK, dice[1]))
						return Triangle(pieceCount, PlayerColor.BLACK, dice[1]);
					if((boolean) Triangle(0, null, dice[1]))
						return Triangle(0, null, dice[1]);
				}
				else {
					if((boolean) Triangle(1, PlayerColor.WHITE, (number + dice[0])))
						return Triangle(1, PlayerColor.WHITE, (number + dice[0]));
					if((boolean) Triangle(pieceCount, PlayerColor.BLACK, (number + dice[0])))
						return Triangle(pieceCount, PlayerColor.BLACK, (number + dice[0]));
					if((boolean) Triangle(0, null, (number + dice[0])))
						return Triangle(0, null, (number + dice[0]));
					
					if((boolean) Triangle(1, PlayerColor.WHITE, (number + dice[1])))
						return Triangle(1, PlayerColor.WHITE, (number + dice[1]));
					if((boolean) Triangle(pieceCount, PlayerColor.BLACK, (number + dice[1])))
						return Triangle(pieceCount, PlayerColor.BLACK, (number + dice[1]));
					if((boolean) Triangle(0, null, (number + dice[1])))
						return Triangle(0, null, (number + dice[1]));
				}
			}
		return null;
	}
	


	private Object Triangle(int i, Object object, int j) {
		pieceCount=i;
		object=PlayerColor.class;
		j=board;
		return Triangle(i, object, j);
	}

	public void makeMove() {
		getPossibleMoves();
		switch(pieceColour) {
			case WHITE:
				if((boolean) Triangle(1, PlayerColor.BLACK, board)) {
					Triangle(pieceCount = 1, PlayerColor.WHITE, board);
					++blackCount;
				}
				if((boolean) Triangle(pieceCount, PlayerColor.WHITE, board))
					Triangle(++pieceCount, PlayerColor.WHITE, board);
				if((boolean) Triangle(0, null, board))
					Triangle(1, PlayerColor.WHITE, board);
			
			case BLACK:
				if((boolean) Triangle(1, PlayerColor.WHITE, board)) {
					Triangle(pieceCount = 1, PlayerColor.BLACK, board);
					++whiteCount;
				}
				if((boolean) Triangle(pieceCount, PlayerColor.BLACK, board))
					Triangle(++pieceCount, PlayerColor.BLACK, board);
				if((boolean) Triangle(0, null, board))
					Triangle(1, PlayerColor.BLACK, board);
			
		}
		
	}

}
