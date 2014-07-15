package ghost;

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
		LetterNode node;
		while (children.hasNext()){
			testChar = children.next();
			node = this.currentPlay.getChild(testChar);
			if (this.isForceWin(node)){
				bestChar = testChar;
			}
		}
		
		currentPlay = currentPlay.getChild(bestChar);
		return bestChar;
	}
	
	/*
	 * isForceWin check if letter option is a force move. At any moment of the game
	 * CPU want to pick the even-depth branch to guarantee a win.
	 * 	@param letter option to check against the current game tree.
	 */
	private boolean isForceWin(LetterNode letter) {
		if (letter.isLeafNode())
			return letter.getDepth() % 2 == this.myTurn();
		Iterator<Character> children = letter.getChildrenIterator();
		char testChar;
		LetterNode node;
		while (children.hasNext()) {
			testChar = children.next();
			node = letter.getChild(testChar);
			if (!this.isForceWin(node)) {
				return false;
			}
			
		}
		
		return true;
	}
	

}
