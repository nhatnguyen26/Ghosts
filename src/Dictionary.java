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
	private final String DEFAULT_PATH = "WORD.LST";
	
	public Dictionary(){
		try {
			root = buildDict(this.DEFAULT_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Dictionary(String dictName) {
		try {
			root = buildDict(dictName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private LetterNode buildDict(String fileName) throws FileNotFoundException {
		FileReader fr = new FileReader(fileName);
		BufferedReader reader = new BufferedReader(fr);
		LetterNode root = new LetterNode();
		String line = null, prevLine = " ";
		try {
			while ((line = reader.readLine()) != null) {
				//if a word is already complete, the longer word start with previous doesn't matter
				if (!line.startsWith(prevLine)) {
					root.extendChildren(line);
				}
				prevLine = line;
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return root;
	}

}
