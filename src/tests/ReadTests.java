package tests;

import dictionary.Dictionary;
import dictionary.Message;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReadTests {

    Message message = new Message();

    /**
     * Read valid new word
     */
    @Test
    public void readValidWordTest() {
        Dictionary d = new Dictionary();
        String word = "test";
        d.add(word);

        String actual = d.read(word);
        String expect = message.wordFound + word;

        assertEquals(actual, expect);
    }

    /**
     * Read invalid new word
     */
    @Test
    public void readInvalidWordTest() {
        Dictionary d = new Dictionary();
        String word = "test";

        String actual = d.read(word);
        String expect = message.wordNotFound + word;

        assertEquals(actual, expect);
    }
}
