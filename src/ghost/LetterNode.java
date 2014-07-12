package ghost;
import java.util.HashMap;
import java.util.Iterator;

/**
 * LetterNode represent the Node in prefix tree use for storing dictionory for the gamme.
 * @author BrainySmurf
 *
 */
public class LetterNode {
	private char letter;
	//use HashMap to take advantage of checking for member and access child Node
	private HashMap<Character,LetterNode> children;
	private int depth; //Node know it own depth
	private LetterNode parent; //Node know it parent
	
	public LetterNode() {
		this.letter = ' ';
		this.parent = null;
		this.depth = 0;
		this.children = new HashMap<Character, LetterNode>();
	}
	
	public LetterNode(char letter) {
		this.letter = letter;
		this.parent = null;
		this.depth = 0;
		this.children = new HashMap<Character, LetterNode>();
	}
	
	public LetterNode(char letter, int depth) {
		this.letter = letter;
		this.parent = null;
		this.depth = depth;
		this.children = new HashMap<Character, LetterNode>();
	}
	
	public LetterNode(char letter, int depth, LetterNode parent) {
		this.letter = letter;
		this.parent = parent;
		this.depth = depth;
		this.children = new HashMap<Character, LetterNode>();
	}
	
	public Iterator<LetterNode> getChildrenIterator(){
		return this.children.values().iterator();
	}
	
	public int getDepth(){
		return this.depth;
	}
	
	public char getLetter(){
		return this.letter;
	}
	
	/*
	 * Add children Node into the current Node to extend the tree
	 * @param word the word to extend (assume the first letter is already checked)
	 */
	public void extendChildren(String word) {
		if (word.equals(""))
			return;
		char firstLetter = word.charAt(0);
		LetterNode node = this.children.get((Character) firstLetter);
		String subStr = word.substring(1);
		if (node == null) {
			node = new LetterNode(firstLetter, this.depth + 1, this);
			this.children.put(firstLetter, node);
		}
		
		node.extendChildren(subStr);
	}
	
	public LetterNode getChild(char letter) {
		return this.children.get((Character) letter);
	}

}
