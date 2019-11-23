
public class BoardState {

	private int[][] Board;
	
	public void setBoard(BoardState inBoard){
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				try {
					this.setSpace(i,j, inBoard.getSpace(i, j));
				} catch (WinningMoveException e) {
					e.printStackTrace();
				}
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
			throw new RuntimeException("Invalid row identifyer");
		
		
		if (col < 0 || col > 7)
			throw new RuntimeException("Invalid col identifyer");
		
		return Board[row][col];
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
					if(Board[row(i)][col(i)] == Board[row(i)][col(i+1)] && Board[row(i)][col(i)] == Board[row(i)][col(i+2)] && Board[row(i)][col(i)] == Board[row(i)][col(i+3)])
						throw new WinningMoveException("", 10000 * Board[row(i)][col(i)]);
				}
				if(row(i) <= 4) {
					if(Board[row(i)][col(i)] == Board[row(i+1)][col(i)] && Board[row(i)][col(i)] == Board[row(i+2)][col(i)] && Board[row(i)][col(i)] == Board[row(i+3)][col(i)])
						throw new WinningMoveException("", 10000 * Board[row(i)][col(i)]);
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
