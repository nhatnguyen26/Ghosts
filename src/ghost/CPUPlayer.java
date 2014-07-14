package ghost;

public class CPUPlayer extends Player {
	
	private Dictionary myDict;
	
	public CPUPlayer(int turn){
		super("CPU",turn);
		myDict = new Dictionary();
	}

	@Override
	public char play() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public char bestMove() {
		char bestChar = ' ';
		
		
		return bestChar;
	}

}
