package ghost;

import static org.junit.Assert.*;

import org.junit.Test;

public class DictionaryTest {

	@SuppressWarnings("unused")
	@Test
	public void testDictionary() {
		//fail("Not yet implemented");
		Dictionary dict = new Dictionary();
		LetterNode dictNode = dict.getDict();
		assertTrue("fugged is not in dictionary",dict.containWord("fugged"));
		assertFalse("fugatos is actually in dictionary",dict.containWord("fugatos"));
	}

}
