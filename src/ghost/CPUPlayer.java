package ghost;

import java.util.ArrayList;
import java.util.Iterator;

public class CPUPlayer extends Player {
	

	private LetterNode currentPlay;
	
	public CPUPlayer(int turn, Dictionary dict){
		super("CPU",turn);
		this.currentPlay = dict.getDict();
	}

	@Override
	public char play(char lastPlayed) {
		// TODO Auto-generated method stub
		currentPlay = currentPlay.getChild(lastPlayed);
		return bestMove();
	}
	
	private char bestMove() {
		char testChar, bestChar = ' ';
		Iterator<Character> children = this.currentPlay.getChildrenIterator();
		while (children.hasNext()){
			testChar = children.next();
			if (isForceWin(testChar))
				bestChar = testChar;
		}

		return bestChar;
	}
	
	/*
	 * isForceWin check if letter option is a force move. At any moment of the game
	 * CPU want to pick the even-depth branch to guarantee a win.
	 * 	@param letter option to check against the current game tree.
	 */
	private boolean isForceWin(char letter) {
		boolean forceWin = true;
		ArrayList<Integer> childDepths = this.currentPlay.getChildDepths(letter);
		for(Integer i : childDepths) {
			if (i.intValue() % 2 != 0)
				forceWin = false;
		}
		return forceWin;
	}
	
	

}
