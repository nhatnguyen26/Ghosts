package ghost;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DictionaryTest {

	@Test
	public void testDictionary1() {
		//fail("Not yet implemented");
		Dictionary dict = new Dictionary();
		LetterNode dictNode = dict.getDict();
		assertTrue("fugged is not in dictionary",dict.containWord("fugged"));
		assertFalse("fugatos is actually in dictionary",dict.containWord("fugatos"));
		dictNode = dictNode.getChild('n');
		System.out.println(dictNode.numWords());
		System.out.println(dictNode);
	}
	
	@Test
	public void testDictionary2() {
		Dictionary dict = new Dictionary("src/TEST",2);
		LetterNode dictNode = dict.getDict();
		System.out.println(dictNode.getChild('b').numWords());
		//assertFalse("awaken is in dictionary",dict.containWord("awaken"));
	}


}
