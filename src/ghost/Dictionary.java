package ghost;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Dictionary class represent the source dictionary of words using Prefix Tree
 * @author BrainySmurf
 *
 */
public class Dictionary {
	
	private LetterNode root;
	private final int DEFAULT_LENGTH = 4;
	private final String DEFAULT_PATH = "src/WORD.LST";
	
	public Dictionary(){
		try {
			root = buildDict(this.DEFAULT_PATH,this.DEFAULT_LENGTH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Dictionary(String dictName) {
		try {
			root = buildDict(dictName,this.DEFAULT_LENGTH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Dictionary(String dictName, int length) {
		try {
			root = buildDict(dictName,length);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Dictionary(LetterNode root){
		this.root = root;
	}
		
	private LetterNode buildDict(String fileName, int minLength) throws FileNotFoundException {
		FileReader fr = new FileReader(fileName);
		BufferedReader reader = new BufferedReader(fr);
		LetterNode root = new LetterNode();
		String line = null, prevLine = " ";
		int total = 0, added = 0;
		try {
			while ((line = reader.readLine()) != null) {
				total++;
				//if a word is already complete, the longer word start with previous doesn't matter
				if (!line.startsWith(prevLine) && line.length() >= minLength) {
					added++;
					root.addWord(line);
				}
				prevLine = line;
			}
			System.out.println("Total from file: " + total);
			System.out.println("Total added: " + added);
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return root;
	}
	
	public LetterNode getDict(){
		return root;
	}
	
	public boolean containWord(String word){
		int d;
		LetterNode curr = root;
		for (d = 0; d < word.length(); d++) {
			curr = curr.getChild((Character) word.charAt(d));
			if (curr == null)
				break;
		}
		return d == word.length();
	}

}
