package tests;

import dictionary.Dictionary;
import dictionary.Message;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeleteTests {

    Message message = new Message();

    /**
     * Delete valid new word
     */
    @Test
    public void deleteValidTest() {
        Dictionary d = new Dictionary();
        String word = "test";

        d.add(word);

        String actual = d.delete(word);
        String expect = message.wordDelete + word;

        assertEquals(actual, expect);
    }

    /**
     * Delete word that is not exist
     */
    @Test
    public void deleteNonExistingTest() {
        Dictionary d = new Dictionary();
        String word = "test";

        String actual = d.delete(word);
        String expect = message.wordUpdateFailNotFound + word;

        assertEquals(actual, expect);
    }

    /**
     * Delete sub word of other word
     */
    @Test
    public void deleteSubWordTest() {
        Dictionary d = new Dictionary();
        String word = "abcdefg";
        String subWord = "abc";

        d.add(word);
        d.add(subWord);

        d.delete(subWord);

        String actual0 = d.read(word);
        String expect0 = message.wordFound + word;

        String actual1 = d.read(subWord);
        String expect1 = message.wordNotFound + subWord;

        assertEquals(expect0, actual0);
        assertEquals(expect1, actual1);
    }

    /**
     * Delete longer word that has other sub word
     */
    @Test
    public void deleteLongerWordTest() {
        Dictionary d = new Dictionary();
        String word = "abc";
        String longerWord = "abcdefg";

        d.add(word);
        d.add(longerWord);

        d.delete(longerWord);

        String actual0 = d.read(word);
        String expect0 = message.wordFound + word;

        String actual1 = d.read(longerWord);
        String expect1 = message.wordNotFound + longerWord;

        assertEquals(expect0, actual0);
        assertEquals(expect1, actual1);
    }
}
