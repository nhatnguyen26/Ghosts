package ghost;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DictionaryTest {

	@SuppressWarnings("unused")
	@Test
	public void testDictionary1() {
		//fail("Not yet implemented");
		Dictionary dict = new Dictionary();
		LetterNode dictNode = dict.getDict();
		assertTrue("fugged is not in dictionary",dict.containWord("fugged"));
		assertFalse("fugatos is actually in dictionary",dict.containWord("fugatos"));
	}
	
	@Test
	public void testDictionary2() {
		Dictionary dict = new Dictionary("src/TEST");
		LetterNode dictNode = dict.getDict();
		assertFalse("awaken is in dictionary",dict.containWord("awaken"));
	}


}
