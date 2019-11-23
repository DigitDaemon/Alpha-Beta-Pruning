
public class WinningMoveException extends Exception{

	private int score;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WinningMoveException(String message, int score) {
		super(message);
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}

}
