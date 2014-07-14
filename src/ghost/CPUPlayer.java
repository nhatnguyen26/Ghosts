package ghost;

public class CPUPlayer extends Player {
	
	private static Dictionary myDict;
	private LetterNode currentPlay;
	
	public CPUPlayer(int turn){
		super("CPU",turn);
		CPUPlayer.myDict = new Dictionary();
		this.currentPlay = CPUPlayer.myDict.getDict();
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
