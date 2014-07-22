package ghost;

import java.util.ArrayList;
import java.util.HashMap;
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
	 * bestMove() return the best move base on the current play.
	 * Sample from a list of forced move regardless of length if possible
	 * If no force move is possible there are two step:
	 * First, to prevent from reaching a branch with too short lose, find a list of highest of minimum depth losing
	 * Second, from the list above, filter the character with longest depth
	 */
	private char bestMove() {
		ArrayList<Character> candidates = new ArrayList<Character>();
		HashMap<Integer,ArrayList<Character>> loses = new HashMap<Integer,ArrayList<Character>>();
		ArrayList<Character> array;	
		int maxLose = 0;
		char testChar, bestChar = ' ';
		Iterator<Character> children = this.currentPlay.getChildrenIterator();
		LetterNode node;
		while (children.hasNext()){
			testChar = children.next();
			node = this.currentPlay.getChild(testChar);
			if (this.isForceWin(node)){
				candidates.add(testChar);
			} else {
				int tmp = this.extremeLoseDepth(node, MIN);
				if (tmp >= maxLose){
					maxLose = tmp;	
					array = loses.get(maxLose);
					if (array == null){
						array = new ArrayList<Character>();
						loses.put(maxLose, array);
					}
					array.add(testChar);
				}
			}
		}
		
		array = candidates.size() > 0 ? candidates : getLoseArrayMaxDepth(loses.get(maxLose));
		
		bestChar =  sampleCharFromList(array);
		currentPlay = currentPlay.getChild(bestChar);
		return bestChar;
	}
	
	private char sampleCharFromList(ArrayList<Character> list) {
		Random rand = new Random();
		int i = rand.nextInt(list.size());
		return list.get(i);
	}
	
	/*
	 * From the losing candidates, find the list of maximum length.
	 */
	private ArrayList<Character> getLoseArrayMaxDepth(ArrayList<Character> arrayLose) {
		HashMap<Integer,ArrayList<Character>> loses = new HashMap<Integer,ArrayList<Character>>();
		int maxLose = 0;
		for (Character c : arrayLose) {
			LetterNode node = this.currentPlay.getChild(c);
			int tmp = this.extremeLoseDepth(node, MAX);
			if (tmp >= maxLose){
				maxLose = tmp;	
				arrayLose = loses.get(maxLose);
				if (arrayLose == null){
					arrayLose = new ArrayList<Character>();
					loses.put(maxLose, arrayLose);
				}
				arrayLose.add(c);
			}			
		}
		
		return loses.get(maxLose);
	}

	private final static int MIN = 100;
	private final static int MAX = 0;
	
	private int extremeLoseDepth(LetterNode letter, int extreme) {
		if (letter.isLeafNode())
			return this.isForceWin(letter) ? extreme : letter.getDepth();
		Iterator<Character> children = letter.getChildrenIterator();
		char testChar;
		LetterNode node;
		int longest = extreme;
		while (children.hasNext()) {
			testChar = children.next();
			node = letter.getChild(testChar);
			int tmp = this.extremeLoseDepth(node, extreme);
			switch (extreme) {
				case MIN:
					longest = longest < tmp ? longest : tmp;
					break;
				case MAX:
					longest = longest > tmp ? longest : tmp;
					break;
			}
			
		}	
		return longest;
	}

	/*
	 * isForceWin check if letter option is a force move to win according to turn
	 * a move is forced if all option lead to win, otherwise consider as losing 
	 * 	@param letter option to check against the current game tree.
	 */
	protected boolean isForceWin(LetterNode letter) {
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
