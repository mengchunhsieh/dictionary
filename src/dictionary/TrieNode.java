package dictionary;

import java.util.HashMap;
import java.util.Map;

/**
 * Trie Node Object
 */
public class TrieNode {

    /**
     * Flag to check if its the end of the node
     */
    private Boolean isEndOfWord ;

    /**
     * All next node (children node)
     */
    private Map<Character, TrieNode> children = new HashMap<>();

    /**
     * Trie Node Constructor
     *
     * @param isEndOfWord is end of word flag
     */
    public TrieNode(Boolean isEndOfWord){
        this.isEndOfWord = isEndOfWord;
    }

    /**
     *  Children Map getter
     *
     * @return
     */
    public Map<Character, TrieNode> getChildren() {
        return this.children;
    }

    /**
     * Is End Of Word flag getter
     *
     * @return
     */
    public Boolean getIsEndOfWord() {
        return this.isEndOfWord;
    }

    /**
     * Is End Of Word flag setter
     *
     * @param isEndOfWord is end of word flag
     */
    public void setEndOfWord(Boolean isEndOfWord) {
        this.isEndOfWord = isEndOfWord;
    }
}
