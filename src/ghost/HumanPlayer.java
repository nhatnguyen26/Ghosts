package ghost;

import java.util.Scanner;

public class HumanPlayer extends Player {
	
	public HumanPlayer(String name, int turn){
		super(name, turn);
	}

	@Override
	public char play(char lastPlayed) {
		// TODO Auto-generated method stub
		String inputStr;
		Scanner input = new Scanner(System.in);
		System.out.print("Next character: ");
		inputStr = input.nextLine();
		while (inputStr.length() > 1) {
			System.out.print("Next character: ");
		}
		return inputStr.charAt(0);
	}

}
