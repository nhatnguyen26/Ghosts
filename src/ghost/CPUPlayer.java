package ghost;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class CPUPlayer extends Player {
	

	private LetterNode currentPlay;
	
	public CPUPlayer(int turn, Dictionary dict){
		super("CPU",turn);
		this.currentPlay = dict.getDict();
	}
	
	public CPUPlayer(String name, int turn, Dictionary dict) {
		super("CPU "+name,turn);
		this.currentPlay = dict.getDict();
	}

	@Override
	public char play(char lastPlayed) {
		// TODO Auto-generated method stub
		// if the CPU play first
		if (lastPlayed != ' ')
			currentPlay = currentPlay.getChild(lastPlayed);
		return bestMove();
	}
	
	/*
	 * TODO: randomize wining move, when force lose, pick the longest one
	 */
	private char bestMove() {
		ArrayList<Character> candidates = new ArrayList<Character>();
		Random rand = new Random();
		int tmp, longest = 0;
		char longestChar = ' ', testChar, bestChar = ' ';
		Iterator<Character> children = this.currentPlay.getChildrenIterator();
		LetterNode node;
		while (children.hasNext()){
			testChar = children.next();
			node = this.currentPlay.getChild(testChar);
			if (this.isForceWin(node)){
				candidates.add(testChar);
			} else {
				tmp = this.maxDepth(node);
				if (tmp > longest) {
					longest = tmp;
					longestChar = testChar;
				}
			}
		}
		
		bestChar = candidates.size() > 0 ? candidates.get(rand.nextInt(candidates.size())) : longestChar;
		currentPlay = currentPlay.getChild(bestChar);
		return bestChar;
	}
	
	private int maxDepth(LetterNode letter) {
		// TODO Auto-generated method stub
		if (letter.isLeafNode())
			return letter.getDepth();
		Iterator<Character> children = letter.getChildrenIterator();
		char testChar;
		LetterNode node;
		int longest = 0;
		while (children.hasNext()) {
			testChar = children.next();
			node = letter.getChild(testChar);
			int tmp = this.maxDepth(node);
			longest = longest > tmp ? longest : tmp;
		}
		
		return longest;
	}

	/*
	 * isForceWin check if letter option is a force move to win according to turn
	 * a move is forced if all option lead to win, otherwise consider as losing 
	 * 	@param letter option to check against the current game tree.
	 */
	private boolean isForceWin(LetterNode letter) {
		if (letter.isLeafNode())
			return letter.getDepth() % 2 == this.myTurn();
		Iterator<Character> children = letter.getChildrenIterator();
		char testChar;
		LetterNode node;
		boolean forced = true;
		while (children.hasNext()) {
			testChar = children.next();
			node = letter.getChild(testChar);
			//forced = forced || this.isForceWin(node);
			if (!this.isForceWin(node))
				forced = false;
		}
		
		return forced;
	}
	

}
