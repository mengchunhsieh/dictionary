package tests;

import dictionary.Dictionary;
import dictionary.Message;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UpdateTests {

    Message message = new Message();

    /**
     * Update valid new word
     */
    @Test
    public void updateValidTest() {
        Dictionary d = new Dictionary();
        String oldWord = "test";
        String newWord = "tess";

        d.add(oldWord);

        String actual = d.update(oldWord, newWord);
        String expect = message.wordUpdated + newWord;

        assertEquals(actual, expect);
    }

    /**
     * Update word that is not exist
     */
    @Test
    public void updateNonExistingWordTest() {
        Dictionary d = new Dictionary();
        String oldWord = "test";
        String newWord = "tess";

        String actual = d.update(oldWord, newWord);
        String expect = message.wordUpdateFail + oldWord;

        assertEquals(actual, expect);
    }

    /**
     * Update to shorter word
     */
    @Test
    public void updateToShorterWordTest() {
        Dictionary d = new Dictionary();
        String oldWord = "abcdefg";
        String newWord = "abcd";

        d.add(oldWord);

        String actual = d.update(oldWord, newWord);
        String expect = message.wordUpdated + newWord;

        assertEquals(message.wordNotFound + oldWord, d.read(oldWord));
        assertEquals(message.wordFound + newWord, d.read(newWord));
        assertEquals(actual, expect);
    }

    /**
     * Update to shorter word
     */
    @Test
    public void updateToLongerWordTest() {
        Dictionary d = new Dictionary();

        String oldWord = "abcd";
        String newWord = "abcdefg";

        d.add(oldWord);

        String actual = d.update(oldWord, newWord);
        String expect = message.wordUpdated + newWord;

        assertEquals(message.wordNotFound + oldWord, d.read(oldWord));
        assertEquals(message.wordFound + newWord, d.read(newWord));
        assertEquals(actual, expect);
    }
}
