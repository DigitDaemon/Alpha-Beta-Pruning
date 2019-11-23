
public class AdversarialSearch {

	static public int startSearch(BoardState inBoard, int maxdepth) {
		BoardState CurrentBoard = new BoardState();
		CurrentBoard.setBoard(inBoard);
		int maxScore = -100000;
		int row = 0;
		int col = 0;
		int alpha = -100000;
		int beta = 100000;
		int depth = 0;
		
		for (int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				if (alpha < beta){
					if(CurrentBoard.getSpace(i,j) == 0) {
					try {
						BoardState outboard = new BoardState();
						outboard.setBoard(CurrentBoard);
						
							outboard.setSpace(i, j, 1);
						if (depth + 1 != maxdepth){
							if (CurrentBoard.getSpace(i, j) == 0){
								
								int passback = Min(alpha, beta, depth++, maxdepth, outboard);
							
								if(passback > maxScore){
									maxScore = passback;
									row = i;
									col = j;
								}
								if(passback > alpha)
									alpha = maxScore;
						
							}
						}
						else{
							return (row * 8) + col;
						}
					} catch (WinningMoveException e) {
						maxScore = e.getScore();
						row = i;
						col = j;
					}
					}
										
				}
				else{
						return (row * 8) + col;
					}
				}
			}		
		
		return (row * 8) + col;
		
	}
	
	static private int Max(int alpha, int beta, int depth, int maxdepth, BoardState inBoard){
	
		BoardState CurrentBoard = new BoardState();
		CurrentBoard.setBoard(inBoard);
		int maxScore = -100000;
		int row = 0;
		int col = 0;
		
		for (int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				if (alpha < beta){
					if(CurrentBoard.getSpace(i, j) == 0) {
					try {
						BoardState outboard = new BoardState();
						outboard.setBoard(CurrentBoard);
						outboard.setSpace(i, j, 1);
						if (depth + 1 != maxdepth){
							if (CurrentBoard.getSpace(i, j) == 0){
								
								int passback = Min(alpha, beta, depth++, maxdepth, outboard);
							
								if(passback > maxScore){
									maxScore = passback;
									row = i;
									col = j;
								}
								if(passback > alpha)
									alpha = maxScore;
						
							}
						}
						else{
							return Evaluate(outboard);
						}
					} catch (WinningMoveException e) {
						maxScore = e.getScore();
						row = i;
						col = j;
					}
					}
					
					
				}
				else{
						return maxScore;
					}
				}
			}		
		
		return maxScore;
		}
		
	static private int Min(int alpha, int beta, int depth, int maxdepth, BoardState inBoard){
		BoardState CurrentBoard = new BoardState();
		CurrentBoard.setBoard(inBoard);
		int minScore = 100000;
		int row = 0;
		int col = 0;
		
		for (int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				if (alpha < beta){
					if(CurrentBoard.getSpace(i, j) == 0) {
					try {
						BoardState outboard = new BoardState();
						outboard.setBoard(CurrentBoard);
						outboard.setSpace(i, j, 1);
						if (depth + 1 != maxdepth){
							if (CurrentBoard.getSpace(i, j) == 0){
								
								int passback = Max(alpha, beta, depth++, maxdepth, outboard);
							
								if(passback < minScore){
									minScore = passback;
									row = i;
									col = j;
								}
								if(passback > alpha)
									beta = minScore;
						
							}
						}
						else{
							return Evaluate(outboard);
						}
					} catch (WinningMoveException e) {
						// TODO Auto-generated catch block
						minScore = e.getScore();
					}
					}
					
					
				}
				else{
						return minScore;
					}
				}
			}		
		
		return minScore;		
	}
	
	static private int Evaluate(BoardState inBoard){
		
		return 0;
	}
	
}
