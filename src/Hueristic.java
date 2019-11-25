
public class Hueristic {
	static int[] baseScore = new int[] {2,3,4,6,6,4,3,2,3,4,6,7,7,6,4,3,4,6,7,8,8,7,6,4,6,7,8,9,9,8,7,6,
		6,7,8,9,9,8,7,6,4,6,7,8,8,7,6,4,3,4,6,7,7,6,4,3,2,3,4,6,6,4,3,2};
	
	static public int AnalyzeBoard(BoardState inBoard) {
		int score = 0;
		for(int i = 0; i<64; i++) {
			score += analyzSpace(inBoard, i);
		}
		
		return score;
	}
	
	static public int analyzSpace(BoardState inBoard, int i) {
		int score = 0;
		if(inBoard.getSpace(i) == 0) {
			int ai = 0;
			int plr = 0;
			if(inBoard.getSpace((i/8) + 1, i%8) != inBoard.getSpace(i)) {
				if(inBoard.getSpace((i/8) + 1, i%8) == 1)
					ai++;
				else
					plr++;
				score += inBoard.getSpace((i/8) + 1, i%8) * baseScore[i];
			}
			if(inBoard.getSpace((i/8) - 1, i%8) != inBoard.getSpace(i)) {
				if(inBoard.getSpace((i/8) - 1, i%8) == 1)
					ai++;
				else
					plr++;
				score += inBoard.getSpace((i/8) - 1, i%8) * baseScore[i];
			}
				
			if(inBoard.getSpace(i/8, (i%8) + 1) != inBoard.getSpace(i)) {
				if(inBoard.getSpace(i/8, (i%8) + 1) == 1)
					ai++;
				else
					plr++;
				score += inBoard.getSpace(i/8, (i%8) + 1) * baseScore[i];
			}
				
			if(inBoard.getSpace(i/8, (i%8) - 1) != inBoard.getSpace(i)) {
				if(inBoard.getSpace(i/8, (i%8) - 1) == 1)
					ai++;
				else
					plr++;
				score += inBoard.getSpace(i/8, (i%8) - 1) * baseScore[i];
			}
			score += baseScore[i] * ai;
			score -= baseScore[i] * plr;
				
		}
		if(inBoard.getSpace(i) != 0) {
			if(inBoard.getSpace((i/8) + 1, i%8) == -1 * inBoard.getSpace(i)){
				score += inBoard.getSpace(i) * baseScore[i];
				if (inBoard.getSpace((i/8) + 2, i%8) == -1 * inBoard.getSpace(i))
					score += inBoard.getSpace(i) * baseScore[i];
			}
			if(inBoard.getSpace((i/8) - 1, i%8) == -1 * inBoard.getSpace(i)){
				score += inBoard.getSpace(i) * baseScore[i];
				if (inBoard.getSpace((i/8) - 2, i%8) == -1 * inBoard.getSpace(i))
					score += inBoard.getSpace(i) * baseScore[i];
			}
			if(inBoard.getSpace(i/8, (i%8) + 1) == -1 * inBoard.getSpace(i)){
				score += inBoard.getSpace(i) * baseScore[i];
				if (inBoard.getSpace(i/8, (i%8) + 2) == -1 * inBoard.getSpace(i))
					score += inBoard.getSpace(i) * baseScore[i];
			}
			if(inBoard.getSpace(i/8, (i%8) - 1) == -1 * inBoard.getSpace(i)) {
				score += inBoard.getSpace(i) * baseScore[i];
				if (inBoard.getSpace(i/8, (i%8) - 2) == -1 * inBoard.getSpace(i))
					score += inBoard.getSpace(i) * baseScore[i];
			}
				
			
			if(inBoard.getSpace((i/8) + 1, i%8) == inBoard.getSpace(i)) {
				score += inBoard.getSpace(i) * 2 * baseScore[i];
				if(inBoard.getSpace((i/8) + 2, i%8) == inBoard.getSpace(i))
					score += score += inBoard.getSpace(i) * 2 * baseScore[i];
				}
			if(inBoard.getSpace((i/8) - 1, i%8) == inBoard.getSpace(i)){
				score += inBoard.getSpace(i) * 2 * baseScore[i];
				if(inBoard.getSpace((i/8) - 2, i%8) == inBoard.getSpace(i))
					score += score += inBoard.getSpace(i) * 2 * baseScore[i];
				}
			if(inBoard.getSpace(i/8, (i%8) + 1) == inBoard.getSpace(i)){
				score += inBoard.getSpace(i) * 2 * baseScore[i];
				if(inBoard.getSpace(i/8, (i%8) + 2) == inBoard.getSpace(i))
					score += score += inBoard.getSpace(i) * 2 * baseScore[i];
				}
			if(inBoard.getSpace(i/8, (i%8) - 1) == inBoard.getSpace(i)){
				score += inBoard.getSpace(i) * 2 * baseScore[i];
				if(inBoard.getSpace(i/8, (i%8) - 2) == inBoard.getSpace(i))
					score += score += inBoard.getSpace(i) * 2 * baseScore[i];
				}
			
		}
		score += inBoard.getSpace(i) * baseScore[i];
		
		return score;
	}

	
}
