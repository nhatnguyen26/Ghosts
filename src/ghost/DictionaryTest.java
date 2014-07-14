package ghost;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

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
	
	@Test
	public void testDepth() {
		Dictionary dict = new Dictionary("src/TEST",2);
		LetterNode dictNode = dict.getDict();
		ArrayList<Integer> array = dictNode.getChildDepths('a');
		assertTrue("3,4 is not in depth for a", array.containsAll(Arrays.asList(3,4)));
		
		dictNode = dictNode.getChild('a');
		array = dictNode.getChildDepths('c');
		assertEquals("array is not null",null,array);
		
		array = dictNode.getChildDepths('b');
		assertTrue("2,3 is not in depth for b", array.containsAll(Arrays.asList(2,3)));
	}


}
