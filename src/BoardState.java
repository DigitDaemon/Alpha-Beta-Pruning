
public class BoardState {

	private int[][] Board;
	
	public void setBoard(BoardState inBoard){
		Board = new int[8][8];
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				Board[i][j] = (int)inBoard.getSpace(i, j);
			}
		}
	}
	
	public void NewBoard(){
		Board = new int[8][8];
		for(int[] row : Board){
			for(int space : row){
				space = 0;
			}
		}
	}
	
	public int getSpace(int row, int col){
		if (row < 0 || row > 7)
			return 0;
		
		
		if (col < 0 || col > 7)
			return 0;
		
		return Board[row][col];
	}
	
	public int getSpace(int index) {
		return getSpace(index/8,index%8);
	}
	
	public void setSpace(int row, int col, int value) throws WinningMoveException{
		if (row < 0 || row > 7)
			throw new RuntimeException("Invalid row identifyer");
		
		
		if (col < 0 || col > 7)
			throw new RuntimeException("Invalid col identifyer");
		
		
		Board[row][col] = value;
		
		checkWin();
	}
	
	public void checkWin() throws WinningMoveException {
		for(int i = 0; i < 64; i++) {
			if(Board[row(i)][col(i)] != 0) {
				if(col(i) <= 4) {
					if(Board[row(i)][col(i)] == Board[row(i)][col(i)+1] && Board[row(i)][col(i)] == Board[row(i)][col(i)+2] && Board[row(i)][col(i)] == Board[row(i)][col(i)+3])
						if(Board[row(i)][col(i)] > 0)
							throw new WinningMoveException("", 1000);
						else
							throw new WinningMoveException("", -10000);
				}
				if(row(i) <= 4) {
					if(Board[row(i)][col(i)] == Board[row(i)+1][col(i)] && Board[row(i)][col(i)] == Board[row(i)+2][col(i)] && Board[row(i)][col(i)] == Board[row(i)+3][col(i)])
						if(Board[row(i)][col(i)] > 0)
							throw new WinningMoveException("", 10000);
						else
							throw new WinningMoveException("", -100000);
				}
			}
		}
	}
	
	public int row(int num) {
		return num/8;
	}
	
	public int col(int num) {
		return num%8;
	}
}
