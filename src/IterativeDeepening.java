



public class IterativeDeepening {
	static final int startDepth = 4;
	
	public static int IterativeSearch(BoardState inBoard) {
		int move = -1;
		long elapsedTime = 0;
		long startTime = System.currentTimeMillis();
		long lastRun = 0;
		int i = startDepth;
		do {
			move = AdversarialSearch.startSearch(inBoard,i);
			lastRun = System.currentTimeMillis() - startTime -elapsedTime;
			elapsedTime += lastRun;
			System.out.println("Elapsed Time: " + elapsedTime/1000);
			System.out.println("Last Time: " + lastRun/1000);
			i++;
		}while ((elapsedTime + lastRun * 40) < 18000);
		
		return move;
	}

}
