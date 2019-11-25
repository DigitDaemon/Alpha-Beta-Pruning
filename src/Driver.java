import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Driver {
	static Scanner kb;
	
	public static void main(String[] args) {
		BoardState board = new BoardState();
		board.NewBoard();
		ArrayList<String> moveList= new ArrayList<String>();
		kb = new Scanner(System.in);
		System.out.print("Enter f if the computer is going first, or s if it is going second: ");
		String choice = kb.next();
		if(choice.toLowerCase().equals("f")) {
			gameLoopFirst(board, moveList);
		} else {
			gameLoopSecond(board, moveList);
		}
		System.out.println("Press enter to exit.");
		kb.next();
		kb.close();
	}
	
	public static void gameLoopFirst(BoardState board, ArrayList<String> moveList) {
		boolean active = true;
		int turn = 0;
		while(active) {
			printBoard(board, moveList);
			int move;
			try {
				if (turn == 0 ) {
					Random rand = new Random();
					int row;
					int col;
					do {
					row = rand.nextInt(2);
					col = rand.nextInt(2);
					}while(board.getSpace(((row +3) * 8) + col + 3) != 0);
					move = ((row +3) * 8) + col + 3;
				}
				else {
					move = IterativeDeepening.IterativeSearch(board);
				}
				char row = (char)(97 + move/8);
				int col = move%8;
				moveList.add((turn + 1) + ". " + row + Integer.toString(col + 1));
				System.out.println("before setSpace");
				board.setSpace(move/8, move%8, 1);
				System.out.println("after setSpace");
				printBoard(board, moveList);
				System.out.print("Enter a move: ");
				String othermove = kb.next();
				int otherrow = othermove.charAt(0) - 97;
				int othercol = othermove.charAt(1) - 49;
				moveList.set(turn, moveList.get(turn) + " " + othermove);
				board.setSpace(otherrow, othercol, -1);
				printBoard(board, moveList);
				System.out.println(active);
			} catch(WinningMoveException wme) {
				System.out.println("ping");
				moveList.set(turn, moveList.get(turn) + " Winner");
				printBoard(board, moveList);
				active = false;
			}
			turn++;
		}
		
	}
	
	public static void gameLoopSecond(BoardState board, ArrayList<String> moveList) {
		boolean active = true;
		int turn = 0;
		while(active) {
			printBoard(board, moveList);
			try {
				System.out.print("Enter a move: ");
				String othermove = kb.next();
				int otherrow = othermove.charAt(0) - 97;
				int othercol = othermove.charAt(1) - 49;
				moveList.add((turn + 1) + ". " + othermove);
				board.setSpace(otherrow, othercol, -1);
				printBoard(board, moveList);
				int move;
				if (turn == 0 ) {
					Random rand = new Random();
					int row;
					int col;
					do {
					row = rand.nextInt(2);
					col = rand.nextInt(2);
					}while(board.getSpace(((row +3) * 8) + col + 3) != 0);
					move = ((row +3) * 8) + col + 3;
				}
				else {
					move = IterativeDeepening.IterativeSearch(board);
				}
				char row = (char)(97 + move/8);
				int col = move%8;
				moveList.set(turn, moveList.get(turn) + " " + row + Integer.toString(col + 1));
				board.setSpace(move/8, move%8, 1);
				printBoard(board, moveList);
			} catch(WinningMoveException wme) {
				moveList.set(turn, moveList.get(turn) + " Winner");
				printBoard(board, moveList);
				active = false;
			}
			turn++;
		}
		
	}
	
	public static void printBoard(BoardState board, ArrayList<String> moveList) {
		String line1 = "  1 2 3 4 5 6 7 8    Player vs. Opponent";
		String space = "                         ";
		char[] row = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
		System.out.println(line1);
		for(int i = 0; i < 8; i++) {
			System.out.print(row[i] + " " + piece(i,0,board) + " " + piece(i,1,board)+ " " + piece(i,2,board)+ " " + piece(i,3,board)+ " " + piece(i,4,board)+ " " + piece(i,5,board)+ " " + piece(i,6,board)+ " " + piece(i,7,board));
			if(moveList.size() >= i + 1)
				System.out.println("        " + moveList.get(i));
			else
				System.out.println();
		}
		if(moveList.size() > 8) {
			for(int i = 8; i < moveList.size(); i++) {
				System.out.println(space + moveList.get(i));
			}
		}
 	}
	
	public static char piece(int row, int col, BoardState board) {
		if (board.getSpace(row, col) == 0)
			return '-';
		else if(board.getSpace(row, col) == 1)
			return 'X';
		else 
			return 'O';
	}
	
	public static void debug(BoardState inBoard) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				System.out.print(Hueristic.analyzSpace(inBoard, (i * 8) + j ) + " ");
			}
			System.out.println();
		}
	}
	
}
