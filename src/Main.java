import java.util.Scanner;

import game.Board;
import game.Color;
import game.PlayStatus;
import game.Winner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Board board = new Board();
		
		// create the Scanner
		Scanner sc = new Scanner(System.in);
					 
		int i,j;
		Color turnColor = Color.WHITE;
		board.displayBoard();
		while(board.getNumDisks() <=64 && board.isGame(turnColor))
		{
			
			System.out.println(turnColor + " turn");
			System.out.println("Pick i");
			i = sc.nextInt();
			System.out.println("pick y");
			j = sc.nextInt();
			while( board.play(i, j, turnColor) == PlayStatus.NotPossibole)
			{
				System.out.println("Not possibole");
				System.out.println("Pick i");
				i = sc.nextInt();
				System.out.println("pick y");
				j = sc.nextInt();
			}
				
			if(turnColor == Color.WHITE)
			{
				turnColor = Color.BLACK;
			}
			else
				turnColor = Color.WHITE;
			
			board.displayBoard();
		}
		Winner winner = board.getWinner();
		System.out.println("And the winner is " + winner.getColor() + " with " + winner.getNumDisks() + " disks");
		
	}

}
