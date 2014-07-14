package ghost;

import java.util.Scanner;

public class HumanPlayer extends Player {
	
	public HumanPlayer(String name, int turn){
		super(name, turn);
	}

	@Override
	public char play() {
		// TODO Auto-generated method stub
		String inputStr;
		Scanner input = new Scanner(System.in);
		do {
			System.out.print("Next character: ");
		} while((inputStr = input.nextLine()).length() > 1);
		input.close();
		return inputStr.charAt(0);
	}

}
