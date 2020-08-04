package tests;

import dictionary.Dictionary;
import dictionary.Message;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddTests {

    Message message = new Message();

    /**
     * Adding valid new word
     */
    @Test
    public void addNewWordTest() {
        Dictionary d = new Dictionary();
        String word = "test";
        String actual = d.add(word);
        String expect = message.wordAdded + word;
        assertEquals(actual, expect);
    }

    /**
     * Adding sub word of other existing word
     */
    @Test
    public void addNewSubWordTest() {
        Dictionary d = new Dictionary();
        String word = "abcdefg";
        String subWord = "abc";
        d.add(word);

        String actual = d.add(subWord);
        String expect = message.wordAdded + subWord;

        assertEquals(actual, expect);
    }

    /**
     * Adding longer word of other existing word
     */
    @Test
    public void addLogerWordTest() {
        Dictionary d = new Dictionary();
        String word = "abc";
        String longerWord = "abcdfg";

        String actual0 = d.add(word);
        String expect0 = message.wordAdded + word;

        String actual1 = d.add(longerWord);
        String expect1 = message.wordAdded + longerWord;

        assertEquals(actual0, expect0);
        assertEquals(actual1, expect1);
    }
}
