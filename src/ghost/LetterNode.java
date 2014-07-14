package ghost;
import java.util.ArrayList;
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
	private HashMap<Character,ArrayList<Integer>> childDepths;
	private int depth; //Node know it own depth
	private LetterNode parent; //Node know it parent
	
	public LetterNode() {
		this.letter = ' ';
		this.parent = null;
		this.depth = 0;
		this.children = new HashMap<Character, LetterNode>();
		this.childDepths = new HashMap<Character,ArrayList<Integer>>();
	}
	
	public LetterNode(char letter) {
		this();
		this.letter = letter;
	}
	
	public LetterNode(char letter, int depth) {
		this(letter);
		this.depth = depth;
	}
	
	public LetterNode(char letter, int depth, LetterNode parent) {
		this(letter,depth);
		this.parent = parent;
	}
	
	public Iterator<Character> getChildrenIterator(){
		return this.children.keySet().iterator();
	}
	
	public int getDepth(){
		return this.depth;
	}
	
	public char getLetter(){
		return this.letter;
	}
	
	public LetterNode getParent(){
		return this.parent;
	}
	
	public void addWord(String word) {
		extendChildren(word);
	}
	
	public boolean isChildrenEmpty() {
		return this.children.isEmpty();
	}
	
	/*
	 * Add children Node into the current Node to extend the tree
	 * At the same time, update possible depths for all child Node.
	 * @param word the word to extend (assume the first letter is already checked)
	 */
	private void extendChildren(String word) {
		if (word.equals(""))
			return;
		char firstLetter = word.charAt(0);
		LetterNode node = this.children.get((Character) firstLetter);
		String subStr = word.substring(1);
		if (node == null) {
			node = new LetterNode(firstLetter, this.depth + 1, this);
			this.children.put(firstLetter, node);
		}
		
		ArrayList<Integer> possDepths = this.childDepths.get((Character) firstLetter);
		if (possDepths == null) {
			possDepths = new ArrayList<Integer>();
			this.childDepths.put(firstLetter, possDepths);
		}
		possDepths.add(word.length());
		
		node.extendChildren(subStr);

	}
	
	public LetterNode getChild(char letter) {
		return this.children.get((Character) letter);
	}
	
	public boolean hasChild(char letter) {
		return getChild(letter) != null;
	}
	
	public ArrayList<Integer> getChildDepths(char letter) {
		return this.childDepths.get((Character) letter);
	}

}
