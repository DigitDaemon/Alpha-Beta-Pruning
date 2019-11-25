
public class AdversarialSearch {

	static public int startSearch(BoardState inBoard, int maxdepth) {
		BoardState CurrentStartBoard = new BoardState();
		CurrentStartBoard.setBoard(inBoard);
		int maxScore = -1000000;
		int row = 0;
		int col = 0;
		int alpha = -1000000;
		int beta = 1000000;
		int depth = 1;
		
		for (int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if (alpha < beta){
					if(CurrentStartBoard.getSpace(i,j) == 0) {
					try {
						BoardState outStartboard = new BoardState();
						outStartboard.NewBoard();
						outStartboard.setBoard(CurrentStartBoard);
							outStartboard.setSpace(i, j, 1);
								int passback = Min(alpha, beta, depth, maxdepth, outStartboard);
								if(passback > maxScore){
									maxScore = passback;
									row = i;
									col = j;
								}
								if(passback > alpha)
									alpha = maxScore;
							
					} catch (WinningMoveException e) {
						maxScore = e.getScore();
						row = i;
						col = j;
						if(e.getScore() > alpha)
							alpha = maxScore;
					}
					}
										
				}
				else{
						//System.out.println("Top level prune");
						//System.out.println("TopLevel: " + maxScore +" depth: " + depth);
						return (row * 8) + col;
					}
				}
			}		
		//System.out.println("TopLevel: " + maxScore +" depth: " + depth);
		return (row * 8) + col;	
	}
	
	static private int Max(int alpha, int beta, int indepth, int maxdepth, BoardState inMaxBoard){
	
		
		int maxScore = -100000;
		int row = 0;
		int col = 0;
		int depth = indepth;
		depth++;
		
		for (int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if (alpha < beta){
					if(inMaxBoard.getSpace(i, j) == 0) {
					try {
						BoardState outboard = new BoardState();
						outboard.setBoard(inMaxBoard);
						outboard.setSpace(i, j, 1);
						if (indepth < maxdepth){
							if (inMaxBoard.getSpace(i, j) == 0){
								
								int passback = Min(alpha, beta, depth, maxdepth, outboard);
							
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
						if(e.getScore() > alpha)
							alpha = maxScore;
					}
					}
					
					
				}
				else{
						//System.out.println("Max Prune");
						//System.out.println("Max: " + maxScore +" depth: " + depth);
						return maxScore;
					}
				}
			}		
		//System.out.println("min: " + maxScore +" depth: " + depth);
		return maxScore;
		}
		
	static private int Min(int alpha, int beta, int indepth, int maxdepth, BoardState inMinBoard){
		int minScore = 100000;
		int row = 0;
		int col = 0;
		int depth = indepth;
		depth++;
		
		for (int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if (alpha < beta){
					if(inMinBoard.getSpace(i, j) == 0) {
					try {
						BoardState outboard = new BoardState();
						outboard.setBoard(inMinBoard);
						outboard.setSpace(i, j, -1);
						if (indepth < maxdepth){
							if (inMinBoard.getSpace(i, j) == 0){
								
								int passback = Max(alpha, beta, depth, maxdepth, outboard);
							
								if(passback < minScore){
									minScore = passback;
									row = i;
									col = j;
								}
								if(passback < beta)
									beta = minScore;
						
							}
						}
						else{
							return Evaluate(outboard);
						}
					} catch (WinningMoveException e) {
						minScore = e.getScore();
						row = i;
						col = j;
						if(e.getScore() < beta)
							beta = minScore;
					}
					}
					
					
				}
				else{
						//System.out.println("Prune min");
						//System.out.println("min: " + minScore +" depth: " + depth);
						return minScore;
					}
				}
			}		
		//System.out.println("min: " + minScore +" depth: " + depth);
		return minScore;		
	}
	
	static private int Evaluate(BoardState inBoard){
		
		return Hueristic.AnalyzeBoard(inBoard);
	}
	
}
