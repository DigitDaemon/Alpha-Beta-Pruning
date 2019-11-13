
public class BoardState {

	private int[][] Board;
	
	public void setBoard(BoardState inBoard){
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				this.setSpace(i,j, inBoard.getSpace(i, j));
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
	
	public void setSpace(int row, int col, int value){
		if (row < 0 || row > 7)
			throw new RuntimeException("Invalid row identifyer");
		
		
		if (col < 0 || col > 7)
			throw new RuntimeException("Invalid col identifyer");
		
		
		Board[row][col] = value;
	}
	
}
